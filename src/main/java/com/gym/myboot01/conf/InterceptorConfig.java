package com.gym.myboot01.conf;

import com.gym.myboot01.interceptor.MyLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyLoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/**").excludePathPatterns("/images/**");
    }

}
