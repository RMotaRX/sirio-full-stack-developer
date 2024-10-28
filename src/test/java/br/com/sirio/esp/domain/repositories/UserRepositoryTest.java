package br.com.sirio.esp.domain.repositories;

import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.domain.models.enums.UserRoleEnum;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @Test
  public void whenValidEmail_thenUserShouldBeFound() {
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("12345");
    user.setRole(UserRoleEnum.ADMIN);
    userRepository.save(user);

    Optional<User> foundUser = userRepository.findByEmail("test@example.com");

    Assertions.assertTrue(foundUser.isPresent());
    Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
  }

  @Test
  public void whenInvalidEmail_thenUserShouldNotBeFound() {
    Optional<User> foundUser = userRepository.findByEmail("invalid@example.com");

    Assertions.assertFalse(foundUser.isPresent());
  }
}
