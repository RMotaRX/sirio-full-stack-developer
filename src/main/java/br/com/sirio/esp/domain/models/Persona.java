package br.com.sirio.esp.domain.models;

import br.com.sirio.esp.domain.models.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personas")
public class Persona implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome", length = 50, nullable = false)
  private String name;

  @Column(length = 11, unique = true)
  private String cpf;

  @Column(name = "celular", length = 20, nullable = false, unique = true)
  private String cellphone;

  @Embedded
  private Address address;

  @Column(length = 8)
  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @Column(name = "data_criacao")
  private LocalDate creationAt;

  @Column(name = "data_atualizacao")
  private LocalDate updatedAt;

  @Column(name = "data_remocao")
  private LocalDate removalAt;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private User userId;
}
