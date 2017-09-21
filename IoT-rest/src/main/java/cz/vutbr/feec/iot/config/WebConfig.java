package cz.vutbr.feec.iot.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.validation.Validator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cz.vutbr.feec.iot.dto.role.RoleDTO;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.rest.AllowOriginInterceptor;
import cz.vutbr.feec.iot.rest.mixin.RoleDTOMixin;
import cz.vutbr.feec.iot.rest.mixin.UserDTOMixin;
import cz.vutbr.feec.iot.security.WebSecurityConfigHttpBasic;

/**
 * The central Spring context and Spring MVC configuration. The @Configuration annotation declares
 * it as Spring configuration. The @EnableWebMvc enables default MVC config for
 * using @Controller, @RequestMapping and so on, see
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc
 * .html#mvc-config-enable
 *
 */

@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties
@EnableWebMvc
@Import({FacadeConfiguration.class, WebSecurityConfigHttpBasic.class, SwaggerConfig.class})
@ComponentScan(basePackages = {ConfigStrings.SECURITY, ConfigStrings.CONTROLLERS,
    ConfigStrings.REST, ConfigStrings.HANDLER})
public class WebConfig extends WebMvcConfigurerAdapter {


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AllowOriginInterceptor());
  }

  /**
   * Enables default Tomcat servlet that serves static files.
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Provides mapping for assets
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  // REST settings
  @Bean
  @Primary
  public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));

    objectMapper.addMixIn(UserDTO.class, UserDTOMixin.class);
    objectMapper.addMixIn(RoleDTO.class, RoleDTOMixin.class);

    //objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

    jsonConverter.setObjectMapper(objectMapper);
    return jsonConverter;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(customJackson2HttpMessageConverter());
  }

}
