package org.training.capital.microservice.mscustomeraggregator.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

public class JWTService {
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

    private Key key;

    public JWTService() {
        key = Keys.hmacShaKeyFor("sdkhjfjhsdgkjsdavbcdhksgcvbsdhgvcshdgvcshgdcvhdgsvchsdgcvhsgdvcsdc".getBytes());
    }

    public String createToken(String username,
                              String role) {
        return Jwts.builder()
                   .subject(username)
                   .issuedAt(new Date())
                   .expiration(Date.from(ZonedDateTime.now()
                                                      .plusHours(1)
                                                      .toInstant()))
                   .claim("role",
                          role)
                   .claim("xyz",
                          new Random().nextLong())
                   .signWith(key)
                   .compact();
    }

    public Jws<Claims> validate(String token){
        JwtParser jwtParserLoc = Jwts.parser()
                                 .setSigningKey(key)
                                 .build();
        try {
            return jwtParserLoc.parseSignedClaims(token);
        } catch (Exception eParam) {
            logger.error("[JWTService][validate]-> *Error* : " + eParam.getMessage(),eParam);
            return null;
        }
    }

}
