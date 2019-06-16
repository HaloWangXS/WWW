package com.halo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebMvc
@EnableSwagger2
@WebAppConfiguration
@ComponentScan(basePackages ="com.halo.json.api")
public class SwaggerConfig{
    /**
     * @Bean用法:
     *  方法级别的注解, 主要用在@Configuration这个类里
     *  产生一个Bean对象, 交给Spring管理
     */

    /**
     * 下述这里配置相当于
     * <beans>
     *     <bean id = "api" class = "springfox.documentation.spring.web.plugins.Docket" />
     * </beans>
     */

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.halo.json.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Halo项目接口文档")
                .description("Halo项目接口测试")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0.0")
                .build();
    }
}