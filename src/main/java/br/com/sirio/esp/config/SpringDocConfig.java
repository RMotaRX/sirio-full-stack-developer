package br.com.sirio.esp.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("SIRIO FULLSTACK DEVELOPER TEST")
            .version("v1")
            .description("Person Registration Project - Fullstack Developer Test")
            .license(new License()
                .name("Linkedin: Rafael Mota")
                .url("https://www.linkedin.com/in/motarxrafael/")
            )
        );

  }
}
