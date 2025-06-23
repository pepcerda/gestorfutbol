package com.jcerdar.gestorfutbol.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.logo.upload-dir}")
    private String logoDir;

    @Value("${app.caixafixa.upload-dir}")
    private String caixaFixaDir;

    @Value("${app.logo.access-url}")
    private String accessUrl; 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessUrl + "**")
                .addResourceLocations("file:" + logoDir);

        registry.addResourceHandler(accessUrl + "**")
                .addResourceLocations("file:" + caixaFixaDir);
    }

}
