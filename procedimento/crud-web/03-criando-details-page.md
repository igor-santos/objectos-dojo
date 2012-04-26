---
layout: post-alpha
title: "Habilitando páginas de detalhe na web"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-03-09"
published: true 
partof: procedimento-crud-web
num: 3
outof: 3
---

#Introdução

Ao trabalhar com aplicações web é muito comum a implementação de views, componentes gráficos que
são responsáveis em dar um feedback ao usuário, as views são páginas html que contém listagens ou
algum tipo de informação que vem do banco de dados tome como exemplo de view as páginas de detalhes 
no artigo implementando pasges.

##Details Page?

Neste artigo será tratado a implementação de uma página de detalhes de uma entidade, páginas de
detalhes contém informações mais específicas de uma entidade, tudo aquilo que não é necessaŕio ser 
exibido em uma página alimentada por um serviço, é possível adicionar listagens de outras entidades
em uma página de detalhes, mas este não é o objetivo deste artigo.

##Criando a classe que representa a página

Assim como na criação de páginas de listagem, é preciso criar uma classe java que represente a
página de detalhes a ser implementada posteriormente, no exemplo abaixo será implementada uma página
que lista todas as informações de um curso de uma faculdade. Mãos à obra!

Primeiramente crie a classe que representa a página de detalhes no pacote `br.com.faculdade.ui.page`
e dê o nome de CursoDetailsPage.

<div class="alert alert info">
	Dica: Lembre-se que para cada classe de details page criada o nome da mesma deve conter as 
	seguintes informações: __Entidade__ + DetailsPage, em nosso exemplo de curso nossa classe
	seria definida como __CursoDetailsPage__. 
</div>

Adicione a anotação `@Decorated` na classe para ligá-la ao arquivo html que será responsável em 
realizar a exibição dos dados da entidade e não se esqueça de estender a classe __BasePage__.

	@Decorated
	public class CursoDetailsPage extends BasePage {
	}

Provavelemte sua IDE irá lançar alguns erros de compilação corrijá-os adicionando o construtor da
classe sem a propriedade Curso e o método getMetaPage(), deixe-o retornando __null__ por hora.

	@Inject
	public CursoDetailsPage(FaculdadeBricks bricks, BuscarCurso buscarCurso) {
	  super(bricks);
	  this.buscarCurso = buscarCurso;
	}
	
	protected MetaPageScript getMetaPage() {
	  return null;
	}

<div class="alert alert info">
	Importante: Não se esqueça de adicionar a anotação @Inject no construtor da classe para evitar
	que NullPointerExceptions ocorram.
</div>

Para que seja possível listar os detalhes de um curso em específico é preciso buscar este curso no
banco de dados através de algum parâmetro que vem da url, por conta disso utilizaremos um buscador
de curso.

	private BuscarCurso buscarCurso;

Declare também uma entidade do tipo Curso e um getter para a mesma, pois a página html irá acessar e
exibir todas as propriedades desta entidade através de seu getter.

	private Curso curso;
	
	public Curso getCurso() {
	  return curso;
	}

###Implementando o método get

Este método é responsável em capturar parâmetros da url e utilizá-los para buscar uma entidade no
banco de dados, sempre que uma página de detalhes for acessada a classe java que representa esta
página será chamada, em seguida o método get será carregado, pois o mesmo é responsável em popular
a propriedade `curso` declarada no topo da classe.

	@Get
	public void get(){
	}

<div class="alert alert info">
	Dica ao implementar o método get é muito importante que o mesmo possua a anotação @Get, com esta
	anotação todas as vezes que o link da página for acessado, ou seja, uma solicitação HTTP do tipo
	Get for realizada o método que for anotado com @Get será carregado.
</div>

É necessário criar um link na página de curso que redirecione o usuário do sistema para a página de
detalhes de curso, para isso abra a tabela de curso criada ao implementar o serviço de Curso e 
adicione um link em uma propriedade única de curso, código em nosso caso.

	<html>
	<body>
	  @ShowIf(dtos.isEmpty())
	  <p>Nenhum resultado encontrado.</p>
	
	  @ShowIf(!dtos.isEmpty())
	  <table>
	    <thead>
	      <tr>
	        <th>Código</th>
	        <th>Nome</th>
	        <th>Quantidade de Turmas</th>
	      </tr>
	    </thead>
	    <tbody>
	      @Repeat(items=dtos, var="dto")
	      <tr>
	        <td><a href="faculdade/curso/${dto.codigo}">${dto.codigo}</a></td>
	        <td>${dto.nome}</td>
	        <td>${dto.numTurmas}</td>
	      </tr>
	    </tbody>
	  </table>
	</body>
	</html>

