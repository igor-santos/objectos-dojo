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
  Obs. Um git pull nada mais é que um fetch e um merge no mesmo comando.
</div>

Bom, o dano estám feito. Correto..?

Não, cabe a você arrumar isso! E eu estou aqui para guiá-lo na resolução dessa lasqueira. Então mãos-a-obra!

### Introdução

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
 <p>É por isso que nesses casos, em especial quando lidamos com a presença de históricos indesejados como uma legião de arquivos e/ou Arquivos <i>colossus</i> (muito grandes).</p>
 <p>Por isso, nesses casos <b>devemos</b> alterar o histórico, para remover presenças indesejaveis em nossos diretórios do projeto.</p>
 <p>Repetindo: Eles representam tarefas simples, mas devem ser usados com <b>extrema cautela</b>! Afinal, agora estaremos lidando mais diretamente com o histórico.</p>
</div>

# Importante!!!

__Por apagar histórico, nas situações de trabalho apenas faça o ``push --force``, e somente apenas se você tiver absoluta certeza, sem qualquer sombra de dúvidas e de preferência certo de um alinhamento estrelar favorável.__

E a solução menos recomendada, mas mais prática:

* Já quando se decide refazer a fork do projeto e recuperar backups, é mantido o histórico do projeto original e os backups serão adicionados a um ou mais commits novos.

__Observação__: Como existe vasta base de conhecimento dos comandos git, me limito a focar em quando e como usar cada uma dessas soluções a partir dos casos particulares que criaremos juntos.

### 1. Montando os cenários

#### 1.1 Antes de começar

* Faça um novo repositório chamado __repo__ no Github;

* Coloque-o diretório /tmp/repo.

#### 1.2 Criando o cenário_alfa

A partir da master crie uma branch chamada __cenario__.

    $ git checkout master
    $ git checkout -b cenario_alfa

Nela crie, adicione, faça commit e push, __para cada__ um de 5 arquivos de texto (distintos).

    $ touch A.txt
    $ git add A.txt 
    $ git commit -m "Add: A.txt"
    $ git push origin cenario_alfa
     
    $ touch B.txt
    $ git add B.txt 
    $ git commit -m "Add: B.txt"
    $ git push origin cenario_alfa
    
    $ touch C.txt
    $ git add C.txt 
    $ git commit -m "Add: C.txt"
    $ git push origin cenario_alfa
    
    $ touch D.txt
    $ git add D.txt 
    $ git commit -m "Add: D.txt"
    $ git push origin cenario_alfa
    
    $ touch E.txt
    $ git add E.txt 
    $ git commit -m "Add: E.txt"
    $ git push origin cenario_alfa

Volte a master e crie uma branch chamada cenario_beta, entre nela, e execute os comandos abaixo.

    $ git checkout master
    $ git checkout -b cenario_beta

    $ touch 1.txt
    $ git add 1.txt 
    $ git commit -m "Add: 1.txt"
    $ git push origin cenario_beta
     
    $ touch 2.txt
    $ git add 2.txt 
    $ git commit -m "Add: 2.txt"
    $ git push origin cenario_beta
    
    $ touch 3.txt
    $ git add 3.txt 
    $ git commit -m "Add: 3.txt"
    $ git push origin cenario_beta
    
    $ touch 4.txt
    $ git add 4.txt 
    $ git commit -m "Add: 4.txt"
    $ git push origin cenario_beta
    
    $ touch 5.txt
    $ git add 5.txt 
    $ git commit -m "Add: 5.txt"
    $ git push origin cenario_beta

Cheque no Github se todos os arquivos estão presentes. Viu?! Ok, blz! Então volte para o terminal.

Crie a partir da master 3 novas branches usando os comandos:

    $ git checkout master
    $ git checkout -b cenario_revert
    $ git checkout -b cenario_procedimento_de_checkout
    $ git checkout -b cenario_reset
    $ git checkout -b cenario_rebase

### 2. O git revert

Como dito anteriormente, o revert:

* É feito a partir de um novo commit;

* Mantém o histórico anterior a esse commit;

* Pode reverter um ou mais commits;

* Pode ser revertido por outro revert.

