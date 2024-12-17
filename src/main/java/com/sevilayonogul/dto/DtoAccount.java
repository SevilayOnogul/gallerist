package com.sevilayonogul.dto;

import java.math.BigDecimal;

import com.sevilayonogul.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount {

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
}
