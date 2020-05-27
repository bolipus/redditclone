package si.plapt.redclone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private UserDetailsService userDetailsService;

  private JwtRequestFilter jwtRequestFilter;


  @Autowired
  public WebSecurityConfig(UserDetailsService userDetailsService){
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

 

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

 @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
      .antMatchers("/authenticate").permitAll()
      .antMatchers("/**").permitAll()
      .antMatchers("/api/v1/**").permitAll()
      //.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
     // .requestMatchers(EndpointRequest.to("info")).permitAll()
      //.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
      //.antMatchers("/actuator/").hasRole("ACTUATOR")
      //.antMatchers("/link/submit").hasRole("USER")
      //.antMatchers("/link/**").permitAll()
      //.antMatchers("/error/**").permitAll()
      //.antMatchers("/api/v1/**").permitAll()
      //.antMatchers("/h2-console/**").permitAll()
      .and()
      .headers().frameOptions().disable()
      .and()
      .csrf().disable()
      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      

      //http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }


}