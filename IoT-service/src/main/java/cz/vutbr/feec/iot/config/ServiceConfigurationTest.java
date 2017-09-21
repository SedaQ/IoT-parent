package cz.vutbr.feec.iot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.vutbr.feec.iot.context.PersistenceApplicationContextTest;

/**
 * @author Pavel Šeda
 *
 */
@Configuration
@Import(PersistenceApplicationContextTest.class)
@ComponentScan(basePackages = {ConfigStrings.SECURITY, ConfigStrings.SERVICE})
public class ServiceConfigurationTest {

}

