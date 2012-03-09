---
layout: post
title: "objectos-dojo :: Branch master e gh-pages"
---

##Trabalhando com branches no dojo

Você já deve saber que a branch _gh-pages_ é responsável por armazenar todos os arquivos que montam as 
páginas estáticas, ou seja, todos os arquivos de markdown, html, less, entre outros. 
Já a branch _master_ é responsável por armazenar todos os códigos JAVA, XML, SQL que foram inseridos 
nos exemplos das páginas.

_Importante: Esta branch é opcional, se em sua página não há códigos para linguagem de programação
não se preocupe com ela_.

##__1. Utilize a branch master (opcional)__

Se sua página tem códigos, será necessário utilizar a branch _master_ do projeto para criar estes
códigos e guardá-los para futuras consultas.
###__1.1. Importe o projeto do GitHub__

Primeiro faça um _fork_ do projeto _objectos-dojo_ contido no usuário _objectos_.<br> 
Agora execute os seguintes comandos:

	$ cd ~/kdo/projetos
	$ git clone git@github.com:username/objectos-dojo.git
	$ cd objectos-dojo

Na listagem acima substitua o _username_ para o nome correto de seu usuário GitHub

_Nota: Teremos dois projetos para trabalhar: objectos-dojo (códigos) e objectos-dojo-pages (páginas)_.
_Sendo que o segundo veremos no item 2._


###__1.2. Criando códigos na branch master

Com o projeto em sua máquina, crie uma _package_ nos seguintes diretórios:

> objectos-dojo-team/src/main/java/br/com/objectos/dojo/seu-login-de-rede-objectos<br>
> objectos-dojo-team/src/test/java/br/com/objectos/dojo/seu-login-de-rede-objectos

Os códigos JAVA devem ficar nestas _packages_.

_Nota: TODOS os códigos DEVEM possuir um cabeçalho padrão. Veja o exemplo de um código pronto_
_com cabeçalho [aqui](https://github.com/objectos/objectos-dojo/blob/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/string/Moeda.java)_. 

###__1.3. Atribua suas alterações ao GitHub
Após concluída as criações/alterações do códigos, devemos colocá-los no github através dos seguintes
comandos:

	$ git add <nome do arquivo>
	$ git commit -m "<mensagem do commit>"
	$ git push origin master

_Importante: Para visualizar o nome do arquivo corretamente, utilize o comando git status_.

Entre no github e faça um [Pull Request](http://help.github.com/send-pull-requests/) de sua 
branch _master_ para a branch _master_ da objectos.

Agora todos os códigos que serviram de exemplos nas páginas poderão ser acessados por completo 
através do github! Veja [aqui](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team).


##__2. Utilize a branch gh-pages__

###__2.1. Importe o projeto do GitHub__

Para importar o projeto veja [aqui](http://dojo.objectos.com.br/contribua/00-importar.html).

###__2.2. Atribua suas alterações ao GitHub__

Após concluída as alterações das páginas devemos colocá-las no github através dos seguintes comandos:

	$ git add <nome do arquivo>
	$ git commit -m "mensagem do commit"
	$ git push origin gh-pages
	
_Importante: Para visualizar o nome do arquivo corretamente utilize o comando git status_.

Entre no github e faça um [Pull Request](http://help.github.com/send-pull-requests/).

Agora os arquivos do projeto poderão ser acessadas através do github! Veja [aqui](https://github.com/objectos/objectos-dojo/tree/gh-pages)

###__2.3. Utilize outra branch (opcional)__

Com o projeto em sua máquina trabalhe em uma branch diferente de _gh-pages_. Para isso, execute o seguinte
comando:

	$ git checkout -b gh-pages-contribua
	
Onde _gh-pages-contribua_ é o nome de uma branch.

Trabalhe nela para evitar qualquer alteração na _gh-pages_. (O processo de push é o mesmo descrito anteriormente).