package com.sevilayonogul.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refresh_token")
public class RefreshToken extends BaseEntity{

	@Column(name = "refresh_tokens")
	private String refreshToken;
	
	@Column(name = "expired_date")
	private Date expiredDate;
	
	@ManyToOne
	private User user;
}
