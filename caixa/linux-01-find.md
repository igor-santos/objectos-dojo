---
layout: post-alpha
title: "Find"
author: "Marcos Piazzolla"
user: "mpiazzolla"
published: true
partof: linux
num: 1
outof: 1
---

## Introdução

Em algumas situações podemos não contar com a ajuda de um ambiente gráfico junto de uma IDE para nos
auxiliar na busca de um arquivo fonte para uma rápida alteração, neste tipo de situação é de suma 
importância saber como utilizar ferramentas unix como _find_.

## Procurando arquivos

Imagine que é preciso realizar uma pequena correção em um arquivo html chamado __MyPage.html__ que se
encontra em um servidor linux do qual temos apenas acesso ao terminal e nada mais, sabemos que o
mesmo se encontra no diretório `/usr/local/www` diretório padrão para aplicações em um servidor
[Apache] (http://pt.wikipedia.org/wiki/Servidor_Apache).Imagine o tempo que seria gasto para
encontrar este arquivo! O que fazer ?

## Utilizando o find

Uma maneira bem rápida de se buscar arquivos via linha de comando seria utilizando o comando _find_
que busca um determinado arquivo dada uma série de parâmetros, atente a sintaxe do comando:

    $find /diretorio/do/arquivo -name "nome_do_arquivo"

Após a execução da linha acima o terminal informaria na próxima linha o caminho completo do arquivo,
caso o mesmo realmente esteja contido no diretório especificado.

Em nosso exemplo nossa busca ao arquivo html seria análoga a

	$find /usr/local/www/ -name "MyPage.html"

Abaixo teríamos o caminho completo do arquivo desejado, lembrando que o find pode retornar mais
de uma localização, pois pode existir mais de um arquivo chamado __MyPage.html__, atente a saída 
do terminal.

	/usr/local/www/httdocs/MyPage.html
	/usr/local/www/httdocs/objectos/MyPage.html
	/usr/local/www/httdocs/objectos/dojo/MyPage.html
	/usr/local/www/httdocs/objectos/dojo/web/MyPage.html
	
Em seguida basta editar o arquivo desejado utilizando o _vi_.

	$vi /usr/local/www/httdocs/MyPage.html