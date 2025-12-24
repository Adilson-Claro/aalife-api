CREATE TABLE estado (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nome    VARCHAR(255) NOT NULL UNIQUE,
    uf      VARCHAR(2) NOT NULL UNIQUE
);

INSERT INTO estado (nome, uf) VALUES
   ('Acre', 'AC'),
   ('Alagoas', 'AL'),
   ('Amapá', 'AP'),
   ('Amazonas', 'AM'),
   ('Bahia', 'BA'),
   ('Ceará', 'CE'),
   ('Distrito Federal', 'DF'),
   ('Espírito Santo', 'ES'),
   ('Goiás', 'GO'),
   ('Maranhão', 'MA'),
   ('Mato Grosso', 'MT'),
   ('Mato Grosso do Sul', 'MS'),
   ('Minas Gerais', 'MG'),
   ('Pará', 'PA'),
   ('Paraíba', 'PB'),
   ('Paraná', 'PR'),
   ('Pernambuco', 'PE'),
   ('Piauí', 'PI'),
   ('Rio de Janeiro', 'RJ'),
   ('Rio Grande do Norte', 'RN'),
   ('Rio Grande do Sul', 'RS'),
   ('Rondônia', 'RO'),
   ('Roraima', 'RR'),
   ('Santa Catarina', 'SC'),
   ('São Paulo', 'SP'),
   ('Sergipe', 'SE'),
   ('Tocantins', 'TO');