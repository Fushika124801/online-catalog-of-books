package com.online.catalog.books.common.security.encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {
  private static final int MIN_LOG = 4;

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(BCryptVersion.$2Y, MIN_LOG);
  }
}
