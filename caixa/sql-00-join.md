---
layout: post
title: "SQL :: Join"
author: "Hellen Carla Paixão Escarate"
published: true
partof: sql
num: 0
---

Nesse Dojo vamos praticar os comandos INNER JOIN, LEFT JOIN e RIGHT JOIN.

Pra isso, vamos usar o MySQL no terminal, executando o comando abaixo. 

É necessário que você complete o comando com seu nome de usuário. 

    $ mysql -u <seu usuario> 

Antes de sair criando as tabelas, não podemos esquecer de primeiro criar um banco de dados que vai se chamar Kata (com 
a primeira letra maiúscula): 

    mysql> create database Kata; 

Certo, agora com o banco de dados criado, precisamos dizer que queremos usá-lo: 

    mysql> use Kata; 
    Database changed

Imagine que você é responsável pelo controle das tasks, e mensalmente você precisa emitir um relatório mostrando o nome
de cada pessoa e o nome de cada TASK. 

Pra isso, criaremos a tabela PESSOAS

    mysql> create table PESSOAS (
    -> ID int not null,
    -> NOME varchar (50) not null,
    -> primary key(ID)
    -> );
    Query OK, 0 rows affected (0.02 sec)

E agora a tabela TASKS:

    mysql> create table TASKS (
    -> ID int not null,
    -> NOME varchar (50) not null,
    -> DATA date,
    -> primary key (ID)
    -> );
    Query OK, 0 rows affected (0.03 sec)

Observe que quando criamos as tabelas, todos os campos estão em letra maiúscula, e o ID de cada tabela será sempre
apenas ID, sem mais informações. Isso é um padrão interno, e deve ser seguido á partir de agora, ok? 

Nosso próximo passo é inserir os registros nas tabelas.

    mysql> insert into PESSOAS values (1, 'Arthur');

Insira alguns registros nas duas tabelas, conforme o exemplo acima. 

Agora, verifique se os registros foram inseridos corretamente, conforme abaixo: 

    mysql> select * from PESSOAS;
    +----+--------+
    | ID | NOME   |
    +----+--------+
    |  1 | Arthur |
    |  2 | Lucas  |
    |  3 | Hellen |
    |  4 | Marcio |
    +----+--------+
    4 rows in set (0.00 sec)

    mysql> insert into TASKS values (1, 'Entidade JPA', '2011-07-06');

    mysql> select * from TASKS;
    +----+------------------------+------------+
    | ID | NOME                   | DATA       |
    +----+------------------------+------------+
    |  1 | Entidade JPA           | 2011-07-06 |
    |  2 | Dojo TDD               | 2011-07-15 |
    |  3 | Contrato JPA           | 2011-07-22 |
    |  4 | Exposição Swap JPA     | 2011-07-30 |
    |  5 | Dojo join              | 2011-07-28 |
    |  6 | Dojo Atalhos Eclipse   | 2011-07-10 |
    |  7 | Refatorar entidade JPA | 2011-07-17 |
    +----+------------------------+------------+
    7 rows in set (0.00 sec)

## Inner Join

Agora que inserimos registros nas duas tabelas podemos usar o inner join. 

Então, precisamos emitir um relatório mostrando cada task pra cada pessoa: 

    mysql> select * from TASKS as t join PESSOAS as p on t.ID = p.ID;
    +----+----------------------+------------+----+--------+
    | ID | NOME                 | DATA       | ID | NOME   |
    +----+----------------------+------------+----+--------+
    |  1 | Entidade JPA         | 2011-07-06 |  1 | Arthur |
    |  2 | Dojo TDD             | 2011-07-15 |  2 | Lucas  |
    |  3 | Contrato JPA         | 2011-07-22 |  3 | Hellen |
    |  4 | Exposição Swap JPA   | 2011-07-30 |  4 | Marcio |
    +----+----------------------+------------+----+--------+
    4 rows in set (0.00 sec)

<div class="alert-message block-message info">
Note que a letra p e t são apelidos que demos as tabelas, nesse exercício o nome das tabelas são pequenos, mas em um
ambiente de produção você pode se deparar com nomes grandes de tabelas e então, vai ter que digitar várias vezes o mesmo
nome perdendo mais tempo e correndo o risco de digitar errado, por isso preferimos usar os apelidos.)
</div>

