package com.neusoft.ehr.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class TokenUtil {

        //64位字符串密钥
        private String secretKey = "XNGpES2W4Miuldtn8gFIkfyfgKXDv0m7OIPDNE9BdhpUWd0c755zkr0gR4NgJWuB";
        //时间戳，是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数（不考虑闰秒），用于表示一个时间点。
        //过期时间戳  (毫秒)
        private Long expiration = 86400000L;

        public String toToken(HashMap hashMap) {
            return Jwts.builder()
                    //.setSubject(userId)
                    .signWith(getSignKey()) //配的密钥
                    .setIssuedAt(new Date(System.currentTimeMillis())) //生成时间
                    .setExpiration(new Date(System.currentTimeMillis() + expiration)) //过期时间
                    .addClaims(hashMap)
                    .compact();
        }

        public String getSub(String token) {
            try {
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(getSignKey())
                        .build()
                        .parseClaimsJws(token);
                return claimsJws.getBody().getSubject();
            } catch (JwtException e) {
                return null;
            }
        }

        public boolean validateToken(String token) {
            try {
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(getSignKey())
                        .build()
                        .parseClaimsJws(token);

                Date now = new Date(System.currentTimeMillis());
                Date expiration = claimsJws.getBody().getExpiration();
                return expiration.after(now);
            } catch (JwtException e) {
                return false;
            }
        }

        private Key getSignKey() {
            return Keys.hmacShaKeyFor(secretKey.getBytes());
        }
}
