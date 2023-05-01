package com.kaivikki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	// Override the default filter chain provided by spring security.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// All requests should be authorized.
		httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// Setup HTTP basic authentication with defaults.
		httpSecurity.httpBasic(Customizer.withDefaults());
		//Disable CSRF ( Cross Site Request Forgery)
		httpSecurity.csrf().disable();
		// return the custom filter chain.
		return httpSecurity.build();
	}
}
