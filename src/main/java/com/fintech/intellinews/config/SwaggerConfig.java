package com.fintech.intellinews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author waynechu
 * Created 2017-10-20 17:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.fintech.intellinews.web";

    private static final String API_VERSION = "1.0.0";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("企业资讯信息平台服务接口")
                .description("企业咨询信息接口")
                .termsOfServiceUrl("http://localhost:8080")
                .contact(new Contact("Wayne Chu", "", "contact@haier.com"))
                .version(API_VERSION)
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

}
