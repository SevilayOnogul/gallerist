package com.sevilayonogul.dto;

import java.math.BigDecimal;

import com.sevilayonogul.enums.CarStatusType;
import com.sevilayonogul.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCarIU {

	@NotNull
	private String plaka;

	@NotNull
	private String brand;

	@NotNull
	private String model;

	@NotNull
	private Integer productionYear;

	@NotNull
	private BigDecimal price;

	@NotNull
	private CurrencyType currencyType;

	@NotNull
	private BigDecimal damagePrice;

	@NotNull
	private CarStatusType carStatusType;
}
