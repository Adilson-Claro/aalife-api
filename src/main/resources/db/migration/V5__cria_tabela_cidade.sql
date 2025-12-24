CREATE TABLE cidade (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL UNIQUE,
    estado_id       INT,
    codigo_ibge     VARCHAR(20) UNIQUE,

    CONSTRAINT fk_cidade_estado
        FOREIGN KEY (estado_id)
            REFERENCES estado(id)
);