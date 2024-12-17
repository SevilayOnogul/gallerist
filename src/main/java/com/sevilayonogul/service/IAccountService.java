package com.sevilayonogul.service;

import com.sevilayonogul.dto.DtoAccount;
import com.sevilayonogul.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
