package org.training.capital.microservice.mscustomeraggregator.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyUserDetailService implements UserDetailsService {
    private final List<UserInfo>        userInfos;
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final Map<String, UserInfo> userInfoMap = new ConcurrentHashMap<>();

    public MyUserDetailService(final List<UserInfo> userInfosParam,
                               BCryptPasswordEncoder cryptPasswordEncoderParam) {
        userInfos            = userInfosParam;
        cryptPasswordEncoder = cryptPasswordEncoderParam;
        userInfos.forEach(u -> userInfoMap.put(u.getUsername(),
                                               u));
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserInfo userInfoLoc = userInfoMap.get(username);
        if (userInfoLoc == null) {
            throw new UsernameNotFoundException("Böyle user yok");
        }
        return User.builder()
                   .username(username)
                   .password(cryptPasswordEncoder.encode(userInfoLoc.getPassword()))
                   .authorities(userInfoLoc.getRole())
                   .build();
    }
}
