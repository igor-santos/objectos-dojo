---
layout: post-alpha
title: "Git :: Fiz um merge incorreto. E agora, como resolvo?"
author: "Hellen Carla Paixão Escarate"
published: false
partof: git
num: 4
outof: 4
---

### Fiz um merge em uma branch que eu não deveria fazer, e agora?

Por acidente, você pode acabar fazendo merges ou pulls de uma branch para outra completamente distinta.

Mandando um pull request com essas atualizações não pode ocorrer! São um RISCO e DE FORMA ALGUMA podem ser aceitas e integradas no projeto original!

### Mas e agora, como posso resolver isso?

Primeiro, siga os passos:

Execute o comando ``git log`` desta forma:

    git log -10
    
Ou com os parâmetros que desejar.

Na lista que aparecerá, tente achar qual foi seu último commit válido, ou seja, o anterior ao pull ou ao merge incorreto.

<div class="alert alert-info">
  O pull nada mais é que um fetch e um merge no mesmo comando.
</div>

Os commit são identificados com hash de caractéres como:

    commit 51c50d0b710d643fadc15sd72ea9b0e88f658b69

Em:

        Testes: Novas classes de teste
    
    commit 51c50d0b710d643fadc15sd72ea9b0e88f658b69
    Author: Robert Styles <robert.styles@domain.com.br>
    Date:   Mon Mar 12 10:10:29 2007 -0300

<div class="alert alert-info">
 Uma dica é filtrar a lista, da maneira que preferir, por commits contendo uma linha com Merge, como esse:
</div>
        Web: Feito merge com servico-aplicacao

    commit f629c09f8fac798010fd5a2fd80821a668432df9
    Merge: 9c278d7 7fec7d7
    Author: Robert Styles <robert.styles@domain.com.br>
    Date:   Sat Mar 10 19:56:08 2007 -0700

Antes de continuarmos para as soluções, que tal simularmos um merge errado?



Como existe vasta base de conhecimento dos comandos git na Web (vide algumas em __Referências__), a continuação deste artigo focará em quando usar cada solução, seguindo com o exemplo acima.

#### Quando usar o git reset?



#### Quando usar o git revert?



#### Quando usar o git commit --ammend?



#### Quando usar o git rebase -i?



#### Quando fazer um re-fork

Em alguns casos, quando seu trabalho na branch acabou de começar, existem poucos arquivos no projeto original ou existem uma série de erros na execução dos comandos anteriores, uma opção simples é refazer a fork do projeto original.

Para isso, você pode seguir os seguintes passos:

1. Faça cuidadosamente o backup de todos os arquivos importantes que você criou ou modificou
1. Remova a pasta do projeto da sua máquina, usando um comando como:

    rm -f -R ~/kdo/projetos/meu-projeto/

1. Vá no repositório do fork meu-projeto em sua página.

1. Clique no botão "Admin", na parte superior direita da página



1. Clique no botão "Delete this repositoy" ... 



... e confirme a remoção do fork clicando em "I understand, delete this repository"

1. Acesse a página do projeto original e faça novamente um fork e clone ele (se não lembrar como fazer clique [aqui][1])

1. Use os comandos

    mkdir ~/kdo/projetos/meu-projeto
    cd ~/kdo/projetos/meu-projeto

Se necessário configure a url do projeto original usando

    $git remote add <alias-da-url> <url-do-projeto-original>

Seu fork estará agora idêntico a úĺtima versão do projeto

1. Entre na branch correta e recupere os backups feitos.

1. Pronto, você acaba de desfazer os comandos errados usando uma das formas mais árduas (tirando os possíveis erros nos comandos citados nas outras seções!)

### Referências

[objectos-dojo :: Tutorial gh-pages][1]



[id]: /path/to/img.jpg "Admin"
[id]: /path/to/img.jpg "Delete this repositoy"
[id]: /path/to/img.jpg "I understand, delete this repository"


[1]: ../contribua/00-importar.html "objectos-dojo :: Tutorial gh-pages"