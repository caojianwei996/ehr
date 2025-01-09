package com.neusoft.ehr.interceptor.auth;
 
 import lombok.Data;
 import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.http.HttpMethod;
 import org.springframework.stereotype.Component;
 
 import java.util.List;
 import java.util.Map;
 
 @Data
 @ConfigurationProperties(AuthenticationProperties.PREFIX)
 @Component
 public class AuthenticationProperties {
     public static final String PREFIX = "application.authentication";
     private Map<HttpMethod, List<String>> permit;
 }