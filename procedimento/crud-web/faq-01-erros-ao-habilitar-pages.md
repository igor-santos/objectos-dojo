---
layout: post-alpha
title: "Erros ao habilitar páginas na web"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-20"
published: true 
partof: faq-crud-web
num: 1
---

##Introdução

Algumas vezes ao habilitar algum tipo de recurso na web, como uma página por exemplo, o programador
se depara com diversos tipos de erros, a idéia deste FAQ é abordar o maior número de erros posível
para que o mesmo possa ser utilizado como referência para resolução destes problemas.

Para o seguinte faq, tome como base a seguinte amostra de código:

    @Decorated
    public class FuncionarioPage extends MyPage {

      @Inject
      public FuncionarioPage() {
        super(bricks);
      } 		
      
      @Override
      protected MetaPageScript getMetaPage() {
        return new FuncionarioPageMeta();
      }

    }

##Foi adicioanda a anotação @Inject no construtor da classe ? 

Verifique se a anotação __@Inject__ foi adicionada corretamente no construtor da classe, caso
contrário é possível que o seguinte *stacktrace* apareça 

	com.google.inject.ConfigurationException: Guice configuration errors:

	1) Could not find a suitable constructor in br.com.objectos.page.FuncionarioPage.
	   Classes must have either one (and only one) constructor annotated with @Inject or a
	   zero-argument constructor that is not private.
	   
	   at br.com.objectos.bd.page.FuncionarioPage.class(FuncionarioPage.java:22)
	   while locating br.com.objectos.bd.page.FuncionarioPage.class
	
	1 error
		at com.google.inject.internal.InjectorImpl.getProvider(InjectorImpl.java:1004)
		at com.google.inject.internal.InjectorImpl.getProvider(InjectorImpl.java:961)

De acordo com o *stacktrace* o a exceção ocorre por conta do construtor da classe que não foi anotado
com __@Inject__
	
    @Inject
	public FuncionarioPage() {
	  super(bricks);
	}