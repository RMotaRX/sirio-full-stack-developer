package br.com.sirio.esp.resources;

import br.com.sirio.esp.config.infra.security.TokenConfig;
import br.com.sirio.esp.domain.dto.request.AuthenticationRequest;
import br.com.sirio.esp.domain.dto.request.RegisterRequest;
import br.com.sirio.esp.domain.dto.response.AuthenticationResponse;
import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.services.implementation.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager manager;
  private final UserServiceImpl userService;
  private final TokenConfig tokenConfig;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {
     var usrPsw = new UsernamePasswordAuthenticationToken(request.email(), request.password());
     var authentication = this.manager.authenticate(usrPsw);

     var token = tokenConfig.generateToken((User) authentication.getPrincipal());

     return ResponseEntity.ok(new AuthenticationResponse(token));
  }

  @PostMapping("/register/users")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
    boolean isRegistered = userService.registerUser(request);
    if (isRegistered) {
      return ResponseEntity.ok("User registered successfully.");
    } else {
      return ResponseEntity.badRequest().body("Email already in use.");
    }
  }
}
