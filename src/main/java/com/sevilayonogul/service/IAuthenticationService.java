package com.sevilayonogul.service;

import com.sevilayonogul.dto.AuthRequest;
import com.sevilayonogul.dto.AuthResponse;
import com.sevilayonogul.dto.DtoUser;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);

	public AuthResponse authenticate(AuthRequest input);
}
