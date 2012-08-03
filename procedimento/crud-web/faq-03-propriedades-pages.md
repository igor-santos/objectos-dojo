---
layout: post-alpha
title: "Acessando propriedades de páginas web via sitebricks"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-20"
published: true 
partof: faq-crud-web
num: 3
outof: 3
---

##Introdução

Para exibir dados de uma entidade em uma página html, o sitebricks dispõe de um sistema de templates
onde cada arquivo html está ligado a uma classe java, é possível acessar propriedades desta classe 
que está ligada com o arquivo html utilizando a seguinte sintaxe `${minhaPropriedade}`. 

Para melhor entendimento deste faq, tome como base os seguintes códigos

Classe java que está ligada ao arquivo html

	@Decorated
	public class ClientePage extends BasePage {
	
	  private Cliente cliente;

      public Cliente getCliente{
        return cliente;
      }
		
	}

Arquivo html com código javascript que acessa as propriedades da classe java

	@Require
	<script type="text/javascript">
	  window.addEvent('domready', function() {
	    new Page({
	      panels : [ {
	        id : 'cliente-tbl',
	        url : Bricks.baseUrl + '/api/super_mercado/bd/cliente/${cliente.codigo}'
	      } ]
	    });
	  });
	</script>

Para acessar propriedades de uma classe java em uma página html via sitebricks é preciso utilizar a 
seguinte sintaxe: `${propriedade.valor}` caso contrário não teremos o resultado esperado. Esta
sintaxe indica ao sitebricks que procure na classe que represente este arquivo html a propriedade
cliente e retorne o valor da propriedade código de cliente, lembrando que o Cliente deve possuir 
um `getCodigo()`.