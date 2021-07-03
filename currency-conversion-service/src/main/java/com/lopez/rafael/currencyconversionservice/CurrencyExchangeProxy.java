package com.lopez.rafael.currencyconversionservice;

import com.lopez.rafael.currencyconversionservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// For the name, typically you want to use the application name of the service you want to call
// If you look at the appilcation.properties file for currency-exchange-service, the application name
// defined there is "currency-exchange"
@FeignClient(name = "currency-exchange", url = "http://localhost:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
