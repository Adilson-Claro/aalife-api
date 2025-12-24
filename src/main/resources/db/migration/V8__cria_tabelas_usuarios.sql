CREATE TABLE usuario_base (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255),
    telefone        VARCHAR(20),
    data_nascimento DATE,
    cpf             VARCHAR(14),
    peso            DECIMAL(5,2),
    altura          DECIMAL(4,2),
    idade           INT,
    email           VARCHAR(255),
    senha           VARCHAR(255),
    role            VARCHAR(50),
    situacao        VARCHAR(20),
    tipo_usuario    VARCHAR(50),
    data_cadastro   TIMESTAMP,
    sexo            VARCHAR(20)
);

CREATE TABLE usuario_administrador (
     id              INT AUTO_INCREMENT PRIMARY KEY,
     nome            VARCHAR(255),
     telefone        VARCHAR(20),
     data_nascimento DATE,
     cpf             VARCHAR(14),
     peso            DECIMAL(5,2),
     altura          DECIMAL(4,2),
     idade           INT,
     email           VARCHAR(255),
     senha           VARCHAR(255),
     role            VARCHAR(50),
     situacao        VARCHAR(20),
     tipo_usuario    VARCHAR(50),
     data_cadastro   TIMESTAMP
);