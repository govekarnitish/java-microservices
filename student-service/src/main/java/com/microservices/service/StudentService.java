package com.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.entity.Student;
import com.microservices.feignclients.AddressFeignClient;
import com.microservices.repository.StudentRepository;
import com.microservices.request.CreateStudentRequest;
import com.microservices.response.AddressResponse;
import com.microservices.response.StudentResponse;

import reactor.core.publisher.Mono;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;
	
	@Autowired
	AddressFeignClient addressFeignClient;

	public StudentResponse createStudent(CreateStudentRequest request) {
		
		Student student = new Student();
		student.setFirstName(request.getFirstName());
		student.setLastName(request.getLastName());
		student.setAddressId(request.getAddressId());
		student.setEmail(request.getEmail());
		
		student = 	studentRepository.save(student);
		
		return new StudentResponse(student);
	}

	public StudentResponse getById(long id) {
		Student student = studentRepository.findById(id).get();
		
		StudentResponse studentResponse = new StudentResponse(student);
		//studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
		
		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
		return studentResponse;
	}
	
	public AddressResponse getAddressById(long addressId) {
		Mono<AddressResponse> addressResponse = webClient.get().uri("/getById/"+ addressId).retrieve().bodyToMono(AddressResponse.class);
		
		return addressResponse.block();
	}

}
