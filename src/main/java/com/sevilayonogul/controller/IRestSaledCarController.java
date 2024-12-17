package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoSaledCar;
import com.sevilayonogul.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar>buyCar(DtoSaledCarIU dtoSaledCarIU);
}
