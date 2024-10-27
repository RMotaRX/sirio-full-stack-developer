package br.com.sirio.esp.domain.repositories;

import br.com.sirio.esp.domain.models.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

  Optional<Persona> findByCpfAndIdNot(String cpf, Long id);
}
