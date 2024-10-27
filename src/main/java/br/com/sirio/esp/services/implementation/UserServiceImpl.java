package br.com.sirio.esp.services.implementation;

import br.com.sirio.esp.domain.dto.request.RegisterRequest;
import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.domain.repositories.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public boolean registerUser(RegisterRequest request) {
    Optional<User> existingUser = userRepository.findByEmail(request.email());
    if (existingUser.isPresent()) {
      return false;
    }
    String encryptedPassword = passwordEncoder.encode(request.password());
    User user = new User(request.email(), encryptedPassword, request.role());
    userRepository.save(user);
    return true;
  }

  public UserDetails loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .map(user -> (UserDetails) user)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
