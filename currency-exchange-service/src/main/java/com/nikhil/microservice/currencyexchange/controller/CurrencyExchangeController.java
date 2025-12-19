package com.nikhil.microservice.currencyexchange.controller;

import com.nikhil.microservice.currencyexchange.bean.CurrencyExchange;
import com.nikhil.microservice.currencyexchange.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{fromCur}/to/{toCur}")
    public CurrencyExchange getExchangeValue(@PathVariable String fromCur,
                                             @PathVariable String toCur){
        logger.info("getExchangeValue called with {} to {}",fromCur,toCur);
        //CurrencyExchange currencyExchange =  new CurrencyExchange(100L,fromCur,toCur, BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(fromCur,toCur)
                .orElseGet(() -> new CurrencyExchange(1001L,fromCur,toCur, BigDecimal.valueOf(1)));
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
