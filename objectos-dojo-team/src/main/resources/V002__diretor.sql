set foreign_key_checks=0;

drop table if exists DATABASE.DIRETOR;

create table DATABASE.DIRETOR (

	ID 				integer 	not null 	auto_increment, 
	NOME 			varchar(50) not null,
	
	primary key 	(ID),
)

type=InnoDB;
