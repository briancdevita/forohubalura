CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(100) NOT NULL,
                         mensaje VARCHAR(255) NOT NULL,
                         fecha_creacion DATETIME NOT NULL,
                         status TINYINT NOT NULL,
                         autor BIGINT NOT NULL,
                         curso VARCHAR(100) NOT NULL,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_topicos_autor FOREIGN KEY (autor) REFERENCES usuarios(id)
);
