package com.example.springbootweek1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

//    @Autowired
    final private  DB db;

    //Construction injection -> mostly used for objects used more,
    // help to set that object final( no change)
    public DBService(DB db){
        this.db= db;
    }

    String getData(){
        return db.getData();
    }
}
