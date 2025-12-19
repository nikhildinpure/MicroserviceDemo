package com.nikhil.microservice.currencyconversion.controller;

import com.nikhil.microservice.currencyconversion.bean.CurrencyConversion;
import com.nikhil.microservice.currencyconversion.service.CurrencyExchangeProxy;
import com.nikhil.microservice.currencyconversion.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{fromCur}/to/{toCur}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String fromCur, @PathVariable String toCur, @PathVariable BigDecimal quantity){

        HashMap<String,String> uriVariables = new HashMap<>();
        uriVariables.put("fromCur",fromCur);
        uriVariables.put("toCur",toCur);

        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
                "http://localhost:8000/currency-exchange/from/{fromCur}/to/{toCur}",
                CurrencyConversion.class,uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        return new CurrencyConversion(currencyConversion.getId(),fromCur,toCur,quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());

    }

    @GetMapping("/currency-conversion-feign/from/{fromCur}/to/{toCur}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String fromCur, @PathVariable String toCur, @PathVariable BigDecimal quantity){

        CurrencyConversion currencyConversion = currencyExchangeService.getExchangeValueFromFeign(fromCur, toCur);

        return new CurrencyConversion(currencyConversion.getId(),fromCur,toCur,quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                "Exchange environment "+currencyConversion.getEnvironment()+" Feign");

    }


}
