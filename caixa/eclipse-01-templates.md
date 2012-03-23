---
layout: post
title: "Eclipse :: Templates"
author: "Marcos Piazzolla"
user: "MarcosPizzolla"
date: "2012-23-03"
published: true
partof: eclipse
num: 1
outof: 1
---

##Introdução

Muitas vezes quando fazemos algum programa temos que definir blocos try/catch, lançar exceções e até 
mesmo definir blocos de código repetido que são utilizados muitas vezes em classes de teste, como o 
`prepararDBUnit()`:

    @Inject
    private DBUnit dbUnit;
    
    @BeforeClass
    public void prepararDBUnit() {
      dbUnit.loadDefaultDataSet();
    }

Chega a ser até cansativo. Se pararmos para pensar perdemos até um pouco de tempo dependendo do tamanho
do bloco de código repetido que estamos digitando, para automatizar o nosso trabalho o eclipse possui
uma série de templates que acabam poupando o desenvolvedor de ter que escrever blocos de código
comum em classes de teste como o `dbUnit` citado acima.

##Criando seu template

Criar um template não é tão difícil. No eclipse siga para `Window > Preferences > Java > Editor > Templates`,
conforme a figura abaixo:

![eclipse-templates] (http://i1245.photobucket.com/albums/gg582/img_repo/eclipse-artigos/eclipse-templates-1.png)

Aqui temos uma listagem de todos os templates do eclipse, lembrando que nosso objetivo é criar
um template que automatize nossas tarefas em classes de teste, na chamda do `dbUnit`.

Para criar nosso template basta clicar na opção `New`, uma janela perguntando o nome do template e conteúdo
do mesmo irá aparecer, basta fornecer um nome, no caso definimos como `_dbUnit`, em seguida criaremos um
`Pattern` que seria o conteúdo do nosso template, ou seja, toda vez que chamarmos o template o seguinte
código aparecerá na tela de seu editor, na imagem abaixo tudo fica mais claro: 

![novo-template] (http://i1245.photobucket.com/albums/gg582/img_repo/eclipse-artigos/novo-template.png)

Clique em `Ok` para finalizar a criação de nosso template, em seguida clique em `Apply` para efetuar
a criação do template.

##Invocando o template em seu código

Para chamar o template recém-criado em seu código basta digitar o nome do template no editor e pressionar
`Enter`, com isso aquele bloco de código que fornecemos na imagem acima será invocado em seu editor,
atente a imagem abaixo:

![dbUnit-macro] (http://i1245.photobucket.com/albums/gg582/img_repo/eclipse-artigos/dbuint-macro.png)

###Outros templates interessantes

Segue abaixo uma lista de templates que podem ajudar você a automotizar suas tarefas, como já sabemos 
como criar um template informaremos apenas o `nome` do template e seu respectivo `pattern`. 

Nome:

    _unsupported

Pattern:

    throw new UnsupportedOperationException();