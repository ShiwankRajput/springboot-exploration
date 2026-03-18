package com.ShivnexEngineering.journalApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ShivnexEngineering.journalApp.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
			.authorizeHttpRequests(auth ->
						auth.requestMatchers("/journal/**", "/users/**").authenticated()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().permitAll()
					);
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.csrf(csrf -> csrf.disable());
		
		http.addFilterBefore(jwtFilter,
				UsernamePasswordAuthenticationFilter.class
			);   // This ensures: JWT Filter runs before Spring’s default login filter.
		
		return http.build();
		
	}
	
	
	/*
	 
	 	You do not need AuthenticationManagerBuilder at all.
		Spring Boot automatically connects:
		
			CustomUserDetailsService
			PasswordEncoder
			Spring Security AuthenticationManager
		
		So simply remove the configureGlobal() method.
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
		
	 
	*/
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
}
