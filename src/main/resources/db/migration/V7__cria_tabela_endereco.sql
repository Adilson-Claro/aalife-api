CREATE TABLE endereco (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    profissional_id     INT NOT NULL,
    cidade_id           INT NOT NULL,
    logradouro          VARCHAR(255) NOT NULL,
    bairro              VARCHAR(255) NOT NULL,
    codigo_ibge         VARCHAR(20) NOT NULL,
    complemento         VARCHAR(255),
    numero              INT NOT NULL,

    CONSTRAINT fk_endereco_profissional
        FOREIGN KEY (profissional_id)
            REFERENCES usuario_profissional(id),

    CONSTRAINT fk_endereco_cidade
        FOREIGN KEY (cidade_id)
            REFERENCES cidade(id)
);