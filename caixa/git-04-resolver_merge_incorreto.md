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

* No entanto, quando usamos ``git checkout``, ``git reset --hard`` e ``git rebase -i``, "quebramos" o histórico, sendo necessário fazer um ``push --force`` para ser mandado para o Github. Essas ações não podem ser revertidas por causa disso, devem ser usadas com muita cautela e em situações claras e específicas!

E a solução menos recomendada, mas mais prática:

* Já quando se decide refazer a fork do projeto e recuperar backups, é mantido o histórico do projeto original e os backups serão adicionados a um ou mais commits novos.

__Observação__: Como existe vasta base de conhecimento dos comandos git, me limito a focar em quando e como usar cada uma dessas soluções a partir dos casos particulares que criaremos juntos.

### 1. O git revert

Como dito anteriormente, o revert:

* É feito a partir de um novo commit;

* Mantém o histórico anterior a esse commit;

* Pode reverter um ou mais commits;

* Pode ser revertido por outro revert.

Portanto, ele é indicado nos casos em que são poucos os arquivos indesejáveis enviados, representando um histórico cujo tamanho não é significantemente grande a ponto de termos de alterá-lo ou removê-lo. 

Antes de continuarmos para a solução, por que não simularmos um caso de merge errado?

#### 1.1 Antes de começar

* Faça um novo repositório chamado __repo__ no Github;

* Coloque-o diretório /tmp/repo.

#### 1.2 Criando o cenário

Crie uma branch chamada buscar_acionista e entre nela.

      $ git checkout -b buscar_acionista
      Switched to a new branch 'buscar_acionista'

Execute os comandos abaixo para criar os arquivos TesteDeBuscarAcionista e BuscarAcionista.

<p></p>

      $ touch TesteDeBuscarAcionista.java
      $ touch BuscarAcionista.java

Adicione os arquivos, faça um commit e dê um push:

      $ git add BuscarAcionista.java
      $ git add TesteDeBuscarAcionista.java
      
      $ git commit -m "Implementado: TesteDeBuscarAcionista e BuscarAcionista"
      
      [buscar_acionista 9f9ebd9] Implementado: TesteDeBuscarAcionista e BuscarAcionista
       0 files changed, 0 insertions(+), 0 deletions(-)
       create mode 100644 BuscarAcionista.java
       create mode 100644 TesteDeBuscarAcionista.java
       
      $ git push origin buscar_acionista
      Counting objects: 3, done.
      Delta compression using up to 3 threads.
      Compressing objects: 100% (2/2), done.
      Writing objects: 100% (2/2), 299 bytes, done.
      Total 2 (delta 0), reused 0 (delta 0)
      To git@github.com:cpetreanu/repo.git
       * [new branch]      buscar_acionista -> buscar_acionista

Cheque no Github se os 2 arquivos estão presentes. Viu?! Ok! Então volte para a master para continuarmos.

      $ git checkout master
      Switched to branch 'master'

Perfeito! Agora crie uma branch chamada form_aeroporto_create e entre nela.

      $ git checkout -b form_aeroporto_create
      Switched to a new branch 'form_aeroporto_create'

E crie, da mesma forma:

      $ touch TesteDeFormDeAeroportoCreate.java
      $ touch FormDeAeroportoCreate.java
      $ touch FormDeAeroportoCreateGuice.java

Faça os adds, commit e push:

      $ git add TesteDeFormDeAeroportoCreate.java
      $ git add FormDeAeroportoCreate.java
      $ git add FormDeAeroportoCreateGuice.java
      
      $ git commit -m "Web: Implementando FormDeAeroportoCreate"
      [form_aeroporto_create 7688f2d] Web: Implementando FormDeAeroportoCreate
       0 files changed, 0 insertions(+), 0 deletions(-)
       create mode 100644 FormDeAeroportoCreate.java
       create mode 100644 FormDeAeroportoCreateGuice.java
       create mode 100644 TesteDeFormDeAeroportoCreate.java
      
      $ git push origin form_aeroporto_create
      Counting objects: 3, done.
      Delta compression using up to 3 threads.
      Compressing objects: 100% (2/2), done.
      Writing objects: 100% (s2/2), 308 bytes, done.
      Total 2 (delta 0), reused 0 (delta 0)
      To git@github.com:cpetreanu/repo.git
       * [new branch]      form_aeroporto_create -> form_aeroporto_create

