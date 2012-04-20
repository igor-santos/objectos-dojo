---
layout: post-alpha
title: "Implementando Serviços"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-19"
published: true
partof: procedimento-crud-forms
num: 5
outof: 5
---

##Introdução
Após criar o teste de serviço da faculdade, implementaremos as classes e o arquivo html que realizam 
o serviço definido no teste.

## Acesso rápido
Para acessar os tópicos siga o checklist abaixo:
<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      Criando a classe de serviço e o método get
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
	Criando a classe Tabela
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
     A
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
      A
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
      A
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
      A
    </td>
    <td>
      <a href="#0_5">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      A
    </td>
    <td>
      <a href="#0_6">help!</a>
    </td>
  </tr>
</table>

## <a id="0_0"> </a>Criando a classe de serviço e o método get
A princípio, devemos indicar que esta classe será um serviço.<br>
Adicione a _annotation_ `@Service` na classe `ServicoDeAluno`.

	@Service
	public class ServicoDeAluno {
	}

Criaremos o método _get_ semelhante aos métodos _post_ e _put_ dos artigos [Implementando Form Create]()
e [Implementando Form Update]() respectivamente. Este método também conterá a [consultaDTO](). 

    @Get
    public Reply<?> get(Request request, @Named("curso") String _curso) {
      FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);
  
      List<ConsultaDeAlunoDTO> dtos = consulta.list(wrapper, _curso);
      TabelaDeAluno tabela = new TabelaDeAluno(dtos);

      return Reply.with(tabela).template(tabela.getClass());
    }
    
Veja como criar a consulta no artigo [Implementando Consultas](http://dojo.objectos.com.br/procedimento/crud-entidade/02.1-implementando-consultas-consultas.html).

Utilize o atalho `Ctrl + 1` para criar a variável de instância de `ConsultaDeAluno` e o atalho
`Alt + S` e depois a tecla `A` para criar o construtor da classe

    private final ConsultaDeAluno consulta;

    public ServicoDeAluno(ConsultaDeAluno consulta) {
      this.consulta = consulta;
    }

Utilize o atalho `Ctrl + 1` para criar a classe `TabelaDeAluno` no pacote `ui.bricks` do projeto onde
será responsável por "enviar" os dados a página `TabelaDeAluno.html` que criaremos futuramente. 

## <a id="0_1"> </a>Criando a classe Tabela
Esquecendo por um momento a classe de serviço, vamos definir os atributos e métodos da classe `TabelaDeAluno`.<br>
Com a classe criada, acrescente a _annotation_ `@Show` onde definimos a qual página _html_ os dados
serão listados. Por padrão, utilizamos o mesmo nome da classe à página, seguido da extensão `.html`

	@Show("TabelaDeAluno.html")
	public class TabelaDeAluno {
	}
	
Como passamos ao construtor da classe de serviço um objeto do tipo _List_, precisamos criar um construtor
e uma variável nesta classe. Utilize novamente o atalho `Alt + S` e depois a tecla `A` para criar o 
construtor a partir da variável.

	@Show("TabelaDeAluno.html")
	public class TabelaDeAluno {
	
	  private final List<ConsultaDeAlunoDTO> dtos;
	
	  public TabelaDeAluno(List<ConsultaDeAlunoDTO> dtos) {
	    this.dtos = dtos;
	  }
	
	}
	
Criaremos um _getter_ para este atributo que será utilizado/definido em `TabelaDeAluno.html`

__Importante: Devemos utilizar o conceito de JavaBeans nesta classe, isto é, a questão
dos nomes de getters e setters devem seguir a padronização do JavaBeans. Leia mais sobre este assunto
[aqui](http://en.wikipedia.org/wiki/JavaBeans)__.		