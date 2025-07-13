package com.api.seek.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "DTO para la generacion del token")
public class LoginRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nombre del usuario", example = "admin")
    @NotBlank(message = "El nombre es obligatorio")
    private String userName;

    @Schema(description = "password del cliente", example = "admin123")
    @NotBlank(message = "El password es obligatorio")
    private String password;

  
}