package com.RecipeBook.RecipeBookApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allow all the frontend origins you might use
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",    // Vite default
            "http://127.0.0.1:3000",    // Vite alternative
            "http://localhost:5173",    // Vite alternative port
            "http://127.0.0.1:5173"     // Vite alternative
        ));
        
        // Allow all common HTTP methods
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"
        ));
        
        // Allow all headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Expose these headers to the frontend
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", 
            "Location",
            "Content-Disposition"
        ));
        
        // Allow credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);
        
        // Cache preflight response for 1 hour
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        // Apply CORS configuration to all endpoints
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}