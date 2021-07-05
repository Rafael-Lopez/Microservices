package com.lopez.rafael.currencyconversionservice.controllers;

import com.lopez.rafael.currencyconversionservice.CurrencyExchangeProxy;
import com.lopez.rafael.currencyconversionservice.models.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        // TODO: We are directly calling localhost:8000 and this does not really work when running inside Docker
        // One option you have is to create an environment variable and use an environment variable in here, or the other
        // option is instead of localhost, you can say currency-exchange and use the service registry, which is provided
        // by Docker, internally you'd be able to use that to call the application. So, instead of localhost,
        // currency-exchange. For now, it's not really important.
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion( currencyConversion.getId(), from, to, quantity,
                currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple().multiply(quantity),
                currencyConversion.getEnvironment() );
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion( currencyConversion.getId(), from, to, quantity,
                currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple().multiply(quantity),
                currencyConversion.getEnvironment() );
    }
}
