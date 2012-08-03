---
layout: post-alpha
title: "Adicionando arquivos"
author: "Hellen Carla Paixão Escarate"
published: true
tags:
- git
- aprendizes
partof: git
num: 2
---

# Introdução
O principal objetivo do Github é lidar com os arquivos enviados pelos desenvolvedores de software.
Nada adianta ter uma conta no Github e não saber adicionar arquivos, seja imagem, classes JAVA,
arquivos HTML, entre outros.<br>
Mostraremos os procedimentos para inserir um arquivo em um repositório do Github.


## Praticando
Depois de ter feito um fork do projeto, antes de adicionarmos arquivos, vamos começar criando uma branch.<br>
Certifique-se de que você está no diretório do projeto: `/kdo/projetos/objectos-dojo`. 

# Criando Branches
Podemos entender o conceito de _branch_, como se traduz do inglês, algo como "ramo" ou "braço do projeto".

Vamos criar uma _branch_:

    $ git branch novafuncionalidade

Muito bem, sua primeira _branch_ está criada. É importante destacar que no nome da _branch_ sempre deve ter 
seu login de rede + o número da "atividade" ficando claro o que ela faz.<br> 
Exemplo: `Usuario_01: novafuncionalidade`. Para os exemplos a seguir, omitiremos o nome do usuário. 

Agora, execute: 

    $ git branch
    
Podemos ver que o comando acima lista todas as _branches_ existentes, e a _branch_ atual aparece com um asterisco. Isso
significa que não estamos na _branch_ que acabamos de criar, então vamos trocar de _branch_: 

	* master
    novafuncionalidade

Para trocar a _branch_ faça:

    $ git checkout novafuncionalidade

Para confirmar se estamos nessa _branch_ mesmo, execute novamente: 

    $ git branch
    master
    * novafuncionalidade

Agora sim, estamos na _branch_ que desejamos, e podemos trabalhar.

Há como cria a _branch_ e já deixá-la selecionada para uso, assim fazemos os dois passos acima usando apenas uma
linha!

	$ `git checkout -b novafuncionalidade`

# Criando arquivos
Vamos então criar um novo diretório, onde vamos adicionar arquivos. Esse diretório deve ter o nome do projeto,
seguido do seu nome de usuário, conforme o comando abaixo. (Execute o comando trocando apenas o nome de usuário, que no
meu caso é hescarate)

    $ mkdir objectos-dojo-hescarate

Agora entre nesse diretório para adicionarmos arquivos. 

    $ cd objectos-dojo-hescarate/

É importante que fique claro, que os exercícios que estamos fazendo simulam as atividades básicas do nosso dia-a-dia, 
criar _branches_, inserir arquivos em projetos, são coisas que precisamos estar __"afiadas"__, pois vamos fazer isto com muita 
frequência. Vamos adicionar um novo arquivo: 

    $ vi config.txt

