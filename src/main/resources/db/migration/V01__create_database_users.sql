CREATE TABLE usuarios(
  id BIGINT(36) NOT NULL AUTO_INCREMENT,
 	nome VARCHAR(40) NOT NULL,
 	email VARCHAR(50) NOT NULL UNIQUE,
 	senha VARCHAR(150) NOT NULL,
 	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuarios (nome, email, senha) VALUES ('Arthur Correia Rodrigues', 'arthurcorreiarodrigues@armyspy.com', 'ArthurCorreiaRodrigues@2024');
INSERT INTO usuarios (nome, email, senha) VALUES ('Beatriz Martins Azevedo', 'beatrizmartinsazevedo@armyspy.com', 'BmA@armyspy');