Por fim, dentro da branch form_aeroporto_create, faça merge com buscar_acionista (e também um ``git status``, se preferir) e ``git push origin buscar_acionista``. Veja:

     $ git merge buscar_acionista
     Merge made by recursive.
      0 files changed, 0 insertions(+), 0 deletions(-)
      create mode 100644 BuscarAcionista.java
      create mode 100644 TesteDeBuscarAcionista.java
     
     $ git push origin form_aeroporto_create
     Counting objects: 4, done.
     Delta compression using up to 3 threads.
     Compressing objects: 100% (2/2), done.
     Writing objects: 100% (2/2), 339 bytes, done.
     Total 2 (delta 1), reused 0 (delta 0)
     To git@github.com:cpetreanu/repo.git
        7688f2d..0606a81  form_aeroporto_create -> form_aeroporto_create

Considerando que os trabalhos nas branchs ``form_aeroporto_create`` e ``buscar_acionista`` são completamente distintos, ou seja. não podem intercalar-se.

Verifique lá no Github, sua branch agora contém arquivos que não deveriam estar lá, certo?

<div class="alert alert-info">
 Observação: Sempre cheque seus arquivos na branch, e seus Pull Requests com o <b>Diff</b> na página do Github para identificar aquilo que foi mandado para origin, ou que está sendo mandado para o projeto original. Se não souber verificar o problema, de nada adianta saber solucioná-lo!
</div>

Tendo isso em mente, vem a grande pergunta: "Mas e agora, como faço para reverter isso?"

#### 1.3 Solução

Devemos encontrar o commit incorreto e revertê-lo. Para isso você pode usar o Github. Se preferir faça como eu e use o ``git log`` assim:

    $ git log

Que retornará:

    commit 81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0
    Merge: 7688f2d 9f9ebd9
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 13:34:58 2012 -0300
    
        Merge branch 'buscar_acionista' into form_aeroporto_create
    
    commit 7688f2d332b75cefa835540be64a54e0b6eded1d
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 18:52:40 2012 -0300
    
        Web: Implementando FormDeAeroportoCreate
    
    commit 9f9ebd90ead253b26503b83a4c201569b703fef0
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 18:43:19 2012 -0300
    
        Implementado: TesteDeBuscarAcionista e BuscarAcionista
    
    commit 426bca1b80fd19e22d5f3fb31f49b3f15698142f
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Fri Mar 16 17:42:32 2012 -0300
    
        first commit

<div class="alert alert-info">
 No caso de você ter feito muitos logs após o commit errôneo, tente usar o <code>git log -10</code> (ou o número que desejar).
 Se preferir, também pode acrescentar a opção <b>--pretty</b> e o <b>grep</b>.
 Exemplo: <code>git log --pretty=oneline -10 | grep 'Merge'</code>.
</div>

Podemos ver que o commit que precisamos reverter é o Merge branch 'buscar_acionista' into form_aeroporto_create para o anterior, Web: Implementando FormDeAeroportoCreate.

Muito bem. Copie o hash do commit (que no meu caso é 81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0) e aperte a tecla __Q__ para sair dessa tela.

Agora faça o revert conforme:

    git revert 81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0 -m -1

<div class="alert alert-info">
 O parâmetro na opção <b>-m</b> informa até onde será revertido. o <b>-1</b> informa para ela serão revertidas as atualizações do commit <b>81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0</b> até o anterior.
</div>

Será aberto um editor de texto (no meu caso o VIM) contendo informação do revert a ser realizado. Para cada linha de atualização do próprio revert existe um __#__. Você deve remover todos os __#__ das novas atualizações dele que devem estar no novo commit.

Portanto, o texto que está assim:

    Revert "Merge branch 'buscar_acionista' into form_aeroporto_create"
    
    This reverts commit 81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0, reversing
    changes made to 7688f2d332b75cefa835540be64a54e0b6eded1d.
    
    # Please enter the commit message for your changes. Lines starting
    # with '#' will be ignored, and an empty message aborts the commit.
    # On branch form_aeroporto_create
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
    #       deleted:    BuscarAcionista.java
    #       deleted:    TesteDeBuscarAcionista.java

Deverá ficar assim:

    Revert "Merge branch 'buscar_acionista' into form_aeroporto_create"
    
    This reverts commit 81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0, reversing
    changes made to 7688f2d332b75cefa835540be64a54e0b6eded1d.
    
    # Please enter the commit message for your changes. Lines starting
    # with '#' will be ignored, and an empty message aborts the commit.
    # On branch form_aeroporto_create
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
            deleted:    BuscarAcionista.java
            deleted:    TesteDeBuscarAcionista.java

