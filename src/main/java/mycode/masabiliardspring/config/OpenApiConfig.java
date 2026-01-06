package mycode.masabiliardspring.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI pisicaOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Masini CRUD Api").
                description("Api pentru gestionarea masinilor")
                .version("v1")
                .contact(new Contact().name("Daniel Radoi").email("daniel.radoi@ulbsibiu.ro"))
                .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation().description("Documentatie API").url("https://swagger.io/tools/open-source/getting-started/"));
    }

    @Bean
    public GroupedOpenApi masinaGroupedOpenApi(){
        return GroupedOpenApi.builder().group("masini").pathsToMatch("/api/masini/**").build();
    }

}
