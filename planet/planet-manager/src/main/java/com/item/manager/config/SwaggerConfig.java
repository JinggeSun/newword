package com.item.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * @author sunjg
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 控制开启或关闭swagger
     */
    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    /**
     * 页面信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("星球管理系统")
                .contact(new Contact("星球","",""))
                .version("1.0")
                .description("API 描述")
                .build();
    }

    /**
     *  创建docket
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.item.manager"))
                .paths(PathSelectors.any())
                .build();
    }

}
