package com.example.studentgrademanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Date;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${apiMajorVersion}")
    private String apiMajorVersion;
    @Value("${apiMinorVersion}")
    private String apiMinorVersion;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.address}")
    private String host;

    @Value("${server.port}")
    private long port;

    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.studentgrademanagement"))
                .paths(PathSelectors.any())
                .build().host(host.concat(":").concat(Long.toString(port)))
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return contextPath;
                    }
                })
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Student Grade Management API")
                .description("<b>Rest Api</b><br /><br />Last Accessed: [" + (new Date(System.currentTimeMillis())) + " ]"
                        + " <script>document.title = \"Student Grade Service\";"
                        + " document.getElementById('header').remove();" + "</script>")
                .version(apiMajorVersion + "." + apiMinorVersion).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
