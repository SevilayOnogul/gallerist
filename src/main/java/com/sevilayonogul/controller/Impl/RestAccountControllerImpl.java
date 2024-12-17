package com.sevilayonogul.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevilayonogul.controller.IRestAccountController;
import com.sevilayonogul.controller.RestBaseController;
import com.sevilayonogul.controller.RootEntity;
import com.sevilayonogul.dto.DtoAccount;
import com.sevilayonogul.dto.DtoAccountIU;
import com.sevilayonogul.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController{
	
    @Autowired
	private IAccountService accountService;
    
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		
		return ok(accountService.saveAccount(dtoAccountIU));
	}

}
