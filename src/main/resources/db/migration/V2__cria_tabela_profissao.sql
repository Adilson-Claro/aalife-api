CREATE TABLE profissao (
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    nome                    VARCHAR(255) NOT NULL UNIQUE,
    orgao_regulamentador    VARCHAR(50) NOT NULL,
    situacao                VARCHAR(20) NOT NULL
);