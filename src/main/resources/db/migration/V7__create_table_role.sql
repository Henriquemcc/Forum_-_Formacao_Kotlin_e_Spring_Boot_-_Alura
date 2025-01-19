CREATE TABLE role(
    id BIGINT NOT NULL auto_increment,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO role (id, nome) VALUES (1, 'LEITURA_ESCRITA');