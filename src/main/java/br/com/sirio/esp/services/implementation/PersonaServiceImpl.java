package br.com.sirio.esp.services.implementation;

import br.com.sirio.esp.config.events.RegisterPersonaEvent;
import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.response.PersonaResponse;
import br.com.sirio.esp.domain.models.Persona;
import br.com.sirio.esp.domain.models.enums.StatusEnum;
import br.com.sirio.esp.domain.repositories.PersonaRepository;
import br.com.sirio.esp.exceptions.CpfAssociatedWithPersonaException;
import br.com.sirio.esp.services.PersonaService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

  private final ModelMapper mapper;
  private final ApplicationEventPublisher publisher;
  private final PersonaRepository personaRepository;

  @Override
  public PersonaResponse registerPersona(PersonaRequest request) {
    Persona persona = mapper.map(request, Persona.class);

    personaRepository.findByCpfAndIdNot(persona.getCpf(), persona.getId())
        .ifPresent(p -> { throw new CpfAssociatedWithPersonaException();
    });

    publisher.publishEvent(new RegisterPersonaEvent(this, persona));

    Persona registerPersona = personaRepository.save(persona);

    return mapper.map(registerPersona, PersonaResponse.class);
  }

  @Override
  public Optional<Object> fetchPersonaById(Long id) {
    return Optional.empty();
  }

  @Override
  public List<PersonaResponse> fetchAllPersonas() {
    return null;
  }

  @Override
  public PersonaResponse updatePersona(Long id, PersonaRequest request) {
    return null;
  }

  @Override
  public void updateStatusProperty(Long id, StatusEnum request) {

  }

  @Override
  public void deletePersona(Long id) {

  }
}
