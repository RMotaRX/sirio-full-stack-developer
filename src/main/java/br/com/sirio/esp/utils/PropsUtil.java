package br.com.sirio.esp.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("sirio-full-stack-developer")
public record PropsUtil(
    Security security
) {
  public record Security(
      Token token
  ) {
    public record Token(
        String secret
    ) { }
  }
}
