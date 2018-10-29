package com.template.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {

    private String value;

    private String[] xssAcceptUrls;
}
