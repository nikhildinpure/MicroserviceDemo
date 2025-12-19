package com.nikhil.microservice.currencyconversion.service;

import com.nikhil.microservice.currencyconversion.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service",url="http://localhost:8000") //without central registry
@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{fromCur}/to/{toCur}")
    CurrencyConversion getExchangeValue(@PathVariable String fromCur,
                                               @PathVariable String toCur);


}
