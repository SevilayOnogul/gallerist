package com.sevilayonogul.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevilayonogul.dto.CurrencyRatesResponse;
import com.sevilayonogul.dto.DtoCar;
import com.sevilayonogul.dto.DtoCustomer;
import com.sevilayonogul.dto.DtoGallerist;
import com.sevilayonogul.dto.DtoSaledCar;
import com.sevilayonogul.dto.DtoSaledCarIU;
import com.sevilayonogul.enums.CarStatusType;
import com.sevilayonogul.exception.BaseException;
import com.sevilayonogul.exception.ErrorMessage;
import com.sevilayonogul.exception.MessageType;
import com.sevilayonogul.model.Car;
import com.sevilayonogul.model.Customer;
import com.sevilayonogul.model.SaledCar;
import com.sevilayonogul.repository.CarRepository;
import com.sevilayonogul.repository.CustomerRepository;
import com.sevilayonogul.repository.GalleristRepository;
import com.sevilayonogul.repository.SaledCarRepository;
import com.sevilayonogul.service.ICurrencyRatesService;
import com.sevilayonogul.service.ISaledCarService;
import com.sevilayonogul.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

	@Autowired
	private SaledCarRepository saledCarRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ICurrencyRatesService currencyRatesService;

	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("11-10-2024", "11-10-2024");
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

		return customerUSDAmount;
	}

	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}

	public BigDecimal remaningCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates("11-10-2024", "11-10-2024");
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

		return remaningCustomerUSDAmount.multiply(usd);
	}

	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
				|| customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
			return true;
		}
		return false;
	}

	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date());

		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

		return saledCar;
	}

	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		
		if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(
					new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
		
		if (!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);

		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
		customerRepository.save(customer);

		return toDTO(savedSaledCar);
	}

	public DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();

		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);

		return dtoSaledCar;
	}
}
