---
layout: post-alpha
title: "Pages"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-26"
published: true
partof: procedimento-crud-web
num: 2
---

## <a id="TOPO"> </a> Introdução
Qualquer _website_ possui uma página (em geral) com a extensão _.html_ responsável por modelar o 
seu design. Elas também possuem boa parte de códigos _JavaScript (JQuery)_ que possibilitam o 
entendimento dos serviços descritos no artigo anterior e outras funcionalidades.

É muito importante que o _design_ das páginas fiquem bem elaborado e elegante, lembre-se que, em 
determinadas situações o site poderá ser o reflexo de seu trabalho, da sua personalidade, e ninguém 
se sentirá confortável em navegar em um site "feio".

## Antes de iniciar 
Este item exige conhecimentos sobre:

- HTML
- SiteBricks
- Serviço

## Especificação

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>
   <td class="tac col2em">
    <a id="topo_0_0"><input type="checkbox" /></a>
   </td>
   <td>
	<a href="#0_0">O que deve ser exibido nas páginas?</a>  
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <a id="topo_0_1"><input type="checkbox" /></a>
   </td>
   <td>
	<a href="#0_1">Precisamos detalhar estas páginas?</a>  
   </td>
 </tr>
</table>

## <a id="0_0"> </a>O que deve ser exibido nas páginas?
Chegamos em um item crucial, isto é, a partir de agora, tudo que for inserido nas páginas o seu 
cliente poderá ver, em outras palavras, o resultado de seu trabalho (algo mais concreto daquilo 
que ele esperava).

Para evitar diversos problemas como manutenção em códigos, é importante saber o que o cliente
deseja/espera visualizar na página (além da funcionalidade).

Tais questões como as seguintes devem estar claras para o desenvolvedor:
- A página poderá ter acesso a todos usuários?
- A página terá botões como _Incluir_ , _Excluir_ ou _Editar_ a entidade em questão?
- Terá os detalhes (características) daquela determinada entidade?

Você poderá absorver (ou não) de seu cliente tais respostas que facilitem o seu trabalho desde o
primeiro procedimento (entender o problema de  seu cliente de uma forma mais abstrata) até o último
passo que são as páginas concretas junto a funcionalidade.

## <a id="0_1"> </a>Precisamos detalhar estas páginas?
As páginas de detalhes possibilitam o usuário visualizar informações mais específicas de uma entidade.
Imagine o seguinte cenário: Uma página de internet já possue a listagem de todos os cursos de uma
determinada faculdade e cada curso possui um _link_ para as suas respectivas páginas de detalhes onde
listam os alunos que fazem aquele curso mais os seus detalhes que poderiam ser:

- Nome do curso
- Disciplinas
- Tempo
- Ranking no MEC

Em outros casos, não há a necessidade de detalhes como a página inicial do site, a página de cadastro,
entre outras.

Procure se informar o tipo de exibição primeiro para depois decidir se haverá a necessidade de uma
página de detalhes conhecida como _DetailsPage_.

## Acesso rápido
Para acessar os tópicos siga o checklist abaixo:
<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_2">Criando a BasePage</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_3"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_3">Criando a IndexPage</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_4"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_4">Criando o CursoPage</a>
    </td>
  </tr>
</table>

## <a id="0_2"> </a>Criando a BasePage
Utilizaremos o mesmo exemplo dos artigos anteriores para maior entendimento (Aluno, curso e faculdade).<br>
Semelhante as classes de serviço, devemos criar um classe base junto a _annotation_ `@Show`

	@Show("BasePage.html")
	public abstract class BasePage {
	}
	
Esta classe será uma superclasse onde podemos obter autenticação, outras páginas ( _PageMeta_ ),
URL base, entre outros itens.

Defina uma constante que será o nosso tipo de arquivo ( _html_ ) e uma variável `Bricks`

Nota 1: As classes e páginas a serem criadas devem estar no pacote `ui.page` do projeto<br>
Nota 2: Se no projeto em que estiver trabalhando já existir um _NomeDoProjetoBricks_ como 
`FaculdadeBricks`, utilize-o (estas classes podem ter métodos de autenticação das páginas
conforme comentamos na especificação).

	@Show("BasePage.html")
	public abstract class BasePage {
	
	  public static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	  
	  private final Bricks bricks;
	
	}
	
Utilize o atalho `Alt + S` depois a tecla `A` para criar o construtor.	

	protected BasePage(Bricks bricks) {
	  this.bricks = bricks;
	}
	
Crie um método _get_ para _bricks_ e um método abstrato no qual será utilizado pelas subclasses	
e suas páginas. 

	public Bricks getBricks() {
	  return bricks;
	}

	protected abstract MetaPageScript getPageMeta();
 
Com a classe criada, definiremos o layout da página. Crie um arquivo `BasePage.html` no pacote
`ui.page`

	<!doctype html>
	<html>
	<head>
	<script></script>
	<title>${title}</title>
	</head>
	<body>
	
	  <div>
	    @Decorated
	    <div></div>
	  </div>
	
	</body>
	</html>
	
Desta forma, nossos códigos e páginas que obtiverem a _annotation_ `@Decorated` deverão aparecer
dentro da _tag div_ descrito acima, sendo assim, conseguimos manter um padrão do layout das páginas. 	

