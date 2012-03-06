---
layout: post
title: "Details Page"
author: "Marcio Endo"
user: "mendo"
published: true 
tags:
- padrão objectos
- procedimento
- sitebricks
- web
---

## Details Page

Implementando página de detalhes nos padrões objectos.

Este artigo assume conhecimento prévio em:

* Java
* SiteBricks 
* HTML
* CSS

### JavaScript

Em parte para estimular o 
<a href="http://en.wikipedia.org/wiki/Decoupling#Software_Development" target="_blank">decoupling</a>,
em parte para facilitar o desenvolvimento de interfaces amigáveis via JavaScript, as
páginas (tanto pages como details pages) são dividos em blocos chamados de painéis.

Os blocos são alimentados independentemente via Ajax, isto é, cada bloco (em geral) possui 
seu próprio conjunto de serviços.

Grande parte da funcionalidade JavaScript é fornecida pelo projeto 
https://github.com/objectos/objectos-js.

### Erros comuns

Listados abaixo alguns erros comuns:

#### Para cada variável declarada no HTML existe um getter em sua classe Java?

Para que o código abaixo funcione:

	<dl>
	  <dt>Tipo</dt>
	  <dd>${minhaEntidade.nome}</dd>
	</dl>
	
Sua classe Java deve possuir um getter para a propriedade `minhaEntidade`

	public class DetailsPage {
	  private Entidade minhaEntidade;
	  
	  public Entidade getMinhaEntidade() {
	    return minhaEntidade;
	  }
	}
	
Além disso o objeto retornado deve possuir um getter para a propriedade `nome`

	public class Entidade {
	   private String nome;
	   
	   public String getNome() {
	     return nome;
	   }
	}