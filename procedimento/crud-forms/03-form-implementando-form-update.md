---
layout: post-alpha
title: "Implementado Form Update: Form"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-13"
published: true
partof: procedimento-crud-forms
num: 1
outof: 1
---

##Introdução 

Após realizar o teste de seu form update, podemos partir para sua implementação e verificar se o form
de fato realiza com sucesso a atualização dos dados de uma entidade no banco de dados. Este artigo 
será muito parecido com a artigo de implementação de forms 
<a href="{{site.baseurl}}/procedimento/crud-forms/01-form-implementando-form.html">create</a>.

Novamente lembrando que, antes de iniciar a leitura deste artigo é de suma importância que o teste 
esteja implementado de form correta.

## Iniciando no método put

Ao realizar a implementação do form sempre devemos começar pelo método que trata a solicitação 
realizada no teste para o form. Se você esta seguindo este artigo desde a implementação do teste
no último artigo, provalvemente deve ter criado a classe do form e o método `put` que trata solicitações 
do tipo __Update__, este método estará bem parecido com este: 

	@Put
	public Reply<?> put(Request request, @Named("curso") String _curso, 
		@Named("aluno") int _aluno) {
		
		return Reply.saying().ok();
	}

Repare que o método put é composto por um número maior de parâmetros, diferente do método post que 
possuía apenas dois parâmetros. Aqui temos um adicional que é o responsável em dizer ao form qual 
Aluno deve ser atualizado.

## Alterando o método put

Altere o retorno do método para `Reply.saying().notFound()` e extraía o retorno deste método em uma 
variável chamada `reply`, defina esta propriedade como retorno do método.

	@Put
	public Reply<?> put(Request request, @Named("curso") String _curso, 
		@Named("aluno") int _aluno) {
		Reply<?> reply = Reply.saying().notFound();
		
		return reply;
	}

Em seguida será necessário definir um buscador de Aluno, que será responsável em capturar no banco de 
dados o Aluno que terá seus dados atualizados, para realizar tal tarefa o método put possui um novo 
parâmetro que é uma propriedade única de Aluno. Procure o Aluno e verifique se o mesmo existe para 
que o form não tente atualizar algo inexistente no banco de dados.

	@Put
	public Reply<?> put(Request request, @Named("curso") String _curso, 
		@Named("aluno") int _aluno) {
		Reply<?> reply = Reply.saying().notFound();
	
		Aluno existente = buscarAluno.porId(_aluno);
		
		return reply;
	} 