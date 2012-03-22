---
layout: post-alpha
title: "Git :: Fiz um merge incorreto. E agora, como resolvo?"
author: "Caio Catanzaro Petreanu"
published: true
partof: git
num: 4
outof: 4
---

## Fiz um merge em uma branch que eu não deveria fazer, e agora?

Digamos que, por acidente, você pode acabar fazendo merges ou pulls de uma branch para outra completamente distinta..

E você acaba mandando um Pull Request com essas atualizações. Bem, isso não pode ocorrer! De forma alguma essas atualizações podem ser aceitas e integradas no projeto original!

<div class="alert alert-info">
  Obs. Um git pull nada mais é que um <b>fetch e um merge no mesmo comando</b>.
</div>

Bom, o dano estám feito. Correto..?

Não, cabe a você arrumar isso! E eu estou aqui para guiá-lo na resolução dessa lasqueira. Então mãos-a-obra!

### 1. Casos de uso

Se você se consegue marcar uma das opções abaixo, está no lugar certo!

<table class="table table-striped">
 <tr>
   <td class="tac col2em">
    <input type="checkbox" />
   </td>
   <td>
    Fiz várias alterações erradas em um commit
   </td>
   <td>
    <a href="#revert">help!</a>
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <input type="checkbox" />
   </td>
   <td>
    Mandei um push de uma feature pro lugar errado
   </td>
   <td>
    <a href="#outros">help!</a>
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <input type="checkbox" />
   </td>
   <td>
    Fiz um merge com uma branch totalmente diferente da que estou
   </td>
   <td>
    <a href="#outros">help!</a>
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <input type="checkbox" />
   </td>
   <td>
    Removi arquivos muito grandes que haviam em meu projeto, mas eles aparecem em commits anteriores
   </td>
   <td>
    <a href="#outros">help!</a>
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <input type="checkbox" />
   </td>
   <td>
    Fiz muitas alterações erradas em vários commits
   </td>
   <td>
    <a href="#outros">help!</a>
   </td>
 </tr>
</table>

### 2. Introdução

Entenda, as formas de solucionar esse problema são diversas e possuem características próprias, mas é importante fazer uma breve explicação delas para que fique claro quais são adequadas a qual situação.

Primeiro, vamos entender de __histórico__. Ele é aquele cara que mantém registrada a sequência de todas as atualizações realizadas no projeto local e remoto (fork e original).

Entendeu? Então vamos aos comandos!

* No ``git revert`` é feito um novo commit, contendo as atualizações inversas aos commits selecionados. E esse commit ainda é reversível! Usar ele mantém todo o histórico anterior, mas em alguns casos pode não ser o que queremos;

<div class="alert alert-info">

 Ele é praticamente mandatório para todos os casos em que manter os commits anteriores no histórico não representa um problema.

</div>

* No entanto, nos demais casos podemos usar um dos 3 procedimentos/comandos:``git checkout``, ``git reset --hard`` e ``git rebase -i``. Com eles, "quebramos" ou remontamos o histórico da branch, sendo necessário fazer um ``push --force`` para ser mandado para o Github. Essas ações não podem ser revertidas e por causa disso, devem ser usadas com muita cautela e em situações claras e específicas!

<div class="alert alert-info">

 <p>Caso você não saiba, fique sabendo: tudo que está no histórico é mantido. Imagine que toda vez que alguem for de alguma forma acessar, ou fazer download, do seu projeto, ele tenha que pegar meros 800 mb a mais, desnecessários.</p>
 <p>O que você acha disso?</p>
 <p>É por isso que nesses casos, em especial quando lidamos com a presença de históricos indesejados como uma legião de arquivos e/ou arquivos <i>colossus</i> (muito grandes).</p>
 <p>Por isso, nesses casos <b>devemos</b> alterar o histórico, para remover presenças indesejaveis em nossos diretórios do projeto.</p>
 <p>Repetindo: Eles representam tarefas simples, mas devem ser usados com <b>extrema cautela</b>! Afinal, agora estaremos lidando mais diretamente com o histórico.</p>
 
</div>

# Importante!!!

