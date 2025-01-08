package com.neusoft.ehr.util.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private final ObjectMapper objectMapper;
    private final JsonWebSignature jws;
    private final JwtClaims jwtClaims;
    private final JwtConsumer consumer;
    private final TokenProperties properties;

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

    public String encode(Long userid) {
        JwtClaims claims = new JwtClaims();
        BeanUtils.copyProperties(jwtClaims, claims);
        claims.setAudience(userid.toString());
        claims.setExpirationTimeMinutesInTheFuture(properties.getExpirationTime());
        claims.setNotBeforeMinutesInThePast(properties.getNotBefore());
        claims.setIssuedAtToNow();
        claims.setGeneratedJwtId();
        jws.setPayload(claims.toJson());
        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            throw new ServiceException(ServiceCode.TOKEN_INCORRECT);
        }
    }

    public Long decode(String token) {
        try {
            return Long.parseLong(consumer.processToClaims(token).getAudience().get(0));
        } catch (InvalidJwtException | MalformedClaimException | IndexOutOfBoundsException e) {
            throw new ServiceException(ServiceCode.TOKEN_INCORRECT);
        }
    }
}
