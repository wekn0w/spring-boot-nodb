package app.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
//@EnableSwagger2 //todo для бута не нужен?
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("app.web.rest"))//allow swagger to find controllers
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo());
    }

    /**
     * @return Base app description
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo("Upskilling app for spring app", "select api from the list below", "1.0", null,
                new springfox.documentation.service.Contact("Author", "https://www.google.com", "negrosila@gmail.com"),
                null, null, Collections.emptyList());
    }
}
