package com.skyler.cobweb.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        // 单个文件大小限制
        factory.setMaxFileSize("1024MB");

        // 上传数据总大小
        factory.setMaxRequestSize("102400MB");

        return factory.createMultipartConfig();
    }
}
