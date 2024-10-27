package br.com.sirio.esp.domain.dto.request;

import br.com.sirio.esp.domain.models.enums.UserRoleEnum;

public record RegisterRequest(
    String email, String password, UserRoleEnum role
) { }
