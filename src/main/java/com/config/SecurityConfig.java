package com.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  RestAuthenticationEntryPoint restAuthenticationEntryPoint;

//  @Autowired
//  private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
//                .cors().disable()
        .csrf().disable()
        .addFilterAfter(new MyCorsFilter(), MyCorsFilter.class)
        .authorizeRequests().antMatchers("/locations/**")
        .access("hasAuthority('admin')")
        .and()
        .authorizeRequests().antMatchers("/employees/**")
        .access("hasAuthority('admin')")
        .and()
        .authorizeRequests().antMatchers("/documents/**")
        .authenticated()
        .and()
        .authorizeRequests().antMatchers("/job_details/**")
        .access("hasAuthority('admin')")
        .and()
        .authorizeRequests().antMatchers("/user")
        .authenticated()
        .and()
        .formLogin()
        .permitAll()
        .loginPage("/login")
//                    .usernameParameter("username").passwordParameter("password")
        .successHandler(new SimpleUrlAuthenticationSuccessHandler())
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
        .and().logout();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
