package com.jdev.business_service.config;

import com.jdev.business_service.filter.ShardContextFilter;
//import com.jdev.business_service.interceptor.ShardInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<ShardContextFilter> shardContextFilter(){
        FilterRegistrationBean<ShardContextFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ShardContextFilter());
        registrationBean.addUrlPatterns("/business", "/customers");
        registrationBean.setOrder(1);
        return registrationBean;
    }
//    private final ShardInterceptor shardInterceptor;

//    public WebConfig(ShardInterceptor shardInterceptor) {
//        this.shardInterceptor = shardInterceptor;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(shardInterceptor).addPathPatterns("/business");
//    }
}
