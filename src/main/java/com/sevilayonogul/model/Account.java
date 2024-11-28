package com.sevilayonogul.model;

import java.math.BigDecimal;

import com.sevilayonogul.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account")
public class Account extends BaseEntity{

	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="iban")
	private String iban;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="currency_type")
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;
}
