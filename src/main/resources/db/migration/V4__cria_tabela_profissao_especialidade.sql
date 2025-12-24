CREATE TABLE profissao_especialidade (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    profissao_id        INT NOT NULL,
    especialidade_id    INT NOT NULL,

    CONSTRAINT fk_pe_profissao
         FOREIGN KEY (profissao_id)
              REFERENCES profissao(id),

    CONSTRAINT fk_pe_especialidade
         FOREIGN KEY (especialidade_id)
              REFERENCES especialidade(id),

    CONSTRAINT uk_profissao_especialidade
         UNIQUE (profissao_id, especialidade_id)
);