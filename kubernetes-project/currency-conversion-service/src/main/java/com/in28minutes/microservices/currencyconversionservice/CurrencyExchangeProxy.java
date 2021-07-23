package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="currency-exchange", url="localhost:8000")
//@FeignClient(name="currency-exchange")

//CHANGE-KUBERNETES
// This approach uses the CURRENCY_EXCHANGE_SERVICE_HOST default environment variable created by Kubernetes when deploying
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
// What happens if the exchange service is down when currency conversion is being deployed? There won't be
// a CURRENCY_EXCHANGE_SERVICE_HOST variable. Therefore, this is not a good approach. It's better to use
// a custom environment variable
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);

}
