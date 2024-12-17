package com.sevilayonogul.service.Impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoCar;
import com.sevilayonogul.dto.DtoGallerist;
import com.sevilayonogul.dto.DtoGalleristCar;
import com.sevilayonogul.dto.DtoGalleristCarIU;
import com.sevilayonogul.exception.BaseException;
import com.sevilayonogul.exception.ErrorMessage;
import com.sevilayonogul.exception.MessageType;
import com.sevilayonogul.handler.Exception;
import com.sevilayonogul.model.Car;
import com.sevilayonogul.model.Gallerist;
import com.sevilayonogul.model.GalleristCar;
import com.sevilayonogul.repository.CarRepository;
import com.sevilayonogul.repository.GalleristCarRepository;
import com.sevilayonogul.repository.GalleristRepository;
import com.sevilayonogul.service.IGalleristCarService;
@Service
public class GalleristCarServiceImpl implements IGalleristCarService{

	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		Optional<Gallerist>optGallerist= galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if(optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
		}
		
		Optional<Car>optCar=carRepository.findById(dtoGalleristCarIU.getCarId());
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getCarId().toString()));
		}
		
		GalleristCar galleristCar=new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
	
		return galleristCar;
	}
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar=new DtoGalleristCar();
		DtoGallerist dtoGallerist=new DtoGallerist();
		DtoCar dtoCar=new DtoCar();
		
		DtoAddress dtoAddress=new DtoAddress();
		
		GalleristCar savedGalleristCar= galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(),dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedGalleristCar.getCar(),dtoCar);
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setGallerist(dtoGallerist);
		dtoGalleristCar.setCar(dtoCar);
		
		return dtoGalleristCar;
	}

	
	

}