__Por apagar histórico, nas situações de trabalho apenas faça o ``push --force``, e somente apenas se você tiver absoluta certeza, sem qualquer sombra de dúvidas e de preferência certo de um alinhamento estrelar favorável.__

* E a solução menos recomendada, mas mais prática é refazer a fork do projeto e recuperar os backups. É mantido o histórico do projeto original e os backups serão adicionados em um ou mais commits novos.

<div id="revert"> </div>

#### Antes de começar

* Faça um novo repositório chamado __repo__ no Github;

<p></p>

* Coloque-o diretório /tmp/repo.

### 3. Cenário alfa

A partir da master crie uma branch chamada __cenario__.

    $ git checkout master
    $ git checkout -b letras

Nela crie, adicione, faça commit e push, __para cada__ um de 5 arquivos de texto (distintos).

    $ touch A.txt
    $ git add A.txt 
    $ git commit -m "Add: A.txt"
    $ git push origin letras
     
    $ touch B.txt
    $ git add B.txt 
    $ git commit -m "Add: B.txt"
    $ git push origin letras
    
    $ touch C.txt
    $ git add C.txt 
    $ git commit -m "Add: C.txt"
    $ git push origin letras
    
    $ touch D.txt
    $ git add D.txt 
    $ git commit -m "Add: D.txt"
    $ git push origin letras
    
    $ touch E.txt
    $ git add E.txt 
    $ git commit -m "Add: E.txt"
    $ git push origin letras

Volte a master e crie uma branch chamada numeros, entre nela, e execute os comandos abaixo.

    $ git checkout master
    $ git checkout -b numeros

    $ touch 1.txt
    $ git add 1.txt 
    $ git commit -m "Add: 1.txt"
    $ git push origin numeros
     
    $ touch 2.txt
    $ git add 2.txt 
    $ git commit -m "Add: 2.txt"
    $ git push origin numeros
    
    $ touch 3.txt
    $ git add 3.txt 
    $ git commit -m "Add: 3.txt"
    $ git push origin numeros
    
    $ touch 4.txt
    $ git add 4.txt 
    $ git commit -m "Add: 4.txt"
    $ git push origin numeros
    
    $ touch 5.txt
    $ git add 5.txt 
    $ git commit -m "Add: 5.txt"
    $ git push origin numeros

Cheque no Github se todos os arquivos estão presentes. Viu?! Ok, blz! Então volte para o terminal.

Crie a partir da master 3 novas branches usando os comandos:

    $ git checkout master
    $ git checkout -b cenario_revert
    $ git checkout -b cenario_procedimento_de_checkout
    $ git checkout -b cenario_reset
    $ git checkout -b cenario_rebase

### 4. O git revert

Como dito anteriormente, o revert:

* É feito a partir de um novo commit;

* Mantém o histórico anterior a esse commit;

* Pode reverter um ou mais commits;

* Pode ser revertido por outro revert.

Portanto, ele é indicado nos casos em que são poucos os arquivos indesejáveis enviados, representando um histórico cujo tamanho não é significantemente grande a ponto de termos de alterá-lo ou removê-lo. 

Antes de continuarmos para a solução, por que não simularmos algo dando errado?

#### 4.1 Simulando um erro

Entre na branch do tópico e faça um merge dela com numeros.

    $ git checkout cenario_revert
    $ git merge numeros

Ok! Agora você terá os arquivos de textos do 1.txt ao 5.txt na sua branch. Faça um push.

    $ git push origin cenario_revert

Verifique lá no Github, sua branch agora contém 5 arquivos. Certo?

Muito bem! Agora remova os arquivos pares, ou seja 2.txt e 4.txt. Crie também o 7.txt.

    $ rm 2.txt 4.txt
    $ touch 7.txt

Adicione os arquivos com __add__, faça o commit e o push.

    $ git add 2.txt
    $ git add 4.txt
    $ git add 7.txt
    $ git commit -m "Arquivos pares"
    $ git push origin cenario_revert

Verifique no Github se sua branch está apenas com arquivos ímpares.

Agora, imagine que você não podia ter adcionado o arquivo 7.txt e muito menos removido o 4.txt. E agora, como fazemos para reverter isso?

