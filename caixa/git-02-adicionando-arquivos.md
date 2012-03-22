---
layout: post-alpha
title: "Git :: Adicionando arquivos"
author: "Hellen Carla Paixão Escarate"
published: true
tags:
- git
- aprendizes
partof: git
num: 2
---

Depois de ter feito um fork do projeto, antes de adicionarmos arquivos, vamos começar criando uma branch. 

Podemos entender o conceito de branch, como se traduz do inglês mesmo, algo como ramo ou braço do projeto. Mas nesse
momento não vamos nos aprofundar nisso, esse assunto vai ser abordado mais a frente, apenas siga todos os passos
corretamente e confie nas instruções, ok?!

Então vamos criar uma branch:

    $ git branch nova_funcionalidade

Muito bem, sua primeira branch está criada. É importante destacar que no nome da branch sempre deve ficar claro o que
ela faz. 

Agora, execute o comando git branch: 

    $ git branch
    * master
    nova_funcionalidade

Podemos ver que o comando acima lista todas as branches existentes, e a branch atual aparece com um asterisco. Isso
significa que não estamos na branch que acabamos de criar, então vamos trocar de branch: 

    $ git checkout nova_funcionalidade

Para confirmar se estamos nessa branch mesmo, execute novamente o comando: 

    $ git branch
    master
   * nova_funcionalidade

Agora sim, estamos na branch que desejamos, e podemos trabalhar


<div class="alert-message block-message info">
`git checkout -b nova_funcionalidade`
Esse comando cria a branch e já deixa selecionada para usarmos, assim fazemos os dois comandos acima usando apenas uma
linha!
</div>

Mas antes de adicionarmos arquivos, precisamos dar mais um passo. 

Certifique-se de que você está no diretório do projeto: `/kdo/projetos/objectos-dojo`

Vamos então criar um novo diretório, onde vamos adicionar arquivos. Esse diretório deve ter o nome do projeto,
seguido do seu nome de usuário, conforme o comando abaixo. (Execute o comando trocando apenas o nome de usuário, que no
meu caso é hescarate)

    $ mkdir objectos-dojo-hescarate

Agora entre nesse diretório para adicionarmos arquivos. 

    $ cd objectos-dojo-hescarate/

É importante que fique claro, que os exercícios que estamos fazendo simulam as atividades básicas do nosso dia-a-dia, 
criar branches, inserir arquivos em projetos, são coisas que precisamos estar `afiados` , pois vamos fazer com muita 
frequência. Portanto agora, finalmente vamos adicionar um novo arquivo: 

    $ vi config.txt

<div class="alert-message block-message info">
Caso você tenha dificuldades com o VIM, vá no tutorial para maiores informações. 
</div>

Agora então execute o comando abaixo: 

    $ git status

Como podemos ver, na mensagem que segue o comando (abaixo), o git status nos mostra o status do diretório em que
estamos trabalhando. 

    # On branch nova_funcionalidade
    # Untracked files:
    #   (use "git add <file>..." to include in what will be committed)
    #
    #	./
    nothing added to commit but untracked files present (use "git add" to track)

Podemos ver que existem arquivos que ainda não foram adicionados ao controle de versão, então precisamos adicioná-los: 

    $ git add config.txt

Agora execute novamente o comando git status:

    $ git status
    # On branch nova_funcionalidade
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
    #	new file:   config.txt
    #

Podemos observar então que o arquivo config.txt foi adicionado ao controle de versão, e que existem mudanças a serem
"comitadas".

Existe também o comando ``git add -i``. Com ele você reberá um menu como esse:

    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	  7: [q]uit	  8: [h]elp
    What now> 
               staged     unstaged path

Com ele é mais interativo operações comuns do git como status, add e diff.

É importante frisar que usar ele lhe dá maior controle do que será colocado no stage.

Bom, veremos algumas funcionalidades dele ao longo dos artigos Git. No momento utilize a opção ``4: [a]dd untracked`` para ver o seguinte menu:

    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	  7: [q]uit	  8: [h]elp
    What now> 4
      1: [c]onfig.txt
    Add untracked>>  

Selecione ``1: [c]onfig.txt`` ou `*` para adicionar o arquivo ao controle de versão. Por, fim, use o `q` para sair do menu.

Vamos então executar o `commit`

    $ git commit -m "criado o arquivo config.txt"

Mas pra que serve o commit? 

O commit seria algo como consolidar ou efetivar as nossas atualizações.

Um detalhe muito importante é o parâmetro `-m`, que permite inserir uma mensagem no commit.

