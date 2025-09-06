package com.jackson.food_ordering_system.common.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
public class AppPropertiesConfig {

    private String frontendBaseUrl;

    private int tokenExpirationHours;

}