<div class="alert alert-info">
 Observação: Sempre cheque seus arquivos na branch, e seus Pull Requests com o <b>Diff</b> na página do Github para identificar aquilo que foi mandado para origin, ou que está sendo mandado para o projeto original. Se não souber verificar o problema, de nada adianta saber solucioná-lo!
</div>

#### 4.2 Solução

Devemos encontrar o commit incorreto e revertê-lo. Para isso você pode usar o Github. Se preferir faça como eu e use o ``git log`` assim:

    $ git log

Que retornará uma série de registros dos commits feitos, como os meus:

    commit 51283b9dee534378c6dba77e12c7e0adfb29493e
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:05:40 2012 -0300
    
        Arquivos pares
    
    commit 0b31782b35e1c80ba03ae4ae9da2c96b63e7c6fc
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:24:10 2012 -0300
    
        Add: 5.txt
    
    commit e702e819ab3a3bc74b147c50e9fe87064996aee7
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:24:06 2012 -0300
    
        Add: 4.txt
    
    commit 773fa02ebf3248c95e0f7c4d64560062b57052ad
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:24:03 2012 -0300
    
        Add: 3.txt
    
    commit 7e17f4dde6f43bd99d1b7abacf2a97f0846c263f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:23:59 2012 -0300
    
        Add: 2.txt
    
    commit 6987e2c1f584f40f4db1d79750879c8373858dc6
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 18:23:55 2012 -0300
    
        Add: 1.txt
    
    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

<div class="alert alert-info">
 No caso de você ter feito muitos logs após o commit errôneo, tente usar o <code>git log -10</code> para listar os 10 últimos commits (ou o número que desejar).
</div>

Usando o Github ou olhando com calma o log, podemos ver que o commit que precisamos reverter é o "Arquivos pares".

Muito bem. Copie o hash do commit (que no meu caso é 51283b9dee534378c6dba77e12c7e0adfb29493e) e aperte a tecla __Q__ para sair dessa tela.

Agora faça o revert conforme:

    git revert 51283b9dee534378c6dba77e12c7e0adfb29493e

Será aberto um editor de texto (no meu caso o VIM) contendo informação do revert a ser realizado. Para cada linha de atualização do próprio revert existe um __#__. Você deve remover todos os __#__ das novas atualizações dele que devem estar no novo commit.

Portanto, o texto que está assim:

    Revert "Arquivos pares"
    
    This reverts commit 51283b9dee534378c6dba77e12c7e0adfb29493e.
    
    # Please enter the commit message for your changes. Lines starting
    # with '#' will be ignored, and an empty message aborts the commit.
    # On branch cenario_revert
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
    #       copied:     7.txt -> 2.txt
    #       renamed:    7.txt -> 4.txt

Feche o arquivo, faça o push e veja tanto no git log quanto no site do Github as mudanças que ocorreram.

    $ git push origin cenario_revert

Você verá que há no histórico um novo commit que reverte as atualizações daquele que foi selecionado. 

    commit e22f46c3fb58bc2f382cdba5babd9b94fb300f29
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:11:31 2012 -0300
    
        Revert "Arquivos pares"
        
        This reverts commit 51283b9dee534378c6dba77e12c7e0adfb29493e.
        
            copied:     7.txt -> 2.txt
            renamed:    7.txt -> 4.txt

Simples, não? Mas e se precisarmos reverter um merge?

#### 4.3 Resolvendo um merge indesejado com revert

Faça um merge "acidental" com letras. Por acidental, quero dizer que esse merge não poderia ter sido feito, e muito menos mandado para a origin.

Vamos usar o que aprendemos para resolver isso:

    $ git log -10

Você porderá ver que apareceu um novo commit.

    commit 37687b9de11e7f12dd0ba20f02ac90e219498b08
    Merge: e22f46c 36277bb
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:15:50 2012 -0300
    
        Merge branch 'letras' into cenario_revert

Vamos reverter ele?

    git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08

Não funcionou né? Pois é, sabemos que um merge local deixa as duas branches envolvidas exatamente iguais. Independentemente de qual branch você está agora, ao reverter um merge deve ser informada para qual branch devemos voltar.

O que, nesse caso são __e22f46c__ (vinda da cenario_revert e representada por 1) e __36277bb__ (vinda da letras e representada por 2).

