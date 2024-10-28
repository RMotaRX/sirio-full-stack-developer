package br.com.sirio.esp.domain.dto.request;

import br.com.sirio.esp.domain.models.enums.UserRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Request payload for user information"
)
public class UserRequest {

  @Schema(
      description = "Unique identifier for the user",
      example = "1"
  )
  private Long id;

  @Schema(
      description = "Full name of the user",
      example = "Fábio Melo Pinto"
  )
  private String name;

  @Schema(
      description = "Email address of the user",
      example = "user@example.com"
  )
  private String email;

  @Schema(
      description = "Password of the user",
      example = "P@ssw0rd"
  )
  private String password;

  @Schema(
      description = "Role assigned to the user",
      example = "ADMIN"
  )
  private UserRoleEnum role;
}
