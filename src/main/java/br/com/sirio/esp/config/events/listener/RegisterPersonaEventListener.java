package br.com.sirio.esp.config.events.listener;

import br.com.sirio.esp.config.events.RegisterPersonaEvent;
import br.com.sirio.esp.domain.models.Persona;
import br.com.sirio.esp.domain.models.enums.StatusEnum;
import java.time.LocalDate;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterPersonaEventListener implements ApplicationListener<RegisterPersonaEvent> {

  @Override
  public void onApplicationEvent(RegisterPersonaEvent event) {
    Persona persona = event.getPersona();

    persona.setStatus(StatusEnum.ACTIVE);
    persona.setCreationAt(LocalDate.now());
  }
}
