package com.hideyoshi.backendportfolio.base.config;

import antlr.actions.python.CodeLexer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${com.hideyoshi.frontendPath}")
    private String FRONTEND_PATH;

    @Value("${com.hideyoshi.frontendConnectionType}")
    private String CONNECTION_TYPE;

    private final String HTTP = "http://";

    private final String HTTPS = "https://";

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        String connectionProtocol = CONNECTION_TYPE.equals("secure")
                ? HTTPS
                : HTTP;

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(connectionProtocol + FRONTEND_PATH));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(List.of("x-auth-token"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
