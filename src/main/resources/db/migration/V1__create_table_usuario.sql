CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;

CREATE TABLE usuario (
    id                          BIGINT PRIMARY KEY,
    nome                        VARCHAR(120)  NOT NULL,
    email                       VARCHAR(160)  NOT NULL UNIQUE,
    senha                       VARCHAR(255)  NOT NULL,
    role                        VARCHAR(30)   NOT NULL,
    situacao                    VARCHAR(30)   NOT NULL,
    data_cadastro               TIMESTAMP     NOT NULL,
    tipoOrgaoRegulamentador     VARCHAR(255),
    cpf                         VARCHAR(11),
    cnpj                        VARCHAR(14),
    razaoSocial                 VARCHAR(255),
    dataNascimento              DATE,
    areaSaude                   VARCHAR(255),
    numeroOrgaoRegulamentador   INTEGER,
    peso                        NUMERIC,
    altura                      NUMERIC,
    tipoUsuario                 VARCHAR(50),
    sexo                        VARCHAR(20)
);

CREATE INDEX idx_usuario_email ON usuario (email);