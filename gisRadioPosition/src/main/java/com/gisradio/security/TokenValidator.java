package com.gisradio.security;


import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Header;
//import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import com.gisradio.security.model.UserToken;

@Component
public class TokenValidator {


	//it will be dinamic for each user storaged at the DB and redis.
    private String secret = "secret-aus2018";

    public UserToken validate(String token) {

        UserToken userToken = null;
        try {
        	
        	/** use it later to get the user id to check on the redis session controller
        	int i = token.lastIndexOf('.');
        	String withoutSignature = token.substring(0, i+1);
        	Jwt<Header,Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
        	*/
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            userToken = new UserToken();

            userToken.setUserName(body.getSubject());
            userToken.setId(Long.parseLong((String) body.get("userId")));
            userToken.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return userToken;
    }
}
