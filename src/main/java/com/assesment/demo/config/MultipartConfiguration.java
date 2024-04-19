package com.assesment.demo.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class MultipartConfiguration {


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("1128KB")); // Set the maximum file size (adjust as needed)
        factory.setMaxRequestSize(DataSize.parse("11 28KB")); // Set the maximum request size (adjust as needed)
        return factory.createMultipartConfig();
    }
}
