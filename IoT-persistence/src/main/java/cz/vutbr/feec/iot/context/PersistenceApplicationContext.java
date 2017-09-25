package cz.vutbr.feec.iot.context;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Pavel Šeda
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {ConfigStrings.DAO})
@EnableSpringDataWebSupport
@ComponentScan(basePackages = {ConfigStrings.ENTITY})
public class PersistenceApplicationContext {

  /**
   * Enables automatic translation of exceptions to DataAccessExceptions.
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor postProcessor() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory().getObject());
  }

  /**
   * Starts up a container that emulates behavior prescribed in JPA spec for container-managed
   * EntityManager
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean jpaFactoryBean =
        new LocalContainerEntityManagerFactoryBean();

    jpaFactoryBean.setLoadTimeWeaver(instrumentationLoadTimeWeaver());
    jpaFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    // jpaFactoryBean.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
    jpaFactoryBean.setPersistenceXmlLocation("classpath:META-INF/persistence-postgresql.xml");
    jpaFactoryBean.setPersistenceUnitName("persistenceProduction");
    return jpaFactoryBean;
  }

  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }

  @Bean
  public LoadTimeWeaver instrumentationLoadTimeWeaver() {
    return new InstrumentationLoadTimeWeaver();
  }

}
