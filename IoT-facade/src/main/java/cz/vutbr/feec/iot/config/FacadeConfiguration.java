package cz.vutbr.feec.iot.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cz.vutbr.feec.iot.validation.EmailValidator;
import cz.vutbr.feec.iot.validation.PasswordMatchesValidator;

/**
 * @author Pavel Šeda
 *
 */
@Configuration
@EnableTransactionManagement
@Import(ServiceConfiguration.class)
@ComponentScan(basePackages = { ConfigStrings.FACADE, ConfigStrings.MAPPING })
public class FacadeConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public EmailValidator usernameValidator() {
		return new EmailValidator();
	}

	@Bean
	public PasswordMatchesValidator passwordMatchesValidator() {
		return new PasswordMatchesValidator();
	}

}