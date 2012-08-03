---
layout: post-alpha
title: "Configurações básicas"
author: "Hellen Carla Paixão Escarate"
published: true
tags:
- git
- aprendizes
- introdução
- commit não aparece
partof: git
num: 1
---

## Introdução 
O GIT (ou GitHub) é um sistema de controle de versão distribuído (vamos falar com detalhes sobre isso 
mais a frente) utilizado por desenvolvedores de softwares no intuito de controlar este desenvolvimento 
como versão de código, repositórios de imagens, hospedagem de códigos para páginas web como github pages, 
entre outras coisas.

Curiosidade: O pai do GIT é o Linus Torvalds, e ele foi criado para controlar o desenvolvimento 
do Kernel Linux. 

## Praticando
É fundamental que você esteja **afiado** no uso do GIT, porque vamos usá-lo com muita frequência.

Por isso, preparamos os próximos tópicos para que você possa treinar as operações básicas, e se caso algo
sair errado, não tem problema, essa é a hora de errar.

### Criando chaves SSH  
Precisamos criar chaves SSH para estabelecer uma conexão segura entre seu computador e o Github.

Então para gerar as chaves SSH, execute o comando abaixo: 

    $ ssh-keygen -t dsa

Depois que você executar esse comando, vai aparecer uma mensagem dizendo pra você entrar com um arquivo para salvar a
chave, se você não digitar nada ele vai deixar no endereço padrão. É o que vamos fazer. Portanto, depois que aparecer a
mensagem abaixo, aperter o `Enter`. 

    Enter file in which to save the key (/home/usuario/.ssh/identity):

Depois que você apertar o `Enter`, aparecerá uma mensagem pedindo para que você entrar com uma senha. Digite-a
e em seguida, será necessário redigitá-la conforme segue abaixo: 

    Enter passphrase (empty for no passphrase): (enter a passphrase)
    Enter same passphrase again: (enter it again)

Depois que você cadastrou a senha, aparecerá uma mensagem de que as chaves foram salvas no diretório `.ssh`, conforme segue
abaixo: 

    Your identification has been saved in /home/usuario/.ssh/identity.
    Your public key has been saved in /home/usuario/.ssh/identity.pub.
    The key fingerprint is:
    a4:e7:f2:39:a7:eb:fd:f8:39:f1:f1:7b:fe:48:a1:09 usuario@localbox

Vamos fazer um teste executando o comando abaixo.<br> 
Quando você acessar o repositório será pedido a senha cadastrada anteriormente.

    $ cat ~/.ssh/id_dsa.pub

Assim que você executar o comando acima, vai aparecer algo semelhante, que é a sua chave pública: 

    ssh-dss AAAAB3NzaC1kc3MAAACBAMNv9DcXARdoQkitn8kxFftuxouXSsV6kOuQv39nd79PokaNYyIUQ6RTxET2BZ8A1tC0Yg+6of
    kOfS0BjVhG5AaMa6ReJITL56JHNqJnSlt/nBG83IlLMKCoBgSGWc8TruZtACFizgke9iXtumQQbgeDiNjLRFuEDyvm85WySR5RAAAA
    FQCEjEiLk6SGHagdtSl8T+cwI5GGCwAAAIA4tjsun+BrYS6vTVUifixXCX5WIQdGOj0Fl9cqif1nct3zXJCUEpg6f69dgcbMPjnieo
    j2arYmLtAOcQKcoa3Leo++qPc0sst677aypJVUum2iR1dLJN1wWwHtjLOby2XInesoD3OBoE+XK6C9XYe96SqZPoxU54YAAAAIAS8i
    sUPfgS4byPLGIndKcgN3morHvoYElyjg3GbFda5mAFnSjkIWtQMPgbIbi3E+uw92FCk773ew5xPlRYUjQXZaNzuh+0sHJrkaBFvbSI
    KoLHiaRlF4VvvpHrqrhiINNAMN2rER+4QI7pP8ysCP1ahjiwON4fsB7uYqKw== hescarate@estacao004

