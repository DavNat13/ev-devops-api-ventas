package com.citt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API REST Ventas",
                version = "1.0",
                description = "API REST Demo para gestionar ventas de productos. Lanzamiento CITT Duoc UC Viña del Mar 2025"
        )
)
public class OpenApiConfing {
}
// Esta clase configura la documentación de la API REST utilizando OpenAPI (Swagger) para el proyecto de ventas. Proporciona información básica sobre la API, como el título, la versión y una descripción. Esto permitirá a los desarrolladores y usuarios de la API entender mejor su propósito y cómo utilizarla.