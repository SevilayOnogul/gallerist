package com.sevilayonogul.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase{

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date birthOfDate;
	
	private DtoAddress address;
	
	private DtoAccount account;
}
