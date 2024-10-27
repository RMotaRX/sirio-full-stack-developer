package br.com.sirio.esp.services;

import br.com.sirio.esp.domain.dto.request.RegisterRequest;

public interface UserService {

  boolean registerUser(RegisterRequest request);
}
