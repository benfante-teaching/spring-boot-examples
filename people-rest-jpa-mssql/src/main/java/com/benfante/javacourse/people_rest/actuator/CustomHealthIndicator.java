package com.benfante.javacourse.people_rest.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${app.custom.health.status:true}")
    private Boolean customHealthStatus;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (customHealthStatus) {
            builder.up().withDetail("app", appName);
        } else {
            builder.down().withDetail("app", appName);
        }
    }
    
}