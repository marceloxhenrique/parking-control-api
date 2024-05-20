package com.api.parkingcontrol.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Parking Spot API", description = "Open API documentation for API Parking Spot"))
public class OpenAPIConfig {
}
