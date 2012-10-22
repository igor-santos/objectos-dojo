---
layout: post-alpha
title: "Procedimento de rebase"
author: "Ricardo Murad"
published: true
partof: git
num: 6
outof: 6
---

##Introdução

No git temos basicamente duas formas de juntar duas ou mais branches ao projeto principal.
Neste procedimento vamos utilizar o comando rebase e merge para atualizar nosso projeto.

#Estado da branch atual

Nunca é demais verificar se tudo está correto antes de fazermos o rebase.
Antes de iniciar o processo vamos verificar o estado de nossa branch com o comando `git status`:

	$ git status

    On branch rmurad_20_teste
    nothing to commit (working directory clean)

Após verificarmos que não há nada pendente é que vamos fazer push e rodar o jenkis para verificar
se há erros na branch:

    ========================
    Tests run: 169, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.836 sec
    
    Results :
    
    Tests run: 169, Failures: 0, Errors: 0, Skipped: 0
    
    [JENKINS] Gravando resultados de teste# Deploying the attached artifact {0}
    ==============================

Ok não há nehum erro. Vamos para o master para atualizar o repositório local:

	$ git checkout master

    Switched to branch 'master'

Para atualizar o repositório digite:

    $ git pull origin master

Resultado:

	From github.com:objectos/projeto-teste
	 * branch            master     -> FETCH_HEAD
	Already up-to-date.


O próximo passo é voltar para nossa branch e aplicar o comando git rebase que traz os commits do
master para nossa branch.

	$ git checkout rmurad_20_teste
	 
	Switched to branch 'rmurad_20_teste'

	$ git rebase master -i

O editor vi será utilizado para que possamos selecionar os commits que desejamos utilizar no rebase.

Exemplo:

	==============================================================================
	pick e7c4b61 form cliente
	pick 0996f15 - refatora cliente key
	pick 5574fa7 - corrige buscador de cliente
	pick 466c221 revisao de codigo
	pick df70f7c - revisao de código
	
	 Rebase f346b81..df70f7c onto f346b81
	
	 Commands:
	  p, pick = use commit
	  r, reword = use commit, but edit the commit message
	  e, edit = use commit, but stop for amending
	  s, squash = use commit, but meld into previous commit
	  f, fixup = like "squash", but discard this commit's log message
	  x, exec = run command (the rest of the line) using shell
	
	 These lines can be re-ordered; they are executed from top to bottom.
	
	 If you remove a line here THAT COMMIT WILL BE LOST.
	 However, if you remove everything, the rebase will be aborted.
	
	 Note that empty commits are commented out
	~                                                                               
	<objetos/projeto_teste/.git/rebase-merge/git-rebase-todo" 22L, 792C 2,1          Tudo
	=================================================================================

Esta é a tela do rebase interativo.
Podemos ver que existem cinco commits. Vamos junta-los em um único commit com o comando squash.
Para fazer isso vamos trocar o comando de pick para squash a partir da segunda linha:

Exemplo:

	pick e7c4b61 form cliente
	squash 0996f15 - refatora cliente key
	squash 5574fa7 - corrige buscador de cliente
	squash 466c221 revisao de codigo
	squash df70f7c - revisao de código

Ao salvar (:wq no vi) e sair do editor, o vi vai reabrir novamente para que possamos fazer a
alteração da mensagem de commit.

Ao sair do vi ainda estamos dentro do processo de rebase. Podemos constatar isso pela mensagem no
prompt:

    (rmurad_20_teste|REBASE-i)

Caso existam conflitos no projeto temos que utilizar a ferramente git diff e corrigir os conflitos.
Feito isso vamos fazer um push do commit e verificar novamente os testes nos jenkis.

	$ git push origin rmurad_20_test --force

Rodamos novamente os testes no jenkis e constatamos que não houveram falhas

	========================
	Tests run: 169, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.836 sec
	
	Results :
	
	Tests run: 169, Failures: 0, Errors: 0, Skipped: 0
	
	[JENKINS] Gravando resultados de teste# Deploying the attached artifact {0}
	==============================

Somente após ter certeza que não há nenhum erro na branch podemos ir ao github e fazer o merge
do projeto na branch master.
Para realizar o merge siga até a aba _Pull Requests_ no repostitório do projeto no github, clique
em seu pull request e siga atá o botão _Merge Pull Request_, a seguinte mensagem surgirá:

    Merge pull request #147 from RicardoMurad/rmurad_01_rebase
    RMURAD_01: habilita artigo de rebase.
    
Se tudo estiver correto basta clicar no botão _Confirm Merge_.