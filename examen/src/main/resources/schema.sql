DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
  id INT NOT NULL AUTO_INCREMENT ,
  nombre VARCHAR(512) NOT NULL,
  apellido_paterno VARCHAR(512) NOT NULL,
  apellido_materno VARCHAR(512) NOT NULL,
  fecha_nacimiento DATETIME NULL,
  ingresos FLOAT NULL,
  codigo_postal CHAR(6) NULL,
  PRIMARY KEY (id));