Em caso de dúvida, cheque o log. Fica fácil ver que o commit anterior na branch em que estamos é o 1 (e22f46c3fb58bc2f382cdba5babd9b94fb300f29).

Agora usamos a opção ``git revert <commit> -m <parent>`` para informar que o revert do merge deverá fazer com que voltem as alterações para o commit 1, proveniente da branch cenario_revert.

    $ git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08 -m 1

O VIM será aberto. Feche-o.

    Revert "Merge branch 'letras' into cenario_revert"
    
    This reverts commit 37687b9de11e7f12dd0ba20f02ac90e219498b08, reversing
    changes made to e22f46c3fb58bc2f382cdba5babd9b94fb300f29.
    
    # Please enter the commit message for your changes. Lines starting
    # with '#' will be ignored, and an empty message aborts the commit.
    # On branch cenario_revert
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
    #       deleted:    A.txt
    #       deleted:    B.txt
    #       deleted:    C.txt
    #       deleted:    D.txt
    #       deleted:    E.txt

    $ git push origin cenario_revert

 E faça o push. Voilá! Revertemos o merge!

#### 4.4 Resumo

<p></p>

__Você selecionou um ou mais commits em sequência, e a partir deles gerou um novo capaz de selecionar e reverter as atualizações dos anteriores e o enviou à origem.__

<div id="outros"> </div>

### 5. Cenário beta

Entre na branch __cenario_procedimento_de_checkout__ e execute os comandos ``git remote`` e ``git pull gh-pages`` abaixo. Ah, e não se esqueça de fazer. um push para origin disso!

    $ git checkout cenario_procedimento_de_checkout
    $ git remote add objectos https:github.com/objectos/objectos-dojo.git
    $ git pull objectos gh-pages
    $ git push origin cenario_procedimento_de_checkout

Irão vir todos os arquivos de gh-pages para sua branch local e remota. Veja lá no Github ver a quantidade de arquivos que entraram no seu projeto com esse commit.

E o log?

    $ git log

Nossa! Parece que ele está cheio de registros tanto da __cenario_procedimento_de_checkout__ quanto da __gh-pages__. Não é? Olhe o seu commit gerado pelo pull também, ele deve estar assim:

    commit 280fde94001ab2b8f89276c6a076d1e3f66ab019
    Merge: 426bca1 a5f651f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Tue Mar 20 14:17:40 2012 -0300

    Merge branch 'gh-pages' of https://github.com/objectos/objectos-dojo into cenario_procedimento_de_checkout

Ok, viu!? E agora? O que fazer? 

Bom, como todo bom leitor você não pulou o tópico de ``git revert`` e me sugere: 

> "Podemos dar um revert que volte as alterações desses  e está tudo resolvido!"

Muito bem! Concordo que é uma possibilidade. Maaass.. você não concorda comigo que todos esses arquivos que vieram continuarão mantidos no __histórico__?

E aí, o que você? O revert resolve? Com certeza!! Mas devemos usá-lo sabendo que toda vez que for feito merge, pull ou clone do projeto (ou fork) será baixado o gh-pages inteiro? Não mesmo!!!

É aí que entram os capítulos 6, 7 e 8!

Antes de ir para eles, faça um merge com direito a push das branches __cenario_reset__ e __cenario_rebase__ com a que estamos.

    $ git checkout cenario_reset
    $ git merge cenario_procedimento_de_checkout
    $ git push origin cenario_reset
    
    $ git checkout cenario_rebase
    $ git merge cenario_procedimento_de_checkout
    $ git push origin cenario_rebase

### 6. O procedimento do git checkout -b

Entre na branch __cenario_procedimento_de_checkout__ e se preferir, use o ``git log``.

    $ git checkout cenario_procedimento_de_checkout

Como pudemos ver ao montar o cenário, o merge feito com gh-pages ocorreu no commit 280fde94001ab2b8f89276c6a076d1e3f66ab019. Mas não é ele que usaremos para reverter o histórico.

Precisamos __do último commit válido antes do merge__. E, como gh-pages veio com vários outros commits, __esse commit não poderá ter vindo dela__.

Então consulte o Github e/ou o log e encontre esse commit. Seguindo o conceito de que o histórico é sequencial (como as pilhas de estrutura de dados), e com um pouco de memória, não tão será difícil assim.