Mas porque inserir uma mensagem no commit?

Imagine muitos programadores, mandando a toda hora uma atualização para o projeto. Quando você olhar as atualizações de
cada um, como será identificado o que cada atualização (commit) fez no projeto?

Para isso, colocamos uma mensagem na hora de fazer o commit. Dessa forma, especificamos o que aquela determinada
atualização fez, assim como no commit que fizemos agora a pouco, na mensagem colocamos `criado o arquivo config.txt`,
então, se listarmos os commits saberemos exatamente o que cada atualização fez.  

Então, vamos lá no github no fork do nosso projeto. Veja se aparece esse arquivo que acabamos de criar, o config.txt. 

Não esqueça que você precisa selecionar a branch na qual está fazendo essas atualizações, como podemos observar na
imagem abaixo, provavelmente a branch selecionada deve ser a master, então escolha a branch `nova_funcionalidade`

![selecionando branch](https://github.com/objectos/objectos-dojo-img/blob/master/github/14_escolhendobranch.png?raw=true)

Agora que você selecionou a branch `nova_funcionalidade`, veja se aparece o novo diretório que criamos
(objectos-dojo-seuusuário), e dentro dele o novo arquivo (o config.txt).

Não aparece!?

Porque!?

Bem, como não entramos muito em detalhes sobre o git, provavelmente não citamos que ele é um DVCS (Distributed
Version Control Systems), em português, Sistema de Controle de Versão `Distribuído`.
Diferente do Subversion, por exemplo, que é centralizado, os commits que efetuamos no git são apenas locais, ou seja,
só na sua máquina. 

Mas então, como atualizar com o repositório?

Esse é o nosso pŕoximo passo! Pra isso usamos o comando `push`: 

    $ git push origin nova_funcionalidade

Como vc pode observar no comando acima, estamos especificando que o push deve ser apenas na branch nova_funcionalidade.
Dessa forma o fork original que fizemos do projeto não será alterado, apenas a branch nova_funcionalidade. 

Agora volte lá no Github, e confira se realmente essas atualizações aparecem!

Agora faça o seguinte, insira uma linha nesse arquivo config.txt

    $ echo "inserindo a primeira linha no arquivo" > config.txt

Você lembra qual o próximo passo que devemos dar? 

Isso mesmo, verificar o status:

    $ git status
    # On branch nova_funcionalidade
    # Changed but not updated:
    #   (use "git add <file>..." to update what will be committed)
    #   (use "git checkout -- <file>..." to discard changes in working directory)
    #
    #	modified:   config.txt
    #
    no changes added to commit (use "git add" and/or "git commit -a")

Se preferir, use aqui o ``git add -i`` e verá no menu:

               staged     unstaged path
      1:    unchanged        +1/-0 config.txt
    
    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	      7: [q]uit	      8: [h]elp
    What now> 

Podemos observar que no status aparece que esse arquivo foi modificado, e então precisamos adicionar essa alteração: 

$ git add config.txt 

Ou, usando o ``git add -i``, selecione ``2: [u]pdate`` para:

    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	      7: [q]uit	      8: [h]elp
    What now> 2
               staged     unstaged path
      1:    unchanged        +1/-0 [c]onfig.txt
    Update>> 

E `1` ou `*` para mandar o config.txt para o sistema.

Depois vamos executar o comando git status novamente: 

    $ git status
    # On branch nova_funcionalidade
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
    #	modified:   config.txt

Podemos observar então que as alterações foram adicionadas e podemos efetuar o commit:

    $ git commit -m "inserindo a primeira linha no arquivo config.txt"

Lembrando que a mensagem deve ser clara, mostrando o que estamos fazendo nesse commit. 

Mas e agora, essa atualização já está disponível lá no Github? 

Não? Então o que precisamos fazer em seguida?

Isso mesmo, executar o push: 

    $ git push origin nova_funcionalidade

E agora, se você verificar no Github está atualizado, ok?

Vamos então inserir mais uma linha no arquivo config.txt. Para inserir uma linha depois da última linha que estiver no
arquivo, use dois sinais de maior, conforme o comando abaixo: 

    $ echo "inserindo a segunda linha" >> config.txt

Se você abrir o arquivo, vai observar que a linha inserida ficou abaixo da linha que já estava no arquivo. 

Agora, faça os procedimentos que você acabou de aprender, adicione a modificação (git status, git add), faça o
commit e envie as alterações através do push.

Adicione agora, uma terceira linha no arquivo: 

$ echo "essa é a terceira linha que vou adicionar" >> config.txt 

Se você novamente abrir o arquivo, vai perceber que a nova linha foi inserida abaixo da segunda linha.

E então execute os procedimentos novamente. 

##TRABALHANDO EM EQUIPE

Em um ambiente de desenvolvimento de software, várias pessoas podem estar alterando o mesmo projeto no qual você
está trabalhando, e uma forma de evitar um pouco os conflitos é trazer essas atualizações do projeto original para o
seu fork do projeto, no qual você está trabalhando, antes de implementar novas funcionalidades. 

Para ficar mais claro, peça para alguém da sua equipe inserir um novo arquivo no projeto original, que deve ser
chamado de nova_funcionalidade.txt, seguido do nome de usuário de quem for criar esse arquivo (no meu caso ficou
nova_funcionalidade_hescarate.txt). 

A idéia de inserir esse arquivo, é simular como se realmente fosse uma nova funcionalidade no projeto, como uma nova 
classe, etc... O que como comentamos mais acima, num ambiente de desenvolvimento de software acontece com muita
frequência, pois várias pessoas estão trabalhando num mesmo projeto ao mesmo tempo. 

Feito isso, vamos usar o comando `pull`, para trazer as novas atualizações do projeto original para o nosso fork do
projeto. 

Como estamos trabalhando com a branch nova_funcionalidade, se executamos o pull, receberemos as atualizações do nosso
próprio fork do projeto, e esse não é o nosso objetivo agora, e sim trazer as atualizações do projeto original. 

Vamos então para a branch master (fork do nosso projeto):

    $ git checkout master

E como então trazer as atualizações do projeto original? 

Pra isso, é necessário adicionar um outro repositório remoto, que vamos dar o nome de objectos, e a url de onde
essas atualizações serão buscadas (do projeto original, de onde fizemos o fork)

    $ git remote add objectos git@github.com:objectos/objectos-dojo.git

Em seguida, vamos usar o comando pull informando que queremos trazer as atualizações do projeto original (objectos) para a nossa branch master (o fork que demos do projeto original). 

    $ git pull objectos master

Agora as atualizações do projeto original vieram para o nosso fork do projeto. 

Certo, mas como ficam as atualizações que eu fiz na branch nova_funcionalidade (quando criamos o arquivo config.txt)?

Esse é o nosso próximo passo!

Volte então para a branch nova_funcionalidade: 

    $ git checkout nova_funcionalidade

Agora precisamos atualizar o fork do projeto (master) com a nova funcionalidade criada na branch `nova_funcionalidade`,
pra isso vamos usar o comando merge: 

    $ git merge master
    Updating e78af0d..91ab9cd
    Fast-forward
     novafuncionalidade_usuário.txt |    3 +++
     1 files changed, 3 insertions(+), 0 deletions(-)
     create mode 100644 config.txt

O comando merge converte as mudanças feitas na branch, nesse caso a master, para a branch a qual que você está executando o comando. 

Agora se você verificar os arquivos contidos na branch `nova_funcionalidade`, vai ver que a nova funcionalidade 
inserida lá no projeto original pelo seu colega de equipe (o arquivo novafuncionalidade_usuário.txt) aparece na sua
branch, pois usamos o comando master. 

Agora então vá no Github, no fork do seu projeto, e veja se as atualizações da branch nova_funcionalidade (novo
diretório criado objectos-dojo-seu usuário + o arquivo config.txt) estão na sua branch master. 

Não? Porque?

É necessário agora fazer um merge de master com nova_funcionalidade, e um push dele para origin (a fork do projeto). 

Vamos então voltar para a branch master:

    $ git checkout master

E então vamos fazer o merge: 

    $ git merge nova_funcionalidade

E agora, por fim, vamos mandar as atualizações da nossa branch master. 

    $ git push origin master

Agora, vá no Github e veja se as atualizações realmente estão na branch master. 

Certo, agora que você fez o merge, não vamos mais precisar da branch nova_funcionalidade. Então podemos excluí-la. 

Primeiro vamos excluir localmente, mas pra isso você precisa ir para a branch master, caso não estiver nela: 

    $ git branch -d nova_funcionalidade
    Deleted branch nova_funcionalidade (was cb80eee).

E agora vamos excluir remotamente: 

    $ git push origin :nova_funcionalidade
    To git@github.com:hescarate/objectos-dojo.git
     - [deleted]         nova_funcionalidade

Pronto, sua branch foi excluída!







 























 


