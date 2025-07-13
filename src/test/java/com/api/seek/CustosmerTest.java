package com.api.seek;

import com.api.seek.dto.*;
import com.api.seek.mapper.CustomerMapper;
import com.api.seek.model.CustomerModel;
import com.api.seek.repository.CustomerRepository;
import com.api.seek.service.CustomerImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerImplTest {

    @InjectMocks
    private CustomerImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        Field field = CustomerImpl.class.getDeclaredField("averageDeath");
        field.setAccessible(true);
        field.set(customerService, 80);
    }

    @Test
    void testSave() throws JsonProcessingException {
        CustomerRequestDto req = new CustomerRequestDto();
        CustomerModel model = new CustomerModel();
        model.setId(1L);
        CustomerResponseDto responseDto = new CustomerResponseDto();

        when(customerMapper.toEntity(req)).thenReturn(model);
        when(customerRepository.save(model)).thenReturn(model);
        when(customerMapper.toDto(model)).thenReturn(responseDto);
        when(objectMapper.writeValueAsString(req)).thenReturn("serialized");

        CustomerResponseDto result = customerService.save(req);

        assertEquals(responseDto, result);
        verify(customerRepository).save(model);
        verify(objectMapper).writeValueAsString(req);
    }

    @Test
    void testGetAllCustomers() {
        CustomerModel model = new CustomerModel();
        model.setId(1L);
        model.setFirstName("John");
        model.setLastName("Doe");
        model.setAge(30);
        model.setDateOfBirth(LocalDate.of(1990, 1, 1));

        when(customerRepository.findAll()).thenReturn(Arrays.asList(model));

        List<CustomerDeathResponseDto> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals(2070, result.get(0).getDateOfDeath().getYear());  
    }

    @Test
    void testGetCustomerMetrics() {
        when(customerRepository.averageAge()).thenReturn(35.0);
        when(customerRepository.calculateDeviation()).thenReturn(5.5);

        CustomerMetricsResponseDto result = customerService.getCustomerMetrics();

        assertEquals(35.0, result.getAverageAge());
        assertEquals(5.5, result.getDeviationAge());
    }

    @Test
    void testGetCustomerMetricsWithNulls() {
        when(customerRepository.averageAge()).thenReturn(null);
        when(customerRepository.calculateDeviation()).thenReturn(null);

        CustomerMetricsResponseDto result = customerService.getCustomerMetrics();

        assertEquals(0.0, result.getAverageAge());
        assertEquals(0.0, result.getDeviationAge());
    }
}
