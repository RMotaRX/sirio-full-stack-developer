package br.com.sirio.esp.domain.dto.request;

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
public class AddressRequest {

  private String street;
  private String number;
  private String additionalInformation;
  private String neighborhood;
  private String zipCode;
  private String city;
  private String state;
}