Salve e feche o arquivo. Faça o commit e push, e veja tanto no git log quanto no site do Github as mudanças que ocorreram.

    $ git commit -m "Feito adequadamente com o VIM o revert"
    # On branch form_aeroporto_create
    nothing to commit (working directory clean)
    
    $ git push origin form_aeroporto_create
    Counting objects: 5, done.
    Delta compression using up to 3 threads.
    Compressing objects: 100% (3/3), done.
    Writing objects: 100% (3/3), 618 bytes, done.
    Total 3 (delta 1), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
       7688f2d..6d0ca6d  form_aeroporto_create -> form_aeroporto_create

    commit 6d0ca6d4100aa13b6af2d1c0db1c5e19ba5595ac
    Author: Caio Petreanu <caio.petreanu@objectos.com.br>
    Date:   Mon Mar 19 15:54:59 2012 -0300
    
        Revert "Merge branch 'buscar_acionista' into form_aeroporto_create"
        
        This reverts commit 81e86e2f683cdb2fad8120a8f3fe6dfae5d513f0, reversing
        changes made to 7688f2d332b75cefa835540be64a54e0b6eded1d.
        
            deleted:    BuscarAcionista.java
            deleted:    TesteDeBuscarAcionista.java

Você verá que há no histórico um novo commit que reverte as atualizações daquele que foi selecionado. Simples, não?

#### Em resumo, você selecionou um ou mais commits em sequência, e a partir deles gerou um novo capaz de selecionar e reverter as atualizações dos anteriores e o enviou à origem.

### 2. O git checkout

O procedimento é simples, mas que deve ser usado com __extrema__ cautela! Afinal, agora estaremos lidando mais diretamente com o histórico.

Lembra dele?

#### Caso A: Exemplo simples

Crie no repósitorio anterior uma branch a partir da master chamada __documentos__. Nela crie, adicione, faça commit e push, __para cada__ um dos 3 arquivos de texto (distinto) criados.

    $ git checkout master
    Switched to branch 'master'
    
    $ git checkout -b documentos
    Switched to a new branch 'documentos'
    
    $ touch Rafaela.txt
    
    $ git add Rafaela.txt 
    
    $ git commit -m "Add: Rafaela.txt"
    [documentos 69a84f6] Add: Rafaela.txt
     0 files changed, 0 insertions(+), 0 deletions(-)
     create mode 100644 Rafaela.txt
     
    $ git push origin documentos
    Counting objects: 3, done.
    Delta compression using up to 3 threads.
    Compressing objects: 100% (2/2), done.
    Writing objects: 100% (2/2), 260 bytes, done.
    Total 2 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
     * [new branch]      documentos -> documentos
     
    $ touch Monique.txt
    
    $ git add Monique.txt 
    
    $ git commit -m "Add: Monique.txt"
    [documentos 5361523] Add: Monique.txt
     0 files changed, 0 insertions(+), 0 deletions(-)
     create mode 100644 Monique.txt
     
    $ git push origin documentos
    Counting objects: 3, done.
    Delta compression using up to 3 threads.
    Compressing objects: 100% (2/2), done.
    Writing objects: 100% (2/2), 268 bytes, done.
    Total 2 (delta 0), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
       69a84f6..5361523  documentos -> documentos
       
    $ touch Juliana.txt
    
    $ git add Juliana.txt 
    
    $ git commit -m "Add: Juliana.txt"
    [documentos cc0126b] Add: Juliana.txt
     0 files changed, 0 insertions(+), 0 deletions(-)
     create mode 100644 Juliana.txt
     
    $ git push origin documentos
    Counting objects: 3, done.
    Delta compression using up to 3 threads.
    Compressing objects: 100% (2/2), done.
    Writing objects: 100% (2/2), 251 bytes, done.
    Total 2 (delta 1), reused 0 (delta 0)
    To git@github.com:cpetreanu/repo.git
       5361523..cc0126b  documentos -> documentos

Vá no Github e verifique os 3 commits que você fez, em que estão os arquivos que foram criados e enviados por você.

Ok, feito!?

Agora, digamos que, você pegou por engano os arquivos Monique.txt e Juliana.txt errado. O que fazer?

Bom, como todo bom leitor você não pulou o tópico anterior, e me sugere: 

> "Podemos dar um rever que remova esses 2 e está tudo blz!"

Muito bem! Concordo que é uma possibilidade. Maaass.. e se cada um desses arquivos ocupa por volta de 400 mb em disco. São tipo um mega textão, tipo um log de msn dessa mulherada?!!

E aí? O revert resolve?

Caso você não saiba, fique sabendo: tudo que está no histórico é mantido. Imagine que toda vez que alguem for de alguma forma acessar, ou fazer download, do seu projeto, ele tenha que pegar meros 800 mb a mais, desnecessários.

O que você acha disso?

É por isso que nesses casos, em especial quando lidamos com a presença de históricos indesejados com:

* Uma legião de arquivos

E / ou:

* Arquivos __colossus_

