package br.com.sirio.esp.resources;

import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.response.PersonaResponse;
import br.com.sirio.esp.services.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personas")
public class PersonaController {

  private final PersonaService personaService;

  @Operation(summary = "Register a new persona", description = "Creates a new persona with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Persona created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid request data")
  })

  @PostMapping
  public ResponseEntity<PersonaResponse> registerPersona(
      @Valid @RequestBody PersonaRequest request, HttpServletResponse response) {
    PersonaResponse persona = personaService.registerPersona(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(persona);
  }
}
