package com.sevilayonogul.service.Impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.AuthRequest;
import com.sevilayonogul.dto.AuthResponse;
import com.sevilayonogul.dto.DtoUser;
import com.sevilayonogul.exception.BaseException;
import com.sevilayonogul.exception.ErrorMessage;
import com.sevilayonogul.exception.MessageType;
import com.sevilayonogul.jwt.JWTService;
import com.sevilayonogul.model.RefreshToken;
import com.sevilayonogul.model.User;
import com.sevilayonogul.repository.RefreshTokenRepository;
import com.sevilayonogul.repository.UserRepository;
import com.sevilayonogul.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	private User createUser(AuthRequest input) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));

		return user;
	}

	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}

	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser = new DtoUser();

		User savedUser = userRepository.save(createUser(input));

		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					input.getUsername(), input.getPassword());

			authenticationProvider.authenticate(authenticationToken);

			Optional<User> optUser = userRepository.findByUsername(input.getUsername());

			String accessToken = jwtService.generateToken(optUser.get());

			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));

			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
	}

}
