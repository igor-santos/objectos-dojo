---
layout: post-alpha
title: "Login errado"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-13"
published: true
partof: faq-crud-forms
num: 2
---

## Failed com Login

É comum no teste de gravação de dados de um _form_ haver uma autenticação de usuário para realizar
aquela operação.

Vejamos um possível _StackTrace_ ao inserirmos um login de usuário incorreto:

	FAILED: form_deve_gravar_imovel
	com.google.sitebricks.client.TransportException: org.codehaus.jackson.JsonParseException: Unexpected character ('<' (code 60)): expected a valid value (number, String, array, object, 'true', 'false' or 'null')
	at [Source: org.jboss.netty.buffer.ChannelBufferInputStream@4f0105c1; line: 1, column: 2]
	at com.google.sitebricks.client.WebResponseImpl$1.using(WebResponseImpl.java:59)
	at br.com.objectos.dojo.TesteDeFormDeImovelCreate.form_deve_gravar_imovel(TesteDeFormDeImovelCreate.java:88)

## Causa da Exception

O _login_ definido no teste está incorreto.
		
	public void form_deve_gravar_imovel() {
	  // Outros códigos
	  
	  Map<String, String> cookies = login("user");
	
	  // Outros códigos
	}
	
Importante: Até mesmo um _debug_ de um _assert_ não será executado neste caso. Quando o teste se trata
de autenticação, qualquer  _breakpoint_ adicionado após esta linha de código não terá utilidade. 
O que pode dificultar a solução do problema como por exemplo, nas linhas abaixo: 	

	FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
	assertThat(json.isValid(), is(true));
	
## Solução

Verifique qual o login possui permissão para realizar aquela determinada operação.

	public void form_deve_gravar_imovel() {
	  // Outros códigos
	  
	  Map<String, String> cookies = login("dojo");
	
	  // Outros códigos
	}
	
A partir de agora, o teste passará.	

	PASSED: form_deve_gravar_imovel
	
	===============================================
	    Default test
	    Tests run: 1, Failures: 0, Skips: 0
	===============================================