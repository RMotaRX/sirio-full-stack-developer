package br.com.sirio.esp.domain.dto.request;

public record AuthenticationRequest(
    String email, String password
) { }
