set foreign_key_checks=0;

create database if not exists DATABASE ;

drop table if exists DATABASE.FUNCIONARIO;

create table DATABASE.FUNCIONARIO (

	ID 				integer 	not null 	auto_increment, 
	NOME 			varchar(50) not null,
	MATRICULA 		char(8) 	not null,
	DATA_NASCIMENTO date() 		not null, 
	ADMISSAO 		datetime 	not null,
	DEMISSAO		datetime,
	DIRETOR_ID		integer 	not null,
	
	primary key 	(ID),
	unique 			(MATRICULA)
)

type=InnoDB;
