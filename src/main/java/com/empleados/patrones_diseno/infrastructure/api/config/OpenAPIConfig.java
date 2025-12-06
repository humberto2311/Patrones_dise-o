// src/main/java/com/empleados/patrones_diseno/infrastructure/api/config/
package com.empleados.patrones_diseno.infrastructure.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Employee Management API",
        version = "1.0.0",
        description = "API para gestión de empleados usando arquitectura hexagonal",
        contact = @Contact(
            name = "Tu Equipo",
            email = "empleados@empresa.com",
            url = "https://empresa.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Servidor Local"),
        @Server(url = "https://api.empresa.com", description = "Servidor Producción")
    }
)
public class OpenAPIConfig {
}