## <a id="0_3"> </a>Criando IndexPage
Da mesma forma que em _BasePage_, definiremos a página inicial que será exibida ( _IndexPage.html_ ). 

	@Show("IndexPage.html")
	public class IndexPage {
	
	  private final Bricks bricks;
	
	  @Inject
	  public IndexPage(Bricks bricks) {
	    this.bricks = bricks;
	  }
	
	  public Bricks getBricks() {
	    return bricks;
	  }
	
	}
	
E uma classe `IndexPageMeta` que estende a `AbstractPageMeta`  (as _Pages_ sempre irão precisar destas duas classes)
	
	public class IndexPageMeta extends AbstractPageMeta {
	
	  @Override
	  protected void pageMetaFor() {
	    display("Faculdade").onClick("");
	  }
	
	}
	
E também o arquivo _.html_ `IndexPage.html` que será nossa página principal da Faculdade

	<!doctype html>
	<html>
	
	<head>
	<script type="text/javascript"></script>
	<title>Faculdade</title>
	</head>
	
	<body>
	
	    <div>
	      <ul>
	        <li>
	          <a href="${bricks.baseUrl}">Faculdade</a>
	        </li>
	      </ul>
	    </div>
	
	</body>

    </html>

Note que agora usamos aquele métogo _get_ de _bricks_ na página.

## <a id="0_4"> </a>Criando o CursoPage
Partiremos para a construção de uma página que poderá listar os cursos desta faculdade e, futuramente,
listar os alunos de um determinado curso e seus detalhes (utilizaremos este exemplo no próximo artigo).

É importante ressaltar que para realizar uma listagem de uma entidade, devemos ter um serviço, uma
consulta e uma tabela. Adotaremos como  existente o `ServicoDeCurso`.

Crie a classe `CursoPage`, torne-a subclasse de `BasePage` definida anteriormente e implemente os
seguinte métodos:

	@Decorated
	public class CursoPage extends BasePage {
	
	  @Inject
	  public CursoPage(Bricks bricks) {
	    super(bricks);
	  }
	
	  @Override
	  protected MetaPageScript getPageMeta() {
	    return new CursoPageMeta();
	  }
	
	}

Neste código você verá a _annotation_ `@Decorated` comentada no arquivo _.html_ do item anterior.

Utilize o atalho para criação da nova classe `CursoPageMeta`, `Ctrl + 1`.

Estando na classe `CursoPageMeta` torne-a subclasse de `AbstractPageMeta` da mesma forma que 
`IndexPageMeta`


	public class CursoPageMeta extends AbstractPageMeta {
	
	  @Override
	  protected void pageMetaFor() {
	    install(new IndexPageMeta());
	
	    display("Curso").onClick("curso");
	  }
	
	}
	
No método _install_ criamos um objeto _IndexPageMeta_ que será a origem desta página atual. O mesmo
pode ocorrer quando definirmos o `CursoDetailsPageMeta` que, irá instanciar `CursoPageMeta`.

Para finalizar, criaremos a _Page_ `CursoPage.html`

	<!doctype html>
	<html>
	<head>
	@Require
	<script type="text/javascript">
	  window.addEvent('domready', function() {
	    new Page({
 	      panels : [ {
            id : 'content',
	
	        url : Bricks.baseUrl + 'api/bd/faculdade/curso'
	      } ]
	    });
	  });
	</script>
	</head>
	<body>
	
	  <div id="content" class="content"></div>
	
	</body>
	</html>
	
Quando esta _Page_ for acessada pela URL que definiremos no módulo, ela executará o serviço de curso
(existente) e ele, por sua vez, listará todos os cursos desta faculdade através das tabelas do serviço.
 
 Só nos resta definir estas classes no módulo `ModuloFaculdadeUI`
 
	@Override
	protected void bindApi() {
	  at("/api/bd/faculdade/curso").serve(ServicoDeCurso.class);
	  at("/api/bd/faculdade/curso/:curso/aluno").serve(ServicoDeAluno.class);
	}
	
	@Override
	protected void bindPages() {
	  at("/").show(IndexPage.class);
	  at("/faculdade/curso").show(CursoPage.class);
	}
	

 Para conseguirmos listar todos os alunos de um determinado curso (nosso objetivo principal) devemos criar
 uma página de detalhes do curso.
 
Mais informações acesse os códigos nos links abaixo:

[BasePage.html](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/BasePage.html)<br>
[BasePage.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/BasePage.java)<br>
[IndexPage.html](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/IndexPage.html)<br>
[IndexPage.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/IndexPage.java)<br>
[IndexPageMeta.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/IndexPageMeta.java)<br>
[CursoPage.html](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/CursoPage.html)<br>
[CursoPage.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/CursoPage.java)<br>
[CursoPageMeta.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/CursoPageMeta.java)<br>
[ModuloFaculdadeUI.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/ModuloFaculdadeUI.java)<br>
 
Siga para o próximo passo. Details Page! <a href="{{ site.baseurl }}/procedimento/crud-web/03-criando-details-page.html" class="btn btn-success">Continuar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>