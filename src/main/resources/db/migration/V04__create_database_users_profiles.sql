CREATE TABLE usuarios_perfis(
  id_usuario BIGINT(36) NOT NULL,
  id_perfil BIGINT(36) NOT NULL,
  PRIMARY KEY (id_usuario, id_perfil),
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
  FOREIGN KEY (id_perfil) REFERENCES perfis(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (1, 1);
INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (1, 2);
INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (1, 3);
INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (1, 4);
INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (1, 5);
INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (1, 6);

INSERT INTO usuarios_perfis (id_usuario, id_perfil) VALUES (2, 6);