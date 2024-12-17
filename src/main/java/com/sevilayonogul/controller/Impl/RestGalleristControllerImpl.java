package com.sevilayonogul.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevilayonogul.controller.IRestCustomerController;
import com.sevilayonogul.controller.IRestGalleristController;
import com.sevilayonogul.controller.RestBaseController;
import com.sevilayonogul.controller.RootEntity;
import com.sevilayonogul.dto.DtoGallerist;
import com.sevilayonogul.dto.DtoGalleristIU;
import com.sevilayonogul.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController{

	@Autowired
	private IGalleristService galleristService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {

		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

}
