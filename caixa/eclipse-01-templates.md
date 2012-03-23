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

Para automatizar o nosso trabalho o eclipse possui uma série de templates que acabam poupando o 
desenvolvedor de ter que escrever blocos de código comum em classes de teste como o `dbUnit` 
citado acima.

##Criando seu template

Criar um template não é tão difícil. No eclipse siga para `Window > Preferences > Java > Editor > Templates`,
conforme a figura abaixo:

![eclipse-templates] (http://i1245.photobucket.com/albums/gg582/img_repo/eclipse-artigos/eclipse-templates.png)

Aqui temos uma listagem de todos os templates do eclipse, lembrando que nosso objetivo é criar
um template que automatize nossas tarefas em classes de teste, na chamda do `dbUnit`.

Para criar nosso template basta clicar na opção `New`, uma janela perguntando o nome do template e o conteúdo
do mesmo irá aparecer, basta fornecer um nome, lembrando que para diferenciar os templates criados pelo desenvolvedor
dos templates do eclipse inserimos um "_" no início do nome do template, portanto o nome de nosso template será
`_dbUnit`, em seguida criaremos um `Pattern` que seria o conteúdo do nosso template, ou seja, toda vez que chamarmos
o template o seguinte código aparecerá na tela de seu editor. A imagem abaixo deixa tudo mais claro: 

![novo-template] (http://i1245.photobucket.com/albums/gg582/img_repo/eclipse-artigos/novo-template.png)

Clique em `Ok` para finalizar a criação de nosso template, em seguida clique em `Apply` para efetuar
a criação do template.

##Invocando o template em seu código

Para chamar o template recém-criado em seu código basta digitar o nome do template no editor e pressionar
`Enter`, com isso aquele bloco de código que fornecemos na imagem acima será invocado em seu editor,
atente a imagem abaixo:

![dbUnit-macro] (http://i1245.photobucket.com/albums/gg582/img_repo/eclipse-artigos/eclipse_macro.png)

###Templates obrigatórios

Segue abaixo uma lista de templates obrigatórios que ajudarão você a automatizar suas tarefas, como foi passado
o procedimento de criação de templates informaremos apenas o `nome` do template e seu respectivo `pattern`. 

Nome:

    _uoe

Pattern:

    throw new UnsupportedOperationException();

Nome: 

    _dbUnit

Pattern:

    @Inject
    private DBUnit dbUnit;

    @BeforeClass
    public void prepararDBUnit() {
      dbUnit.loadDefaultDataSet();
    }
