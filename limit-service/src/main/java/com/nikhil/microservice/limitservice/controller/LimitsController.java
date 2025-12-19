package com.nikhil.microservice.limitservice.controller;

import com.nikhil.microservice.limitservice.bean.Limits;
import com.nikhil.microservice.limitservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits getLimits(){
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
