package com.ShivnexEngineering.journalApp.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ShivnexEngineering.journalApp.service.CustomUserDetailsService;
import com.ShivnexEngineering.journalApp.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUserName(jwt);
			
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(jwt, userDetails.getUsername())) {
				
				// represents a logged-in user
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
		}
		
		filterChain.doFilter(request, response);  // Pass request to next step (controller)
		
	}

}