Devemos alterar o histórico, para remover presenças indesejaveis em nossos diretórios do projeto.

E você verá agora como!

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

# Importate!!!

__Em situações de trabalho apenas faça o seguinte passo, e somente apenas se você tiver absoluta certeza, sem qualquer sombra de dúvidas e de preferência com alinhamento estrelar favorável.__

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

<div id="referencias"> </div>

### Referências<div id="referencias"> </div>

[objectos-dojo :: Tutorial gh-pages][1]

Crie os seguintes arquivos:

<div class="alert alert-info"><p><b>Observação:</b> Nos comandos abaixo, ao usar cat &gt;&gt; &lt;arquivo&gt;, é concatenado um texto por input (stdin) ao &lt;arquivo&gt;, e ele é fechado usando ^D (que no teclado, significa ctrl pressionado e 'd'). Use copiar e colar no terminal para agilizar as coisas.</p></div>

* TesteDeBuscarFuncionario.java

      $ cat >> TesteDeBuscarFuncionario.java
      /*
      * Copyright 2012 Objectos, Fábrica de Software LTDA.
      *
      * Licensed under the Apache License, Version 2.0 (the "License"); you may not
      * use this file except in compliance with the License. You may obtain a copy of
      * the License at
      *
      * http://www.apache.org/licenses/LICENSE-2.0
      *
      * Unless required by applicable law or agreed to in writing, software
      * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
      * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
      * License for the specific language governing permissions and limitations under
      * the License.
      */
      @Test
      @Guice(modules = { ModuloDeTesteObjectosDojo.class })
      public class TesteDeBuscarFuncionario {
      
        @Inject
        private BuscarFuncionario buscarFuncionario;
      
        @Inject
        private DBUnit dbUnit;
      
        public void prepararDBUnit() {
          dbUnit.loadDefaultDataSet();
        }
      
        public void busca_por_id() {
          Funcionario res = buscarFuncionario.porId(3);
      
          assertThat(res.getId(), equalTo(3));
          assertThat(res.getMatricula(), equalTo("T0033000"));
          assertThat(res.getNome(), equalTo("Briann Adams"));
          assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980, 6, 01)));
          assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004, 12, 10, 9, 0)));
          assertThat(res.getDataDemissao(), equalTo(new DateTime(2012, 1, 3, 12, 30)));
        }
      
      }

* BuscarFuncionario.java

      $ cat >> BuscarFuncionario.java
      /*
      * Copyright 2012 Objectos, Fábrica de Software LTDA.
      *
      * Licensed under the Apache License, Version 2.0 (the "License"); you may not
      * use this file except in compliance with the License. You may obtain a copy of
      * the License at
      *
      * http://www.apache.org/licenses/LICENSE-2.0
      *
      * Unless required by applicable law or agreed to in writing, software
      * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
      * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
      * License for the specific language governing permissions and limitations under
      * the License.
      */
      @ImplementedBy(BuscarFuncionarioGuice.class)
      public interface BuscarFuncionario {
      
        Funcionario porId(int id);
      
      }

* BuscarFuncionarioGuice.java

      $ cat >> BuscarFuncionarioGuice.java
      /*
       * Copyright 2012 Objectos, Fábrica de Software LTDA.
       *
       * Licensed under the Apache License, Version 2.0 (the "License"); you may not
       * use this file except in compliance with the License. You may obtain a copy of
       * the License at
       *
       * http://www.apache.org/licenses/LICENSE-2.0
       *
       * Unless required by applicable law or agreed to in writing, software
       * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
       * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
       * License for the specific language governing permissions and limitations under
       * the License.
       */
      class BuscarFuncionarioGuice implements BuscarFuncionario {
      
        private final Provider<NativeSql> sqlProvider;
      
        @Inject
        public BuscarFuncionarioGuice(Provider<NativeSql> sqlProvider) {
          this.sqlProvider = sqlProvider;
        }
      
        @Override
        public Funcionario porId(int id) {
          return newSelect()
      
              .add("where FUNCIONARIO.ID = ?").param(id)
      
              .single();
        }
        
        private NativeSql newSelect() {
          return sqlProvider.get()
      
              .add("select *")
              .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
      
              .add("join DATABASE.SUPERIOR as SUPERIOR")
              .add("from FUNCIONARIO.SUPERIOR_ID as SUPERIOR.ID")
      
              .andLoadWith(new FuncionarioLoader());
        }
      
      }

[id]: /path/to/img.jpg "Admin"
[id]: /path/to/img.jpg "Delete this repositoy"
[id]: /path/to/img.jpg "I understand, delete this repository"

[0]: #referencias "Referências"
[1]: ../contribua/00-importar.html "objectos-dojo :: Tutorial gh-pages"