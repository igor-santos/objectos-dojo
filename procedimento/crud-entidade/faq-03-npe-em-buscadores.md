---
layout: post-alpha
title: "NullPointerException em Buscadores"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
published: true 
partof: faq-crud-entidade
num: 3
outof: 3
---

Ao executarmos um teste de buscador nos deparamos com uma série de NullPointerExceptions, todos os
testes que buscam um entidade por algum critério falham, como por exemplo o seguinte código de Buscador:

<pre>
	<code>
@Test
@GuiceModules(ModuloDeTesteClientesABC.class)
public class TesteDeBuscarCliente {

    private BuscarCliente buscarCliente;

    public void busca_por_id_deve_funcionar() {
        Cliente cliente = buscarCliente.porId(1);
		
        assertThat(cliente.getId(), equlaTo(1));
        //outros asserts
    }
}
	</code>
</pre>	

Abaixo temos uma amostra de seu STACKTRACE quando executamos o teste:
	
	FAILED: busca_por_id_deve_funcionar
	java.lang.NullPointerException
 		at br.com.objectos.dojo.TesteDeBuscarCliente.busca_por_id_deve_funcionar(TesteDeBuscarCliente.java:56)
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
		
__Causas:__

NullPointers geralmente ocorrem quando tentamos acessar propriedades de algo que não foi instânciado,
ou seja, temos duas possíveis causas para esta série de NullPointers:

+ Buscador não foi instânciado;

+ Seus dados de teste estão corretos, ou seja através destes dados é possível encontrar alguma
entidade no banco de dados ?

__Soluções:__

+ Foram utilizados dados de que existem de fato no banco de dados (mini-arquivo) ?

+ Foi adicionada a anotação @Inject na declaração de seu buscador ?