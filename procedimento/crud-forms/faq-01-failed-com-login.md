---
layout: post-alpha
title: "Login errado"
author: "Tiago Aguiar"
user: "taguiar"
published: true
partof: faq-crud-forms
num: 1
outof: 1
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
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:81)
	at org.testng.internal.Invoker.invokeMethod(Invoker.java:673)
	at org.testng.internal.Invoker.invokeTestMethod(Invoker.java:842)
	at org.testng.internal.Invoker.invokeTestMethods(Invoker.java:1166)
	at org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:125)
	at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:109)
	at org.testng.TestRunner.runWorkers(TestRunner.java:1172)
	at org.testng.TestRunner.privateRun(TestRunner.java:757)
	at org.testng.TestRunner.run(TestRunner.java:608)
	at org.testng.SuiteRunner.runTest(SuiteRunner.java:334)
	at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:329)
	at org.testng.SuiteRunner.privateRun(SuiteRunner.java:291)
	at org.testng.SuiteRunner.run(SuiteRunner.java:240)
	at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
	at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:86)
	at org.testng.TestNG.runSuitesSequentially(TestNG.java:1158)
	at org.testng.TestNG.runSuitesLocally(TestNG.java:1083)
	at org.testng.TestNG.run(TestNG.java:999) 

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