---
layout: post
title: "Create Page"
author: "Tiago Aguiar"
user: "taguiar"
published: "2012-04-27"
partof: procedimento-crud-web
num: 4
outof: 4
---

## <a id="TOPO"> </a> Introdução
Conforme comentamos na especificação do artigo [Page]({{ site.baseurl }}/procedimento/crud-web/02-page.html), 
em determinados casos, você precisará adicionar funcionalidades como _Criar_, _Excluir_ e  _Editar_. 
Utilizaremos um exemplo para demonstrar o procedimento de Criação, onde precisamos 
criar um novo curso para a faculdade dos artigos anteriores.

## Antes de iniciar 
Este item exige conhecimentos sobre:

- HTML
- JavaScript
- SiteBricks
- Serviço
- Form


## Acesso rápido
Para acessar os tópicos siga o checklist abaixo:
<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_1">Criando o CursoCreatePage.html</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_2">Criando o FormDeCurso.html</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_3"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_3">Criando o CursoCreatePage.java</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_4"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_4">Criando o CursoCreatePageMeta.java</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_5"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_5">Criando o FormDeCurso.java</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_6"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_6">Atualizando o Módulo</a>
    </td>
  </tr>
</table>

## <a id="0_1"> </a>Criando o CursoCreatePage.html

O primeiro passo é editar o arquivo `CursoPage.html` e inserir um _link_ responsável pelo 
redirecionamento à página de criação. Este redirecionamento (em geral) também é feito por um botão,
uma ação, entre outras funcionalidades.

Nota: Omitiremos os códigos que define o estilo da página (css) para um melhor entendimento.

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
	    
	    <div>
	      <ul>
	        <li>
	          <a href="curso/criar">Criar</a>
	        </li>
	      </ul>
	    </div>
	
	</body>
	
	</html>

Crie o arquivo `CursoCreatePage.html` no pacote `ui.page.bd` com os seguintes códigos:

	<!doctype html>
	<html>
	<body>
	
	  <div>
	    @FormDeCurso(url=formAction, method="post")
	    <div></div>
	  </div>
	
	</body>
	</html>

Neste passo, definimos em uma _div_ um `@FormDeCurso` para que o formulário de `FormDeCurso.html` 
(ainda não criado) possa ser preenchido e o `FormDeCursoCreate` executado.

Importante: Estas páginas aparentam ser bem simples, mas é necessário ter conhecimentos básicos em 
_SiteBricks_.

## <a id="0_2"> </a>Criando o FormDeCurso.html
Iremos começar a criar o formulário onde o usuário preencherá os dados que deverão ser cadastrados/
inseridos no banco de dados. Para maior entendimento, definiremos apenas a criação do nome do curso.
Este arquivo deve estar no pacote `ui.api.crud` do projeto.

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
	

Definimos através de _JavaScript_ o formulário com o campo _nome_ a ser preenchido.<br>
Para mais informações sobre a biblioteca de códigos _JavaScript_ utilizada, acesse [aqui](https://github.com/objectos/objectos-js).
	
## <a id="0_3"> </a>Criando o CursoCreatePage.java
A partir de agora, criaremos as classes das páginas já prontas. Iniciaremos pela classe `CursoCreatePage`
no pacote `ui.page.bd` do projeto.

Esta classe terá todos os métodos definidos em `CursoPage`, porém, com um método adicional. Este método
é o `getFormAction()`, responsável por "executar" o `FormDeCursoCreate`. Vejamos os códigos:

	@Decorated
	public class CursoCreatePage extends BasePage {
	
	  @Inject
	  public CursoCreatePage(Bricks bricks) {
	    super(bricks);
	  }
	
	  public String getFormAction() {
	    return bricks.getBaseUrl() + "/api/crud/curso";
	  }
	
	  @Override
	  protected MetaPageScript getPageMeta() {
	    return new CursoCreatePageMeta();
	  }
	
	}
	
Utilize os atalhos para criar as classes como o `Ctrl + 1`.
	
## <a id="0_4"> </a>Criando o CursoCreatePageMeta.java
Mais uma vez temos códigos semelhante, estes códigos estão em `CursoPageMeta`. Só que desta vez, 
especificaremos a _String_ em _onClick_ para a página de `CursoCreatePage.html`. Crie a classe no 
pacote `ui.page.bd` do projeto.

	public class CursoCreatePageMeta extends AbstractPageMeta {
	
	  @Override
	  protected void pageMetaFor() {
	    install(new CursoPageMeta());
	
	    display("Novo Curso").onClick("bd/curso/criar");
	  }
	
	}
	
## <a id="0_5"> </a>Criando o FormDeCurso.java
Esta é a classe que terá os dados preenchidos na página do formulário anterior. Crie-a no pacote
`ui.api.crud`. Não esquece de torná-la uma subclasse de `AbstractForm` que se encontra no pacote
`br.com.objectos.comuns.sitebricks`.

	@Show("FormDeCurso.html")
	public class FormCurso extends AbstractForm<Curso> {
	
	  public String getNome() {
	    return pojo != null ? pojo.getNome() : "";
	  }
	
	}
	
## <a id="0_6"> </a>Atualizando o Módulo
	
Para finalizar, adicione essas classes `CursoCreatePage` e `FormDeCurso` no módulo `ModuloFaculdadeUI`
conforme os códigos a seguir:	
 
	@Override
	protected void bindApiCrud() {
	  at("api/crud/faculdade/curso").serve(FormDeCursoCreate.class);
	  at("api/crud/faculdade/curso/:curso/aluno").serve(FormDeAlunoCreate.class);
	  at("api/crud/faculdade/curso/:curso/aluno/:aluno").serve(FormDeAlunoUpdate.class);
	}
	
	@Override
	protected void bindApi() {
	  at("/api/bd/faculdade/curso").serve(ServicoDeCurso.class);
	  at("/api/bd/faculdade/curso/:curso/aluno").serve(ServicoDeAluno.class);
	}
	
	@Override
	protected void bindPages() {
	  at("/").show(IndexPage.class);
	  at("/faculdade/curso").show(CursoPage.class);
	  at("/faculdade/curso/criar").show(CursoCreatePage.class);
	}
	
	@Override
	private void bindBricks() {
	  embed(FormDeCurso.class).as("FormDeCurso");
	}

Para mais informações acesse os códigos nos links abaixo:

[FormDeCurso.html](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/FormDeCurso.html)<br>
[FormDeCurso.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/FormDeCurso.java)<br>
[CursoCreatePage.html](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/CursoCreatePage.html)<br>
[CursoCreatePage.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/CursoCreatePage.java)<br>
[CursoCreatePageMeta.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/CursoCreatePageMeta.java)<br>
[ModuloFaculdadeUI.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/ModuloFaculdadeUI.java)<br>
 
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>