---
layout: post-alpha
title: "Classes Vazias em Forms"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-04-12
published: true 
partof: faq-crud-forms
num: 3
outof: 3
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

Nesse tipo de situação o ideal seria implementar um classe __Vazia__. A classe Vazia consiste em uma
implementação da interface Construtor da entidade do __getter__ acima na classe Construtor, lembrando
que todos os seus métodos lançam exceções do tipo `UnsupportedOperationException`, já que ninguém
deve instanciar este tipo de classe, seus métodos devem apenas ser sobrescritos.

## Implementando classe Vazia 

Caso não exista uma classe Vazia para a entidade na qual esteja trabalhando basta criar uma, o 
procedimento é bem simples. Crie-a no diretório `src/main/java` dentro do pacote em que se encontram
as entidades do projeto, por padrão seu nome será __EntidadeVazio__, onde entidade seria a classe a 
ser implementada, atente a amostra de código:

	public class CursoVazio implements Curso.Construtor {

		public int getId() {
			throw new UnsupportedOperationException();
		}

	}

Lance uma UnsupportedOperationException para cada método da classe e salve as alterações.

## Utilizando a classe no Form

Para utilizar a classe Vazia no form e resolver o problema de getCurso basta alterar o retorno do
método getCurso para new CursoVazio e em seguida sobrescrever o método getId() de Curso, atente ao
código abaixo:

	public class Construtor implements Aluno.Construtor {
	
		public Curso getCurso() {
			return new Curso Vazio {
				@Override
				int getId() {
					return curso;
				};
			}
		}
	
	}

Como o método retorna uma instância de Curso, podemos sobrescrever através da classe Vazia o único
método necessário para cadastrar Curso, getId() e fornecer o valor da URL referente a Curso que o
form captura. Lembrando que para isso é necessário passar o valor da URL como parâmetro do 
construtor da classe Construtor.

		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request); 
		Aluno pojo = new Conatrutor(wrapper, _curso).novaInstancia();		