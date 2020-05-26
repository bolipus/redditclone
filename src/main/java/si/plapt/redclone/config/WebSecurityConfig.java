package si.plapt.redclone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.reactive.config.CorsRegistry;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
//@ConditionalOnProperty(value = "app.security.basic.enabled", havingValue = "false")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;

  @Autowired
  public WebSecurityConfig(UserDetailsService userDetailsService){
    this.userDetailsService = userDetailsService;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {

   

    http.authorizeRequests()
      .requestMatchers(EndpointRequest.to("info")).permitAll()
      .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
      .antMatchers("/actuator/").hasRole("ACTUATOR")
      //.antMatchers("/link/submit").hasRole("USER")
      //.antMatchers("/link/**").permitAll()
      .antMatchers("/user").permitAll()
      .antMatchers("/error/**").permitAll()
      .antMatchers("/api/v1/**").permitAll()
      .antMatchers("/").permitAll()
      .antMatchers("/h2-console/**").permitAll()
      .and()
      .httpBasic()
      .and()
      .headers().frameOptions().disable()
      .and()
      .csrf().disable();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService);
  }


}