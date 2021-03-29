package com.online.catalog.books.common.security;

import com.online.catalog.books.common.security.handler.AuthEntryPointJwt;
import com.online.catalog.books.common.security.handler.CustomAccessDeniedHandler;
import com.online.catalog.books.common.security.jwt.AuthTokenFilter;
import com.online.catalog.books.common.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String COOKIES = "JSESSIONID";
  private static final int MAXIMUM_SESSIONS_COUNT = 1;

  private final CustomUserDetailsService userDetailsService;
  private final PasswordEncoder encoder;
  private final AuthEntryPointJwt unauthorizedHandler;
  private final CustomAccessDeniedHandler accessDeniedHandler;

  @Autowired
  public SecurityConfig(
      CustomUserDetailsService userDetailsService,
      PasswordEncoder encoder,
      AuthEntryPointJwt unauthorizedHandler,
      CustomAccessDeniedHandler accessDeniedHandler) {
    this.userDetailsService = userDetailsService;
    this.encoder = encoder;
    this.unauthorizedHandler = unauthorizedHandler;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
    configuration.setAllowedHeaders(
        Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()

        .antMatchers("/")
        .permitAll()

        .antMatchers("/h2-console/**")
        .permitAll()

        .antMatchers("/auth/signin")
        .permitAll()

        .anyRequest()
        .authenticated()

        .and()
        .httpBasic()
        .authenticationEntryPoint(unauthorizedHandler)

        .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler)

        .and()
        .logout()
        .invalidateHttpSession(true)
        .deleteCookies(COOKIES)
        .permitAll()

        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .sessionFixation()
        .changeSessionId()
        .maximumSessions(MAXIMUM_SESSIONS_COUNT);

    http.headers().frameOptions().disable();
    http.addFilterBefore(
        authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
