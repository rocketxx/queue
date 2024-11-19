package gnam.queue.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Configura il gruppo API
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api") // Nome del gruppo
                .packagesToScan("gnam.queue.controller") // Pacchetti da includere nella documentazione
                .build();
    }
}
