package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoCar;
import com.sevilayonogul.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
