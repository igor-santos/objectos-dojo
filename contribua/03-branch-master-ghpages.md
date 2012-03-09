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

##__1. Importe o projeto do GitHub__

Para importar veja [aqui](http://dojo.objectos.com.br/contribua/00-importar.html)

##__2. Utilize a branch correta__

Com o projeto em sua máquina trabalhe em uma branch diferente de _gh-pages_. Veja o seguinte exemplo:

	$ git checkout -b gh-pages-contribua
	
Onde _gh-pages-contribua_ é o nome de uma branch

##__3. Atribua suas mudanças ao GitHub__

Após concluída as alterações do projeto devemos colocá-las no github através do seguinte comando:

	$ git push origin gh-pages-contribua
	
Entre no github e faça um [Pull Request](http://help.github.com/send-pull-requests/) de sua branch _gh-pages-contribua_ para a branch _gh-pages_
da objectos.

##__4. Utilize a branch master (opcional)__

Se sua página tem códigos para exemplo, será necessário utilizar a branch _master_ do projeto para 
criar estes códigos.
> ###__4.1. Importe o projeto do GitHub pela branch master__

	cd ~/kdo/projetos
	git clone git@github.com:username/objectos-dojo.git
	cd objectos-dojo

Na listagem acima substitua o _username_ para o nome correto de seu usuário GitHub
