package com.sevilayonogul.service;

import com.sevilayonogul.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRates(String startDate,String endDate);
}
