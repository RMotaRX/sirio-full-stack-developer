package br.com.sirio.esp.resources;

import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.response.PersonaResponse;
import br.com.sirio.esp.services.PersonaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personas")
public class PersonaController implements IPersonaController {

  private final PersonaService personaService;

  @PostMapping
  public ResponseEntity<PersonaResponse> registerPersona(
      @Valid @RequestBody PersonaRequest request) {
    PersonaResponse persona = personaService.registerPersona(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(persona);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonaResponse> fetchPersonaById(
      @PathVariable(name = "id", required = true) Long id) {
    Optional<PersonaResponse> persona = personaService.fetchPersonaById(id);

    return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<PersonaResponse>> fetchAllPersonas() {
    List<PersonaResponse> personas = personaService.fetchAllPersonas();

    return ResponseEntity.ok(personas);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<PersonaResponse> updatePersona(
      @PathVariable(name = "id", required = true) Long id,
      @Valid @RequestBody PersonaRequest request) {
    PersonaResponse persona = personaService.updatePersona(id, request);

    return ResponseEntity.ok(persona);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removePersona(
      @PathVariable(name = "id", required = true) Long id) {
    personaService.deletePersona(id);
  }
}
