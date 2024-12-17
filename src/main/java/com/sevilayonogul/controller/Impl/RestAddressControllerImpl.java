package com.sevilayonogul.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevilayonogul.controller.IRestAddressController;
import com.sevilayonogul.controller.RestBaseController;
import com.sevilayonogul.controller.RootEntity;
import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoAddressIU;
import com.sevilayonogul.service.IAddressService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{

	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		
		return ok(addressService.saveAddress(dtoAddressIU));
	}

}
