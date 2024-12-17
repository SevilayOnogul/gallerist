package com.sevilayonogul.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGalleristIU {

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private Long addressId;
}
