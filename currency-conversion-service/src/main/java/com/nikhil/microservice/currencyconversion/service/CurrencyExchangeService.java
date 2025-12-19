package com.nikhil.microservice.currencyconversion.service;

import com.nikhil.microservice.currencyconversion.bean.CurrencyConversion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class CurrencyExchangeService {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyExchangeService(CurrencyExchangeProxy currencyExchangeProxy){
            this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @Retry(name = "currencyExchangeRetry",fallbackMethod = "retryFallback")
    @CircuitBreaker(name = "currencyExchangeCB",fallbackMethod = "fallbackCurrencyExchange")
    @RateLimiter(name = "currencyExchangeRL", fallbackMethod = "rateLimitFallback")
    public CurrencyConversion getExchangeValueFromFeign(String fromCur,
                                                        String toCur){
        return currencyExchangeProxy.getExchangeValue(fromCur,toCur);
    }


    public CurrencyConversion fallbackCurrencyExchange(String fromCur, String toCur, Throwable ex){
        return new CurrencyConversion(0L,fromCur,toCur, BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO, "Exchange-Service-Down");
    }

    public CurrencyConversion rateLimitFallback(String fromCur, String toCur, Throwable ex){
        return new CurrencyConversion(0L,fromCur,toCur, BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO, "Rate-Limiting");
    }

    public CurrencyConversion retryFallback(String fromCur, String toCur, Throwable ex){
        return new CurrencyConversion(0L,fromCur,toCur, BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO, "Retry fallback");
    }



}
