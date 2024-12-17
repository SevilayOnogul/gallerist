package com.sevilayonogul.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class DtoAddressIU {
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String district;
	
	@NotEmpty
	private String neighborhood;
	
	@NotEmpty
	private String street;
}
