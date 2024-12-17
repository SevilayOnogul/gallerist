package com.sevilayonogul.service.Impl;

import java.util.Date;
import java.util.Optional;

import org.antlr.v4.runtime.BaseErrorListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoGallerist;
import com.sevilayonogul.dto.DtoGalleristIU;
import com.sevilayonogul.exception.BaseException;
import com.sevilayonogul.exception.ErrorMessage;
import com.sevilayonogul.exception.MessageType;
import com.sevilayonogul.model.Address;
import com.sevilayonogul.model.Gallerist;
import com.sevilayonogul.repository.AddressRepository;
import com.sevilayonogul.repository.GalleristRepository;
import com.sevilayonogul.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService{

	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		
		Optional <Address> optAddress= addressRepository.findById(dtoGalleristIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristIU.getAddressId().toString()));
		}
		
		Gallerist gallerist=new Gallerist();
		gallerist.setCreateTime(new Date());
	
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		gallerist.setAddress(optAddress.get());
		
		return gallerist;
		
	}
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist=new DtoGallerist();
		DtoAddress dtoAddress=new DtoAddress();
		
		Gallerist savedGallerist= galleristRepository.save(createGallerist(dtoGalleristIU));
		
		BeanUtils.copyProperties(savedGallerist,dtoGallerist);
		BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);
		
		dtoGallerist.setAddress(dtoAddress);
		return dtoGallerist;
		}
	

	


}
