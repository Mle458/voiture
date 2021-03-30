package com.arkeup.poc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arkeup.poc.config.security.jwt.JwtFilter;
import com.arkeup.poc.services.application.user.UserASImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserASImpl userASImpl;
	
	@Autowired
    private JwtFilter jwtFilter;
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userASImpl);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeRequests()
				.antMatchers("/webjars/**", "/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**")
	            .permitAll()
	            .and().exceptionHandling().and().sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
   
   @Bean
   PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

}