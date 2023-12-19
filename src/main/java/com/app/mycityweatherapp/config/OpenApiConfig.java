package com.app.mycityweatherapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("My City Weather API")
                .version("0.1")
                .summary("Provee los detalles del clima en tiempo real por ciudad")
                .description("Microservicio dedicado a consultar detalles del clima, puede consultarse con el nombre de la ciudad," +
                        "y, opcionalmente, se le puede enviar el código de país, si las fuentes consultadas no están disponibles, " +
                        "retorna la data mas reciente"));
    }
}
