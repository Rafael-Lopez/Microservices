package com.lopez.rafael.currencyexchangeservice.repositories;

import com.lopez.rafael.currencyexchangeservice.models.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
