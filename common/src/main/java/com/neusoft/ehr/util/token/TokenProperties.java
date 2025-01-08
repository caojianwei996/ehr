package com.neusoft.ehr.util.token;

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

@Data
@ConfigurationProperties(TokenProperties.PREFIX)
@Component
public class TokenProperties {
    public static final String PREFIX = "application.token";
    private Long expirationTime;
    private String issuer;
    private Long notBefore;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String subject;

    public void setPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.privateKey = KeyFactory.getInstance("EC").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
    }

    public void setPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.publicKey = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
    }
}
