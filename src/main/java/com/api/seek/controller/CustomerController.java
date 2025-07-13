package com.api.seek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.seek.dto.ApiErrorResponse;
import com.api.seek.dto.CustomerDeathResponseDto;
import com.api.seek.dto.CustomerMetricsResponseDto;
import com.api.seek.dto.CustomerRequestDto;
import com.api.seek.dto.CustomerResponseDto;
import com.api.seek.service.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer", description = "Operaciones relacionadas con los customer")
@Slf4j
public class CustomerController {

	@Autowired
	private Customer customerService;

	@Operation(summary = "Crear un nuevo customer Exitoso")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Customer creado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
			@ApiResponse(responseCode = "403", description = "No tiene permiso de acceso", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))) })
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerResponseDto> addCustomer(@Valid @RequestBody CustomerRequestDto req) {

		log.info("Inicia el proceso de post customer");

		CustomerResponseDto customer = customerService.save(req);

		log.info("Finaliza el proceso de post customer con id " + customer.getId());

		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}

	@Operation(summary = "Listar Customers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customers listados Exitoso"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
			@ApiResponse(responseCode = "403", description = "No tiene permiso de acceso", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))) })
	@GetMapping
	public ResponseEntity<List<CustomerDeathResponseDto>> listCustomers() {

		List<CustomerDeathResponseDto> customers = customerService.getAllCustomers();

		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@Operation(summary = "Metricas Customers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Metricas Customers Exitoso"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
			@ApiResponse(responseCode = "403", description = "No tiene permiso de acceso", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))) })
	@GetMapping(value = "/metrics")
	public ResponseEntity<CustomerMetricsResponseDto> metricsCustomer() {

		CustomerMetricsResponseDto metrics = customerService.getCustomerMetrics();

		return new ResponseEntity<>(metrics, HttpStatus.OK);
	}
}
