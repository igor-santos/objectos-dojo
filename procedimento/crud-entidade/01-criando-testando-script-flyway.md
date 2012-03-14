---
layout: post
title: Criando e testando Scripts Flyway
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-03-14"
published: true
partof: procedimento-crud-entidade
num: 1
outof: 1 
---

##1º: Importando o projeto

A princípio devemos ter o projeto em nosso computador. Utilizaremos o projeto _objectos-apps_ para
descrever os procedimentos seguintes:

Execute o seguinte comando para clonar o projeto _objectos-apps_.

	$ cd ~/kdo/projetos/
	$ git clone git@github.com:objectos/objectos-apps.git
	$ cd objectos-apps/
	$ git branch
	
Note que estamos na branch _master_. Devemos trabalhar em uma nova branch neste padrão: 
_login-de-rede-objectos-XX-nome da entidade_ (onde _XX_ representa o próximo número de branch)

##Erro:
Provavelmente o Eclipse mostrará um erro no arquivo POM.XML do projeto.
Vejamos:

POM.XML de _objectos-apps_:

	<parent>
		<artifactId>objectos-comuns-parent</artifactId>
		<groupId>br.com.objectos</groupId>
		<version>2.0.5</version>
	</parent>

Isto ocorre porque há algumas dependências que devem ser carregadas no projeto.

##Solução:

Siga os seguintes procedimentos:

###1 - No Eclipse:
	1.1 - Selecionar todos os projetos;
	1.2 - Clicar com o direito -> Clicar em 'Refresh';
	1.3 - Esperar o Eclipse terminar o procedimento;
	
###2 - No terminal:
	
	$ cd ~/kdo/projetos/objectos-apps
	$ mvn-ci
	
###3 - Informações no terminal...
	
	[INFO] Scanning for projects...
	Downloading: http://rio.objectos.com.br/nexus/content/groups/public/br/com/objectos/objectos-comuns-parent/2.0.5/objectos-comuns-parent-2.0.5.pom
	Downloaded: http://rio.objectos.com.br/nexus/content/groups/public/br/com/objectos/objectos-comuns-parent/2.0.5/objectos-comuns-parent-2.0.5.pom (18 KB at 24.8 KB/sec)
	[INFO] ------------------------------------------------------------------------
	[INFO] Reactor Build Order:
	[INFO] 
	[INFO] objectos :: apps
	[INFO] objectos :: apps :: base
	[INFO] objectos :: apps :: cur :: Currículos
	[INFO] objectos :: apps :: fin :: Finanças
	[INFO] objectos :: apps :: fun :: Funcionários
	[INFO] objectos :: apps :: usr :: Usuários
	[INFO]                                                                         
	[INFO] ------------------------------------------------------------------------
	[INFO] Building objectos :: apps 0.1.11-SNAPSHOT
	[INFO] ------------------------------------------------------------------------
	[INFO] ------------------------------------------------------------------------
	[INFO] Reactor Summary:
	[INFO] 
	[INFO] objectos :: apps .................................. FAILURE [0.001s]
	[INFO] objectos :: apps :: base .......................... SKIPPED
	[INFO] objectos :: apps :: cur :: Currículos ............. SKIPPED
	[INFO] objectos :: apps :: fin :: Finanças ............... SKIPPED
	[INFO] objectos :: apps :: fun :: Funcionários ........... SKIPPED
	[INFO] objectos :: apps :: usr :: Usuários ............... SKIPPED
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD FAILURE
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time: 1.264s
	[INFO] Finished at: Mon Mar 12 10:55:14 BRT 2012
	[INFO] Final Memory: 3M/116M
	[INFO] ------------------------------------------------------------------------
	[ERROR] Unknown lifecycle phase "i". You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -> [Help 1]
	[ERROR] 
	[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
	[ERROR] Re-run Maven using the -X switch to enable full debug logging.
	[ERROR] 
	[ERROR] For more information about the errors and possible solutions, please read the following articles:
	[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
	
###4 - Voltando ao Eclipse:
	4.1 - Selecionar todos os projetos;
 	4.2 - Na barra de menu -> Project -> Clean -> Selecione 'Clean all projects' -> Clique em Ok;
	4.3 - Clicar com direito no projeto -> Maven -> Update Project Configuration. 
	
Isto deve resolver o problema.

##2º: Criando o Script Flyway
 