Portanto, ele é indicado nos casos em que são poucos os arquivos indesejáveis enviados, representando um histórico cujo tamanho não é significantemente grande a ponto de termos de alterá-lo ou removê-lo. 

Antes de continuarmos para a solução, por que não simularmos algo dando errado?

#### 2.1 Simulando um erro

Entre na branch do tópico e faça um merge dela com cenario_beta.

    $ git checkout cenario_revert
    $ git merge cenario_beta

Ok! Agora você terá os arquivos de textos do 1.txt ao 5.txt na sua branch. Faça um push.

    $ git push origin cenario_revert

Faça outro merge, agora com cenario_beta, e finalize com um push.

    $ git merge cenario_beta
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

#### 2.2 Solução

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

Usando o Github ou olhando com calma o log, podemos ver que o commit que o commit que precisamos reverter é o "Arquivos pares".

Muito bem. Copie o hash do commit (que no meu caso é 51283b9dee534378c6dba77e12c7e0adfb29493e) e aperte a tecla __Q__ para sair dessa tela.

Agora faça o revert conforme:

    git revert 51283b9dee534378c6dba77e12c7e0adfb29493e

<div class="alert alert-info">
 O parâmetro na opção <b>-m</b> informa até onde será revertido. o <b>-1</b> informa para ela serão revertidas as atualizações do commit <b>51283b9dee534378c6dba77e12c7e0adfb29493e</b> até o anterior.
</div>

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

Deverá ficar assim:

    Revert "Arquivos pares"
    
    This reverts commit 51283b9dee534378c6dba77e12c7e0adfb29493e.
    
    # Please enter the commit message for your changes. Lines starting
    # with '#' will be ignored, and an empty message aborts the commit.
    # On branch cenario_revert
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
            copied:     7.txt -> 2.txt
            renamed:    7.txt -> 4.txt

Salve e feche o arquivo. Faça o push e veja tanto no git log quanto no site do Github as mudanças que ocorreram.

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

#### 2.3 Resolvendo um merge indesejado com revert

Faça um merge "acidental" com cenario_alfa. Por acidental, quero dizer que esse merge não poderia ter sido feito, e muito menos mandado para a origin.

Vamos usar o que aprendemos para resolver isso:

    $ git log -10

Você porderá ver que apareceu um novo commit.

    commit 37687b9de11e7f12dd0ba20f02ac90e219498b08
    Merge: e22f46c 36277bb
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 19:15:50 2012 -0300
    
        Merge branch 'cenario_alfa' into cenario_revert

Vamos reverter ele?

    git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08

Não funcionou né? Pois é, sabemos que um merge local deixa as duas branches envolvidas exatamente iguais. Independentemente de qual branch você está agora, ao reverter um merge deve ser informada para qual branch devemos voltar.

O que, nesse caso são __e22f46c__ (vinda da cenario_revert e representada por 1) e __36277bb__ (vinda da cenario_alfa e representada por 2).

Em caso de dúvida, cheque o log. Fica fácil ver que o commit anterior na branch em que estamos é o 1 (e22f46c3fb58bc2f382cdba5babd9b94fb300f29).

Agora usamos a opção __-m__ para informar que o revert do merge deverá fazer com que voltem as alterações para o commit 1, proveniente da branch cenario_revert.

    $ git revert 37687b9de11e7f12dd0ba20f02ac90e219498b08 -m 1

O VIM será aberto.

    Revert "Merge branch 'cenario_alfa' into cenario_revert"
    
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

Remova os sustenidos. salve e feche o programa. Faça o push.

    $ git push origin cenario_revert

E Voilá! Revertemos o merge.

#### 2.4 Resumo

__Você selecionou um ou mais commits em sequência, e a partir deles gerou um novo capaz de selecionar e reverter as atualizações dos anteriores e o enviou à origem.__

### 3. O git checkout

É um procedimento é fácil, por possuir apenas alguns passos muito simples.

#### Caso A: Exemplo simples

