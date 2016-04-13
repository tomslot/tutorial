package com.zooplus.forex.service;

import com.zooplus.forex.model.CurrencyEnum;
import com.zooplus.forex.model.CurrencyQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CurrencyConversionService {
    private static final Logger log = LoggerFactory.getLogger(CurrencyConversionService.class);

    public void covert(CurrencyQuery query){

        if (query.getSrcCurrency() == query.getDstCurrency()){
            query.setConvertedAmount(query.getAmount());
            log.info("Source and target currencies are same, trivial conversion");
            return;
        }

        Map<String, Double> rates = CurrencyLayerConnector.getExchangeRatesForDate(query.getDate());
        Double rateUsdToSource = getExchangeRateToUsd(rates, query.getSrcCurrency());
        Double rateUsdToTarget = getExchangeRateToUsd(rates, query.getDstCurrency());
        Double srcToDestRate =  rateUsdToTarget / rateUsdToSource;

        log.info("Calculated {} to {} exchange rate: {}", query.getSrcCurrency(), query.getDstCurrency(), srcToDestRate);

        query.setConvertedAmount(query.getAmount() * srcToDestRate);
    }

    private Double getExchangeRateToUsd(Map<String, Double> rates, CurrencyEnum currency){
        Double result = 1.;

        if (currency != CurrencyEnum.USD){
            String label = "USD" + currency;
            result = rates.get(label);
            log.info("Fetched rate {}={}", label, result);

            if (result == null){
                throw new IllegalStateException("Could not find exchange rate " + label);
            }
        }

        return result;
    }
}