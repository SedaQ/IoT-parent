package cz.vutbr.feec.iot.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cz.vutbr.feec.iot.context.PersistenceApplicationContext;

/**
 * @author Pavel Šeda
 *
 */
@Configuration
@EnableTransactionManagement
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = { ConfigStrings.SECURITY, ConfigStrings.SERVICE })
public class ServiceConfiguration {

}