Crie no repósitorio anterior uma branch a partir da master chamada __documentos__. Nela crie, adicione, faça commit e push, __para cada__ um dos 3 arquivos de texto (distinto) criados.

    $ git checkout master
    $ git checkout -b documentos
    
    $ touch Rafaela.txt
    $ git add Rafaela.txt 
    $ git commit -m "Add: Rafaela.txt"
    $ git push origin documentos
     
    $ touch Monique.txt
    $ git add Monique.txt 
    $ git commit -m "Add: Monique.txt"
    $ git push origin documentos
       
    $ touch Juliana.txt
    $ git add Juliana.txt 
    $ git commit -m "Add: Juliana.txt"
    $ git push origin documentos

Vá no Github e verifique os 3 commits que você fez, em que estão os arquivos que foram criados e enviados por você.

Ok, feito!?

Agora, digamos que, você pegou por engano os arquivos Monique.txt e Juliana.txt errado. O que fazer?

Bom, como todo bom leitor você não pulou o tópico anterior, e me sugere: 

> "Podemos dar um rever que remova esses 2 e está tudo blz!"

Muito bem! Concordo que é uma possibilidade. Maaass.. e se cada um desses arquivos ocupa por volta de 400 mb em disco. São tipo um mega textão, tipo um log de msn dessa mulherada?!!

E aí? O revert resolve?

__Retomando...__

Conforme você aprendeu anteriormente, ache os commits que incluíram Monique.txt e Juliana.txt no repositório.

    cc0126b8117f1aab7d542d0eb7d7382b26844f5d Add: Juliana.txt
    53615234aebe6df16f42599a64d4bea6bd99d9c2 Add: Monique.txt
    69a84f6666cdc652bb7150796de1d1985a74c8dd Add: Rafaela.txt
    426bca1b80fd19e22d5f3fb31f49b3f15698142f first commit

Como podemos ver eles são: cc0126b8117f1aab7d542d0eb7d7382b26844f5d e 53615234aebe6df16f42599a64d4bea6bd99d9c2. Mas para qual deles vamos voltar?

Seguindo o conceito de que o histórico é sequencial, e com um pouco de memória, sabemos que __Add: Juliana.txt__ foi o último commit, e __Add: Monique.txt__ foi o penúltimo.

Se voltarmos ao histórico do último, o penúltimo ainda existirá! Mas não o inverso. Se você entende de estrutura de dados, verá que os históricos, ou logs, são nada mais nada menos que pilhas.

Sendo assim, trabalharemos com antepenúltimo commit, 69a84f6666cdc652bb7150796de1d1985a74c8dd. Lembrando que, deve ficar claro que o seu provavelmente será diferente.

Mas , chega de ladainha. Vamos lá!! Faça o checkout no commit!

    $ git checkout 69a84f6666cdc652bb7150796de1d1985a74c8dd
    Note: checking out '69a84f6666cdc652bb7150796de1d1985a74c8dd'.
    
    You are in 'detached HEAD' state. You can look around, make experimental
    changes and commit them, and you can discard any commits you make in this
    state without impacting any branches by performing another checkout.
    
    If you want to create a new branch to retain commits you create, you may
    do so (now or later) by using -b with the checkout command again. Example:
    
      git checkout -b new_branch_name
    
    HEAD is now at 69a84f6... Add: Rafaela.txt

Crie uma branch a partir dele chamada __documentos-restore__, e remova __documentos__.

    $ git checkout -b documentos-restore
    Switched to a new branch 'documentos-restore'
    
    $ git branch -D documentoss
    Deleted branch documentos (was cc0126b).

Crie uma nova branch chamada __documentos__ a partir de __documentos-restore__, entre nela e remova __documentos-restore__.

    $ git checkout -b documentos
    Switched to a new branch 'documentos'
    
    $ git branch -D documentos-restore
    Deleted branch documentos-restore (was 69a84f6).

<div class="alert alert-info">
 <b>Ou</b> renomeie documentos-restore para documentos com <code>git branch -M documentos-restore documentos</code>.
</div>

E ai..? O que temos agora?? Aha! Isso mesmo, pelo visto a branch documentos já não é mais a mesma... ela é, digamos, o commit "Add: Rafaela.txt".

Mas pra que isso?

