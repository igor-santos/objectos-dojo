---
layout: post-alpha
title: "'NullPointerException' ao executar teste de buscador"
author: "noobie"
published: true
partof: faq-crud-entidade
num: 0
outof: 0
---

## Sintoma

ao fazer tal coisa, tal coisa acontece...

Por exemplo stacktrace

    java.lang.NullPointerException
      at br.com.objectos.dojo.TesteDeBuscarCliente.busca_por_id(TesteDeBuscarCliente.java:42)
      at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
      at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
      at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
      at java.lang.reflect.Method.invoke(Method.java:597)
      at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:80)
      at org.testng.internal.Invoker.invokeMethod(Invoker.java:691)
      at org.testng.internal.Invoker.invokeTestMethod(Invoker.java:883)
      at org.testng.internal.Invoker.invokeTestMethods(Invoker.java:1208)
      at org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:127)
      at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:111)
      at org.testng.TestRunner.privateRun(TestRunner.java:758)
      at org.testng.TestRunner.run(TestRunner.java:613)
      at org.testng.SuiteRunner.runTest(SuiteRunner.java:334)
      at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:329)
      at org.testng.SuiteRunner.privateRun(SuiteRunner.java:291)
      at org.testng.SuiteRunner.run(SuiteRunner.java:240)
      at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:53)
      at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:87)
      at org.testng.TestNG.runSuitesSequentially(TestNG.java:1142)
      at org.testng.TestNG.runSuitesLocally(TestNG.java:1067)
      at org.testng.TestNG.run(TestNG.java:979)
      at org.testng.remote.RemoteTestNG.run(RemoteTestNG.java:109)
      at org.testng.remote.RemoteTestNG.initAndRun(RemoteTestNG.java:202)
      at org.testng.remote.RemoteTestNG.main(RemoteTestNG.java:173)
    
logo no primeiro teste:

    public void busca_por_id() {
      int id = 1;
    
      Cliente res = buscarCliente.porId(id);
    
      assertThat(res.getId(), equalTo(1));
    }


## Causa

Faltou estudar mais, Faltou fazer follow-up. Faltou escrever artigo.

Faltou chamar método X, colocar anotação Y, fazer coisa Z, etc.

## Solução

1. Passo 1
1. Passo 2
1. Passo 3