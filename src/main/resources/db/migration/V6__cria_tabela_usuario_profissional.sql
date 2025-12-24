CREATE TABLE usuario_profissional (
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    telefone                   VARCHAR(20),
    razao_social               VARCHAR(255),
    cnpj                       VARCHAR(18),
    email                      VARCHAR(255),
    senha                      VARCHAR(255),
    role                       VARCHAR(50),
    situacao                   VARCHAR(20),
    tipo_usuario               VARCHAR(50),
    data_cadastro              TIMESTAMP,
    tipo_conselho              VARCHAR(50) NOT NULL,
    nome_profissional          VARCHAR(255),
    cpf                        VARCHAR(14) UNIQUE,
    numero_conselho            VARCHAR(50),
    area_saude                 VARCHAR(50),
    profissao_especialidade_id INT NOT NULL,
    endereco_id                INT,

     CONSTRAINT fk_prof_especialidade
         FOREIGN KEY (profissao_especialidade_id)
             REFERENCES profissao_especialidade(id)
);