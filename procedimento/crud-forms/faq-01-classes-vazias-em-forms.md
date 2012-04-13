---
layout: post-alpha
title: "Classes Vazias em Forms"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-04-12
published: true 
partof: faq-crud-forms
num: 1
outof: 1
---

## Utilizando classes vazias na implementação dos forms

Para o este FAQ, considere o seguinte bloco de código:

	@Post
	public Reply(Request request, @Named("curso") int _curso) {
		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request); 
		
		return null;
	}

## Introdução

É muito comum ao implementar um form create existirem entidades que se relacionam, Faculdade tem Curso
e Aluno tem Curso, por exemplo. Ou seja para cadastrar um Aluno é preciso saber em qual Curso o mesmo
se encontra, o form teria uma URL bem parecida com a seguinte:

	faculdade/curso/10/aluno

## Na classe Construtor

Ao implementar a classe Construtror provavelmente existira um método que retorne um Curso, porém o 
form não captura nenhum Curso, apenas um valor que representa sua chave primária no banco.

	public class Construtor implements Aluno.Construtor {
	
		public Curso getCurso() {
			return null;
		}
	
	}

Nesse tipo de situação o ideal seria implementar um classe __Vazia__. A classe Vazia consisite em uma
implementação da interface Construtor da entidade que será gravada no banco de dados, porém todos
os seus métodos lançam exceções do tipo `UnsupportedOperationException`, já que ninguém deve instanciar 
este tipo de classe, seu métodos devem apenas ser sobrescritos.

## Implementando classe Vazia 

Caso não exista uma classe Vazia para a entidade na qual esteja trabalhando basta criar uma, o 
procedimento é bem 