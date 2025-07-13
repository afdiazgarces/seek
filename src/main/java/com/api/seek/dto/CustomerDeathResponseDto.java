package com.api.seek.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "DTO datos del cliente y psoble fallecimiento")
@Builder
@Data
public class CustomerDeathResponseDto  {
	
	@Schema(description = "ID del cliente", example = "1")
	private Long id;

	@Schema(description = "Nombre del cliente", example = "Luis")
	private String firstName;

	@Schema(description = "Apellido del cliente", example = "Padilla")
	private String lastName;

	@Schema(description = "Edad actual del cliente", example = "35")
	private Integer age;

	@Schema(description = "Fecha de nacimiento", example = "1989-04-15")
	private LocalDate dateOfBirth;

	@Schema(description = "Fecha de fallecimiento", example = "1989-04-15")
	private LocalDate dateOfDeath;

}
