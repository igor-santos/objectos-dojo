---
layout: post-alpha
title: "Alterando e deletando tabelas com Scripts Flyway"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-06-04"
published: true
partof: faq-crud-entidade
num: 5
outof: 5
---

##Introdução

Ao desenvolver um projeto, você passará por situações onde é necessário criar e/ou deletar algumas
tabelas no banco de dados para melhorar o relacionamento entre elas. Demonstraremos como deletar
uma tabela que não será mais utilizada sem afetar a estrutura de dados. Em outras palavras,
o banco de dados em produção não sofrerá uma consequência.

## Tabelas
Supondo que os dados estão armazenados da seguinte forma:

	<BD.TABELA_A ID="1" NOME="Tabela A 01" />
	<BD.TABELA_A ID="2" NOME="Tabela A 02" />
	
	<BD.TABELA_B TABELA_A_ID="1" TABELA_C_ID="1" />
	<BD.TABELA_B TABELA_A_ID="1" TABELA_C_ID="2" />
	
	<BD.TABELA_C ID="1" TABELA_D_ID="1" TIPO="0" />
	<BD.TABELA_C ID="2" TABELA_D_ID="2" TIPO="0" />
	
	<BD.TABELA_D ID="1" VALOR="10" />
	<BD.TABELA_D ID="2" VALOR="10" />
	
E agora, queremos deletar a `TABELA_B` e atribuir os dados de `TABELA_B.TABELA_A_ID` a `TABELA_C`.

	<BD.TABELA_A ID="1" NOME="Tabela A 01" />
	<BD.TABELA_A ID="2" NOME="Tabela A 02" />

	<BD.TABELA_C ID="1" TABELA_A_ID="1" TABELA_D_ID="1" TIPO="0" />
	<BD.TABELA_C ID="2" TABELA_A_ID="1" TABELA_D_ID="2" TIPO="0" />

Nota :Esta operação deve ser feita de modo que os dados não sejam perdidos (deletados) pois eles
estão em produção.

## Alterando e deletando as tabelas
Primeiro iremos criar uma tabela temporária com a(s) nova(s) coluna(s).


	set foreign_key_checks=0;
	
	drop table if exists BD.TABELA_C_TMP;
	
	create table BD.TABELA_C_TMP (
	
	ID integer not null auto_increment,
	TIPO integer not null,
	TABELA_A_ID integer not null,
	TABELA_D_ID integer not null, 
	
	primary key (ID),
	index (TABELA_A_ID, TABELA_D_ID)
	
	)engine=InnoDB;
	
Agora vamos inserir os dados que se encontram em `TABELA_B` para a nova tabela (temporária).	
	
	insert into BD.TABELA_C_TMP 
	(TIPO, TABELA_A_ID, TABELA_D_ID) 
	select 
	C.TIPO, B.TABELA_A_ID, C.TABELA_D_ID
	from BD.TABELA_B as B
	join BD.TABELA_C as C
	on B.TABELA_C_ID = C.ID;
	
Nossa nova tabela já possui os dados e está quase pronta. Precisamos renomeá-la, mas para isto,
é necessário deletar as outras tabelas que não serão mais utilizadas.	
	
Importante: Caso a tabela tenha chaves estrangeiras é necessário removê-las	
	
	alter table BD.TABELA_B drop foreign key FK_TABELA_B_TABELA_A_ID;
	alter table BD.TABELA_B drop foreign key FK_TABELA_B_TABELA_C_ID;
	
Agora você deletar as tabelas `TABELA_B` e `TABELA_C` e renomear a `TABELA_C_TMP` para `TABELA_C`.<br>
Nota: Mantenha estes passos em um único _Script Flyway_.	
	
	drop table if exists BD.TABELA_C;
	drop table if exists BD.TABELA_B;
	
	rename table BD.TABELA_C_TMP to BD.TABELA_C;
	
	