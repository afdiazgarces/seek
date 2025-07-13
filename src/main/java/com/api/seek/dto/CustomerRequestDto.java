package com.api.seek.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "DTO para creación de un nuevo cliente")
public class CustomerRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nombre del cliente", example = "Luis")
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @Schema(description = "Apellido del cliente", example = "Padilla")
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @Schema(description = "Edad actual del cliente", example = "35")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    private Integer age;

    @Schema(description = "Fecha de nacimiento", example = "1989-04-15")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}