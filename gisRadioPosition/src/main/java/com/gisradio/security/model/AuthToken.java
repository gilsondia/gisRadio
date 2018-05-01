package com.gisradio.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthToken extends UsernamePasswordAuthenticationToken{

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536312226706733284L;
	private String token;

    public AuthToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /*
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }*/
}
