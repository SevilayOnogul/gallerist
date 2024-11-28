package com.sevilayonogul.controller;

import com.sevilayonogul.dto.AuthRequest;
import com.sevilayonogul.dto.AuthResponse;
import com.sevilayonogul.dto.DtoUser;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse>authenticate(AuthRequest input);
}
