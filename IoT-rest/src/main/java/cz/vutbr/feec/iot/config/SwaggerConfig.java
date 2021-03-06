package cz.vutbr.feec.iot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger documentation is accessible on the following link: http://localhost:8080/swagger-ui.html
 * (localhost example)
 * 
 * @author Pavel Šeda
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("public-api")
        		.apiInfo(apiInfo())
        		.select()
        		.apis(RequestHandlerSelectors.basePackage(ConfigStrings.REST))
        		//.apis(RequestHandlerSelectors.any())   
        		.paths(PathSelectors.any())
        		.build();
    // @formatter:on
  }

  private ApiInfo apiInfo() {
    // @formatter:off
		return new ApiInfoBuilder()
				.title("IoT backend REST API documentation")
				.description("Developed By Pavel Šeda")
				.termsOfServiceUrl("Lincensed by VUT FEKT")
				.build();
	// @formatter:on
  }
}
