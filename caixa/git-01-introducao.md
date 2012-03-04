---
layout: post-alpha
title: "Git :: Introdução"
author: "Hellen Carla Paixão Escarate"
published: true
tags:
- git
- aprendizes
- introdução
- commit não aparece
---

## Introdução 

No tutorial anterior, vimos um pouco sobre o sistema de controle de versão. 

Agora, precisamos entender mais sobre a ferramenta que vamos usar diariamente, o GIT. 

O GIT é um sistema de controle de versão distribuído (vamos falar com detalhes sobre isso mais a frente). 

Só uma curiosidade: O pai do GIT é o Linus Torvalds, e ele foi criado para controlar o desenvolvimento do Kernel Linux. 

# Definir seu nome de usuário e e-mail

Antes de praticar e fazer qualquer **commit**, é importante definir seu nome de usuário e e-mail, pois essa informação é
usada para associar seu commit a sua conta do Github. 

    $ git config --global user.name "Nome Sobrenome"
    $ git config --global user.email "seu_e-mail@youremail.com"

(O nome deve ser seu nome real e não seu nome de usuário no Github)

É importante certificar-se que no Github o e-mail é o mesmo que foi adicionado acima. Caso contrário pode ser que os
seus commits não apareçam. Para confirmar essa informação, no Github vá em ****


## Praticando

É fundamental que você esteja **afiado** no uso do GIT, porque vamos usá-lo com muita frequência.

Por isso, preparamos os próximos tópicos, para que aqui, você possa treinar as operações básicas, e se caso algo
sair errado, não tem problema, essa é a hora de errar.

Então, vamos lá, siga para o próximo passo. 

### Criando chaves públicas e privadas  

Porque precisamos criar essas chaves?

Precisamos criar chaves SSH para estabelecer uma conexão segura entre seu computador e o Github. 

Então para gerar as chaves SSH, execute o comando abaixo: 

    $ ssh-keygen -t dsa

Depois que você executar esse comando, vai aparecer uma mensagem dizendo pra você entrar com um arquivo para salvar a
chave, se você não digitar nada ele vai deixar no endereço padrão. É o que vamos fazer. Portanto, depois que aparecer a
mensagem abaixo, clique em **Enter** . 

    Enter file in which to save the key (/home/usuario/.ssh/identity):

Depois que você apertou ENTER, aparece uma mensagem pedindo para que você entre com uma senha, e em seguida é
necessário redigitá-la conforme segue abaixo: 

    Enter passphrase (empty for no passphrase): (enter a passphrase)
    Enter same passphrase again: (enter it again)

Depois que você cadastrou a senha, aparece uma mensagem de que as chaves foram salvas no diretório .ssh, conforme segue
abaixo: 

    Your identification has been saved in /home/usuario/.ssh/identity.
    Your public key has been saved in /home/usuario/.ssh/identity.pub.
    The key fingerprint is:
    a4:e7:f2:39:a7:eb:fd:f8:39:f1:f1:7b:fe:48:a1:09 usuario@localbox

Vamos fazer um teste agora, executando o comando abaixo. Provavelmente vai aparecer uma caixinha pedindo a senha que
você cadastrou antes.

(Sempre que você acessar o repositório será pedida essa senha).

    $ cat ~/.ssh/id_dsa.pub

Assim que você executou o comando acima, vai aparecer algo semelhante, que é a sua chave pública: 

    ssh-dss AAAAB3NzaC1kc3MAAACBAMNv9DcXARdoQkitn8kxFftuxouXSsV6kOuQv39nd79PokaNYyIUQ6RTxET2BZ8A1tC0Yg+6of
    kOfS0BjVhG5AaMa6ReJITL56JHNqJnSlt/nBG83IlLMKCoBgSGWc8TruZtACFizgke9iXtumQQbgeDiNjLRFuEDyvm85WySR5RAAAA
    FQCEjEiLk6SGHagdtSl8T+cwI5GGCwAAAIA4tjsun+BrYS6vTVUifixXCX5WIQdGOj0Fl9cqif1nct3zXJCUEpg6f69dgcbMPjnieo
    j2arYmLtAOcQKcoa3Leo++qPc0sst677aypJVUum2iR1dLJN1wWwHtjLOby2XInesoD3OBoE+XK6C9XYe96SqZPoxU54YAAAAIAS8i
    sUPfgS4byPLGIndKcgN3morHvoYElyjg3GbFda5mAFnSjkIWtQMPgbIbi3E+uw92FCk773ew5xPlRYUjQXZaNzuh+0sHJrkaBFvbSI
    KoLHiaRlF4VvvpHrqrhiINNAMN2rER+4QI7pP8ysCP1ahjiwON4fsB7uYqKw== hescarate@estacao004

Certo, depois de ter gerado a chave pública, é necessário cadastrá-la no Github: https://github.com/

### Github

O Github é um serviço muito interessante, onde é possível compartilhar projetos, de maneira que todos possam
contribuir com o código.

Agora então você precisa criar uma conta no Github para cadastrar a chave pública. 

Como eu demorei pra achar onde se cria a conta, você pode acessar por esse link direto: https://github.com/signup/free,
assim fica mais fácil!

Vai aparecer então uma tela, como essa abaixo: 

