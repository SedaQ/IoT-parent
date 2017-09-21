package cz.vutbr.feec.iot.security;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * mvn clean install -Dspring.profiles.active=prod
 * 
 * @author Pavel Šeda
 *
 */
@Configuration
@EnableWebSecurity
@Profile("prod")
public class WebSecurityConfigOAuth2 extends WebSecurityConfigurerAdapter {

  @Inject
  private AuthenticationProviderImpl authProvider;
  @Inject
  @Qualifier("myUserDetailsServiceImpl")
  private org.springframework.security.core.userdetails.UserDetailsService myUserDetailsFacade;

  @Value("${spring.inmemory.auth.prod.username}")
  private String username;
  @Value("${spring.inmemory.auth.prod.password}")
  private String password;
  @Value("${spring.inmemory.auth.prod.authorities}")
  private String authorities;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
		auth.authenticationProvider(authProvider);
		auth.userDetailsService(myUserDetailsFacade);

		auth.inMemoryAuthentication().withUser(username).password(password).authorities(authorities);
	// @formatter:on
  }

  /*
   * @Override public void configure(WebSecurity web) throws Exception {
   * web.ignoring().antMatchers("/assets/**", "/v2/api-docs", "/configuration/ui",
   * "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**"); }
   */

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
		http
		    .authorizeRequests()
		        .antMatchers("/").permitAll()
		        .antMatchers(HttpMethod.POST, "/api/rest/**").authenticated()
				.antMatchers(HttpMethod.PUT, "/api/rest/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/rest/**").authenticated()
				.antMatchers(HttpMethod.OPTIONS, "/api/rest/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic()
				.and()
				.rememberMe()
				.rememberMeServices(rememberMeServices())
				.and()
			.exceptionHandling()
			    .and()
			.csrf();
	// @formatter:on
  }

  // @Bean
  // public SessionRegistry sessionRegistry() {
  // return new SessionRegistryImpl();
  // }

  /**
   * This bean configures the {@link TokenBasedRememberMeServices} with
   * {@link CustomJPAUserDetailsService}
   *
   * @return
   */
  @Bean
  public AbstractRememberMeServices rememberMeServices() {
    TokenBasedRememberMeServices rememberMeServices =
        new TokenBasedRememberMeServices("rememberMeForTwoWeeks", myUserDetailsFacade);
    rememberMeServices.setTokenValiditySeconds((int) TimeUnit.SECONDS.convert(1, TimeUnit.DAYS));
    rememberMeServices.setParameter("remember-me");
    // rememberMeServices.setAlwaysRemember(true);
    return rememberMeServices;
  }
}
