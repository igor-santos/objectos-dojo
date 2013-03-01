use OBJ_BASE;

drop table if exists OBJ_BASE.CLIENTE;

CREATE TABLE CLIENTE(

 ID int not null auto_increment,
 NOME varchar(60) not null,
 TELEFONE varchar(60) not null,
 ENDERECO varchar(60) not null,
 CPF varchar(15) not null,
 
 primary key(ID)
)