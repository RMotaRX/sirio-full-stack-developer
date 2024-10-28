package br.com.sirio.esp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Request payload for user authentication"
)
public record AuthenticationRequest(
    @Schema(
        description = "Email address of the user",
        example = "user@example.com"
    )
    String email,

    @Schema(
        description = "Password of the user",
        example = "P@ssw0rd"
    )
    String password
) { }
