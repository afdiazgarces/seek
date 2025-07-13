package com.api.seek.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Schema(description = "DTO para respuesta de un nuevo cliente")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

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

}