![criandoconta](https://github.com/objectos/objectos-dojo-img/blob/master/github/1_criandoconta.png?raw=true)

Depois que você preencher os dados, clique no botão **Create an account**, que ficará destacado em azul quando você
passar o mouse em cima:

![new account](https://github.com/objectos/objectos-dojo-img/blob/master/github/2_clicaremcreatenewaccount.png?raw=true)

Pronto! Agora você tem uma conta no Git Hub! 

O próximo passo é inserir a sua chave pública, pra isso, clique em **Account Settings** que fica num menu lá em cima,
à direita, como podemos ver em destaque abaixo:

![account settings](https://github.com/objectos/objectos-dojo-img/blob/master/github/3_configuracoesgit.png?raw=true) 

Agora que você clicou em **Account Settings** vai aparecer um outro menu, mais abaixo, à esqueda. Nesse menu você vai
escolher a opção **SSH Public Keys**: 

![ssh public keys](https://github.com/objectos/objectos-dojo-img/blob/master/github/4_inserindochave.png?raw=true)

Veja que aparece à direita a opção **Add another public key**

![another public key](https://github.com/objectos/objectos-dojo-img/blob/master/github/5_inserirchavepublica.png?raw=true)

Clique nessa opção, e vai aparecer conforme a imagem abaixo. O campo **Key** é onde vamos colar a sua chave pública: 

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

Selecione com o mouse e copie toda a chave pública, conforme o exemplo acima, e cole em **Key** (não é necessário
preencher nada no campo title). 

![colando chave](https://github.com/objectos/objectos-dojo-img/blob/master/github/6_colandochavepublica.png?raw=true)

Basta clicar em Add key para finalizar, e se você observar em seguida, aparecerá no título o seu nome de usuário, 
conforme a tela abaixo:

![ok](https://github.com/objectos/objectos-dojo-img/blob/master/github/8_chavepublica.png?raw=true)

Ok! Agora que criamos uma conta no Git Hub e adicionamos a chave pública vamos para o próximo passo que é importar o
projeto. 

#### Definir seu nome de usuário e e-mail

Antes de mais nada, e de fazer qualquer **commit**, é importante definir seu nome de usuário e e-mail, pois essa informação é
usada para associar seu commit a sua conta do Github.

    $ git config --global user.name "Nome Sobrenome"
    $ git config --global user.email "seu_e-mail@objectos.com"

Obs: O nome deve ser seu nome real e não seu nome de usuário no Github. 

É importante certificar-se que no Github o e-mail é o mesmo que foi adicionado acima. Caso contrário seus commits podem
não aparecer. Para confirmar vá no Github em *Accont Settings* e escolha a opção *Email adresses* e confirme se o e-mail
cadastrado é seuemail@objectos.com.br.  

Outro detalhe importante é verificar se no [Gravatar](http://en.gravatar.com/) o e-mail cadastrado também é o mesmo (que foi
cadastrado acima). Caso contrário provavelmente sua imagem não aparecerá.  

### Importando Projetos 

No Github, vá na página da Objectos, no projeto objectos-dojo:

https://github.com/objectos/objectos-dojo

Agora vamos fazer um *fork* desse projeto, clicando no botão *FORK*, conforme a imagem abaixo: 

![forkdoprojeto](https://github.com/objectos/objectos-dojo-img/blob/master/github/9_forkdoprojeto.png?raw=true)

Dar um fork no projeto seria algo como **pegar aquele projeto pra você**, fica sendo a **sua cópia** do projeto, onde
você pode fazer suas alterações sem modificar o projeto original. 

Sempre vamos trabalhar com o nosso fork do projeto, ou seja a nossa versão. 

O próximo passo é importar esse projeto para a nossa máquina.

No Github, clique lá em cima da página no seu usuário (caso não esteja nessa tela): 

![gitseu usuario](https://github.com/objectos/objectos-dojo-img/blob/master/github/10_forkseuusuairo.png?raw=true)

Você vai observar que agora, o projeto objectos-dojo aparece nos seus respositórios, e mostra de onde você fez o fork
conforme a imagem abaixo: 

![seu repositorio](https://github.com/objectos/objectos-dojo-img/blob/master/github/11_forkclicarrepositorio.png?raw=true)

Clique então em **objectos-dojo** (conforme mostra sublinhado na imagem acima), e então já deve aparecer o botão 
**source** habilitado (caso contrário, clique nele). 

![botao source habilitado](https://github.com/objectos/objectos-dojo-img/blob/master/github/12_fork_endereco_git.png?raw=true) 

E então mais abaixo, você pode observar que aparece o endereço SSH git: 

![endereço ssh git](https://github.com/objectos/objectos-dojo-img/blob/master/github/13_fork_endereco_git.png?raw=trueg)

Então copie esse endereço, pois vamos importá-lo pelo terminal.

No terminal, entre no diretório `~/kdo/projetos`, pois é nesse diretório que vamos importar nosso fork do projeto. 

    $ git clone git@github.com:hescarate/objectos-dojo.git

Depois que você executou o comando, deve aparecer algo semelhante, confirmando que foi feito o clone do projeto: 

    Cloning into objectos-dojo...
    remote: Counting objects: 994, done.
    remote: Compressing objects: 100% (392/392), done.
    remote: Total 994 (delta 371), reused 974 (delta 353)
    Receiving objects: 100% (994/994), 11.73 MiB | 1.11 MiB/s, done.
    Resolving deltas: 100% (371/371), done.

Esse projeto é um projeto de treinamento, mas lembre-se, em qualquer projeto que você for trabalhar aqui é necessário
**primeiro fazer um fork do projeto, e então importar, fazendo um clone do seu fork**, para depois começar a trabalhar no
projeto. 

## Referências 

* [Help do Github](http://help.github.com/linux-set-up-git/)

