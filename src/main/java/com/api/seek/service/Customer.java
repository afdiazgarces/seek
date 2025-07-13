package com.api.seek.service;

import java.util.List;

import com.api.seek.dto.CustomerDeathResponseDto;
import com.api.seek.dto.CustomerMetricsResponseDto;
import com.api.seek.dto.CustomerRequestDto;
import com.api.seek.dto.CustomerResponseDto;

public interface Customer {
	
	CustomerResponseDto save(CustomerRequestDto req);
	
	List<CustomerDeathResponseDto> getAllCustomers();
	
	CustomerMetricsResponseDto getCustomerMetrics();

}
