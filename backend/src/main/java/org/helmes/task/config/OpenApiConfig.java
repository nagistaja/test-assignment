package org.helmes.task.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Sectors App",
        version = "1.0",
        description = "Test task for Helmes",
        contact = @Contact(name = "Jaak Vaher", email = "jaak.vaher@gmail.com")))
public class OpenApiConfig {
}
