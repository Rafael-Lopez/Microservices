package com.lopez.rafael.currencyexchangeservice.controllers;

import com.lopez.rafael.currencyexchangeservice.models.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Environment environment;

    @Autowired
    public CurrencyExchangeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        return currencyExchange;
    }
}
