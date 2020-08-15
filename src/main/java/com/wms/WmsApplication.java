package com.wms;

import com.wms.aspect.GlobalInterceptor;
import com.wms.aspect.ResolveParamInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class WmsApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }

    @Bean
    public GlobalInterceptor getGlobalInterceptor(){
        return new GlobalInterceptor();
    }
    @Bean
    public ResolveParamInterceptor getResolveParamInterceptor(){
        return new ResolveParamInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getGlobalInterceptor());
        registry.addInterceptor(getResolveParamInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(getResolveParamInterceptor());
    }
}
