package com.sevilayonogul.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.DtoCar;
import com.sevilayonogul.dto.DtoCarIU;
import com.sevilayonogul.model.Car;
import com.sevilayonogul.repository.CarRepository;
import com.sevilayonogul.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	private CarRepository carRepository;

	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreateTime(new Date());

		BeanUtils.copyProperties(dtoCarIU, car);
		return car;
	}

	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		DtoCar dtoCar = new DtoCar();

		Car savedCar = carRepository.save(createCar(dtoCarIU));

		BeanUtils.copyProperties(savedCar, dtoCar);
		return dtoCar;
	}

}
