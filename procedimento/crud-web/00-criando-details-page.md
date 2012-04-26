---
layout: post-alpha
title: "Habilitando páginas de detalhe na web"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-03-09"
published: true 
partof: procedimento-crud-web
num: 1
outof: 1
---

#Introdução

Ao trabalhar com aplicações web é muito comum a implementação de views, componentes gráficos que
são responsáveis em dar um feedback ao usuário, as views são páginas html que contém listagens ou
algum tipo de informação que vem do banco de dados tome como exemplo de view as páginas de detalhes 
no artigo de implementação de pages.

##Details Page?

Neste artigo será tratado a implementação de uma página de detalhes de uma entidade, páginas de
detalhes contém informações mais específicas de uma entidade, tudo aquilo que não é necessaŕio ser 
exibido em uma página alimentada por um serviço, é possível adicionar listagens de outras entidades
em uma página de detalhes, mas este não é o objetivo deste artigo.

##Criando a classe que representa a página

Assim como na criação de páginas de listagem, é preciso criar uma classe java que represente a
página de detalhes a ser implementada posteriormente, no exemplo abaixo será implementada uma página
que todas as informações de um curso de uma faculdade. Mãos à obra!

Primeiramente crie a classe que representa a página de detalhes no pacote `br.com.faculdade.ui.page`
e dê o nome de CursoDetailsPage.

<div class="alert alert info">
	Para cada details page criado lembre-se que seu nome deve conter o nome da entidade seguido de
	details page, da mesma forma como fora feito acima em Curso, CursoDetailsPage
</div>

Adicione a anotação `@Decorated` na classe para ligá-la ao arquivo html que será responsável em 
realizar a exibição dos dados da entidade, não se esqueça de estender a classe __BasePage__.

	@Decorated
	public class CursoDetailsPage extends BasePage {
	}

Para que seja possível listar os detalhes de um curso em específico é preciso buscar este curso no
banco de dados através de algum parâmetro que vem da url, por conta disso utilizaremos um buscador
de curso.

	private BuscarCurso buscarCurso;

Declare também uma entidade do tipo Curso, pois a página html irá acessar e exibir todas as
propriedades desta entidade.

	private Curso curso;