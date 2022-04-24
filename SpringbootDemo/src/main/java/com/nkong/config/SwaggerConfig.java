package com.nkong.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("nkong", "", "");

    @Value("${swagger.enable}")
    private boolean flg;

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // RequestHandlerSelectors:配置要扫描接口的方式
                // basePackage:指定要扫描的包，any():扫描全部，none():不扫描
//                .apis(RequestHandlerSelectors.basePackage("com.nkong"))
                // 过滤，扫描某路径下的接口
//                .paths(PathSelectors.ant("/hello/**"))
                .build()
                // 分组
                .groupName("nkong").enable(flg);
    }

    public ApiInfo apiInfo() {
        return new ApiInfo("Swagger 测试",
                "Api Documentation",
                "1.0",
                "www.nkong.com",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
