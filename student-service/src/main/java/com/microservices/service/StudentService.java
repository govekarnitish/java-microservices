package com.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.entity.Student;
import com.microservices.repository.StudentRepository;
import com.microservices.request.CreateStudentRequest;
import com.microservices.response.StudentResponse;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;
	

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
		
		return new StudentResponse(student);
	}

}