No nosso caso, trabalharemos o nosso primeiro commit.

    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

<div class="alert alert-info">
 Lembre-se que a identificação do seu provavelmente será diferente.
</div>

Bom, chega de ladainha. Faça o ``checkout -b`` para uma branch nova chamada __bkp__ pelo commit! Assim:

    $ git checkout -b bkp 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Switched to a new branch 'bkp'

Remova a branch __cenario_procedimento_de_checkout__ e renomeie __bkp__ para cenario_procedimento_de_checkout.

    $ git branch -D cenario_procedimento_de_checkout
    $ git branch -M cenario_procedimento_de_checkout

E ai..? O que temos agora?? Aha! Isso mesmo, pelo visto a branch __cenario_procedimento_de_checkout__ já não é mais a mesma... ela é, digamos, o commit "first commit".

Mas pra que isso? Se você quiser descobrir, faça esse comando:

    $ git push origin cenario_procedimento_de_checkout
    To git@github.com:cpetreanu/repo.git
     ! [rejected]        cenario_procedimento_de_checkout -> cenario_procedimento_de_checkout (non-fast-forward)
    error: failed to push some refs to 'git@github.com:cpetreanu/repo.git'
    To prevent you from losing history, non-fast-forward updates were rejected
    Merge the remote changes (e.g. 'git pull') before pushing again.  See the
    'Note about fast-forwards' section of 'git push --help' for details.

Eita!! Não funcionou? O que houve aí?!

> To prevent you from losing history, non-fast-forward updates were rejected

Parece pra mim que você está recebendo um alerta.. E pelo visto é porque estamos pra alterar o histórico. E agora?

__Force seu push__, ignorando o aviso:

    $ git push origin cenario_procedimento_de_checkout --force
    Total 0 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
     + 280fde9...426bca1 cenario_procedimento_de_checkout -> cenario_procedimento_de_checkout (forced update)

Veja só.... __(forced update)__. Ah, mas será que funcionou? Vá lá ver no Github e depois volte aqui.

Aqui parece que funcionou, veja como ficou todo o meu git log:

    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

Beleza, entendeu como funciona?

#### 6.1 Resumo

<p></p>

__Você entrou em um commit - de preferência o último válido - e substituiu a branch "corrompida" por ele. Por fim, realizarou um ``push --force`` para a origem, revertendo todo o histórico para o estado desse commit.__

### 7. O git reset --hard

Entre na branch __cenario_reset__.

    $ git checkout cenario_reset

Estaremos usando o cenário criado no capítulo 2. Ao invés de fazer o procedimento de checkout do capítulo anterior, existe a opção de executar todo ele em apenas 2 comandos! O ``git reset --hard`` e o ``git push --force``.

Sabemos já o último commit válido (426bca1b80fd19e22d5f3fb31f49b3f15698142f). Use o comando abaixo para que ele se torne o HEAD (último commit) da sua branch.

    $ git reset --hard 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    HEAD is now at 426bca1 first commit

<div class="alert alert-info">
 A opção <code>--hard</code> faz com que todos os arquivos no stage, que ainda não foram para um commit, sejam apagados. Em contraposição a ela, existe a opção <code>--soft</code>.
</div>

Feita a alteração do HEAD, faça o push para origin. Claro que você só conseguirá isso usando o argumento __--force__.

    $ git push --force origin cenario_reset
    Total 0 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
     + 280fde9...426bca1 cenario_reset -> cenario_reset (forced update)

Feito isso, verifique no Github por traços da gh-pages nas sua branch.

Nada né? Perfeito!

#### 7.1 Resumo

__Nesse caso, você apontou o commit HEAD de sua branch para o último commit válido antes do merge, e mandou essa alteraçõa de histórico para a origem remota. Pode-se concluir que trata-se uma solução análoga à de checkout.__

### 8. O git rebase -i

Entre na sua branch __cenario_rebase__.

    

Novamente estaremos no mesmo cenário. Dê um __ls -l__ e confira se quiser!

