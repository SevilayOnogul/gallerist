package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress>saveAddress(DtoAddressIU dtoAddressIU);
}
