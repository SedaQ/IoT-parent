package cz.vutbr.feec.iot.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cz.vutbr.feec.iot.dto.role.RoleDTO;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.rest.AllowOriginInterceptor;
import cz.vutbr.feec.iot.rest.controller.MainController;
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
// @EnableConfigurationProperties
// @ConfigurationProperties
@EnableWebMvc
@Import({FacadeConfiguration.class, WebSecurityConfigHttpBasic.class, SwaggerConfig.class})
@ComponentScan(basePackages = {ConfigStrings.SECURITY, ConfigStrings.CONTROLLERS,
    ConfigStrings.REST, ConfigStrings.HANDLER})
public class WebConfig extends WebMvcConfigurerAdapter {

  private final static Logger logger = LogManager.getLogger(WebConfig.class);

  public static final String TEXTS = "Texts";

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
    registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
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

    // objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

    jsonConverter.setObjectMapper(objectMapper);
    return jsonConverter;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(customJackson2HttpMessageConverter());
  }

  /**
   * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
   */
  @Bean
  public ViewResolver viewResolver() {
    logger.debug("registering JSP in /WEB-INF/jsp/ as views");
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    // viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setContentType("text/html; charset=UTF-8");
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  /**
   * Provides localized messages.
   */
  @Bean
  public MessageSource messageSource() {
    logger.debug("registering ResourceBundle 'Texts' for messages");
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename(TEXTS);
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  /**
   * Provides JSR-303 Validator.
   */
  @Bean
  public Validator validator() {
    logger.debug("registering JSR-303 validator");
    return new LocalValidatorFactoryBean();
  }


}
