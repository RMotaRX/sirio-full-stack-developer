package br.com.sirio.esp.services.implementation;

import br.com.sirio.esp.config.events.RegisterPersonaEvent;
import br.com.sirio.esp.config.events.UpdatePersonaEvent;
import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.response.PersonaResponse;
import br.com.sirio.esp.domain.models.Persona;
import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.domain.models.enums.StatusEnum;
import br.com.sirio.esp.domain.repositories.PersonaRepository;
import br.com.sirio.esp.domain.repositories.UserRepository;
import br.com.sirio.esp.exceptions.CpfAssociatedWithPersonaException;
import br.com.sirio.esp.exceptions.UnauthorizedException;
import br.com.sirio.esp.services.PersonaService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonaServiceImpl.class);

  private final ModelMapper mapper;
  private final ApplicationEventPublisher publisher;
  private final UserRepository userRepository;
  private final PersonaRepository personaRepository;

  @Override
  @Transactional
  public PersonaResponse registerPersona(PersonaRequest request) {
    LOGGER.info("Registering persona with CPF: {}", request.getCpf());
    Persona persona = mapper.map(request, Persona.class);

    var email = getCurrentUserEmail();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UnauthorizedException("User not found."));

    personaRepository.findByCpfAndIdNot(persona.getCpf(), persona.getId())
        .ifPresent(p -> {
          LOGGER.error("CPF {} is already associated with another persona.", persona.getCpf());
          throw new CpfAssociatedWithPersonaException();
    });

    persona.setUserId(user);

    publisher.publishEvent(new RegisterPersonaEvent(this, persona));

    Persona registerPersona = personaRepository.save(persona);
    LOGGER.info("Successfully registered persona with ID: {}", registerPersona.getId());

    return mapper.map(registerPersona, PersonaResponse.class);
  }

  @Override
  public Optional<PersonaResponse> fetchPersonaById(Long id) {
    Optional<Persona> persona = personaRepository.findById(id);

    return persona.map(p -> mapper.map(p, PersonaResponse.class));
  }

  @Override
  public List<PersonaResponse> fetchAllPersonas() {
    List<Persona> personas = personaRepository.findAll();

    return personas.stream()
                   .map(persona -> mapper.map(persona, PersonaResponse.class))
                   .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public PersonaResponse updatePersona(Long id, PersonaRequest request) {
    Persona existingPersona = personaRepository.findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException(1));

    var email = getCurrentUserEmail();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UnauthorizedException("User not found."));

    personaRepository.findByCpfAndIdNot(request.getCellphone(), id)
        .ifPresent(p -> { throw new CpfAssociatedWithPersonaException(); }
        );

    BeanUtils.copyProperties(request, existingPersona);
    existingPersona.setId(id);
    existingPersona.setUserId(user);

    publisher.publishEvent(new UpdatePersonaEvent(this, existingPersona));

    return mapper.map(personaRepository.save(existingPersona), PersonaResponse.class);
  }

  @Override
  @Transactional
  public void deletePersona(Long id) {
    var email = getCurrentUserEmail();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UnauthorizedException("User not found."));

    Persona persona = personaRepository.findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException(1));

    /*
    * The correct approach here would have been to transaction the information
    * in an appropriate table to persist the history; however, I ran out of time to do it.
    * */
    persona.setRemovalAt(LocalDateTime.now());
    persona.setStatus(StatusEnum.REMOVED);
    persona.setUserId(user);

    personaRepository.save(persona);
    LOGGER.info("Successfully marked persona with ID: {} as removed.", id);
  }

  private String getCurrentUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
    throw new UnauthorizedException("User not authenticated");
  }
}
