package com.dine.config;

import com.dine.interceptor.JwtTokenAdminInterceptor;
import com.dine.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * Configuration class for registering web-layer components
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * Register custom interceptor
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("Starting to register custom interceptor...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }

    /**
     * Generate API documentation with Knife4j
     */
    @Bean
    public Docket docket() {
        log.info("Starting to generate the API Documentation...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Dine Master Project API Documentation")
                .version("2.0")
                .description("API documentation for the Dine Master Project")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                //Specify the package to scan for generating API documentation
                .apis(RequestHandlerSelectors.basePackage("com.dine.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * Set static resource mapping
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Starting to set static resource mapping...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * extend spring mvc message converters
     * @param converters
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("extend message converter...");
        //create message converter object
        MappingJackson2HttpMessageConverter converter =  new MappingJackson2HttpMessageConverter();
        //set object converter for message converter, object converter: java to jason
        converter.setObjectMapper(new JacksonObjectMapper());
        //add message converter to converters container-priority
        converters.add(0, converter);
    }
}

