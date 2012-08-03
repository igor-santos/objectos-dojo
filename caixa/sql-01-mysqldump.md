---
layout: post
title: "Backup de Banco de Dados - mysqldump"
author: "Afonso Jardim Filgueiras"
user: "afilgueiras"
published: true
partof: sql
num: 1
outof: 1 
---
## Intodução
Quando trabalhamos com dados, a probabilidade de alterarmos um dado e/ou deletarmos algum dado incorreto
é muito grande. Por isso, quando lidamos com informação, uma das boas práticas seria realizar um _backup_
destas informações para que futuramente, se um destes incidentes ocorrerem, as informações possam ser restauradas.

## Utilizando o mysqldump

Para fazer _backup_ de um determinado banco de dados (utilizando o mysqldump) siga o procedimento a seguir:

	$ mysqldump -uUSUARIO -pSENHA NOME_BANCO_DE_DADOS > /tmp/nomedoarquivo.sql

E para restaurar os dados criados anteriormente:

	$ mysql -uUSUARIO -pSENHA NOME_BANCO_DE_DADOS < /tmp/nomedoarquivo.sql

Lembrando que você deve alterar o nome de usuário, senha e banco de dados

* USUARIO : o usuário correto no banco de dados
* SENHA : a senha no MySQL para o usuário
* NOME_BANCO_DE_DADOS : o banco de dados que será feito o _backup_. 

