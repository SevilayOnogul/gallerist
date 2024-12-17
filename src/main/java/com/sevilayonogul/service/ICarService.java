package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoCar;
import com.sevilayonogul.dto.DtoCarIU;

public interface ICarService {
	public DtoCar saveCar(DtoCarIU dtoCarIU);
}
