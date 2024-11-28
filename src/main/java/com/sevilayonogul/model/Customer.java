package com.sevilayonogul.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer extends BaseEntity{

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="tckn")
	private String tckn;
	
	@Column(name="birth_of_date")
	private Date birthOfDate;
	
	@OneToOne
	private Address address;
	
	@OneToOne
	private Account account;
}
