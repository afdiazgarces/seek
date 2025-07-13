package com.api.seek.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "DTO para respuesta con metricas")
@Builder
@Data
public class CustomerMetricsResponseDto {

    @Schema(description = "Promedio de edad de todos los clientes", example = "42.3")
	private Double averageAge;
    
    @Schema(description = "Desviación estándar de las edades", example = "6.5")
	private Double deviationAge;

}
