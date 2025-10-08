package com.rv.banco.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String SWAGGER_TITLE = "Gestion de cuentas API";
    private static final String SWAGGER_DESCRIPTION = "Backend API para proyecto de gestión de cuentas.";

    @Bean
    OpenAPI api() {
        return new OpenAPI().info(
                new Info().title(SWAGGER_TITLE)
                        .description(SWAGGER_DESCRIPTION)
        );
    }
}
