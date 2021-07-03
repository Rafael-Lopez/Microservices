package com.lopez.rafael.currencyconversionservice.controllers;

import com.lopez.rafael.currencyconversionservice.models.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        return new CurrencyConversion(1000L, from, to, quantity, BigDecimal.valueOf(1), BigDecimal.valueOf(2), "" );
    }
}
