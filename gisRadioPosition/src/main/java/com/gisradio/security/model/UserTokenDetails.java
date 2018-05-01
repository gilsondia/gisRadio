package com.gisradio.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class UserTokenDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -243318591785344341L;
	private String userName;
    private String token;
    private Long id;
    private Collection<? extends GrantedAuthority> authorities;


    public UserTokenDetails(String userName, long id, String token, List<GrantedAuthority> grantedAuthorities) {

        this.userName = userName;
        this.id = id;
        this.token= token;
        this.authorities = grantedAuthorities;
    }

    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    
    public String getPassword() {
        return null;
    }

    
    public String getUsername() {
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }


    public Long getId() {
        return id;
    }
    //methods below is part of the implements.
    
    public boolean isAccountNonExpired() {
        return true;
    }

    
    public boolean isAccountNonLocked() {
        return true;
    }

    
    public boolean isCredentialsNonExpired() {
        return true;
    }

    
    public boolean isEnabled() {
        return true;
    }

}
