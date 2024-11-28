package com.sevilayonogul.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004","Kayıt bulunamadı"),
	TOKEN_IS_EXPIRED("1005","Token ın süresi bitmiştir"),
	USERNAME_NOT_FOUND("1006","Username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007","Kullanıcı adı veya şifre hatalı"),
	GENERAL_EXCEPTION("9999","Genel bir hata oluştu");
	
	private String code;
	private String message;
	
	private MessageType(String code,String message) {
		this.code=code;
		this.message=message;
	}
}
