CREATE TABLE especialidade (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL UNIQUE,
    profissao_id    INT NOT NULL,
    situacao        VARCHAR(20) NOT NULL,

    CONSTRAINT fk_especialidade_profissao
        FOREIGN KEY (profissao_id)
            REFERENCES profissao(id)
);