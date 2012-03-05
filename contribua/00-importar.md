---
layout: post
title: "objectos-dojo :: Tutorial gh-pages"
author: "Marcio Endo"
user: "mendo"
published: true
---

## 1. No GitHub crie um fork deste projeto.

Caso não saiba como fazê-lo, esta [página](http://help.github.com/fork-a-repo/) poderá ajudá-lo.

## 2. Importe o projeto para a sua máquina.

Por padrão, todos os projetos na objectos ficam em um diretório local `~/kdo/projetos`

    $ cd
    $ mkdir -p kdo/projetos
    $ cd kdo/projetos
    $ git clone git@github.com:username/objectos-dojo.git objectos-dojo-pages
    $ cd objectos-dojo-pages
    $ git checkout gh-pages
    
Na listagem acima substitua o `username` para o nome correto de seu usuário GitHub. Por exemplo,
se o seu usuário no GitHub é `machadodeassis`, o comando fica:

	$ git clone git@github.com:machadodeassis/objectos-dojo.git objectos-dojo-pages
    
## 3. Importe o projeto para o Eclipse (opcional)
    
Praticamente todo desenvolvimento na objectos é feito no Eclipse. Isto é, em um dado
momento, qualquer estação de trabalho na objectos terá uma instância do Eclipse rodando.
Por questões de praticidade, portanto, é recomendável importar este projeto no Eclipse. 

Para contribuir com artigos para este projeto, no entanto, o Eclipse __NÃO__ é obrigatório. 
Nada impede, por exemplo, que você utilize outros editores como vim.

Para tanto basta ir para `Eclipse->File->Import`

Na janela que se abre `General->Existing projects into workspace`

O projeto estará no diretório:

`~/kdo/projetos/objectos-dojo-pages`

## 4. Escreva artigos!

Tendo o projeto localmente em sua máquina, o próximo passo é escrever artigos <a href="{{ site.baseurl }}/contribua/01-artigos.html" class="btn btn-success">Continuar!</a>