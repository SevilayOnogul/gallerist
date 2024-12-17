package com.sevilayonogul.dto;

import java.math.BigDecimal;

import com.sevilayonogul.enums.CarStatusType;
import com.sevilayonogul.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends DtoBase{
	private String plaka;

	private String brand;

	private String model;

	private Integer productionYear;

	private BigDecimal price;

	private CurrencyType currencyType;

	private BigDecimal damagePrice;

	private CarStatusType carStatusType;
}
