package com.sevilayonogul.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoAddressIU;
import com.sevilayonogul.model.Address;
import com.sevilayonogul.repository.AddressRepository;
import com.sevilayonogul.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;

	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		address.setCreateTime(new Date());

		BeanUtils.copyProperties(dtoAddressIU, address);
		return address;
	}

	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();

		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		BeanUtils.copyProperties(savedAddress, dtoAddress);

		return dtoAddress;
	}

}
