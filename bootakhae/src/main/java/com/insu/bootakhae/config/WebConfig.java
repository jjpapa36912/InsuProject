package com.insu.bootakhae.config;

import com.insu.bootakhae.web.argumentresolver.LoginMemberArgumentResolver;
import com.insu.bootakhae.web.interceptor.LoginCheckInterceptor;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginMemberArgumentResolver());
  }

//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(new LoginCheckInterceptor())
//        .order(1)
//        .addPathPatterns("/**")
//        .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*.ico",
//            "/error", "/swagger-ui/**", "/api-docs/**");
//  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}