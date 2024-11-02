package com.dine.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dine.jwt")
@Data
public class JwtProperties {

    /**
     * Configuration related to generating JWT for employees in management-side
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * Configuration related to generating JWT tokens for customers in user-side
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
