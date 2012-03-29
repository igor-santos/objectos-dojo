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
dos benefícios em utilizá-lo para resolução de conflitos.

##Resolvendo conflitos

Ao realizar um merge com uma branch nos deparamos com a seguinte mensagem, indicando um conflito:

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
 
A interface do `VI` é bem simples não há muito que comentar a seu respeito, seu forte está mesmo
em suas funcionalidades que são muito úteis, mesmo o editor não disponibilizando uma interface gráfica 
muito amigável, veremos isso utilizando o mesmo para resolução de conflitos. 

Pois bem, como fora mostrado acima ao realizarmos um merge um conflito aconteceu, visualizamos o arquivo
através do `VI`, em seguida resolvemos de vez o conflito. Agora devemos decidir o que está correto e 
que deve ser removido, vamos supor que as informações que vem do `HEAD` estejam corretas e as informações
abaixo devem ser removidas, para isso podemos navegar pelo documento e utilizar a função delete 
pressionando duas vezes a tecla 'd'(`dd`), isso irá remover a linha onde o cursor está localizado, 
repitada o processo para as outras linhas, remova também os sinais de `=` e a linha final com os
sinais de `>>`

Pronto! resolvemos os conflitos, mas e agora como sair do editor para que o push possa ser realzado? Simples,
basta utilizar outra das funções do editor, o `q` de quit (sair), lembrando que não podemos simplesmente
sair do editor sem salvar as autualizações realizadas, por isso antes de sair utilize a função `w` write 
para salvar as atualizações, em seguida podemos sair do editor e realizar o push para o GitHub.

Ao invés de efetuar um salvar: `w` e em seguida sair: `q`, podemos combinar estas funções chamando `wq`,
assim salvamos nosso arquivo e voltamos para o terminal, com isso não é preciso ter que chamar uma 
função por vez.
