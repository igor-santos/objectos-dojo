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
de quem os resolve, neste artigo utilizaremos o VI para a resolução de conflitos e mostraremos alguns
dos beneficios em utilizá-lo para resolução de conflitos.

##Resolvendo conflitos

Ao realizar um merge com uma branch nos deparamos com a seguinte mensagem, indicando um conflito
ao realizarmos o merge:

    mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ git merge mpiazzolla_conflitos_2
	Auto-merging config.txt
	CONFLICT (add/add): Merge conflict in config.txt
	Automatic merge failed; fix conflicts and then commit the result.
 
Primeiramente vamos abrir o arquivo `config.txt` utilizando o `VI`:

	mpiazzolla@estacao003 ~/kdo/projetos/objectos-dojo $ vi config.txt
	
	<<<<<<< HEAD
	A
	B
	C

	=======
	T
	Z
	Q
	>>>>>>> mpiazzolla_conflitos_2
 
 A interface do `VI` é bem simples não há muito que comentar a seu respeito, seu forte está em suas 
 funcionalidades