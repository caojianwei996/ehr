package com.neusoft.ehr.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Token工具类
 *
 * @author 曹健伟
 */
@Component
public class TokenUtil {
    /**
     * JSON转换器
     */
    private final ObjectMapper objectMapper;
    /**
     * JWS链
     */
    private final JsonWebSignature jws;
    /**
     * JWT链
     */
    private final JwtClaims jwtClaims;
    /**
     * JWT消费者模板
     */
    private final JwtConsumer consumer;
    /**
     * Token配置
     */
    private final TokenProperties properties;

    /**
     * TokenUtil配置类
     *
     * @param objectMapper JSON转换器
     * @param properties   Token配置
     */
    public TokenUtil(ObjectMapper objectMapper, TokenProperties properties) {
        this.objectMapper = objectMapper;
        AlgorithmConstraints algorithmConstraints = new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512);
        this.jws = new JsonWebSignature();
        this.jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512);
        this.jws.setKey(properties.getPrivateKey());
        this.jws.setAlgorithmConstraints(algorithmConstraints);
        this.jwtClaims = new JwtClaims();
        this.jwtClaims.setIssuer(properties.getIssuer());
        this.jwtClaims.setSubject(properties.getSubject());
        this.consumer = new JwtConsumerBuilder()
                .setEnableRequireIntegrity()
                .setJwsAlgorithmConstraints(algorithmConstraints)
                .setVerificationKey(properties.getPublicKey())
                .setExpectedIssuer(properties.getIssuer())
                .setRequireSubject()
                .setExpectedSubject(properties.getSubject())
                .setRequireJwtId()
                .setRequireExpirationTime()
                .setRequireIssuedAt()
                .setRequireNotBefore()
                .setSkipDefaultAudienceValidation()
                .build();
        this.properties = properties;
    }

    /**
     * 生成JWT
     *
     * @param audience 载荷
     * @return JWT
     */
    public String encode(Object audience) {
        try {
            JwtClaims claims = new JwtClaims();
            BeanUtils.copyProperties(jwtClaims, claims);
            claims.setAudience(objectMapper.writeValueAsString(audience));
            claims.setExpirationTimeMinutesInTheFuture(properties.getExpirationTime());
            claims.setNotBeforeMinutesInThePast(properties.getNotBefore());
            claims.setIssuedAtToNow();
            claims.setGeneratedJwtId();
            jws.setPayload(claims.toJson());
            return jws.getCompactSerialization();
        } catch (Throwable e) {
            throw new ServiceException(ServiceCode.TOKEN_INCORRECT);
        }
    }

    /**
     * 解析JWT
     *
     * @param token  JWT
     * @param tClass 载荷的字节码
     * @param <T>    载荷的类型
     * @return 载荷
     */
    public <T> T decode(String token, Class<T> tClass) {
        try {
            String json = consumer.processToClaims(token).getAudience().get(0);
            return objectMapper.readValue(json, tClass);
        } catch (Throwable e) {
            throw new ServiceException(ServiceCode.TOKEN_INCORRECT);
        }
    }
}
