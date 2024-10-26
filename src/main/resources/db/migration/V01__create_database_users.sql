CREATE TABLE personas(
  id BIGINT(36) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  celular VARCHAR(20) NOT NULL UNIQUE,
  logradouro VARCHAR(50),
  numero VARCHAR(6),
  complemento VARCHAR(20),
  bairro VARCHAR(20),
  cep VARCHAR(20),
  cidade VARCHAR(50),
  estado VARCHAR(2),
  status VARCHAR(8) NOT NULL,
  data_criacao DATE,
  data_remocao DATE,
  id_usuario BIGINT(36) NOT NULL
  PRIMARY KEY (id, cpf),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;