package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
}
