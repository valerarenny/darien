package com.test.darien.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Value("${info.title}")
    private String title;

    @Value("${info.Description}")
    private String description;

    @Value("${info.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info().title(title).description(description).version(version)
                .license(apiLicence()).contact(apiContact());
    }

    private License apiLicence() {
        return new License().name("Apache License Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0\"");
    }

    private Contact apiContact() {
        return new Contact().name("Renny Valera").email("valerarenny@gmail.com").url(null);
    }

}
