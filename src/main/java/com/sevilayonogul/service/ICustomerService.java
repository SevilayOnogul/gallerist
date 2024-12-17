package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoCustomer;
import com.sevilayonogul.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
