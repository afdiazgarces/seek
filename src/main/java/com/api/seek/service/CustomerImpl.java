package com.api.seek.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.seek.dto.CustomerDeathResponseDto;
import com.api.seek.dto.CustomerMetricsResponseDto;
import com.api.seek.dto.CustomerRequestDto;
import com.api.seek.dto.CustomerResponseDto;
import com.api.seek.mapper.CustomerMapper;
import com.api.seek.model.CustomerModel;
import com.api.seek.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomerImpl implements Customer {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${parameter.customer.deaht}")
	private int averageDeath;


	@Override
	public CustomerResponseDto save(CustomerRequestDto req) {

		try {
			log.info("Request recibido: {}", objectMapper.writeValueAsString(req));
		} catch (JsonProcessingException e) {
			log.error("Error al serializar CustomerRequestDto", e);
		}

		CustomerModel saved = customerRepository.save(customerMapper.toEntity(req));
		
		log.info("Service save : {}", saved.getId());

		return customerMapper.toDto(saved);
	}

	@Override
	public List<CustomerDeathResponseDto> getAllCustomers() {
		return customerRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	private CustomerDeathResponseDto mapToResponse(CustomerModel client) {

		log.info("Init de mapToResponse para  getAllCustomers ");
		
		return CustomerDeathResponseDto.builder().id(client.getId()).firstName(client.getFirstName())
				.lastName(client.getLastName()).age(client.getAge()).dateOfBirth(client.getDateOfBirth())
				.dateOfDeath(client.getDateOfBirth().plusYears(averageDeath)).build();

	}
	
	
	@Override
	public CustomerMetricsResponseDto getCustomerMetrics() {
	    Double average = customerRepository.averageAge();    
	    
		log.info("average Result: {}", average);

	    Double deviation = customerRepository.calculateDeviation();

		log.info("deviation Result: {}", deviation);

	    return CustomerMetricsResponseDto.builder()
	            .averageAge(average != null ? average : 0.0)
	            .deviationAge(deviation != null ? deviation : 0.0)
	            .build();
	}


}
