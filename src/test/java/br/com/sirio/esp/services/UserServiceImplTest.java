package br.com.sirio.esp.services;

import br.com.sirio.esp.domain.dto.request.RegisterRequest;
import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.domain.models.enums.UserRoleEnum;
import br.com.sirio.esp.domain.repositories.UserRepository;
import br.com.sirio.esp.services.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import({UserServiceImpl.class, BCryptPasswordEncoder.class})
public class UserServiceImplTest {

  @Autowired private UserRepository userRepository;
  @Autowired private UserServiceImpl userService;
  @Autowired private BCryptPasswordEncoder passwordEncoder;

  private RegisterRequest validRequest;
  private RegisterRequest duplicateRequest;

  @BeforeEach
  void setUp() {
    validRequest = new RegisterRequest("user@example.com", "P@ssw0rd", UserRoleEnum.ADMIN);
    duplicateRequest = new RegisterRequest("user@example.com", "AnotherP@ssw0rd", UserRoleEnum.USER);
  }

  @Test
  void registerUser_shouldReturnTrue_whenUserDoesNotExist() {
    boolean result = userService.registerUser(validRequest);

    assertThat(result).isTrue();
    Optional<User> savedUser = userRepository.findByEmail(validRequest.email());
    assertThat(savedUser).isPresent();
    assertThat(passwordEncoder.matches(validRequest.password(), savedUser.get().getPassword())).isTrue();
    assertThat(savedUser.get().getRole()).isEqualTo(UserRoleEnum.ADMIN);
  }

  @Test
  void registerUser_shouldReturnFalse_whenUserAlreadyExists() {
    userService.registerUser(validRequest);

    boolean result = userService.registerUser(duplicateRequest);

    assertThat(result).isFalse();
    assertThat(userRepository.findByEmail(duplicateRequest.email())).isPresent();
  }

  @Test
  void loadUserByUsername_shouldReturnUserDetails_whenUserExists() {
    userService.registerUser(validRequest);

    UserDetails userDetails = userService.loadUserByUsername(validRequest.email());

    assertThat(userDetails.getUsername()).isEqualTo(validRequest.email());
    assertThat(userDetails.getAuthorities()).hasSize(2);
    assertThat(userDetails.getAuthorities().toString()).contains("ROLE_ADMIN", "ROLE_USER");
  }

  @Test
  void loadUserByUsername_shouldThrowUsernameNotFoundException_whenUserDoesNotExist() {
    assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistent@example.com"));
  }
}
