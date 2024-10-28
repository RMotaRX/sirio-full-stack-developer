package br.com.sirio.esp.domain.repositories;

import static br.com.sirio.esp.utils.ModelFactoryUtils.buildPersonas;
import static br.com.sirio.esp.utils.ModelFactoryUtils.buildUsers;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.sirio.esp.domain.models.Persona;
import br.com.sirio.esp.domain.models.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.properties")
class PersonaRepositoryTest {

  @Autowired private UserRepository userRepository;
  @Autowired private PersonaRepository personaRepository;

  @Test
  public void shouldRegisterPersonasAndUsersProcessFetchOperation() {
    List<User> users = buildUsers();
    userRepository.saveAll(users);

    List<Persona> personas = buildPersonas(users);
    personaRepository.saveAll(personas);

    assertThat(users).hasSize(2);
    assertThat(personas).hasSize(1);

    assertThat(users).containsExactlyInAnyOrderElementsOf(users);
    assertThat(personas).containsExactlyInAnyOrderElementsOf(personas);
  }
}
