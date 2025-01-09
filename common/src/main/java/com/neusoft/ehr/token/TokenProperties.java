package com.neusoft.ehr.token;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Token配置属性
 *
 * @author 曹健伟
 */
@Data
@ConfigurationProperties(TokenProperties.PREFIX)
@Component
public class TokenProperties {
    /**
     * 配置前缀
     */
    public static final String PREFIX = "application.token";
    /**
     * 过期时间
     */
    private Long expirationTime;
    /**
     * 签署者
     */
    private String issuer;
    /**
     * 生效时间
     */
    private Long notBefore;
    /**
     * ECDSA私钥
     */
    private PrivateKey privateKey;
    /**
     * ECDSA公钥
     */
    private PublicKey publicKey;
    /**
     * 主题
     */
    private String subject;

    /**
     * 设置私钥
     *
     * @param privateKey Base64编码的PKCS8格式的ECDSA私钥
     * @throws NoSuchAlgorithmException 非法方式
     * @throws InvalidKeySpecException  非法格式
     */
    public void setPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.privateKey = KeyFactory.getInstance("EC").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
    }

    /**
     * 设置公钥
     *
     * @param publicKey Base64编码的X509格式的ECDSA公钥
     * @throws NoSuchAlgorithmException 非法方式
     * @throws InvalidKeySpecException  非法格式
     */
    public void setPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.publicKey = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
    }
}
