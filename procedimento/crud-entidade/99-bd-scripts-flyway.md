---
layout: post
title: "Scripts Flyway"
author: "Hellen Escarate"
user: "hescarate"
published: true
tags:
- flyway migration
- flyway
- procedimento
- script flyway 
- mysql
---



## Scripts Flyway 


### Onde deve ficar o script flyway (sql migrations)?

No diretório `src/main/resources/db/migration/`


### Como deve ser nomeado? 

As migrações SQL devem seguir um padrão de nomenclatura: 
como por exemplo: V1__adicionando_nova_tabela.sql:

* V = prefixo
* 1 = versão
* __ = separador: é formado por dois underscores, se a descrição estiver presente. 
* adicionando_nova_tabela = descrição
* .sql = sufixo

ATENÇÃO:
NUNCA editar um arquivo existente, ainda que esteja errado.
Se for necessário fazer alterações, crie um novo script:
V2__corrigindo_a_tabela.sql

### Exemplo de script

      drop table if exists BASE.TABELA;

      create table BASE.TABELA
      (ID integer not null auto_increment,
      NOME varchar(60),
      CODIGO varchar(20),
      DATA date not null,
      DATA_HORA datetime not null,
      ENTIDADE_RELACIONADA_ID integer not null,
      IDADE tinyint,
      VERDADE_FALSO tinyint,

      primary key (ID),
      UNIQUE (CODIGO)
      ) ENGINE=InnoDB;

Comentários podem ser feitos:  
      
      /* Comentando */

      -- ou comentando de outra forma


É fundamental pensar na especificação antes de criar uma tabela, saber se os campos podem ser nulos, se algum campo 
deve ser único, qual o tamanho dos campos, etc...
Tomaremos como base o exemplo dado nesse artigo, segue a descrição de cada campo: 

* ID: Por ser chave primária o ID não pode ser nulo. 
* NOME: por padrão pode ser usado o tamanho 60.
* CODIGO: por padrão pode ser usado o tamanho 20.
* DATA: o tipo data armazena uma data comum. (ano, mês e dia)
* DATA_HORA: o tipo datetime armazenada data e hora (ano, mês, dia hora, min, seg)
* ENTIDADE_RELATIONADA_ID: esse campo em questão é uma chave estrangeira e deve ser idêntico a chave primária da entidade. 
* IDADE: pode ser usado nesse campo o tipo tinyint, é o menor valor que podemos armazenar (de -128 à 127, ou de 0 à 255) - 
1 byte
* VERDADE_FALSO também podemos usar o tipo tinyint. 0 = false, e 1 = true. 

### Porque é necessário uma ferramenta de migração de banco de dados?

Numa equipe de desenvolvimento de software cada pessoa trabalha com uma versão do código na sua máquina e da mesma 
forma uma versão do banco de dados. 
Para o código temos sistemas de controle de versão, mas e para o banco de dados?

### Conclusão

Com esse kata você aprende:
1. Porque usar uma ferramenta de migração de banco de dados;
2. O procedimento correto para se criar um script flyway. 

### Referências

[wiki Flyway] (http://code.google.com/p/flyway/)








