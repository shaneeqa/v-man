package com.aneeq.venuemanager.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

public class ApiDocConfig {
    public OpenAPI config() {
        return new OpenAPI()
                .info(new Info()
                        .title("Venue Allocation Management API")
                        .description("API for managing venues in the system")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("anzee")
                                .email("ad@cvv.vm")))
                .externalDocs(new ExternalDocumentation()
                        .description("Venue Manager documentation")
                        .url("https://github.com/shaneeqa/v-man.git"));
    }
}
