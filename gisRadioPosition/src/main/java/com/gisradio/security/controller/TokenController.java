package com.gisradio.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gisradio.security.TokenGenerator;
import com.gisradio.security.model.UserToken;

@RestController
@RequestMapping("/token")
public class TokenController {


    private TokenGenerator tokenGenerator;

    public TokenController(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final UserToken userToken) {

        return tokenGenerator.generate(userToken);

    }
}
