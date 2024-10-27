package br.com.sirio.esp.domain.dto.request;

import br.com.sirio.esp.domain.models.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  private Long id;
  private String name;
  private String email;
  private String password;
  private UserRoleEnum role;
}
