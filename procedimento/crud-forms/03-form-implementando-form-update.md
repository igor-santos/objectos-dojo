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

##Iniciando no método put

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

##Alterando o método put

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
parâmetro que é uma propriedade única de Aluno. Verifique se o Aluno existe para que o form não tente
atualizar algo inexistente no banco de dados.

	@Put
	public Reply<?> put(Request request, @Named("curso") String _curso, 
		@Named("aluno") int _aluno) {
		Reply<?> reply = Reply.saying().notFound();
	
		Aluno existente = buscarAluno.porId(_aluno);
		if(existente != null) {
			
		}
		
		return reply;
	} 

Se o Aluno existir será realizado um processo de atualização do mesmo, caso contrário o form irá 
redirecionar o usuário para uma página não existente. O processo de atualização é de responsabilidade 
de um método chamado `reply()`, aproveite para converter o objeto Request em um RequestWrapper,
utilizando a classe __NomeDoProjetoRequestWrapper__, pois será necessário criar uma classe chamada 
Construtor futuramente, envie os objetos RequestWrapper e Aluno para o método reply().

Não se esqueça de fazer com que o método Reply seja atribuído a propriedade reply definida no início
do método. 

	Aluno existente = buscarAluno.porId(_aluno);
	if(existente != null) {
		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);
		reply = reply(wrapprer, existente);
	}

##Criando o método reply

Como o método `reply` não foi criado ainda, um erro de compilação será lançado. Crie-o logo abaixo do
método put e deixe o retorno definido como null.

	public Reply<?>reply(RequestWrapper wrapper, Aluno existente){
		return null;
	}

Será preciso criar um objeto do tipo Aluno e eniviá-lo ao banco de dados para que seja realizado um 
update, para isso será necessário a utilização da inner class Construtor que será responsável em 
criar um objeto do tipo Aluno dado um RequestWrapper e uma ou mais entidades.

###Implementando a inner class Construtor

Lembrando que todos os parâmetros que foram fornecidos na QueryString do teste estão contidos no 
objeto Request que foi convertido em um RequestWrapper, a seguir será implementada uma classe auxiliar
chamada Construtor, com ela é possível criar o novo objeto Aluno que será enviado ao banco de dados
para a realização do Update. Atente à sua implementação.

	private class Construtor implements Aluno.Construtor {
		
		private final RequestWrapper wrapper;
		
		private final Aluno existente;
		
		public Construtor(RequestWrapper wrapper, Aluno existente) {
			this.wrapper = wrapper;
			this.curso = curso;
		}
		
		@Override
		public Aluno novaInstancia() {
			return new AlunoJdbc(this);
		}
		
		@Override
		public Curso getCurso() {
			return existente.getCurso();
		}
		
		@Override
		public String getNome() {
			return wrapper.param("nome");
		}
		
		@Override
		public String getMatricula() {
			return wrapper.param("matricula");
		}
		
		@Override
		public DateTime getDataDeCriacao() {
			return new DateTime();
		}
		
	}

A novidade aqui é que não é preciso buscar um Curso ou utilizar uma classe Vazia como nos forms create,
pois Curso já está contido no objeto Aluno que foi capturado do banco através do buscador, por isso
que Aluno é enviado ao construtor, assim em `getCurso()` basta retornar o curso do Aluno que foi 
enviado como parâmetro ao construtor da classe.

##De volta ao método reply

Após definir a inner class Construtor, é preciso criar o novo objeto Aluno que será enviado ao banco
de dados, para isso basta instanciar a classe Construtor e chamar `novaInstancia()` para retornar um
Aluno.

	public Reply<?>reply(RequestWrapper wrapper, Aluno existente){
		Aluno pojo = new Construtor(wrapper, existente).novaInstancia();
	
		return null;
	}

Agora é preciso atualizar nosso objeto no banco de dados, para isso utilizaremos uma implementação da
interface Forms, por isso, declare uma propriedade deste tipo no início da classe:

	private Forms forms;

Após declarar Forms, altere o retorno do método e chame o método `newFormsFor(pojo)` de forms e passe para 
o mesmo o <a href="http://pt.wikipedia.org/wiki/Plain_Old_Java_Objects">pojo</a> que foi gerado pela 
inner class criada anteriormente.

	Aluno pojo = new Construtor(wrapper, existente).novaInstancia();
		
	return newFormsFor(pojo);
	
Em seguida serão criadas outras duas inner classes responsáveis em efetuar a alteração da entidade no banco de
dados e o redireciomento para a página de detalhes da entidade após o update da mesma.

##Criando Actions

Chame o método `withUpdateAction(new AlunoUpdateAction())` logo abaixo de `newFormsFor(pojo)`
e já defina a primeira inner class:  __AlunoUpdateAction__

	return newFormsFor(pojo)
	
		.withUpdateAction(new AlunoUpdateAction(existente));

Um erro de compilação irá aparecer, isso por que a classe ainda não existe, basta cria-lá logo abaixo do método
`reply`, lembrando que a mesma deve implementar a interface `Form.UpdateAction<Entidade>`, deve-se especificar
no lugar de "Entidade" o tipo de entidade a ser atualizada no banco, no nosso caso Aluno.

	private class AlunoUpdateAction implements Form.UpdateAction<Aluno> {

		private final Aluno existente;

		public AlunoUpdateAction(Aluno existente) {
		  this.existente = existente;
		}
	
		@Override
		public Aluno execute(Aluno pojo) {
		  return null;
		}	
	}

		@Override
		public Aluno execute(Aluno pojo) {
		  return null;
		}

	}

Assim como no processo de gravação será preciso um objeto do tipo NativeSqlFactory para nos auxiliar
no processo de update, declare o mesmo logo abaixo da propriedade Form no início da classe.

Antes de atualizar Aluno no banco de dados é preciso atualizar as informações do objeto Aluno que foi 
capturado do banco a partir do buscador com as informações do objeto Aluno que foi criado a partir da
inner class Construtor que contém as informações atualizadas de Aluno, para isso é preciso implementar 
o método `atualizarCom(Aluno pojo)` na interface Aluno, atente a implementação do método em AlunoJdbc.

	@Override
	public CadastroDeCedulaDeCreditoBancario atualizarCom(CadastroDeCedulaDeCreditoBancario pojo) {
	  this.completo = pojo.isCompleto();
	  this.coobrigacao = pojo.getCoobrigacao();

	    return this;
	}

Corrija os erros de compilação que aparecerem por conta das novas atualizações. Voltando ao form
agora é possível realizar a sincronização entre os objetos em `AlunoUpdateAction`, atente a código
abaixo.

		@Override
		public Aluno execute(Aluno pojo) {
          Aluno atualizado = existente.atualizarCom(pojo);
          sqlFactory.update(atualizado).execute();
          return atualizado;
		}	
	}