Nesse exemplo acima, selecionamos todos os campos da tabela TASKS, e através do **join**, juntamos com todos os campos 
da tabela PESSOAS. 

Como queremos juntar apenas o campo NOME da tabela TASKS com o campo NOME da tabela PESSOAS, como podemos fazer?

Selecionamos apenas os campos que queremos: 

    mysql> select t.NOME, p.NOME from TASKS as t join PESSOAS as p on t.ID = p.ID;
    +----------------------+--------+
    | NOME                 | NOME   |
    +----------------------+--------+
    | Entidade JPA         | Arthur |
    | Dojo TDD             | Lucas  |
    | Contrato JPA         | Hellen |
    | Exposição Swap JPA   | Marcio |
    +----------------------+--------+
    4 rows in set (0.00 sec)

Certo, você atribuiu uma task pra cada pessoa usando o **join**.

E se você quiser saber todas as taks disponíveis, mesmo que não tenha mais pessoas para atribuir? (na tabela PESSOAS
desse exemplo, temos apenas 4 pessoas).

Exatamente como você pensou, vamos ver agora o **LEFT JOIN**:

    mysql> select t.NOME, p.NOME from TASKS as t left join PESSOAS as p on t.ID = p.ID;
    +------------------------+--------+
    | NOME                   | NOME   |
    +------------------------+--------+
    | Entidade JPA           | Arthur |
    | Dojo TDD               | Lucas  |
    | Contrato JPA           | Hellen |
    | Exposição Swap JPA     | Marcio |
    | Dojo join              | NULL   |
    | Dojo Atalhos Eclipse   | NULL   |
    | Refatorar entidade JPA | NULL   |
    +------------------------+--------+
    7 rows in set (0.00 sec)

Você pode observar que quando usamos o **left join**, ele retorna todos os registros da tabela da esquerda, independente
de ter registros correspondentes na tabela da direita.

Podemos usar também o **RIGTH JOIN**

Ele faz a mesma coisa, mas retornando os registros da tabela da direita. 

    mysql> select t.NOME, p.NOME from TASKS as t right join PESSOAS as p on t.ID = p.ID;
   +----------------------+--------+
   | NOME                 | NOME   |
   +----------------------+--------+
   | Entidade JPA         | Arthur |
   | Dojo TDD             | Lucas  |
   | Contrato JPA         | Hellen |
   | Exposição Swap JPA   | Marcio |
   +----------------------+--------+
   4 rows in set (0.00 sec)

Conforme o comando acima, a tabela da direita tem apenas 4 registros, por isso não retornou mais, mas se fizemos o 
contrário, selecionamos primeiro a tabela PESSOAS e juntarmos com a tabela TASKS, quando executarmos o right join vão 
aparecer todos os registros: 
 
    mysql> select p.NOME, t.NOME from PESSOAS as p right join TASKS as t on p.ID = t.ID;
    +--------+------------------------+
    | NOME   | NOME                   |
    +--------+------------------------+
    | Arthur | Entidade JPA           |
    | Lucas  | Dojo TDD               |
    | Hellen | Contrato JPA           |
    | Marcio | Exposição Swap JPA     |
    | NULL   | Dojo join              |
    | NULL   | Dojo Atalhos Eclipse   |
    | NULL   | Refatorar entidade JPA |
    +--------+------------------------+
    7 rows in set (0.00 sec)

Entre o right e o left join, o mais usado é o LEFT.

Um outro detalhe interessante é que as tabelas que usamos nesse exemplo não estão relacionadas entre si através de chave
estrangeira. 

Vamos agora colcocar em prática outro exemplo com a tabela livros: 

    mysql> create table LIVROS (
    -> ID int not null,
    -> NOME varchar(50),
    -> AUTOR varchar (50),
    -> primary key (ID)
    -> );

Depois de criada a tabela, vamos inserir os valores: 

    mysql> insert into LIVROS values (1, 'SCJP', 'Sierra e Bates');
    Query OK, 1 row affected (0.00 sec)

Verifique se os valores foram inseridos corretamente na tabela: 

    mysql> select * from LIVROS;
    +----+-----------------------+----------------+
    | ID | NOME                  | AUTOR          |
    +----+-----------------------+----------------+
    |  1 | SCJP                  | Sierra e Bates |
    |  2 | TDD                   | Kent Beck      |
    |  3 | Use a cabeça! - Java  | Sierra e Bates |
    |  4 | Refatoração           | Martin Fowler  |
    |  5 | Programming Scala     | Wanpler, Payne |
    +----+-----------------------+----------------+
    5 rows in set (0.00 sec)

