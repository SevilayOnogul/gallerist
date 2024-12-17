package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoSaledCar;
import com.sevilayonogul.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