<div class="alert alert info">
	Importante: A propriedade ${dto.codigo} que foi adicionada no link deve existir na interface, ou
	seja deve existir um getCodgio na interface ConsultaDeCursoDTO, caso contrário uma excessão será
	lançada reclamando que o método em questão não existe.
</div>

###Alterando o módulo do projeto

Após adicionar o link para a página de detalhes de Curso na __TabelaDeCurso__ é preciso adicionar
um bind no módulo para o link definido na tabela, com este bind será possível ligar a classe java ao 
arquivo html, ou seja, sempre que for realizada uma solicitação a URL definida na tabela, existirá
uma classe java que será responsável em fazer algo em relação a solicitção, por exemplo, popular
uma entidade e alimentar uma página html. Abra o módulo do projeto __ModuloFaculdadeUI__ e
realize a seguinte alteração.

    protected void bindPage() {
      at(/faculdade/curso/:curso).show(CursoDetailsPage.class);
    }

Repare que foi adicionado um caracter coringa após curso, o valor de `:curso` será alterado de
acordo com o tipo de página de curso que for acessada.

###Finalizando o método get

De volta ao método get adicione como parâmetro do mesmo o caracter coringa da url recém adicionada
no módulo, em seguida faça uma busca de curso por este critério e atribua o resultado a propriedade
`curso` definida anteriormente.

    @Get
    public void get(@Named("curso") String _curso){
      curso = buscarCurso.porCodigo(_curso);
    }

##Implementando o método getMetaPage

Este método é responsável em exibir na parte superior da página informações referentes a entidade,
formando uma trilha desde a entidade atual até a raiz do sistema, esta trilha é conhecida como
__breadcrumb__.

Ajuste o retorno do método para que o mesmo retorne um __CursoDetailsPageMeta__ e certifique-se de
enviar um curso como parâmetro do construtor da classe a ser criada, corrija o erro de compilação 
criando a classe no pacote `br.com.faculdade.ui.page`, atente ao retorno do método.

    public MetaPageScript getMetaPage() {
      return new CursoDetailsPageMeta(curso);
    }

###Implementando DetailsPageMeta

Ao criar a classe faça com que a mesma extenda AbstractPageMeta, declare um objeto do tipo curso,
gere o construtor da classe e sobrescreva o método `pageMetaFor`.

    public class CursoDetailsPageMeta extends AbstractPageMeta {
      
      private final Curso curso;
      
      public CursoDetailsPageMeta(Curso curso) {
        this.curso = curso;
      }
      
      @Override
      public void pageMetaFor() {
        install(new CursoPageMeta());
        
        display(curso.getNome()).onClick("faculdade", "curso", curso.getCodigo);
      }
      
    }

Atenção aos métodos install e display, ambos responsáveis na exibição dos breadcrumbs, install
sempre deve chamar um page meta anterior ao page meta atual, tome como exemplo CursoDetailsPageMeta
que deve chamar CursoPageMeta que provavelmente chamará IndexPageMeta definindo o caminho até a raiz
da aplicação.

Já o método display é responsável em exibir todo o caminho até a página atual, Basta uma leitura
rápida no bloco onde o método é chamado para perceber o que o mesmo faz.

##Implementando DetailsPage.html

Tudo está praticamente pronto basta implementar o arquivo html responsável em exibir as informações 
de um curso na tela. Crie o arquivo CursoDetailsPage.html no mesmo pacote onde foi criada a classe
CursoDetailsPage, atente a forma como o arquivo html exibe as propriedades de curso na tela.

	<!doctype html>
	<html>
	<body>
	  <div class="content-details">
	    <h3>Detalhes</h3>
	
	    <div class="row">
	      <div class="span6">
	        <dl>
	          <dt>Código</dt>
	          <dd>${curso.codigo}</dd>
	          <dt>Nome</dt>
	          <dd>${curso.nome}</dd>
	          <dt>Código</dt>
	          <dd>${curso.codigo}</dd>
	        </dl>
	      </div>
	        
	  </div>
	</body>
	</html>

<div class="alert alert info">
    Importante: As propriedades são acessadas na classe CursoDetailsPage através do método getCurso(),
    no html o prefixo get é omitido utilizando-se apenas o nome da propriedade a ser acessada, repare
    que todas as propriedades que estão sendo exibidas possuem getters na interface da entidade Curso.
</div>