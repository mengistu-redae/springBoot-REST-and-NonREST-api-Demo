package com.rest.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    // custom web MVC configuration 
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Define the locations of static resources
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/resources/");
    }
}