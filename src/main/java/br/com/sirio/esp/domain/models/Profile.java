package br.com.sirio.esp.domain.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfis")
public class Profile {

  @Id
  private Long id;

  @Column(name = "descricao", length = 50, nullable = false)
  private String description;
}