Se vocẽ que descobrir, faça esse comando:

    $ git push origin documentos
    To git@github.com:cpetreanu/repo.git
     ! [rejected]        documentos -> documentos (non-fast-forward)
    error: failed to push some refs to 'git@github.com:cpetreanu/repo.git'
    To prevent you from losing history, non-fast-forward updates were rejected
    Merge the remote changes (e.g. 'git pull') before pushing again.  See the
    'Note about fast-forwards' section of 'git push --help' for details.

Ops!! Não funcionou? O que houve aí?!

> To prevent you from losing history, non-fast-forward updates were rejected

Parece pra mim que você está recebendo um alerta.. E pelo visto é porque estamos pra alterar o histórico.

Force seu push, ignorando o aviso:

    $ git push --force origin documentos
    Total 0 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
     + cc0126b...69a84f6 documentos -> documentos (forced update)

Veja só.... _forced update_. Ah, mas será que funcionou? Vá lá ver no Github e depois volte aqui.

Aqui parece que funcionou, veja meu git log:

    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 16:25:38 2012 -0300
    
        Add: Rafaela.txt
    
    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

Beleza? Vamos ao próximo exemplo então:

#### Caso B: Exemplo realista

Você está desenvolvendo em sua branch, quando por algum motivo acidentalmente "inesperado" você faz um merge dela com, digamos.., a gh-pages. E despercebido ainda fez um PUSH!

Convenhamos, o seu projeto não tem nenhuma relação com ela, e de nada vai servir tê-la aí. Ah sim, você não pode remover sua branch (e claro que não vai adiantar), e muito menos reverter seus commit, pois se a massa de arquivos descarregados em sua máquina foi enorme imagina como está o histórico no projeto do Github! 

Vamos causar um acidente?

Entre na master, adicione o projeto objectos/gh-pages nas conexões remotas e faça "aquele" merge.



__Resumindo, você entrou em um commit - de preferência o último válido - e sobrescreveu ele sobre a branch "corrompida". Por fim, realizarou um ``push --force`` para a origem, revertendo todo o histórico para o estado desse commit.__

### 3. O git reset

#### Adaptando o cenário

Crie uma branch chamada



### 4. O git rebase -i

#### Adaptando o cenários

Crie uma branch chamada



### 5. Refazer a fork?

Em alguns casos, quando seu trabalho na branch acabou de começar, existem poucos arquivos no projeto original ou existem uma série de erros na execução dos comandos anteriores, uma opção simples é refazer a fork do projeto original.

Para isso, você pode seguir os seguintes passos:

1. Faça cuidadosamente o backup de todos os arquivos importantes que você criou ou modificou
1. Remova a pasta do projeto da sua máquina, usando um comando como:

    rm -f -R /tmp/repo/

1. Vá no repositório do fork __repo__ em sua página.

1. Clique no botão "Admin", na parte superior direita da página

1. Clique no botão "Delete this repository" ... e confirme a remoção do fork clicando em "I understand, delete this repository"

1. Acesse a página do projeto original e faça novamente um fork e clone ele (se não lembrar como fazer clique [aqui][1])

1. Use os comandos

    mkdir /tmp/repo/
    cd /tmp/repo

Se necessário configure a url do projeto original usando

    $git remote add <alias-da-url> <url-do-projeto-original>

Seu fork estará agora idêntico a úĺtima versão do projeto

1. Entre na branch correta e recupere os backups feitos.

1. Pronto, você acaba de desfazer os comandos errados usando uma das formas mais árduas (tirando os possíveis erros nos comandos citados nas outras seções!)

### Finalizando

Obrigado aos meus leitores pela paciência e espero que tenham com este trabalho aprendido coisas novas, mas principalmente úteis no dia-a-dia de cada um.

Caso não precisem mais, não se esqueçam de remover os arquivos, branchs, forks criados aqui para aprendizado.

<div id="referencias"> </div>

### Referências<div id="referencias"> </div>

[objectos-dojo :: Tutorial gh-pages][1]

[id]: /path/to/img.jpg "Admin"
[id]: /path/to/img.jpg "Delete this repositoy"
[id]: /path/to/img.jpg "I understand, delete this repository"

[0]: #referencias "Referências"
[1]: ../contribua/00-importar.html "objectos-dojo :: Tutorial gh-pages"