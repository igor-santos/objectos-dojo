---
layout: post-alpha
title: "NullPointerException em Consultas"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
published: true 
partof: faq-crud-entidade
num: 2
---

## Npe's em consultas

Ao executarmos um teste de consulta nos deparamos com um STACKTRACE análogo ao que está logo abaixo:

	RemoteTestNG starting
	FAILED CONFIGURATION: @BeforeClass prepararDBUnit
	java.lang.NullPointerException

		at br.com.objectos.dojo.TesteDeConsultaDeLivros.prepararDBUnit(TesteDeConsultaDeLivros.java:38)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
		at java.lang.reflect.Method.invoke(Method.java:597)
		at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:81)
		at org.testng.internal.Invoker.invokeConfigurationMethod(Invoker.java:525)
		at org.testng.internal.Invoker.invokeConfigurations(Invoker.java:202)
		at org.testng.internal.Invoker.invokeConfigurations(Invoker.java:130)
		at org.testng.internal.TestMethodWorker.invokeBeforeClassMethods(TestMethodWorker.java:173)
		at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:105)
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
		at org.testng.remote.RemoteTestNG.run(RemoteTestNG.java:111)
		at org.testng.remote.RemoteTestNG.initAndRun(RemoteTestNG.java:203)
		at org.testng.remote.RemoteTestNG.main(RemoteTestNG.java:174)

	SKIPPED: filtro_por_genero_deve_funcionar
	SKIPPED: filtro_por_nome_do_livro_deve_funcionar
	SKIPPED: filtro_por_nome_do_autor
	SKIPPED: ordenacao_padrao_deve_ser_por_nome_e_genero

__Causas:__ 

+ Não foi adicionada a anotação @Guice em baixo de @Test, logo no inicio da classe, por 
isso não foi possível carregar o banco de dados (mini-arquivo) para a realização dos asserts;

__Soluções:__

+ Basta adicionar a anotação Guice: @Guice(modules = { ModuloDeTesteBibliotecaXPTO.class }) logo
abaixo da anotação @Test no inicío da classe.