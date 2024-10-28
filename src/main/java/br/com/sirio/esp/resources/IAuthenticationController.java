package br.com.sirio.esp.resources;

import br.com.sirio.esp.domain.dto.request.AuthenticationRequest;
import br.com.sirio.esp.domain.dto.request.RegisterRequest;
import br.com.sirio.esp.domain.dto.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

  /**
   * Authenticates a user and returns a token.
   *
   * @param request the authentication request containing email and password.
   * @return a response entity containing the authentication response.
   */
  @Operation(summary = "User login", description = "Authenticates a user and returns a token.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User authenticated successfully, returns token."),
      @ApiResponse(responseCode = "400", description = "Invalid credentials provided.")
  })
  ResponseEntity<AuthenticationResponse> login(
      @RequestBody AuthenticationRequest request
  );

  /**
   * Registers a new user in the system.
   *
   * @param request the registration request containing user details.
   * @return a response entity containing the registration status.
   */
  @Operation(summary = "User registration", description = "Registers a new user in the system.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User registered successfully."),
      @ApiResponse(responseCode = "400", description = "Email already in use.")
  })
  ResponseEntity<String> register(
      @RequestBody RegisterRequest request
  );
}
