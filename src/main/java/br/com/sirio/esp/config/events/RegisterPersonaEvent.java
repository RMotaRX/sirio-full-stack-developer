package br.com.sirio.esp.config.events;

import br.com.sirio.esp.domain.models.Persona;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegisterPersonaEvent extends ApplicationEvent {

  private final Persona persona;

  public RegisterPersonaEvent(Object source, Persona persona) {
    super(source);
    this.persona = persona;
  }
}
