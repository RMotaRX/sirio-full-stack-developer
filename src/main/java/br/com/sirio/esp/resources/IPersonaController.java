package br.com.sirio.esp.resources;

import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.response.PersonaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IPersonaController {

  @Operation(
      summary = "Register a new persona",
      description = "Creates a new persona with the provided details."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Persona created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid request data"),
      @ApiResponse(responseCode = "409", description = "Persona with given CPF already exists"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<PersonaResponse> registerPersona(
      @Parameter(description = "Persona details for registration", required = true)
      @Valid @RequestBody PersonaRequest request
  );

  @Operation(
      summary = "Fetch persona by ID",
      description = "Retrieves a persona's details based on the provided ID."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Persona found successfully"),
      @ApiResponse(responseCode = "404", description = "Persona not found"),
      @ApiResponse(responseCode = "400", description = "Invalid ID format"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<PersonaResponse> fetchPersonaById(
      @Parameter(description = "ID of the persona to fetch", required = true, example = "1")
      @PathVariable(name = "id") Long id
  );

  @Operation(
      summary = "Fetch all personas",
      description = "Retrieves a list of all personas in the system."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Personas retrieved successfully"),
      @ApiResponse(responseCode = "204", description = "No personas found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<List<PersonaResponse>> fetchAllPersonas();

  @Operation(summary = "Update an existing persona", description = "Updates the details of an existing persona.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Persona updated successfully."),
      @ApiResponse(responseCode = "400", description = "Invalid request data."),
      @ApiResponse(responseCode = "404", description = "Persona not found.")
  })
  ResponseEntity<PersonaResponse> updatePersona(
      @PathVariable(name = "id", required = true) Long id,
      @RequestBody @Valid PersonaRequest request
  );

  @Operation(summary = "Delete a persona", description = "Deletes a persona based on the provided ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Persona deleted successfully."),
      @ApiResponse(responseCode = "404", description = "Persona not found.")
  })
  void removePersona(
      @PathVariable(name = "id", required = true) Long id
  );
}
