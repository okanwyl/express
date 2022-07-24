package com.obss.okan.express.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
class WebMvcConfiguration {

    @Bean
    PageableHandlerMethodArgumentResolverCustomizer pageableHandlerMethodArgumentResolverCustomizer() {
        return pageableResolver -> {
            pageableResolver.setSizeParameterName("limit");
            pageableResolver.setPageParameterName("offset");
        };
    }
}