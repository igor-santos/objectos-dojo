set foreign_key_checks=0;

drop table if exists DATABASE.SUPERIOR;

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
