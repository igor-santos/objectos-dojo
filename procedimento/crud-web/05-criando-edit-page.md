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

##<a id="topo"> </a> Introdução

Em aplicações que utilizam banco de dados é muito comum a realização de rotinas do tipo update,
pois as informações tendem a mudar com o passar do tempo, para a realização deste tipo de operação
existem componentes gráficos como formulários, responsáveis em armazenar informações que futuramente
serão enviadas ao banco de dados onde será realizado o processo de update

## Acesso rápido

Para acessar os tópicos do artigo siga o checklist abaixo:

<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      Edit Page ?
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
      Criando a classe java que representa a página html
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
      Implementando o método getFormAction
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
      Implementando o método getMetaPage
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
      Implementando a classe EditPageMeta
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
      Habilitando a página na web
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
      Crie a classe que corresponde ao form
    </td>
    <td>
      <a href="#0_6">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Crie o formulário html
    </td>
    <td>
      <a href="#0_7">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Crie o html do edit page
    </td>
    <td>
      <a href="#0_8">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Alterando o módulo
    </td>
    <td>
      <a href="#0_9">help!</a>
    </td>
  </tr>
</table>

## <a id="0_0"> </a> Edit Page ?

Este artigo tem como objetivo a implementação de páginas de edição, este tipo de página é bem
parecido com uma página de criação, a única diferença entre ambos é a operação
<a href="http://pt.wikipedia.org/wiki/CRUD">CRUD</a> que é realizada e a forma como a página de
edição é acessada em relação à página de criação.

## <a id="0_1"> </a> Criando a classe java que representa a página html

Crie a classe __CursoEditPage__ no pacote `br.com.faculdade.ui.page`, e certifique-se de que a mesma
extenda a página de detalhes da entidade na qual será realizada o update, em nosso caso Curso.

Corrija os erros de compilação que aparecerem adicionando o construtor da classe, não se esqueça de
anotar o construtor da classe com `@Inject`, caso contrário será lançada uma 
<a href="http://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">NullPointerException</a>
quando a página for chamada.

	public class CursoEditPage extends CursoDetailsPage {
	
	  @Inject
	  public CursoEditPage(FaculdadeBricks bricks, BuscarCurso buscarCurso) {
		super(bricks, buscarCurso);
	  }
	
	}

<div class="alert alert info">
	Ao implementar um Edit page não é preciso implementar o método get pois o mesmo já foi
	implementado na superclasse, lembrando que o propósito deste método é popular uma entidade do
	tipo curso que será utilizada em CursoEditPageMeta.
</div>

### <a id="0_2"> </a> Implementando o método getFormAction

Ainda em CursoEditPage é preciso implementar o método getFormAction que é responsável em definir a
url para o form que será responsável em realizar o processo de update de curso, repare que esta url
é a mesma que foi definida no módulo do projeto para o form de update da entidade.

	public String getFormAction() {
	  String baseUrl = bricks.getBaseUrl();
	  Curso curso = getCurso();
	  
	  return String.format("%s/api/crud/faculdade/curso/%s", baseUrl, curso.getCodigo());
	}

Como foi definida uma url para acessar um recurso da aplicação é essencial a utilização de uma
propriedade do tipo __Bricks__ para capturar a url base do sistema, além do mais estamos definindo
no método uma url que utiliza um caracter coringa por conta disso o método `getCurso` de
CursoDetailsPage foi chamado, para que seja possível capturar a propriedade `codigo` e criar a url através
do método<a href="http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#format%28java.util.Locale,%20java.lang.String,%20java.lang.Object...%29">
format</a> da classe String.

### <a id="0_3"> </a> Implementando o método getMetaPage

Este método vem da superclasse e deve ser sobrescrito para que retorne um CursoEditPageMeta que é
responsável em exibir na página web o caminho do recurso que está sendo acessado no momento.

	public MetaPageScript getMetaPage() {
	  return new CursoEditPageMeta(getCurso());
	}

Não se esqueça de enviar um curso como parâmetro ao construtor da classe, ele será utilizado na
exibição de informações de curso na parte superior da página de edição.

### <a id="0_4"> </a> Implementando a classe EditPageMeta

