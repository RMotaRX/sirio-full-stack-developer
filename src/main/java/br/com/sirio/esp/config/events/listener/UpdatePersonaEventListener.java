package br.com.sirio.esp.config.events.listener;

import br.com.sirio.esp.config.events.UpdatePersonaEvent;
import br.com.sirio.esp.domain.models.Persona;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonaEventListener implements ApplicationListener<UpdatePersonaEvent> {

  @Override
  public void onApplicationEvent(UpdatePersonaEvent event) {
    Persona persona = event.getPersona();

    persona.setUpdatedAt(LocalDateTime.now());
  }
}
