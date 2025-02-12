package org.training.capital.microservice.mscustomeraggregator.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "user.login.info")
@Data
public class MySecurityConfig {
    private List<UserInfo> userInfos;
}
