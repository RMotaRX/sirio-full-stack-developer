package br.com.sirio.esp.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Column(name = "logradouro", length = 50)
  private String street;

  @Column(name = "numero", length = 6)
  private String number;

  @Column(name = "complemento", length = 20)
  private String additionalInformation;

  @Column(name = "bairro", length = 20)
  private String neighborhood;

  @Column(name = "cep", length = 9)
  private String zipCode;

  @Column(name = "cidade", length = 50)
  private String city;

  @Column(name = "estado", length = 2)
  private String state;
}
