package br.com.sirio.esp.domain.dto.request;

import br.com.sirio.esp.domain.models.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequest {

  @JsonIgnore
  private Long id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String cpf;

  private String cellphone;

  @Valid
  @Embedded
  private AddressRequest address;

  @JsonIgnore
  private StatusEnum status;

  @JsonIgnore
  private LocalDate creationAt;

  @JsonIgnore
  private LocalDate updatedAt;

  @JsonIgnore
  private LocalDate removalAt ;

  private UserRequest userId;
}
