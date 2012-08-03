---
layout: post
title: "Cláusula Join"
author: "Hellen Carla Paixão Escarate"
published: true
partof: sql
num: 0
---

## Introdução
Em alguns casos, precisamos de uma consulta que envolve duas ou mais entidades.
Utilizamos a cláusula JOIN contida na linguagem SQL para resolver este problemas. Ela é muito útil para consultas como, por exemplo, listar todos
os funcionários do setor Y de uma empresa X (junção da tabela Funcionario, Setor e Empresa). Como o próprio
nome diz, _Join_ (Juntar).

## Criando um Banco de Dados
Iremos demonstrar 3 tipos possíveis de se executar um _join_. Elas são: 

- Inner Join 
- Left Join
- Right Join

Para isso, vamos utilizar o _MySQL_ no terminal. 

	$ mysql -u<seu usuario> -p<sua senha>

Importante: A senha só será necessária se seu usuário foi cadastrado com senha no _MySql_. Com isso,
evitamos o erro `ERROR 1044 (42000): Access denied`. Caso contrário, digite apenas o nome de usuário. 

Nota: Para executar qualquer comando escrito, deve-se apertar a tecla `Enter` após o sinal de ponto-e-vírgula.

Antes de criar as tabelas, não podemos esquecer de primeiro criar um banco de dados chamado __Exemplo__ (com 
a primeira letra maiúscula): 

    mysql> create database Exemplo; 

Certo, agora com o banco de dados criado, precisamos dizer que queremos usá-lo: 

    mysql> use Exemplo; 
    Database changed

Imagine que você é responsável pelo controle das _TASK_ (atividades da equipe), e mensalmente você precisa emitir um relatório mostrando o nome
de cada pessoa e a sua respectiva _task_. 

Criaremos a tabela `PESSOA`

    mysql> create table PESSOA (
    -> ID int not null,
    -> NOME varchar (50) not null,
    -> primary key(ID)
    -> );
    Query OK, 0 rows affected (0.02 sec)

E agora a tabela `TASK`:

    mysql> create table TASK (
    -> ID int not null,
    -> NOME varchar (50) not null,
    -> DATA date,
    -> PESSOA_ID int not null,
    -> primary key (ID)
    -> );
    Query OK, 0 rows affected (0.03 sec)

Observe que quando criamos as tabelas, todos os campos estão em letra maiúscula, e o ID de cada tabela será sempre
apenas ID, sem mais informações. Isso é um padrão interno, e deve ser seguido á partir de agora, ok? 

Nosso próximo passo é inserir os registros nas tabelas como os exemplos abaixo:

    mysql> insert into PESSOA values (1, 'Arthur');
    mysql> insert into TASK values (1, 'Entidade JPA', '2011-07-06', 1);

Insira alguns registros nas duas tabelas para que elas fiquem com esta estrutura: 

    +----+--------+
    | ID | NOME   |
    +----+--------+
    |  1 | Arthur |
    |  2 | Lucas  |
    |  3 | Hellen |
    |  4 | Marcio |
    +----+--------+
    4 rows in set (0.00 sec)


	+----+------------------------+------------+-----------+
	| ID | NOME                   | DATA       | PESSOA_ID |
	+----+------------------------+------------+-----------+
	|  1 | Entidade JPA           | 2011-07-06 |         1 |
	|  2 | Dojo TDD               | 2011-07-15 |         1 |
	|  3 | Contrato JPA           | 2011-07-22 |         2 |
	|  4 | Exposicao JPA          | 2011-07-30 |         2 |
	|  5 | Dojo JOIN              | 2011-07-28 |         3 |
	|  6 | Dojo Atalhos Eclipse   | 2011-07-10 |         3 |
	|  7 | Refatorar Entidade JPA | 2011-07-17 |         4 |
	+----+------------------------+------------+-----------+
    7 rows in set (0.00 sec)

Utilize o _select_ para visualizar as tabelas

	mysql> select * from PESSOA
	mysql> select * from TASK

## Inner Join

Agora que inserimos registros nas duas tabelas podemos utilizar o _inner join_. 

Como descrevemos acima, precisamos emitir um relatório mostrando cada _task_ de cada pessoa: 

    mysql> select t.NOME as TASK, p.NOME as PESSOA from TASK as t join PESSOA as p on t.PESSOA_ID = p.ID;
	+---------------+--------+
	| TASK          | PESSOA |
	+---------------+--------+
	| Entidade JPA  | Arthur |
	| Dojo TDD      | Arthur |
	| Contrato JPA  | Lucas  |
	| Exposicao JPA | Lucas  |
	| Dojo JOIN     | Helen  |
	+---------------+--------+
	5 rows in set (0.00 sec)

Note que a letra p e t são apelidos que demos as tabelas, nesse exercício o nome das tabelas são pequenos, mas em um
ambiente de produção você pode se deparar com nomes grandes de tabelas e então, vai ter que digitar várias vezes o mesmo
nome perdendo mais tempo e correndo o risco de digitar errado, por isso preferimos usar os apelidos.

Note também que através dos atributos semelhantes (os ids) conseguimos juntar as tabelas. E a cláusula _inner join_ pode ser
omitida a palavra _inner_.

## Left Join

E se você quiser saber todas as _tasks_ disponíveis, mesmo que não tenha mais pessoas definidas para aquela _task_?

    mysql> select t.NOME, p.NOME from TASK as t left join PESSOA as p on t.PESSOA_ID = p.ID;
	+------------------------+--------+
	| NOME                   | NOME   |
	+------------------------+--------+
	| Entidade JPA           | Arthur |
	| Dojo TDD               | Arthur |
	| Contrato JPA           | Lucas  |
	| Exposicao JPA          | Lucas  |
	| Dojo JOIN              | Helen  |
	| Dojo Atalhos Eclipse   | NULL   |
	| Refatorar Entidade JPA | NULL   |
	+------------------------+--------+
	7 rows in set (0.00 sec)



Você pode observar que quando usamos o _left join_, ele retorna todos os registros da tabela da esquerda, independente
se a _task_ não é realizada por ninguém.


## Right Join
O _right join_ faz a mesma coisa, mas retornando os registros da tabela da direita. 

    mysql> select t.NOME, p.NOME from TASK as t right join PESSOA as p on t.PESSOA_ID = p.ID;
	+---------------+--------+
	| NOME          | NOME   |
	+---------------+--------+
	| Entidade JPA  | Arthur |
	| Dojo TDD      | Arthur |
	| Contrato JPA  | Lucas  |
	| Exposicao JPA | Lucas  |
	| Dojo JOIN     | Helen  |
	| NULL          | Marcio |
	+---------------+--------+
	6 rows in set (0.00 sec)

Entre o _right join_ e o _left join_, o mais usado é o _left join_.

## Excluindo o Banco de Dados

Após finalizado todos os teste vamos excluir todo o banco de dados e suas respectivas tabelas. 

    mysql> drop database Exemplo;
	Query OK, 2 rows affected (0.02 sec)
	
Para sair do _MySql_:

	mysql> exit
	Bye	