package br.com.sirio.esp.services;

import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.response.PersonaResponse;
import br.com.sirio.esp.domain.models.enums.StatusEnum;
import java.util.List;
import java.util.Optional;

public interface PersonaService {

  PersonaResponse registerPersona(PersonaRequest request);

  Optional<Object> fetchPersonaById(Long id);

  List<PersonaResponse> fetchAllPersonas();

  PersonaResponse updatePersona(Long id, PersonaRequest request);

  void updateStatusProperty(Long id, StatusEnum request);

  void deletePersona(Long id);
}
