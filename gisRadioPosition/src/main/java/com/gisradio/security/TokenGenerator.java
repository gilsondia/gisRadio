package com.gisradio.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import com.gisradio.security.model.UserToken;

@Component
public class TokenGenerator {


    /**
     * *
     * @param userToken
     * @return
     */
	public String generate(UserToken userToken) {


        Claims claims = Jwts.claims().setSubject(userToken.getUserName());
        claims.put("userId", String.valueOf(userToken.getId()));
        claims.put("role", userToken.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "secret-aus2018")
                .compact();
    }
}
