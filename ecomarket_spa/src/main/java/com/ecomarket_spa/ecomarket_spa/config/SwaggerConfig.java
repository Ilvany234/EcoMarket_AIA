package com.ecomarket_spa.ecomarket_spa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EcoMarket API")
                        .version("1.0.0")
                        .description("Documentación de la API REST de EcoMarket SPA.")
                        .termsOfService("https://ecomarket.cl/terminos")
                        .contact(new Contact()
                                .name("Soporte EcoMarket")
                                .email("soporte@ecomarket.cl")
                                .url("https://ecomarket.cl"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
