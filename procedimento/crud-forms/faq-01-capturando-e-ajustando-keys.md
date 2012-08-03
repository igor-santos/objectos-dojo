---
layout: post-alpha
title: "Capturando keys ao habilitar forms"
author: "Marcos Piazzolla"
published: true
partof: faq-crud-forms
num: 1
outof: 1
---

##Introdução

Em alguns momentos ao habilitar um form utiliza-se a classe utilitária __&lt;NomeDoProjeto&gt;Requests__,
que captura um determinado objeto da sessão, seja ele um Cliente, Produto, etc. Com isso é possível
implementar o método `getUrl()` que representa a ação do form. O problema ocorre quando a classe
__Requests__ não consegue capturar um determinado objeto da sessão.

Para este artigo tomar como base o seguinte bloco de código

	@Show("FormDeProduto.html")
	public class FormDeProduto extends AbstractForm<Produto> {
	
	  private final BibliotecaBricks bricks;
	
	  private ProdutoKey key;
	
	  @Inject
	  public FormDeProduto(BibliotecaBricks bricks) {
	    this.bricks = bricks;
	  }
	
	  @Override
	  public String getUrl() {
	    String baseUrl = bricks.getBaseUrl();
	    ClienteKey clienteKey = getKey();
	
	    return String.format("%s/api/crud/%s/produto", baseUrl, clientKey.getUrl());
	  }
	
	  public ProdutoKey getKey() {
	    return key;
	  }
	
	  public void setKey(ProdutoKey key) {
	    this.key = key;
	  }
	
	}

##Habilitando forms

Neste tipo de situação é de suma importância prestar atenção em como as páginas das entidades estão
estruturadas, em nosso exemplo precisamos capturar um objeto do tipo _Cliente_, no qual a classe
__Requests__ não captura da sessão.

//Tópicos restantes

//Onde está o parâmetro Cliente

//Como capturar um Cliente

//Ajustando sua key no html