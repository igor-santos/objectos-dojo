---
layout: post-alpha
title: "Pages"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-26"
published: true
partof: procedimento-crud-web
num: 2
outof: 2
---

## <a id="TOPO"> </a> Introdução
Qualquer _website_ possui uma página (em geral) com a extensão _.html_ responsáveis por modelar o 
seu design. Elas também possuem boa parte de códigos _JavaScript (JQuery)_ que possibilitam o ]
entendimento dos serviços descritos no artigo anterior e outras funcionalidades.

É muito importante que o _design_ das páginas seja bem elaborado e elegante, lembre-se que, em 
determinadas situações o site poderá ser o reflexo de seu trabalho, da sua personalidade, e ninguém 
se sentirá confortável em navegar em um site "feio".

## Acesso rápido
Para acessar os tópicos siga o checklist abaixo:
<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_0">Criando a classe BasePage</a>
    </td>
  </tr>
    <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_1">Criando a página BasePage</a>
    </td>
  </tr>
      <tr>
    <td class="tac col2em">
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_2">Criando a classe IndexPage</a>
    </td>
  </tr>
</table>

## <a id="0_0"> </a>Criando a classe BasePage
Utilizaremos o mesmo exemplo dos artigos anteriores para maior entendimento (Aluno, curso e faculdade)
Semelhante as classes de serviço, devemos criar um classe base junto a _annotation_ `@Show`

	@Show("BasePage.html")
	public abstract class BasePage {
	}
	
Esta classe será uma superclasse onde podemos obter autenticação, outras páginas ( _PageMeta_ ),
URL base, entre outros itens.

Defina uma constante que será o nosso tipo de arquivo ( _html_ ) e uma variável `Bricks`

Nota 1: As classes e páginas a serem criadas devem estar no pacote `ui.page` do projeto
Nota 2: Se no projeto em que estiver trabalhando já existir um _Bricks_ como `FaculdadeBricks` por
exemplo, utilize-o (estas classes podem ter métodos de autenticação conforme comentamos)

	@Show("BasePage.html")
	public abstract class BasePage {
	
	  public static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	  
	  private final Bricks bricks;
	
	}
	
Utilize o atalho `Alt + S` depois a tecla `A` para criar o construtor	

	protected BasePage(Bricks bricks) {
	  this.bricks = bricks;
	}
	
Crie um método _get_ para _bricks_ e um método abstrato no qual será utilizado pelas subclasses	
e suas páginas 

	public Bricks getBricks() {
	  return bricks;
	}

	protected abstract MetaPageScript getPageMeta();
 
## <a id="0_1"> </a>Criando a página BasePage
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
dentro da _tag div_ descrito acima, sendo assim, manteremos um padrão do layout das páginas 	

## <a id="0_2"> </a>Criando a classe IndexPage
Da mesma do _BasePage_, definiremos a página que será exibida ( _IndexPage.html_ ) 

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