---
layout: post-alpha
title: "Implementando Serviços"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-19"
published: true
partof: procedimento-crud-web
num: 5
outof: 5
---

## <a id="TOPO"> </a>Introdução
Após criar o teste de serviço da faculdade onde queremos listar o nome e a matricula de todos os 
alunos de um determinado curiso, implementaremos as classes e o arquivo html que realizam 
o serviço definido no teste.

## Acesso rápido
Para acessar os tópicos siga o checklist abaixo:
<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_0">Criando a classe de serviço e o método get</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
	<a href="#0_1">Criando a classe Tabela</a>
    </td>
  </tr>
    <tr>
    <td class="tac col2em">
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
	<a href="#0_2">Criando a página web </a>
    </td>
  </tr>
</table>

## <a id="0_0"> </a>Criando a classe de serviço e o método get
A princípio, devemos indicar que esta classe será um serviço.<br>
Adicione a _annotation_ `@Service` na classe `ServicoDeAluno`.

	@Service
	public class ServicoDeAluno {
	}

Criaremos o método _get_ semelhante aos métodos _post_ e _put_ dos artigos [Implementando Form Create](http://dojo.objectos.com.br/procedimento/crud-forms/01-form-implementando-form.html)
e [Implementando Form Update](http://dojo.objectos.com.br/procedimento/crud-forms/03a-form-implementando-form-update.html) 
respectivamente. Este método também conterá a [consulta](http://dojo.objectos.com.br/procedimento/crud-entidade/02.1-implementando-consultas-consultas.html). 

    @Get
    public Reply<?> get(Request request, @Named("curso") String _curso) {
      FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);
  
      List<ConsultaDeAlunoDTO> dtos = consulta.list(wrapper, _curso);
      TabelaDeAluno tabela = new TabelaDeAluno(dtos);

      return Reply.with(tabela).template(tabela.getClass());
    }
    
Nota: NÃO esqueça de adicionar a _annotation_ `@Get`    

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

    public List<ConsultaDeAlunoDTO> getDtos() {
      return dtos;
    }
    
Para criar este _getter_ rapidamente utilize o atalho `Alt + S` e depois a tecla `R`.

Temos agora as duas classes finalizadas, `ServicoDeAluno` e `TabelaDeAluno`.

## <a id="0_2"> </a>Criando a página web
Como comentamos, os dados deveram aparecer em uma página para que possam ser visualizados. Tal página
será criada com o nome `TabelaDeAluno.html`

	<!doctype html>
	<html>
	<body>
	  @ShowIf(dtos.isEmpty())
	  <p>Nenhum resultado encontrado.</p>
	
	  @ShowIf(!dtos.isEmpty())
	  <table>
	    <thead>
	      <tr>
	        <th>Nome</th>
	        <th>Matricula</th>
	      </tr>
	    </thead>
	    <tbody>
	      @Repeat(items=dtos, var="dto")
	      <tr>
	        <td>${dto.nome}</td>
	        <td>${dto.matricula}</td>
	      </tr>
	    </tbody>
	  </table>
	</body>
	</html>
	
Com este código fica mais claro o que haviamos comentado em relação aos _getters_. Note que a classe
`TabelaDeAluno` possui um método `getDtos()` e partir deste padrão junto as anotações `@ShowIf` `@Repeat` 
e `${objeto.atributo}` executamos os métodos _getters_ definidos nas classes.<br>
É importante ressaltar que o objeto _dto_ DEVE possuir um _getter_ para _nome_ e _matricula_

Com as anotações `@ShowIf` podemos testar algumas condições, como por exemplo, se a lista está vazia.<br>
Quanto a `@Repeat` temos um _interator_ da lista. É neste momento que os dados do aluno começam a ser
inseridos na página para visualização.	

Para mais informações acesse os códigos nos links abaixo:

[ServicoDeAluno.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/ServicoDeAluno.java)<br>
[TabelaDeAluno.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/TabelaDeAluno.java)<br>
[TabelaDeAluno.html](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/TabelaDeAluno.html)<br>

Retornar aos Procedimentos! <a href="{{ site.baseurl }}/procedimento/" class="btn btn-success">Voltar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>    	