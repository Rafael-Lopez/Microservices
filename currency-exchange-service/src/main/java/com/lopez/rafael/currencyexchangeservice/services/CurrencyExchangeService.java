package com.lopez.rafael.currencyexchangeservice.services;

import com.lopez.rafael.currencyexchangeservice.models.CurrencyExchange;
import com.lopez.rafael.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public CurrencyExchange findByFromAndTo(String from, String to) {
        return currencyExchangeRepository.findByFromAndTo(from, to);
    }
}
