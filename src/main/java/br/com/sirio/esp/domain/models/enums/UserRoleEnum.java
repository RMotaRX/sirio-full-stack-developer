package br.com.sirio.esp.domain.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

  ADMIN("admin"),
  USER("user");

  private final String role;
}
