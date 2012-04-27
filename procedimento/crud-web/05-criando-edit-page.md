---
layout: post-alpha
title: "Habilitando páginas de edição na web"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-27"
published: true 
partof: procedimento-crud-web
num: 5
outof: 5
---

## Introdução

Em aplicações que trabalham com banco de dados é muito comum a realização de rotinas do tipo update 
pois as informações tendem a mudar com o passar do tempo, para realização deste tipo de operação
existem componentes gráficos como formulários, que são responsáveis em armazenar informações que
futuramente serão enviadas ao banco de dados onde será realizado o processo de update.

## Edit Page ?

Este artigo tem como objetivo a implementação de páginas de edição, este tipo de página é bem
parecido com uma página de criação, a única diferença entre ambos é a operação
<a href="http://pt.wikipedia.org/wiki/CRUD">CRUD</a> que é realizada e a form como a página de
edição é acessada em relação à página de criação.

##Criando a classe java que representa a página html

Primeiramente crie a classe __CursoEditPage__ no pacote `br.com.projeto.ui.page`, o mesmo pacote
onde foram definidas as outras pages, certifique-se de que sua classe extenda a página de detalhes
da entidade na qual será realizada o update, em nosso caso Curso.

	public class CursoEditPage extends CursoDetailsPage {
	}

Como estamos estendendo a classe CursoDetailsPage não é necessário a criação do método `get` pois
o mesmo já foi implementado na superclasse.