Depois de ter gerado a chave pública, é necessário cadastrá-la no Github.<br> 
Acesse o site [aqui](https://github.com/signup/free).

### Criando uma conta
Estando no site, você verá uma tela, como essa abaixo: 

![criandoconta](https://github.com/objectos/objectos-dojo-img/blob/master/github/1_criandoconta.png?raw=true)

Depois que você preencher os dados, clique no botão `Create an account`.

Pronto! Agora você tem uma conta no Git Hub! 

O próximo passo é inserir a sua chave pública, pra isso, clique em `Account Settings` que fica no canto superior à direita, 
como podemos ver em destaque abaixo:

![account settings](https://github.com/objectos/objectos-dojo-img/blob/master/github/3_configuracoesgit.png?raw=true) 

Aparecerá então, um outro menu, mais abaixo à esqueda.<br> 
Nesse menu você vai escolher a opção `SSH Keys`: 

![ssh public keys](https://github.com/objectos/objectos-dojo-img/blob/master/github/4_inserindochave.png?raw=true)

Veja que aparece à direita a opção `Add SSH key`

![another public key](https://github.com/objectos/objectos-dojo-img/blob/master/github/5_inserirchavepublica.png?raw=true)

Clique nessa opção conforme a imagem abaixo. O campo _Key_ é onde vamos colar a chave pública: 

![colar chave publica](https://github.com/objectos/objectos-dojo-img/blob/master/github/7_chavepublica_a_inserir.png?raw=true)

Agora vamos voltar no terminal, e exibir novamente a chave pública que criamos: 

    $ cat ~/.ssh/id_dsa.pub

Depois que você executar o comando acima, a chave pública vai aparecer: 

    ssh-dss AAAAB3NzaC1kc3MAAACBAMNv9DcXARdoQkitn8kxFftuxouXSsV6kOuQv39nd79PokaNYyIUQ6RTxET2BZ8A1tC0Yg+6of
    kOfS0BjVhG5AaMa6ReJITL56JHNqJnSlt/nBG83IlLMKCoBgSGWc8TruZtACFizgke9iXtumQQbgeDiNjLRFuEDyvm85WySR5RAAAA
    FQCEjEiLk6SGHagdtSl8T+cwI5GGCwAAAIA4tjsun+BrYS6vTVUifixXCX5WIQdGOj0Fl9cqif1nct3zXJCUEpg6f69dgcbMPjnieo
    j2arYmLtAOcQKcoa3Leo++qPc0sst677aypJVUum2iR1dLJN1wWwHtjLOby2XInesoD3OBoE+XK6C9XYe96SqZPoxU54YAAAAIAS8i
    sUPfgS4byPLGIndKcgN3morHvoYElyjg3GbFda5mAFnSjkIWtQMPgbIbi3E+uw92FCk773ew5xPlRYUjQXZaNzuh+0sHJrkaBFvbSI
    KoLHiaRlF4VvvpHrqrhiINNAMN2rER+4QI7pP8ysCP1ahjiwON4fsB7uYqKw== hescarate@estacao004

Selecione com o mouse e copie toda a chave pública, conforme o exemplo acima, e cole no campo _Key_ do site
 (não é necessário preencher nada no campo _Title_). 

![colando chave](https://github.com/objectos/objectos-dojo-img/blob/master/github/6_colandochavepublica.png?raw=true)

Basta clicar em `Add key` para finalizar. Observe que, aparecerá no título o seu nome de usuário, 
conforme a tela abaixo:

![ok](https://github.com/objectos/objectos-dojo-img/blob/master/github/8_chavepublica.png?raw=true)

#### Definir seu nome de usuário e e-mail

É importante definir seu nome de usuário e e-mail, pois essa informação é usada para associar seu _commit_
(veremos os _commits_ mais adiante) a sua conta do Github.

    $ git config --global user.name "Nome Sobrenome"
    $ git config --global user.email "seu_e-mail@objectos.com"

Nota: O nome deve ser seu nome real e não seu nome de usuário no Github. 

É importante certificar-se que no Github o e-mail é o mesmo que foi adicionado acima. Caso contrário seus _commits_ podem
não aparecer. Para confirmar vá no Github em `Accont Settings` e escolha a opção `Emails` e confirme se o e-mail
cadastrado é `seuemail@objectos.com.br`.  

Outro detalhe importante é verificar se no [Gravatar](http://en.gravatar.com/) o e-mail cadastrado também é o mesmo (que foi
cadastrado acima). Caso contrário, sua imagem não aparecerá.  

Ok! Agora que configuramos o usuário para usar o GitHub vamos importar um projeto. 

### Importando Projetos 

No Github, vá na página da [Objectos](https://github.com/objectos), no projeto __objectos-dojo__:

Agora vamos fazer um _fork_ desse projeto, clicando no botão `Fork`, conforme a imagem abaixo: 

![forkdoprojeto](https://github.com/objectos/objectos-dojo-img/blob/master/github/9_forkdoprojeto.png?raw=true)

Nota: O _fork_ seria algo como "pegar aquele projeto pra você", a "sua cópia" do projeto, onde
você pode fazer suas alterações sem modificar o projeto original. 

Sempre vamos trabalhar com o nosso _fork_ do projeto, ou seja a nossa versão. 

No Github, clique vá na página do seu usuário (caso não esteja nessa tela): 

![gitseu usuario](https://github.com/objectos/objectos-dojo-img/blob/master/github/10_forkseuusuairo.png?raw=true)

Você vai observar que agora, o projeto __objectos-dojo__ aparece nos seus respositórios, e mostra de onde você fez o _fork_
conforme a imagem abaixo: 

![seu repositorio](https://github.com/objectos/objectos-dojo-img/blob/master/github/11_forkclicarrepositorio.png?raw=true)

Clique em `objectos-dojo` e então e veja se o botão `Code` está habilitado (caso contrário, clique nele). 

![botao source habilitado](https://github.com/objectos/objectos-dojo-img/blob/master/github/12_fork_endereco_git.png?raw=true) 

Mais abaixo, você pode observar o endereço SSH deste repositório: 

![endereço ssh git](https://github.com/objectos/objectos-dojo-img/blob/master/github/13_fork_endereco_git.png?raw=trueg)

Copie esse endereço, pois vamos importá-lo pelo terminal.

No terminal, entre no diretório dos projetos `cd ~/kdo/projetos` e digite:

    $ git clone git@github.com:hescarate/objectos-dojo.git
    
Importante: Substitua o nome `hescarate` pelo seu usuário do github!   

Depois que você executou o comando, deve aparecer algo semelhante, confirmando que foi feito o clone do projeto: 

    Cloning into objectos-dojo...
    remote: Counting objects: 994, done.
    remote: Compressing objects: 100% (392/392), done.
    remote: Total 994 (delta 371), reused 974 (delta 353)
    Receiving objects: 100% (994/994), 11.73 MiB | 1.11 MiB/s, done.
    Resolving deltas: 100% (371/371), done.

Utilizamos um fork neste caso, mas há projetos onde será necessário cloná-lo diretamente do diretório 
da Objectos, ou seja, o _fork_ não se aplica a todos os projetos.<br>
Na dúvida, pergunte ao seu líder se há a necessidade de um _fork_!

## Referências 

* [Help do Github](http://help.github.com/linux-set-up-git/)
