create database fuzuapp;
use fuzuapp;

create table Usuario (id INT, Email VARCHAR(20), Nome VARCHAR(100), Senha VARCHAR(20), Login VARCHAR(20));
alter table Usuario add primary key (id);
ALTER TABLE Usuario MODIFY COLUMN id INT auto_increment;

create table Favorito (id INT, descricao VARCHAR(500), foto_url VARCHAR(200), nome_usuario VARCHAR(150), tipo VARCHAR(30), url VARCHAR(200), horario VARCHAR(100), latitude DOUBLE, longitude DOUBLE);
alter table Favorito add primary key (id);
ALTER TABLE Favorito MODIFY COLUMN id INT auto_increment;





