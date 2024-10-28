package br.com.sirio.esp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Request payload for address details of a persona"
)
public class AddressRequest {

  @Schema(
      description = "Street name of the address",
      example = "Rua São João"
  )
  private String street;

  @Schema(
      description = "House or apartment number",
      example = "1076"
  )
  private String number;

  @Schema(
      description = "Additional information for the address, such as apartment or suite number",
      example = "Apt 101"
  )
  private String additionalInformation;

  @Schema(
      description = "Neighborhood of the address",
      example = "Jardim Imbé"
  )
  private String neighborhood;

  @Schema(
      description = "ZIP or postal code",
      example = "21532-010"
  )
  private String zipCode;

  @Schema(
      description = "City of the address",
      example = "Rio de Janeiro"
  )
  private String city;

  @Schema(
      description = "State of the address, using abbreviation if applicable",
      example = "RJ"
  )
  private String state;
}
