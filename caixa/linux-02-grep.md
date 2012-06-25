---
layout: post-alpha
title: "Grep"
author: "Marcos Piazzolla"
user: "mpiazzolla"
published: true
partof: linux
num: 2
outof: 2
---

## Introdução

### O que é grep ?

Grep é uma ferramenta Unix que busca palavras em um arquivo dado uma série de parâmetros, através
do grep é possível buscar linhas ou até mesmo blocos contendo a(s) palavra(s) especificada(s).

### Antes de começar

Crie o arquivo lista.txt que contém informações necessárias para a realização dos exemplos deste
artigo, para isso utilize o vi.

	Marco Polo
	Marco Polo
	Tiago Avilla
	Lucas Andrei
	Lucas Andrei
	Lucas Andre
	Lucas Andre
	Willian Guimarães
	Josefino Siqueira
	Josefino Siqueira
	Antonio Carlos 
	Antonio Carlos
	Roberto Dias
	Willian Guimarães
	Marco Polo

### Realizando buscas com grep

Como fora dito anteriormente é possível buscar linhas ou até blocos utilizando o grep, atente a
sintaxe do comando.

	$grep "palavra_a_ser_procurada" nome_do_arquivo 

Vamos realizar uma busca pelo arquivo lista.txt onde apenas as linhas contendo "Josefino Siqueira"
nos interessam.

	$grep "Josefino Siqueira" lista.txt
	
	Josefino Siqueira
	Josefino Siqueira
	
## Filtrando suas buscas com o grep

Algumas vezes ao procurar uma palavra em um arquivo é possível que o grep acabe retornando linhas
que contém parte da informação especificada no comando, resultando em uma busca ruim, atente
a saída abaixo.

	$grep "Lucas Andre" lista.txt
	
	Lucas Andrei
	Lucas Andrei
	Lucas Andre

Para evitar este tipo de resultado utilize o parâmetro `-w` que retorna todas as linhas que batem
com o parâmetro fornecido na busca.
	
	$grep -w "Lucas Andre" lista.txt
	
	Lucas Andre
	Lucas Andre

## Redirecionando a saída de sua busca

Quando utilizamos o grep é possível redirecionar a saída de sua busca em um novo arquivo, para isso
utilize o operador `>`.

	grep "Lucas Andre" lista.txt > exit.txt
	
Com isso um novo arquivo será criado contendo o resultado da busca realizada pelo grep.

	cat exit.txt
	
	Lucas Andre
	Lucas Andre