Temos então a tabela PESSOAS que já foi criada anteriormente, e a nova tabela que acabamos de criar, LIVROS. 

Imagine que você é a pessoa responsável pelos livros da empresa, e foi encarregado de todo mês entregar um livro para 
cada pessoa ler, quando acabar o prazo, você precisa pegá-lo de volta, isso é uma regra a ser seguida mensalmente.

    mysql> select * from LIVROS as l join PESSOAS as p on l.ID = p.ID;
    +----+-----------------------+----------------+----+--------+
    | ID | NOME                  | AUTOR          | ID | NOME   |
    +----+-----------------------+----------------+----+--------+
    |  1 | SCJP                  | Sierra e Bates |  1 | Arthur |
    |  2 | TDD                   | Kent Beck      |  2 | Lucas  |
    |  3 | Use a cabeça! - Java  | Sierra e Bates |  3 | Hellen |
    |  4 | Refatoração           | Martin Fowler  |  4 | Marcio |
    +----+-----------------------+----------------+----+--------+
    4 rows in set (0.00 sec)

Vamos então simplificar esse relatório deixando apenas o nome do livro e o nome da pessoa.

    mysql> select l.NOME, p.NOME from LIVROS as l join PESSOAS as p on l.ID = p.ID;
    +-----------------------+--------+
    | NOME                  | NOME   |
    +-----------------------+--------+
    | SCJP                  | Arthur |
    | TDD                   | Lucas  |
    | Use a cabeça! - Java  | Hellen |
    | Refatoração           | Marcio |
    +-----------------------+--------+
    4 rows in set (0.00 sec)

Mas, e se você precisar saber quais os livros que não estão com ninguém (caso alguém termine de ler seu livro antes de
um mês)?

Podemos usar o **LEFT JOIN** com a cláusula WHERE:

    mysql> select * from LIVROS as l left join PESSOAS as p on l.ID = p.ID
    -> where p.ID is null;
    +----+-------------------+----------------+------+------+
    | ID | NOME              | AUTOR          | ID   | NOME |
    +----+-------------------+----------------+------+------+
    |  5 | Programming Scala | Wanpler, Payne | NULL | NULL |
    +----+-------------------+----------------+------+------+
    1 row in set (0.00 sec)

Caso você queira apenas o nome do livro e da pessoa, informe no select. 

    mysql> select l.NOME, p.NOME from LIVROS as l left join PESSOAS as p on l.ID = p.ID
    -> where p.ID is null;
    +-------------------+------+
    | NOME              | NOME |
    +-------------------+------+
    | Programming Scala | NULL |
    +-------------------+------+
    1 row in set (0.00 sec)

Agora então sabemos, que o livro "Programming Scala" não está emprestado com nenhum funcionário.

Como já treinamos esses exercícios, não vamos mais usar essas tabelas, portanto é necessário excluí-las.

Antes de excluirmos as tabelas vamos ver quais tabelas estão nesse database, para não esquecermos de excluir nenhuma
delas: 

    mysql> show tables;
    +----------------+
    | Tables_in_Kata |
    +----------------+
    | LIVROS         |
    | PESSOAS        |
    | TASKS          |
    +----------------+
    3 rows in set (0.00 sec) 

Agora vamos usar o comando DROP para excluir as tabelas: 

    mysql> drop table LIVROS;
    Query OK, 0 rows affected (0.00 sec)

Para agilizar, podemos também usar apenas uma vez o comando DROP: 

    mysql> drop table PESSOAS, TASKS;
    Query OK, 0 rows affected (0.00 sec)

Assim com apenas uma linha excluímos todas as tabelas de uma só vez.

Vamos então exibir todas as tabelas pra nos certificar que não falta nenhuma:

    mysql> show tables;
    Empty set (0.00 sec)

Ok, excluímos todas as tabelas. Agora precisamos excluir o banco de dados que criamos: 

    mysql> drop database Kata;
    Query OK, 0 rows affected, 2 warnings (0.02 sec)

Certo, agora então finalizamos esse exercício; 


## Referências:

Artigo escrito por Hellen Escarate


































 









