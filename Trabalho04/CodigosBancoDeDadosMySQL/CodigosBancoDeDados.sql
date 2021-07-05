/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gustavo
 * Created: 08/06/2021
 */

create database exercicio3_JavaWeb;
use exercicio3_JavaWeb;

drop table if exists tb_usuario;

drop table if exists tb_cliente;

CREATE TABLE tb_usuario (
id_Usuario INT,
login_Usuario VARCHAR(50),
senha_Usuario VARCHAR(50),
nome_Usuario VARCHAR(100),
CONSTRAINT pk_TbUsuario PRIMARY KEY (id_Usuario)
);

insert into tb_usuario(id_Usuario,login_Usuario,senha_Usuario,nome_Usuario) values
(1,"Gustavo","1234","Gustavo Achinitz"),
(2,"Alice","1234","Alice Conceição"),
(3,"Joao","1234","João Armenio");

CREATE TABLE tb_cliente (
id_cliente INTEGER, 
cpf_cliente CHAR(11), 
nome_cliente VARCHAR(100),
email_cliente VARCHAR(100),
data_cliente DATE,
rua_cliente VARCHAR(100),
nr_cliente INTEGER,
cep_cliente CHAR(8),
cidade_cliente VARCHAR(100),
uf_cliente CHAR(2),
CONSTRAINT pk_TbUsuario PRIMARY KEY (id_cliente)
);

truncate tb_cliente;

insert into tb_cliente(id_cliente,cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,cidade_cliente,uf_cliente) VALUES 
(1,"12345678910","Gustavo","gustavo@email.com",now(),"Rua 1", 11111111,"81111000","Curitiba","PR"),
(2,"14587456987","Pedro","pedro@email.com",now(),"Rua 2", 22222222,"81111000","Santa Catarina","SC"),
(3,"48759877564","Joao","joao@email.com",now(),"Rua 3", 33333333,"81111000","Sao Paulo","SP"),
(4,"35795148632","Helena","helena@email.com",now(),"Rua 4", 44444444,"81111000","Rio Grande do Sul","RS"),
(5,"01452145214","Marcelo","marcelo@email.com",now(),"Rua 5", 55555555,"81111000","Xanxere","SC"),
(6,"95487596547","Davi","davi@email.com",now(),"Rua 6", 66666666,"81111000","Paranagua","PR"),
(7,"03654789541","Alexandre","alexandre@email.com",now(),"Rua 7", 77777777,"81111000","Recife","PE"),
(8,"02789521400","Gabriel","gabriel@email.com",now(),"Rua 8", 88888888,"81111000","Campinas","SP"),
(9,"15478951102","Debora","debora@email.com",now(),"Rua 9", 99999999,"81111000","Torres","RS"),
(10,"51234567891","Matheus","matheus@email.com",now(),"Rua 10", 10101010,"81111000","Belo Horizonte","MG"),
(11,"41234567891","Alice","alice@email.com",now(),"Rua 11", 12121212,"81111000","Florianopolis","SC");

select * from tb_cliente;

select * from tb_usuario;

SELECT tb_usuario.login_Usuario from tb_usuario;