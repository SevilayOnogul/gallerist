package com.sevilayonogul.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sevilayonogul.controller.IRestCurrencyRatesResponseController;
import com.sevilayonogul.controller.RestBaseController;
import com.sevilayonogul.controller.RootEntity;
import com.sevilayonogul.dto.CurrencyRatesResponse;
import com.sevilayonogul.service.ICurrencyRatesService;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesResponseController {

	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@GetMapping("/currency-rates")
	@Override
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
	}

}
