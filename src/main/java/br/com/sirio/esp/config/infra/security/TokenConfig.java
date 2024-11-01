package br.com.sirio.esp.config.infra.security;

import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.utils.PropsUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenConfig {

  private final PropsUtil propsUtil;

  private final String secret;

  public TokenConfig(PropsUtil propsUtil) {
    this.propsUtil = propsUtil;
    this.secret = propsUtil.security().token().secret();
  }

  public String generateToken(User user) {

    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.create()
          .withIssuer("auth-api")
          .withSubject(user.getEmail())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);
    } catch (JWTCreationException ex) {
      throw new RuntimeException("Error while generating token", ex);
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
          .withIssuer("auth-api")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException ex) {
      return "";
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}

// TODO: Separate the authentication and verify the information of the logged-in user via token with Node.Js Application
