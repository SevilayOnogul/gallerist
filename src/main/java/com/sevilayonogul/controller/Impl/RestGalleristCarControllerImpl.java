package com.sevilayonogul.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevilayonogul.controller.IRestGalleristCarController;
import com.sevilayonogul.controller.RestBaseController;
import com.sevilayonogul.controller.RootEntity;
import com.sevilayonogul.dto.DtoGalleristCar;
import com.sevilayonogul.dto.DtoGalleristCarIU;
import com.sevilayonogul.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController{

	@Autowired
	private IGalleristCarService galleristCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}
