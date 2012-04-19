---
layout: post-alpha
title: "Implementado Form Update: Form - parte 1"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-13"
published: true
partof: procedimento-crud-forms
num: 2
---

##Introdução 
 
Após realizar o teste de seu form update, podemos partir para sua implementação e verificar se o form
de fato realiza com sucesso a atualização dos dados de uma entidade no banco de dados. Este artigo 
será muito parecido com a artigo de implementação de forms 
<a href="{{site.baseurl}}/procedimento/crud-forms/01-form-implementando-form.html">create</a>.

Novamente lembrando que, antes de iniciar a leitura deste artigo é de suma importância que o teste 
esteja implementado de form correta.

## Acesso rápido

Para acessar os tópicos do artigo siga o checklist abaixo:

<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      Iniciando no método put
    </td>
    <td>
      <a href="#0_0">help!</a>
    </td>    
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Alterando o método put
    </td>
    <td>
      <a href="#0_1">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Implementando o método reply
    </td>
    <td>
      <a href="#0_2">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Implementando a inner class Construtor
    </td>
    <td>
      <a href="#0_3">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      De volta ao método reply
    </td>
    <td>
      <a href="#0_4">help!</a>
    </td>
  </tr>
</table>


##<a id="0_0"> </a> Iniciando no método put

Ao realizar a implementação do form sempre devemos começar pelo método que trata a solicitação 
realizada no teste do form. Se você esta seguindo este artigo desde a implementação do teste
no último artigo, provalvemente deve ter criado a classe do form e o método `put` que trata
solicitações do tipo __PUT__, este método estará bem parecido com este: 

	@Put
	public Reply<?> put(Request request, @Named("curso") String _curso, 
	  @Named("aluno") int _aluno) {
		
	  return Reply.saying().ok();
	}

Repare que o método put é composto por um número maior de parâmetros, diferente do método post que 
possuía apenas dois parâmetros. Aqui temos um adicional que é o responsável em dizer ao form qual 
Aluno deve ser atualizado.

##<a id="0_1"> </a> Alterando o método put

Altere o retorno do método para `Reply.saying().notFound()` e extraía o retorno deste método em uma 
variável chamada `reply`, defina esta propriedade como retorno do método.

	@Put
	public Reply<?> put(Request request, @Named("curso") String _curso, 
	  @Named("aluno") int _aluno) {
	  Reply<?> reply = Reply.saying().notFound();
		
	  return reply;
	}

Em seguida será necessário definir um buscador de Aluno, que será responsável em buscar no banco de 
dados o Aluno que terá seus dados atualizados, para realizar tal tarefa o método put possui um novo 
parâmetro que é uma propriedade única de Aluno. Realize uma busca de Aluno através deste parâmetro e 
verifique se o mesmo é diferente de null, ou seja se ele existe no banco de dados.

	  Aluno existente = buscarAluno.porId(_aluno);
	  if(existente != null) {
			
	  }

Caso o Aluno exista será realizado o processo de __Update__, caso contrário será lançado um erro
<a href="http://pt.wikipedia.org/wiki/HTTP_404>">404</a>.

Sabendo que o Aluno existe no banco de dados, instancie um __NomeDoProjetoRequestWrapper__ e converta
o objeto __Request__ do método, em um __RequestWrapper__, em seguida atribua a propriedade `reply`
o retorno do método `reply(existente, wrapper)` que ainda não existe.

	if(existente != null) {
	  FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);
	  reply = reply(wrapprer, existente);
	}

Corrija os erros de compilação criando o método `reply(existente, wrapper)` logo abaixo de put, deixe
o que o método retorne null por hora, sua implementação será discutida no próximo tópico.

	private Reply<?> reply(Aluno existente, FaculdadeRequestWrapper wrapper ) {
	  return null;
	}


##<a id="0_2"> </a>Implementando o método reply

Com a implementação do método put finalizada deve-se implementar o método reply que será responsável
em atualizar as informações de um determinado Aluno no banco de dados.

Antes de iniciar a implementação do método reply, será preciso criar a classe auxiliar __Construtor__,
que cria um objeto Aluno a partir das informações do RequestWrapper.

###<a id="0_3"> </a>Implementando a inner class Construtor

Como todos os parâmetros fornecidos no QueryString do teste estão contidos no RequestWrapper, a classe 
Construtor precisa deste objeto como parâmetro além de um objeto Aluno, pois existem informações de 
Aluno que não devem ser atualizadas como __Data de criação__, por exemplo.

Atente a implementação da classe Construtor abaixo:

	private class Construtor implements Aluno.Construtor {
		
	private final Aluno existente;
	
	private final RequestWrapper wrapper;
		
	  public Construtor(Aluno existente, RequestWrapper wrapper) {
	    this.curso = curso;
	    this.wrapper = wrapper;
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

Para cada propriedade de Aluno que não deve ser atualizada basta retornar a propriedade atual do 
Aluno que está sendo enviado no construtor da classe no método em questão, por exemplo, se __Curso__
não for atualizado basta retornar:

	  @Override
	  public Curso getCurso() {
	    return existente.getCurso();
	  }

<div class="alert alert-warning">
	Dica: Não se esqueça de que 'existente' possui as informações do Aluno com as informações 
	desatualizadas no banco.
</div>

###<a id="0_4"> </a> De volta ao método reply

Após definir a inner class Construtor, é preciso criar o novo objeto Aluno que será enviado ao banco
de dados, para isso basta instanciar a classe Construtor e chamar `novaInstancia()` para retornar um
Aluno.

	public Reply<?>reply(RequestWrapper wrapper, Aluno existente){
	  Aluno pojo = new Construtor(wrapper, existente).novaInstancia();
	
	  return null;
	}

Agora é preciso atualizar Aluno no banco de dados, para isso utilizaremos uma implementação da
interface Forms, por isso, declare uma propriedade deste tipo no início da classe:

	private Forms forms;

Após declarar Forms, altere o retorno do método e chame o método `newFormsFor(pojo)` de forms e passe para 
o mesmo o <a href="http://pt.wikipedia.org/wiki/Plain_Old_Java_Objects">pojo</a> que foi gerado pela 
inner class criada anteriormente.

	Aluno pojo = new Construtor(wrapper, existente).novaInstancia();
	
	return newFormsFor(pojo);
	
Em seguida serão criadas outras duas inner classes responsáveis em efetuar a alteração da entidade no banco de
dados e o redireciomento para a página de detalhes da entidade após o update da mesma.


<p><b>Continuar com os Actions ? </b><a href="{{ site.baseurl }}/procedimento/crud-forms/03b-form-implementando-form-update.html" 
class="btn btn-warning">Parte 2</a></p>.
