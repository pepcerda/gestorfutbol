package com.jcerdar.gestorfutbol.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
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
                .addResourceLocations("file:" + logoDir, "file:" + caixaFixaDir)
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic());
    }

}
