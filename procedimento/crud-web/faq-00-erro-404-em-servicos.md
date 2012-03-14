---
layout: post-alpha
title: "Erro 404 em serviços"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
published: true 
partof: faq-crud-web
num: 0
outof: 1
---

## Erro 404 em Serviços - Causas e possíveis soluções

Quando executamos um teste de Serviço, nos deparamos com o seguinte STACKTRACE:

    FAILED: usuarios_devem_listar
    java.lang.AssertionError: 
    Expected: <200>
         got: <404>

	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:21)
	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:8)
	at br.com.objectos.dojo.TesteDeServicoDeProduto.usuarios_devem_listar(TesteDeServicoDeProduto.java:42)
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
	at org.testng.remote.RemoteTestNG.run(RemoteTestNG.java:111)
	at org.testng.remote.RemoteTestNG.initAndRun(RemoteTestNG.java:203)
	at org.testng.remote.RemoteTestNG.main(RemoteTestNG.java:174)

Ou seja o recurso a ser acessado em seu teste não existe;

### Causas:

URL incorreta;

Anotação @Get na classe do serviço

### Soluções:

+ A url foi definida corretamente, ou seja os parametros definidos nela realmente direcionam
para uma entidade que existe no banco de dados?

+ A url que foi definida no módulo 'ModuloMeuProjeto.java' está de acordo com a URL que foi
definida em seu teste?
 
+ Foi definida a anotação @Get no método reply() na classe do serviço?



