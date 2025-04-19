package com.jdev.business_service.config;

import com.jdev.business_service.interceptor.ShardInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ShardInterceptor shardInterceptor;

    public WebConfig(ShardInterceptor shardInterceptor) {
        this.shardInterceptor = shardInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shardInterceptor).addPathPatterns("/business");
    }
}
