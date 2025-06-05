package com.example.springbootweek1;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton") // prototype, request, websocket, session
    Apple getApple(){
        return new Apple();
    }
}