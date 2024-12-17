package com.sevilayonogul.controller;

import com.sevilayonogul.dto.DtoAccount;
import com.sevilayonogul.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
