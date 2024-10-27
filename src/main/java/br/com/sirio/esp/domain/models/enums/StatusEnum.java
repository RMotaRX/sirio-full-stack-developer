package br.com.sirio.esp.domain.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
  ACTIVE("Ativo"),
  REMOVED("Removido");

  private final String description;
}
