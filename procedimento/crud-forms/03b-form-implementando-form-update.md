---
layout: post-alpha
title: "Implementado Form Update: Form - parte 2"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-19"
published: true
partof: procedimento-crud-forms
num: 3
outof: 3
---

## Acesso rápido

Para acessar os tópicos do artigo siga o checklist abaixo:

<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      Implementando AlunoUpdateAction
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
      Implementando o método atualizarCom(MinhaEntidade)
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
      Implementando o método getUpdate
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
      Implementando AlunoRedirectAction
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
      Finalizando o método reply
    </td>
    <td>
      <a href="#0_4">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Declarações da classe e Construtor
    </td>
    <td>
      <a href="#0_5">help!</a>
    </td>
  </tr>
</table>

##<a id="0_0"> </a> Implementando AlunoUpdateAction

Chame o método `withUpdateAction(new AlunoUpdateAction())` logo abaixo de `newFormsFor(pojo)`
e já defina a primeira inner class:  __AlunoUpdateAction__

	return newFormsFor(pojo)

	  .withUpdateAction(new AlunoUpdateAction(existente));

Um erro de compilação surgirá, isso por que a classe ainda não existe, basta cria-lá logo abaixo do
método `reply`.

Lembrando que a mesma deve implementar a interface `Form.UpdateAction<MinhaEntidade>`.

Apenas adicione os métodos da interface na classe utilizando o atalho __CTRL+1__ do eclipse, não
se esqueça de definir uma propriedade do tipo Aluno e o construtor da classe, no final a classe terá a
seguinte implementação.

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

Declare um __NativeSqlFactory__ logo abaixo de Form no topo da classe, este objeto nos ajudará 
futuramente na realização do Update.

	private NativeSqlFactory sqlFactory;

Antes de atualizar as propriedades de Aluno no banco de dados é preciso enviar as informações para
um objeto e gravar no banco todas as propriedades do mesmo, para isso serão criados dois métodos
`atualizarCom(MinhaEntidade)` e `getUpdate()` que serão abordados no próximo tópico.

###<a id="0_1"> </a> Implementando o método atualizarCom(MinhaEntidade)

No método `execute` atualize as informações de Aluno utilizando o método `atualizarCom(MinhaEntidade pojo)` 

	  public Aluno execute(Aluno pojo) {
	    Aluno atualizado = existente.atualizarCom(pojo);
        return null;
	  }

Um erro de compilação ocorrerá indicando que o método `atualizarCom(Aluno pojo)` não existe, adicione
o método na interface Aluno e realize as devidas correções caso erros ocorram. Em seguida  
basta implementar `atualizarCom(Aluno pojo)` em AlunoJdbc.

	@Override
	  public Aluno atualizarCom(Aluno pojo) {
		this.nome = pojo.getNome();
		this.matricula = pojo.getMatricula();
		this.curso = pojo.getCurso();
		this.dataCriacao = pojo.getdataCriacao();
		
	    return this;
	}

###<a id="0_2"> </a> Implementando o método getUpdate

De volta ao form, no método `execute` de __AlunoUpdateAction__ será preciso utilizar um
NativeSqlFactory para realizar o processo de Update, por isso declare o mesmo logo abaixo de 
`BuscarAluno` e realize o processo de Update.

	@Override
	public Aluno execute(Aluno pojo) {
		Aluno atualizado = existente.atualizarCom(pojo);
		sqlFactory.update(atualizado).execute();
			
		return atualizado;
	}	

Ao chamar o método `execute` um erro de compilação será lançado indicando que Aluno não implementa
a interface __Updatable__, na interface Aluno faça o mesmo extender __Updatable__, será preciso
adicionar o método `getUpdate` em AlunoJdbc, atente a sua implementação:

    @Override
    public Update getUpdate() {
      return Update.get()

        .add("update FACULDADE.ALUNO as ALUNO")
        .add("set ALUNO.NOME = ?").param(nome)
        .add(", ALUNO.CURSO_ID = ?").param(curso.getId())
        .add(", ALUNO.MATRICULA = ?").param(matricula)
        
        .add("where ALUNO.ID = ?").param(id);
  }

Após as alterações acima, podemos seguir para o próximo Action, AlunoRedirectAction.

##<a id="0_3"> </a> Implementando AlunoRedirectAction

No método reply, logo abaixo de `.withUpdateAction`, chame o método `.withRedirectAction(new AlunoRedirectAction())`
	
	return newFormsFor(pojo)

	  .withUpdateAction(new AlunoUpdateAction(existente));

	  .withRedirectAction(new AlunoRedirectAction());

Corrija o erro de compilação criando a classe logo abaixo de __AlunoUpdateAction__.

Para realizar esta implementação precisamos de um objeto do tipo __Bricks__ que será responsável em
capturar toda a URL base do sistema até o recurso que que deve ser redirecionado. Por isso defina
logo acima da declaração de __Forms__ a propriedade Bricks - __NomeDoProjetoBricks__, seu nome varia
de acordo com o projeto para facilitar sua declaração.

	private FaculdadeBricks bricks;

Após definir o bloco acima, implemente o RedirectAction:

	private class AlunoRedirectAction implements Form.Redirect<Aluno> {
		@Override 
		public String getUrl(Aluno pojo) {
			String baseUrl = bricks.getBaseUrl();
			Curso curso = pojo.getCurso();
			return String.format("%s/faculdade/curso/%s/aluno/%d", baseUrl, curso.getCodigo(), pojo.getId());
		}
	}

##<a id="0_4"> </a> Finalizando o método reply

Após finalizar o último Action, basta adicionar o método `update` e finalizá-lo, atente a versão
final do método reply:

	public Reply<?>reply(RequestWrapper wrapper, Aluno existente){
	  Aluno pojo = new Construtor(wrapper, existente).novaInstancia();
	
	  return newFormsFor(pojo)

	    .withUpdateAction(new AlunoUpdateAction(existente))

	    .withRedirectAction(new AlunoRedirectAction())
	    
	    .update();
	}

##<a id="0_5"> </a> Declarações da classe e Construtor

O form está praticamente pronto basta definir o constutor da classe e anotá-lo com ` @Inject`, 
atente as declarações das propriedades e ao construtor da classe:

	private final FaculdadeBricks bricks;
	private final Forms forms;
	
	private final BuscarAluno buscarAluno;
	private final NativeSqlFactory sqlFactory;

	@Inject
	public FormDeAlunoCreate(FaculdadeBricks bricks, Forms forms, BuscarAluno buscarAluno,
	  NativeSqlFactory sqlFactory) {
		this.bricks = bricks;
		this.forms = forms;
		this.buscarAluno = buscarAluno;
		this.sqlFactory = sqlFactory; 

<div class="alert alert-warning">
	Não se esqueça de adicionar a anotação @Inject no construtor da classe para evitar exceções como  
	<a href="http://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	NullPointerExceptions</a>
</div>