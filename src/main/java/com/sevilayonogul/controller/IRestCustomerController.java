package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoCustomer;
import com.sevilayonogul.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
