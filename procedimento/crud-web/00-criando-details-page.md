---
layout: post-alpha
title: "Habilitando páginas de detalhe na web"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-03-09"
published: true 
partof: procedimento-crud-web
num: 1
outof: 1
---

#Introdução

Ao trabalhar com aplicações web é muito comum a implementação de views, componentes gráficos que
são responsáveis em dar um feedback ao usuário, as views são páginas html que contém listagens ou
algum tipo de informação que vem do banco de dados tome como exemplo de view as páginas de detalhes 
no artigo de implementação de pages.

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
realizar a exibição dos dados da entidade, não se esqueça de estender a classe __BasePage__.

	@Decorated
	public class CursoDetailsPage extends BasePage {
	}

Provavelemte sua IDE irá lançar alguns erros de compilação corrijá-os adicionando o construtor da
classe sem a propriedade Curso e o método getMetaPage(), deixe retornando __null__ por hora.

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

##Implementando o método get

Este método é responsável em capturar parâmetros da url e utilizá-los para buscar uma entidade no
banco de dados, sempre que uma página de detalhes for acessada a classe java que representa esta
página será chamada e o método get será o primeiro método a ser carregado, pois o mesmo é responsável
em popular a propriedade curso declarada no topo da classe.

	@Get
	public void get(){
	}

<div class="alert alert info">
	Dica ao implementar o método get é muito importante que o mesmo possua a anotação @Get, com esta
	anotação todas as vezes que o link da página for acessado ou seja uma solicitação http do tipo
	get for realizada o método que for anotado com get será carregado.
</div>

É necessário criar um link na página de curso que redirecione o usuário do sistema para a página de
detalhes de curso, para isso abra a tabela de curso criada ao implementar o serviço de Curso e 
adicione um link em uma propriedade única de curso.

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
	Importante: A propriedade ${dto.codigo} que foi adicionada no link deve existir na interface
	ConsultaDeCursoDTO, caso contrário uma excessão será lançada reclamando que o método em questão
	não existe.
</div>

##Implementando o método get - parte 2