package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoGalleristCar;
import com.sevilayonogul.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
