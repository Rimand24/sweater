package edu.rimand.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathPart = isWindows() ? "file:/" : "file://";
        uploadPath = uploadPath.endsWith("/") ? uploadPath : uploadPath + "/";

        registry.addResourceHandler("/img/**")
                .addResourceLocations(pathPart + uploadPath);

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    private boolean isWindows(){
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("win"));
    }
}