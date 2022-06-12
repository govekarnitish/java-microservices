package com.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.entity.Address;
import com.microservices.repository.AddressRepository;
import com.microservices.request.CreateAddressRequest;
import com.microservices.response.AddressResponse;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	

	public AddressResponse createAddress(CreateAddressRequest request) {
		
		Address address = new Address();
		
		address.setCity(request.getCity());
		address.setStreet(request.getStreet());
		addressRepository.save(address);
		return new AddressResponse(address);
	}

	public AddressResponse getById(long id) {
		
		Address address = addressRepository.findById(id).get();
		
		return new AddressResponse(address);
	}

}
