package com.sevilayonogul.controller;

import com.sevilayonogul.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesResponseController {

	public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate,String endDate);
	
}
