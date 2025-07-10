package com.project.expenses;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(""https://resilient-parfait-cfd883.netlify.app"")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
