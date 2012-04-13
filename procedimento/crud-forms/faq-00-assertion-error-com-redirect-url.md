---
layout: post-alpha
title: "RedirectURL errada"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
published: true
partof: faq-crud-forms
num: 0
---

## Assertion Error de redirectUrl

Quando se trata de forms de criação é bem comum a realização de um teste de redirect, ou seja
após a entidade ser gravada no bd, o sistema deve redirecionar o usuário para a página de detalhes 
dessa entidade.

Abaixo em nosso STACKTRACE temos um erro bem comum em relação a testes de redirectURL em forms:

	java.lang.AssertionError: 
	Expected: a string ending with "api/crud/cliente/CLI_ABC"
         got: "http://localhost:4000/bd/cliente/CLI_ABC"

	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:21)
	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:8)
	at br.com.objectos.dojo.TesteDeFormDeClienteCreate.form_deve_gravar_no_bd(TesteDeFormDeClienteCreate.java:72)
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


__Causa:__

+ O redirectURL que foi definido no teste está incorreto pois é análogo a url que foi definida 
logo no inicio do teste para acessar o form, é necessário especificar o caminho completo da 
entidade que foi cadastrada.

__Solução:__

+ Basta corrigir o assert do redirectUrl para: 

<pre>
	<code>
	    assertThat(redirectUrl, endsWith("bd/cliente/CLI_ABC"));
	</code>
</pre>

Com isso será possível testar se realmente foi possível acessar a página de detalhes da
entidade cadastrada.
