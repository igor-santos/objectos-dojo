---
layout: post-alpha
title: "Git :: Resolvendo conflitos com o VI"
author: "Marcos Piazzolla"
published: true
partof: git
num: 5
outof: 5
---

##Introdução

Ao trabalhar com sistemas de controle de versão como o `GitHub` é muito comum o desenvolvedor ter 
que resolver conflitos. A utilização do editor de texto para a resolução dos mesmos fica a critério
de quem os resolve, neste artigo utilizaremos o `VI` para a resolução de conflitos e mostraremos alguns
dos benefícios em utilizá-lo para resolução de conflitos.

##Resolvendo conflitos

Ao realizar um merge com uma branch nos deparamos com a seguinte mensagem, indicando um conflito:
	
    mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ git merge mpiazzolla_conflitos_2
	Auto-merging config.txt
	CONFLICT (add/add): Merge conflict in config.txt
	Automatic merge failed; fix conflicts and then commit the result.
 
Primeiramente vamos abrir o arquivo `config.txt` utilizando o `VI`:

	mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ vi config.txt
	
![editor-tela] (http://i1245.photobucket.com/albums/gg582/img_repo/vi/editor-tela.png)	
	 
A interface do `VI` é bem simples não há muito que comentar a seu respeito, seu forte está mesmo
em suas funcionalidades que são muito úteis, mesmo o editor não disponibilizando uma interface gráfica 
muito amigável, veremos isso utilizando o mesmo para resolução de conflitos. 

###Selecionando linhas

Vamos supor que as informações que vem do `HEAD` estejam corretas e as informações abaixo dele devem
ser removidas. Como já sabemos quais linhas desejamos excluir, podemos alternar para o modo gráfico do
editor pressioando `ESC` primeiramente para sair do modo texto, caso esteja nele, em seguida pressione
`Shift + v` com isso o editor passa para a seleção de linhas no modo gráfico, utilize as setas do 
teclado e selecione as linhas a serem removidas, atente a imagem abaixo:

![vi-shift-v] (http://i1245.photobucket.com/albums/gg582/img_repo/vi/shift-v.jpg)

###Excluindo linhas

Como já foi definido o que será removido, basta utilizar a operação de exclusão que é realizada através das
teclas `d` ou `Delete`, fica a critério do desenvolvedor qual delas utilizar. Ao pressionarmos qualquer uma
destas teclas o editor lança uma mensagem na parte inferior esquerda informando quantas linhas foram excluídas

![linhas-excluidas] (http://i1245.photobucket.com/albums/gg582/img_repo/vi/linhas-excluidas.png)

Ainda sobrou a linha indicando o `HEAD`, mova o cursor até esta linha e apague-a utilizando `dd`,
como não temos nenhum texto selecionado temos de pressionar o `d` duas vezes.

###Apaguei acidentamente uma linha que não devia, como corrigir isso?

Em algum momento na correção do conflito o desenvolvedor pode acidentalmente apagar uma linha que
não deveria ser removida, o `VI` não disponibiliza recursos como `CTRL + Z` para desfazer ações, mas
possui uma função muito prática que é o `undo`, que desfaz a última ação. Para utilizá-lo, basta pressionar 
`ESC` para sair do modo texto e em seguida digite `:undo`, assim a última ação será desfeita.

###Salvando as alterações no arquivo do conflito

Existe um momento onde é preciso efetuar as alterações no arquivo do conflito, diferente dos outros editores 
o `VI` não possui o tão conhecido `CTRL + S` para salvar as alterações no documento, para que seja
possível salvar as atualizações existe a função `w` que salva todas as alterações realizadas até o momento, 
basta pressionar `ESC` para sair do modo texto e em seguida pressioanar `:w` 

![salvar-w] (http://i1245.photobucket.com/albums/gg582/img_repo/vi/salvar-w.png)

Como é mostrado acima o editor lança uma mensagem, indicando que as alterações foram gravadas no arquivo

###Saindo do editor

Chegou o momento de realizar um commit e enviar as informações ao `GitHub` para resolver os conflitos de 
vez, antes disso devemos sair do editor através da função `q`, novamete, basta pressionar `ESC` para sair do 
modo texto caso esteja nele e pressionar `:q`, assim voltamos para o terminal.

	 mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $
	 
Em seguida basta adicionar o arquivo e realizar o commit:
	
	mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ git add config.txt
	mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ git commit -m "Resolvido conflito em config.txt"
	mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ git push origin mpiazzolla_conflitos