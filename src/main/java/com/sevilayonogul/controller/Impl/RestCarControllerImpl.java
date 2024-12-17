package com.sevilayonogul.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevilayonogul.controller.IRestCarController;
import com.sevilayonogul.controller.RestBaseController;
import com.sevilayonogul.controller.RootEntity;
import com.sevilayonogul.dto.DtoCar;
import com.sevilayonogul.dto.DtoCarIU;
import com.sevilayonogul.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

}
