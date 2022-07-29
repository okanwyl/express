package com.obss.okan.express.application.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@EnableConfigurationProperties(SecurityConfigurationProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
    implements WebMvcConfigurer {
  private final SecurityConfigurationProperties properties;

  SecurityConfiguration(SecurityConfigurationProperties properties) {
    this.properties = properties;
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers(POST, "/users", "/users/login");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedMethods("GET", "HEAD", "POST", "DELETE", "PUT")
        .allowedOrigins(properties.getAllowedOrigins().toArray(new String[0]))
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}

@ConstructorBinding
@ConfigurationProperties("security")
class SecurityConfigurationProperties {
  private final List<String> allowedOrigins;

  SecurityConfigurationProperties(List<String> allowedOrigins) {
    this.allowedOrigins = allowedOrigins;
  }

  public List<String> getAllowedOrigins() {
    return allowedOrigins;
  }
}
