package com.example.springbootweek1;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConditionalOnProperty(name="deploy.env", havingValue = "production")
public class ProdDB implements DB{

    public String getData(){
        return "prod db";
    }
}
