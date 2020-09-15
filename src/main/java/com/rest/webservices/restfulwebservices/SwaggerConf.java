package com.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf {

    private final Contact CONTACT = new Contact("Diego Felipe Ramirez", "https://github.com/diegoram1594", "diegoramirez1594@gmail.com");
    private final ApiInfo API_INFO = new ApiInfoBuilder()
                                            .contact(CONTACT)
                                            .description("Restful WebServices Spring")
                                            .build();

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(API_INFO);
    }
}
