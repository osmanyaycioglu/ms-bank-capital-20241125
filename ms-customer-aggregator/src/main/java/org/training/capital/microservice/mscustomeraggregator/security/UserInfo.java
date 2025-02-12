package org.training.capital.microservice.mscustomeraggregator.security;

import lombok.Data;

@Data
public class UserInfo {
    private String username;
    private String password;
    private String role;
}
