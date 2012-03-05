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

## mysqldump

Para fazer backup:

	$ mysqldump -uUSUARIO -pSENHA NOME_BANCO_DE_DADOS > /tmp/nomedoarquivo.sql

Restaurando os dados criados anteriormente

	$ mysql -uUSUARIO -pSENHA NOME_BANCO_DE_DADOS < /tmp/nomedoarquivo.sql

Obs: obviamente substitua os dados por:

* USUARIO : o usuário correto no banco de dados
* SENHA : a senha no MySQL para o usuário
* NOME_BANCO_DE_DADOS : o banco de dados para o qual o backup será feito 