Ao criar a classe certifique-se de que a mesma extenda AbstractPageMeta, corrija os erros de
compilação adicionando métodos e construtor da classe não se esquecendo de declarar uma propriedade
do tipo curso.

	public class CursoEditPageMeta extends AbstractPageMeta {
	  
	  private final Curso curso;
	  
	  public CursoEditPageMeta(Curso curso) {
	    this.curso = curso;
	  }
	  
	  protected void pageMetaFor() {
	    install(new CursoDetailsPageMeta(curso));
	    
	    display("Editar").onClick("faculdade", "curso", curso.getCodigo(), "editar");
	  }
	  
	}

Atenção as chamadas aos métodos `install` e `display` em pageMetaFor, ambos são responsáveis em
exibir na tela o caminho até a página atual, equanto install exibe todo o caminho até
__CursoDetailsPage__, display exibe o restante do caminho até a página atual.

<div class="alert alert info">
	Dica: Sempre que implementar um Edit Page certifique-se de que o método install chame o
	DetailsPageMeta da entidade que estiver trabalhando.
</div>

## <a id="0_5"> </a> Habilitando a página na web

Após implementar as classes que representão o __EditPage__ é preciso habilitar o form na web, para
isso é preciso implementar o form html, uma classe auxiliar que representa o form e o arquivo html
que equivale ao edit page de curso, essas três implementações foram separadas em tópicos abaixo. 

### <a id="0_6"> </a> Crie a classe que corresponde ao form

Antes de criar o arquivo html correspondente ao form crie a classe que representa o form em
`br.com.faculdade.ui.api.crud` e certifique-se de que a classe extenda FormAbstrato, como esta
classe utiliza <a href="http://docs.oracle.com/javase/tutorial/java/generics/index.html">generics</a>
é preciso informar o tipo de entidade a ser utilizada, em nosso caso, Curso.

	@Show("FormDeCurso.html")
	public class FormDeDefinicaoDeConjunto extends FormAbstrato<Curso> {
	
	  public String getNome() {
	    return pojo != null ? pojo.getNome() : "";
	  }
	
	}

Adicione o método `getNome` que equivale a única propriedade a ser alterada em curso, não se esqueça
de adicionar a validação no retorno do método como é feito acima.

<div class="alert alert info">
	Dica: Para cada propriedade, ou melhor, para cada campo que existir no form, deve existir um getter
	para a propriedade do campo nesta classe.
</div>


### <a id="0_7"> </a> Crie o formulário html

No pacote `br.com.faculdade.ui.api.crud` crie o html correspondente ao form, atente a sua implementação.

	<!doctype html>
	<html>
	<head>
	@Require
	<script type="text/javascript">
	  window.addEvent('domready', function() {
	    new Bd.Form({
	      fields : {
	
	        nome : {
	          type : 'text',
	          'label' : 'Nome',
	
	          elementProperties : {
	            'class' : 'span5',
	            'value' : '${nome}'
	          }
	        }
	    
	      }
	    });
	  });
	</script>
	</head>
	<body>
	  <form id="crud-frm" action="${url}" method="${method}">
	  </form>
	</body>
	</html>

### <a id="0_8"> </a> Crie o html do edit page

Crie no pacote `br.com.faculdade.ui.page.bd` o html que corresponde a página de edição

	<!doctype html>
	<html>
	<body>
	
	  @HdBd(title='Editar Curso: ' + curso.nome)
	  <div></div>
	
	  <div id="content" class="content">
	    @FormDeCurso(url=formAction, method="put", pojo=definicao)
	    <div></div>
	  </div>
	
	</body>
	</html>

<div class="alert alert info">
	Importante: Repare que logo no início do html temos uma chamada a curso.nome, para que isso
	seja possível é preciso um método getter para Curso em CursoEditPage, caso contrário uma exceção
	será lançada dizendo que o método em questão não existe.
</div>

## <a id="0_9"> </a> Alterando o módulo

Abra o módulo do projeto e adicione os binds para os arquivos criados nos seguintes métodos

	private void bindBricks() {
	  embed(FormDeCurso.class).as("FormDeCurso");
	}
	
	private void bindPages() {
	  at("/bd/faculdade/curso/:curso/editar").show(CursoEditPage.class);
	}

<p>Leia mais uma vez! <a href="#topo" class="btn btn-warning">Revisar!</a></p>