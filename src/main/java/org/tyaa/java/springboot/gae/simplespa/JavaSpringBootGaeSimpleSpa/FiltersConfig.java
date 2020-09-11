package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.filters.AuthFilter;

@Configuration
public class FiltersConfig {
    @Bean
    public FilterRegistrationBean<AuthFilter>
        authFilterRegistration() {
        final FilterRegistrationBean<AuthFilter> registration =
                new FilterRegistrationBean<>();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/api/auth/users/*");
        registration.setOrder(1);
        return registration;
    }
}
