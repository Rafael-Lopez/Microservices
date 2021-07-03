package com.lopez.rafael.currencyexchangeservice.controllers;

import com.lopez.rafael.currencyexchangeservice.models.CurrencyExchange;
import com.lopez.rafael.currencyexchangeservice.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Environment environment;
    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeService currencyExchangeService) {
        this.environment = environment;
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeService.findByFromAndTo(from, to);
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " and " + to);
        }

        return currencyExchange;
    }
}
