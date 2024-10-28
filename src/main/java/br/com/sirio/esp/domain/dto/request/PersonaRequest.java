package br.com.sirio.esp.domain.dto.request;

import br.com.sirio.esp.domain.models.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Request payload for creating or updating a persona"
)
public class PersonaRequest {

  @JsonIgnore
  @Schema(
      description = "Unique identifier of the persona (ignored on creation)",
      example = "123"
  )
  private Long id;

  @NotEmpty
  @Schema(
      description = "Name of the persona",
      example = "Fábio Melo Pinto"
  )
  private String name;

  @NotEmpty
  @Schema(
      description = "CPF of the persona, must be unique",
      example = "91461200172"
  )
  private String cpf;

  @NotNull
  @Schema(
      description = "Birthdate of the persona",
      example = "1939-01-19"
  )
  private LocalDate birthdate;

  @Schema(
      description = "Cellphone number of the persona",
      example = "+55 21 939 578 176"
  )
  private String cellphone;

  @Valid
  @Embedded
  @Schema(
      description = "Address details of the persona"
  )
  private AddressRequest address;

  @JsonIgnore
  @Schema(
      description = "Status of the persona (ignored on creation)",
      example = "ACTIVE"
  )
  private StatusEnum status;

  @JsonIgnore
  @Schema(
      description = "Creation timestamp (automatically set)",
      example = "2024-10-28T14:27:31.959"
  )
  private LocalDateTime creationAt;

  @JsonIgnore
  @Schema(
      description = "Update timestamp (automatically set)",
      example = "2024-10-28T15:45:12.500"
  )
  private LocalDateTime updatedAt;

  @JsonIgnore
  @Schema(
      description = "Removal timestamp (automatically set)",
      example = "2024-11-10T10:15:00.000"
  )
  private LocalDateTime removalAt;
}
