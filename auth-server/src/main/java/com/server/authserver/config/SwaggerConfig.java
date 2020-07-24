package com.server.authserver.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;

@Slf4j
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    private static final String VERSON = "1.0";

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path:/}")
    private String context;

    @Value("${swagger.base.package:com.server}")
    private String basePackage;

    public SwaggerConfig() {
        log.info("[SERVER-COMMON] init swagger ui...");
    }

    private String buildAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            String address = "http://" + ip + ":" + port + "/" + context + "/doc.html";
            log.info("[SERVER-COMMON] swagger ui init finish, address is {}", address);
            return address;
        } catch (Exception e) {
            log.warn("[SERVER-COMMON] init swagger failed, error is {}", e.getMessage());
        }
        return "http://localhost:" + port + "/" + context + "/doc.html";
    }

    @Bean
    public Docket createRestApi() {
        log.info("[SERVER-COMMON] scan base package is: {}", basePackage);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("授权服务")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微服务统一文档管理平台")
                .description("微服务统一文档管理平台")
                .termsOfServiceUrl(buildAddress())
                .contact("dengsh@96.com")
                .version(VERSON)
                .build();
    }
}