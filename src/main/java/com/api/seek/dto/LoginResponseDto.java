package com.api.seek.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@Schema(description = "DTO para la respuesta del token")
public class LoginResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Token Generado", example = "Lu1214353f$%$is")
    private String token;


  
}