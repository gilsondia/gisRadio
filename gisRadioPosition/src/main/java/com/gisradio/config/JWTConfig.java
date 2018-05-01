package com.gisradio.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gisradio.security.OAuthProvider;
import com.gisradio.security.OAuthEntryPoint;
import com.gisradio.security.OAuthFilter;
import com.gisradio.security.TokenSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JWTConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private OAuthProvider oAuthProvider;
    @Autowired
    private OAuthEntryPoint oAuthEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
    	ArrayList<AuthenticationProvider> list = new ArrayList<AuthenticationProvider>();
    	list.add(oAuthProvider);
        return new ProviderManager(list);
    }

    @Bean
    public OAuthFilter authenticationTokenFilter() {
        OAuthFilter filter = new OAuthFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new TokenSuccessHandler());
        return filter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("**/rest/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(oAuthEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }
}