package br.com.sirio.esp.domain.dto.response;

import br.com.sirio.esp.domain.models.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaResponse {

  private Long id;
  private String name;
  private String cpf;
  private String cellphone;
  private Address address;
  private String status;
  private LocalDate creationDate;
  private LocalDate removalDate;
  private UserResponse userId;
}
