package br.com.ctw.gestaoentrega.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                                .email("enzoventuri09@gmail.com"))
                );
    }

}
