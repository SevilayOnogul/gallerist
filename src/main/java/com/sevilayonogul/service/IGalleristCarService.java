package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoGalleristCar;
import com.sevilayonogul.dto.DtoGalleristCarIU;


public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
