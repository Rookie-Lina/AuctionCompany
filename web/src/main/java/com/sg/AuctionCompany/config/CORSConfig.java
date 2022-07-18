package com.sg.AuctionCompany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @Description  解决跨域请求
 * @auther Rookie_lin
 * @create 2022-07-18 17:45
 */
@Configuration
public class CORSConfig {


    public CorsConfiguration addCorsMappings() {
        CorsConfiguration registry = new CorsConfiguration();
        registry.addAllowedOrigin("*");
        registry.addAllowedHeader("*");
        registry.addAllowedMethod("*");
        return registry;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", addCorsMappings());
        return new CorsFilter(source);
    }
}
