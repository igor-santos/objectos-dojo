---
layout: post-alpha
title: "Criando jobs no Jenkins"
author: "Marcos Piazzolla"
published: true
partof: processo
num: 3
---

##Introdução

O Jenkins é uma ferramenta utilizada para realizar o processo de integração contínua, ou seja de tempos
em tempos o mesmo executa todos os teste de nossa aplicação a fim de verificar se existem testes
falhando, neste artigo será mostrado como criar um job no Jenkins para que seja possível realizar
a verificação dos testes nas branches de um usuário em particular.

##Criando seu job no Jenkins

Primeiramente siga ao firefox e acesse o Jenkins pela seguinte url
[http://rio.objectos.com.br:8080/jenkins/] (http://rio.objectos.com.br:8080/jenkins/), no painel,
ao lado esquerdo clique em _Novo(a) Job_

![painel] (http://i1245.photobucket.com/albums/gg582/img_repo/jenkins/painel.png)

Em seguida o Jenkins irá nos redirecionar para a página de configuração de jobs

![config] (http://i1245.photobucket.com/albums/gg582/img_repo/jenkins/config.png)

Sempre defina o nome do job utilizando o seguinte padrão __usuario-nomeProjeto__, na opção
__Copiar Job existente__, copie o job do usuário que criou o projeto, neste caso __moe-nomeDoProjeto__,
clique em ok e siga para a página de configurações do usuário

![config_2] (http://i1245.photobucket.com/albums/gg582/img_repo/jenkins/config_2.png)

Em __Projeto nome__ defina o nome de seu projeto e no campo __Descrição__ adicione o nome do projeto,
feito isso, na mesma página indique ao Jenkins o nome das branches do usuário que serão realizadas as
verificações dos testes, neste caso mantenha o padrão dos nomes : nomeDoSeuUsuario&#42;
 
![config_3] (http://i1245.photobucket.com/albums/gg582/img_repo/jenkins/config_3.png)

Em seguida basta clicar em Salvar e depois em Apply, nosso Job foi criado com sucesso.