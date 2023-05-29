CREATE TABLE cidade (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL
);

CREATE TABLE estado (
  id INT PRIMARY KEY AUTO_INCREMENT,
  sigla CHAR(2) NOT NULL,
  cidade_id INT,
  FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);

CREATE TABLE usuario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  idade INT,
  estado_sigla CHAR(2),
  data_cadastro DATE,
  cidade_id INT,
  FOREIGN KEY (estado_sigla) REFERENCES estado(sigla),
  FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);
