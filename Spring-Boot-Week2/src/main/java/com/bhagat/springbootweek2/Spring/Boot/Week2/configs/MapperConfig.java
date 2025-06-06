package com.bhagat.springbootweek2.Spring.Boot.Week2.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelmapper(){
        return new ModelMapper();
    }
}
