package org.training.capital.microservice.mscustomeraggregator.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationProvider authenticationProvider;
    private final JWTService             jwtService;


    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        Authentication authenticationLoc = new UsernamePasswordAuthenticationToken(username,
                                                                                   password);
        Authentication                         authenticateLoc   = authenticationProvider.authenticate(authenticationLoc);
        Collection<? extends GrantedAuthority> authoritiesLoc    = authenticateLoc.getAuthorities();
        GrantedAuthority                       firstAuthorityLoc = null;
        for (GrantedAuthority authorityLoc : authoritiesLoc) {
            firstAuthorityLoc = authorityLoc;
            break;
        }
        return jwtService.createToken(username,
                                      firstAuthorityLoc.getAuthority());
    }

}