Temos o cenário e o commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f. Vamos usar o rebase no modo interativo (<b>-i</b>, que abre um editor de texto) ver as atualizações de todos os commits entre "first commit" e o HEAD (cenario_rebase).

    $ git rebase -i 426bca1b80fd19e22d5f3fb31f49b3f15698142f cenario_rebase

Note os vários commits vindos de gh-pages. Aqui podemos modificar aqueles que serão mantidos ou removidos do histórico, __remontando-o__.

Apague todos os commits menos um (não importa qual seja). Salve e feche o arquivo. Como podemos ver, o rebase parou por causa de conflitos de path. Faça um git status.

    $ git checkout cenario_rebase
    error: could not apply b82ad6a... Finalizado objetos falsos
    hint: after resolving the conflicts, mark the corrected paths
    hint: with 'git add <paths>' and run 'git rebase --continue'
    Could not apply b82ad6a... Finalizado objetos falsos
    
    $ git status
    # Not currently on any branch.
    # Unmerged paths:
    #   (use "git reset HEAD <file>..." to unstage)
    #   (use "git add/rm <file>..." as appropriate to mark resolution)
    #
    #	deleted by us:      procedimento/crud-entidade/00.2-criando-objetos-falsos.md
    #
    no changes added to commit (use "git add" and/or "git commit -a")

Existe um path incorreto/inexistente para o arquivo mostrado. Para resolver isso vamos remover essa atualização, e por fim dar continuidade ao rebase.

    $ git checkout -f
    
    $ git status
    # Not currently on any branch.
    nothing to commit (working directory clean)
    
    $ git rebase --continue
    Successfully rebased and updated refs/heads/cenario_rebase.

Faça um ``push --force`` e vejá no Github os resultados. E o log também só pra ter certeza!

    $ git push origin cenario_rebase

Pronto! Trabalho realizado com sucesso!

#### 8.1 Resumo

__Assim, você conseguiu reverter o merge (e histórico) ao remover todos os commits (menos 1), entre o "first commit" e o HEAD da branch com o merge com gh-pages. Na sequência removeu as alterações desse commit, fazendo com que o único remanescente fosse o nosso querido 426bca1b80fd19e22d5f3fb31f49b3f15698142f.__

### 9. Refazer a fork?

Em alguns casos, quando seu trabalho na branch acabou de começar, existem poucos arquivos no projeto original ou existem uma série de erros na execução dos comandos anteriores, uma opção simples é refazer a fork do projeto original.

Para isso, você pode seguir os seguintes passos:

* Faça cuidadosamente o backup de todos os arquivos importantes que você criou ou modificou

* Remova a pasta do projeto da sua máquina, usando um comando como:

<p></p>

        rm -f -R /tmp/repo/

* Vá no repositório do fork __repo__ em sua página.

* Clique no botão "Admin", na parte superior direita da página

* Clique no botão "Delete this repository" ... e confirme a remoção do fork clicando em "I understand, delete this repository"

* Acesse a página do projeto original e faça novamente um fork e clone ele (se não lembrar como fazer clique [aqui][1])

* Use os comandos:

<p></p>

        mkdir /tmp/repo/
        cd /tmp/repo

Se necessário configure a url do projeto original usando

    $git remote add <alias-da-url> <url-do-projeto-original>

Seu fork estará agora idêntico a última versão do projeto

* Entre na branch correta e recupere os backups feitos.

* Pronto, você acaba de desfazer os comandos errados usando uma das formas mais árduas (tirando os possíveis erros nos comandos citados nas outras seções!)

### 10. Finalizando

Obrigado aos meus leitores pela paciência e espero que tenham com este trabalho aprendido coisas novas, mas principalmente úteis no dia-a-dia de cada um.

Caso não precisem mais, não se esqueçam de remover os arquivos, branchs, forks criados aqui para aprendizado.

<div id="referencias"> </div>

### 11. Referências<div id="referencias"> </div>

<p></p>

[objectos-dojo :: Tutorial gh-pages][1]

[Git Book - Undoing in Git][2]

[0]: #referencias "Referências"
[1]: ../contribua/00-importar.html "objectos-dojo :: Tutorial gh-pages"
[2]: http://book.git-scm.com/4_undoing_in_git_-_reset,_checkout_and_revert.html "Git Book - Undoing in Git - Reset, Checkout and Revert"