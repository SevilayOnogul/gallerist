package com.sevilayonogul.service.Impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.DtoAccount;
import com.sevilayonogul.dto.DtoAddress;
import com.sevilayonogul.dto.DtoCustomer;
import com.sevilayonogul.dto.DtoCustomerIU;
import com.sevilayonogul.exception.BaseException;
import com.sevilayonogul.exception.ErrorMessage;
import com.sevilayonogul.exception.MessageType;
import com.sevilayonogul.model.Account;
import com.sevilayonogul.model.Address;
import com.sevilayonogul.model.Customer;
import com.sevilayonogul.repository.AccountRepository;
import com.sevilayonogul.repository.AddressRepository;
import com.sevilayonogul.repository.CustomerRepository;
import com.sevilayonogul.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
		Optional<Address> optAddress= addressRepository.findById(dtoCustomerIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
		}
		Optional<Account> optAccount= accountRepository.findById(dtoCustomerIU.getAccountId());
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
		}
		
		Customer customer=new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer=new DtoCustomer();
		DtoAddress dtoAddress=new DtoAddress();
		DtoAccount dtoAccount=new DtoAccount();
		
		Customer savedCustomer=customerRepository.save(createCustomer(dtoCustomerIU));
		
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		
		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		return dtoCustomer;
	}

	
}
