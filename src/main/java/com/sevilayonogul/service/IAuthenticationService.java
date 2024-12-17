package com.sevilayonogul.service;

import com.sevilayonogul.dto.AuthRequest;
import com.sevilayonogul.dto.AuthResponse;
import com.sevilayonogul.dto.DtoUser;
import com.sevilayonogul.dto.RefreshTokenRequest;
import com.sevilayonogul.model.RefreshToken;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);

	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input); 
}
