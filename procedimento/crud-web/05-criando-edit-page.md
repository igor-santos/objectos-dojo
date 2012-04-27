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

## Introdução

Em aplicações que trabalham com banco de dados é muito comum a realização de rotinas do tipo update 
pois as informações tendem a mudar com o passar do tempo, para realização deste tipo de operação
existem componentes gráficos como formulários, que são responsáveis em armazenar informações que
futuramente serão enviadas ao banco de dados onde será realizado o processo de update.

<div class="alert alert-danger">Melhore a introdução, mencione que o edit page chama o form, etc.</div>

## Edit Page ?

Este artigo tem como objetivo a implementação de páginas de edição, este tipo de página é bem
parecido com uma página de criação, a única diferença entre ambos é a operação
<a href="http://pt.wikipedia.org/wiki/CRUD">CRUD</a> que é realizada e a form como a página de
edição é acessada em relação à página de criação.

## Criando a classe java que representa a página html

Primeiramente crie a classe __CursoEditPage__ no pacote `br.com.projeto.ui.page`, o mesmo pacote
onde foram definidas as outras pages, certifique-se de que sua classe extenda a página de detalhes
da entidade na qual será realizada o update, em nosso caso Curso.

	public class CursoEditPage extends CursoDetailsPage {
	}

Como estamos estendendo a classe CursoDetailsPage não é necessário a criação do método `get` pois
o mesmo já foi implementado na superclasse.

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

### Implementando o método getFormAction

Ainda em CursoEditPage é preciso implementar o método getFormAction que é responsável em definir a
url para o form que será responsável em realizar o processo de update de curso, atente a sua
implementação para maiores esclarecimentos, repare que está url é a mesma que foi definida no
módulo do projeto para o form de update da entidade.

	public String getFormAction() {
	  String baseUrl = bricks.getBaseUrl();
	  Curso curso = getCurso();
	  
	  return String.format("%s/api/crud/faculdade/curso/%s", baseUrl, curso.getCodigo());
	}