Nota: Caso você tenha dificuldades com o VIM. Leia mais [aqui](http://dojo.objectos.com.br/caixa/linux-00-vim.html). 

Digite o código abaixo: 

    $ git status

Como podemos ver na mensagem abaixo, o `git status` nos mostra o _status_ do diretório em que
estamos trabalhando. 

    # On branch novafuncionalidade
    # Untracked files:
    #   (use "git add <file>..." to include in what will be committed)
    #
    #	./
    nothing added to commit but untracked files present (use "git add" to track)

Podemos ver que existem arquivos que ainda não foram adicionados ao controle de versão, então precisamos adicioná-los: 

    $ git add config.txt

Agora execute novamente o comando `git status`:

    $ git status
    # On branch novafuncionalidade
    # Changes to be committed:
    #   (use "git reset HEAD <file>..." to unstage)
    #
    #	new file:   config.txt
    #

Podemos observar então que o arquivo `config.txt` foi adicionado ao controle de versão, e que existem mudanças a serem
enviadas.

NOta: Existe também o `git add -i`. Com ele você receberá um menu como esse:

    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	  7: [q]uit	  8: [h]elp
    What now> 
               staged     unstaged path

É muito mais interativo executar as operações comuns do git como _status_, _add_ e _diff_.

É importante frisar que usá-lo, você terá um maior controle do que será colocado no _stage_.

Bom, veremos algumas funcionalidades dele ao longo dos artigos Git. No momento utilize a opção ``4: [a]dd untracked`` para ver o seguinte menu:

    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	  7: [q]uit	  8: [h]elp
    What now> 4
      1: [c]onfig.txt
    Add untracked>>  

Selecione ``1: [c]onfig.txt`` ou `*` para adicionar o arquivo ao controle de versão, Aperte `Enter` e use o `q` para sair do menu.

Vamos então executar o `commit`

    $ git commit -m "criado o arquivo config.txt"

Mas pra que serve o _commit_? 

O _commit_ seria algo como consolidar ou efetivar as nossas atualizações.

Um detalhe muito importante é o parâmetro `-m`, que permite inserir uma mensagem no _commit_.

Mas porque inserir uma mensagem no _commit_?

Imagine muitos programadores, mandando a toda hora uma atualização para o projeto. Quando você olhar as atualizações de
cada um, como será identificado o que cada atualização (_commit_) fez no projeto?

Para isso, colocamos uma mensagem na hora de fazer o _commit_. Dessa forma, especificamos o que aquela determinada
atualização fez, assim como no _commit_ que fizemos agora a pouco, na mensagem colocamos "criado o arquivo config.txt",
então, se listarmos os _commits_ saberemos exatamente o que cada atualização fez.  

Vamos ao Github no _fork_ do nosso projeto. Veja se aparece esse arquivo que acabamos de criar, o config.txt. 

Não esqueça que você precisa selecionar a _branch_ na qual está fazendo essas atualizações, como podemos observar na
imagem abaixo, provavelmente a _branch_ selecionada deve ser a _master_, então escolha a branch _novaFuncionalidade_

![selecionando branch](https://github.com/objectos/objectos-dojo-img/blob/master/github/14_escolhendobranch.png?raw=true)

Agora que você selecionou a _branch_ correta, veja se aparece o novo diretório que criamos
(objectos-dojo-seu_usuario), e dentro dele o novo arquivo (o config.txt).

Não aparece!?

Porque!?

Bem, como não entramos muito em detalhes sobre o git, provavelmente não citamos que ele é um DVCS (Distributed
Version Control Systems), em português, Sistema de Controle de Versão Distribuído.
Diferente do _Subversion_, por exemplo, que é centralizado, os _commits_ que efetuamos no git são apenas locais, ou seja,
só na sua máquina. 

Mas então, como atualizar com o repositório?

Esse é o nosso próximo passo!

# Adicionando arquivos ao Github
 
Pra adicionar as alterações e/ou criações de arquivos, precisamos do `add`, `commit` e `push`: 

    $ git push origin novafuncionalidade
    
Importante: Ao realizar o `git push` a senha cadastrada anteriormente será solicitada. Digite-a para
concluir o `push`.    

Como vc pode observar no comando acima, estamos especificando que o _push_ deve ser apenas na _branch_ novafuncionalidade.
Dessa forma o _fork_ original que fizemos do projeto não será alterado, apenas a _branch_ novafuncionalidade. 

Agora volte ao Github, e confira se realmente essas atualizações aparecem!

Faça o seguinte, insira uma linha nesse arquivo config.txt

    $ echo "inserindo a primeira linha no arquivo" > config.txt

Você lembra qual o próximo passo que devemos dar? 

Isso mesmo, verificar o status:

    $ git status
    # On branch novafuncionalidade
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

Ou, usando o ``git add -i``, selecione ``2: [u]pdate`` depois aperte `Enter` para:

    *** Commands ***
      1: [s]tatus	  2: [u]pdate	  3: [r]evert	  4: [a]dd untracked
      5: [p]atch	  6: [d]iff	      7: [q]uit	      8: [h]elp
    What now> 2
               staged     unstaged path
      1:    unchanged        +1/-0 [c]onfig.txt
    Update>> 

E `1` ou `*` para mandar o config.txt para o sistema seguido de `Enter` e depois `q` para finalizar.

Execute o `git status` novamente: 

    $ git status
    # On branch novafuncionalidade
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

    $ git push origin novafuncionalidade

E agora, se você verificar no Github está atualizado, ok?

Vamos então inserir mais uma linha no arquivo config.txt. Para inserir uma linha depois da última linha que estiver no
arquivo, use dois sinais de maior, conforme o comando abaixo: 

    $ echo "inserindo a segunda linha" >> config.txt

Se você abrir o arquivo, vai observar que a linha inserida ficou abaixo da linha que já estava no arquivo. 

Agora, faça os procedimentos que você acabou de aprender, adicione a modificação, faça o
_commit_ e envie as alterações através do _push_.

Adicione agora, uma terceira linha no arquivo: 

	$ echo "essa é a terceira linha que vou adicionar" >> config.txt 

Se você abrir novamente o arquivo, vai perceber que a nova linha foi inserida abaixo da segunda linha.

E então execute os procedimentos novamente. 

##Trabalhando em equipe: Adicionando arquivos

Em um ambiente de desenvolvimento de software, várias pessoas podem estar alterando o mesmo projeto no qual você
está trabalhando, e uma forma de evitar um pouco os conflitos é trazer essas atualizações do projeto original para o
seu fork do projeto, no qual você está trabalhando, antes de implementar novas funcionalidades. 

Para ficar mais claro, peça para alguém da sua equipe inserir um novo arquivo no projeto original, que deve ser
chamado de __novafuncionalidade.txt__, seguido do nome de usuário de quem for criar esse arquivo (no meu caso ficou
__novafuncionalidade_hescarate.txt__). 

A idéia de inserir esse arquivo, é simular como se realmente fosse uma nova funcionalidade no projeto, como uma nova 
classe, interface, etc. Como comentamos acima, num ambiente de desenvolvimento de software isto acontece com muita
frequência, pois várias pessoas estão trabalhando em um projeto ao mesmo tempo. 

Feito isso, vamos usar o `pull`, para trazer as novas atualizações do projeto original para o nosso _fork_ do
projeto. 

Como estamos trabalhando com a branch __novafuncionalidade__, se executamos o `pull`, receberemos as atualizações do nosso
próprio _fork_ do projeto, e esse não é o nosso objetivo agora, e sim trazer as atualizações do projeto original. 

Vamos então para a _branch master_ ( _fork_ do nosso projeto):

    $ git checkout master

E como então trazer as atualizações do projeto original? 

Pra isso, é necessário adicionar um outro repositório remoto, que vamos dar o nome de __objectos__, e a url de onde
essas atualizações serão buscadas (do projeto original, de onde fizemos o _fork_)

    $ git remote add objectos git@github.com:objectos/objectos-dojo.git

Em seguida, vamos usar o `pull` informando que queremos trazer as atualizações do projeto original ( _objectos_ ) para 
a nossa _branch master_ (o _fork_ que demos do projeto original). 

    $ git pull objectos master

Agora as atualizações do projeto original vieram para o nosso _fork_ do projeto. 

Certo, mas como ficam as atualizações que eu fiz na _branch_ __novafuncionalidade__ (quando criamos o arquivo __config.txt__)?

Esse é o nosso próximo passo!

Volte então para a _branch_ __novafuncionalidade__: 

    $ git checkout novafuncionalidade

Agora precisamos atualizar o _fork_ do projeto ( _master_ ) com a nova funcionalidade criada na _branch_ __novafuncionalidade__,
pra isso vamos usar o `merge`: 

    $ git merge master
    Updating e78af0d..91ab9cd
    Fast-forward
    novafuncionalidade_usuário.txt |    3 +++
    1 files changed, 3 insertions(+), 0 deletions(-)
    create mode 100644 config.txt

O `merge` converte as mudanças feitas na _branch_, nesse caso a _master_, para a _branch_ a qual que você está executando o comando. 

Agora se você verificar os arquivos contidos na _branch_ __novafuncionalidade__, vai ver que a nova funcionalidade 
inserida lá no projeto original pelo seu colega de equipe (o arquivo __novafuncionalidade_usuário.txt__) aparece na sua
branch, pois usamos o `merge`. 

Vá ao Github, no _fork_ do seu projeto, e veja se as atualizações da _branch_ __novafuncionalidade__ (novo
diretório criado _objectos-dojo-seu-usuário_ mais o arquivo config.txt) estão na sua _branch master_. 

Não? Porque?

É necessário agora fazer um `merge` da _master_ com __novafuncionalidade__, e um `push` dele para _origin_ (a _fork_ do projeto). 

Vamos então voltar para a _branch master_:

    $ git checkout master

E então vamos fazer o `merge`: 

    $ git merge novafuncionalidade

Por fim, vamos mandar as atualizações da nossa _branch master_. 

    $ git push origin master

Vá ao Github e veja se as atualizações realmente estão na _branch master_. 

Certo, agora que você fez o `merge`, não vamos mais precisar da _branch_ __novafuncionalidade__. Então podemos excluí-la. 

Primeiro vamos excluir localmente, mas pra isso você precisa ir para a _branch master_, caso não esteja nela: 

    $ git branch -d novafuncionalidade
    Deleted branch novafuncionalidade (was cb80eee).

E agora vamos excluir remotamente: 

    $ git push origin :novafuncionalidade
    To git@github.com:hescarate/objectos-dojo.git
     - [deleted]         novafuncionalidade

Pronto, sua branch foi excluída!