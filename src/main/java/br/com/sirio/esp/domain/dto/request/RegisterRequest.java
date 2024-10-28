package br.com.sirio.esp.domain.dto.request;

import br.com.sirio.esp.domain.models.enums.UserRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Request payload for user registration"
)
public record RegisterRequest(
    @Schema(
        description = "Email address of the user",
        example = "user@example.com"
    )
    String email,

    @Schema(
        description = "Password of the user",
        example = "P@ssw0rd"
    )
    String password,

    @Schema(
        description = "Role assigned to the user",
        example = "ADMIN"
    )
    UserRoleEnum role
) { }
