package br.com.ctw.gestaoentrega.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to configure Swagger
 */
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API para Gestão de Entregas")
                        .version("1.0")
                        .description("API utilizada para Gestão de Entregas juntamente com controle de autorização e autenticação com JWT")
                        .contact(new Contact()
                                .name("Enzo Venturi")
                                .email("enzoventuri09@gmail.com")))
                .components(new Components()
                        .addSecuritySchemes("Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Introduza o token JWT no formato: Bearer <seu token aqui>")
                        )
                );
    }

}
