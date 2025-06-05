package com.example.springbootweek1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//import org.springframework.stereotype.Component;
//
//@Component
public class Apple {


    void eatApple(){
        System.out.println("i am eating the Apple");
    }

    @PostConstruct void postConstructApple(){
        System.out.println("creating apple bean");
    }
    @PreDestroy
    void preDestroyApple(){
        System.out.println("deleating apple bean");
    }
}
