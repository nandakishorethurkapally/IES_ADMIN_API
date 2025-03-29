//package com.nandu.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//// it will genarate documentaion for API's
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket apiDoc() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.nandu.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}