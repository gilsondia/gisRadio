package com.gisradio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gisradio.security.model.AuthToken;
import com.gisradio.security.model.UserToken;
import com.gisradio.security.model.UserTokenDetails;

import java.util.List;

@Component
public class OAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private TokenValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    /**
     * method to verify the JWT request.
     * */
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        AuthToken jwtAuthenticationToken = (AuthToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        UserToken userToken = validator.validate(token);

        if (userToken == null) {
            throw new RuntimeException("Token is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userToken.getRole());
        return new UserTokenDetails(userToken.getUserName(), userToken.getId(),token, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (AuthToken.class.isAssignableFrom(aClass));
    }
}
