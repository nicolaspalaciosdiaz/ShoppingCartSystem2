package com.example.ShoppingCartSystem2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("store");
        //registry.addViewController("/home").setViewName("home");
        registry.addViewController("/store").setViewName("store");
        registry.addViewController("/cart").setViewName("cart");
        registry.addViewController("/login").setViewName("login");
    }
}
