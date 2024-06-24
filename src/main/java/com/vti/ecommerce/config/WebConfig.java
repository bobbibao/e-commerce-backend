package com.vti.ecommerce.config;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {
        // Cho phép SameSite=None để cho phép cookie session hoạt động trên các domain khác nhau.
        return CookieSameSiteSupplier.ofNone().whenHasName("SESSION");
    }
}