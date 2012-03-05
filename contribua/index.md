---
layout: post
title: "Como contribuir"
author: "Marcio Endo"
user: "mendo"
published: true
---

## Para os impacientes

1. Faça um fork do projeto no [GitHub](https://github.com/objectos/objectos-dojo)
1. Importe o projeto para o Eclipse

## Um lugar para equipes aprenderem juntas

Quem já participou de um processo seletivo na objectos sabe que uma das perguntas feita é
a seguinte:

"Ninguém na objectos estará aqui para ensinar, por outro lado, a objectos busca pessoas 
que sejam capazes de aprender. Isto posto e em seu entendimento, qual a diferença 
entre 'querer aprender' e 'querer ser ensinado'?"

Para crescer, a objectos acredita que cada equipe precisa ser totalmente independente.
Em particular e em especial, não deve depender de alguém para adquirir, 
obter, melhorar e criar conhecimento.

Neste ponto, faço minhas as palavras de [Uncle Bob Martin](https://twitter.com/#!/unclebobmartin) 
quando define, na visão dele, o que é um programador profissional:

> If you are a professional, then you are responsible for your own career. 
> You are responsible for reading and learning. 
> You are responsible for staying up-to-date with the industry and the technology. 
> Too many programmers feel that it is their employer's job to train them. 
> Sorry, this is just dead wrong. Do you think doctors behave that way? 
> Do you think lawyers behave that way? No, they train themselves on their own time, and their own nickel. 
> They spend much of their off-hours reading journals and decisions. 
> They keep themselves up-to-date. And so must we. 
> The relationship between you and your employer is spelled out nicely in your employment contract. 
> In short: They promise to pay you, and you promise to do a good job 

(A definição completa pode ser encontrada [aqui](http://programmer.97things.oreilly.com/wiki/index.php/The_Professional_Programmer))

Neste contexto, o objectos-dojo é o local para que equipes e programadores:

1. Adquiram conhecimento por conta própria;
1. Criem conhecimento por conta própria; e
1. Propaguem conhecimento por contat própria.

## Primeiros passos

O projeto é totalmente de código aberto hospedado no [GitHub](https://github.com/objectos/objectos-dojo). 
Este possui duas partes principais:

1. Este site em que estão hospedados artigos, tutoriais, faqs, procedimentos.
1. Códigos-fonte que servem como laboratório e como exemplos.

### Implementação

É muito importante notar o seguinte.

* Códigos-fonte ficam hospedados na branch [master](https://github.com/objectos/objectos-dojo)
* Artigos, páginas, e documentos ficam hospedados na branch [gh-pages](https://github.com/objectos/objectos-dojo/tree/gh-pages)

## Importando os projetos para o Eclipse

Praticamente todo desenvolvimento na objectos é feito no Eclipse.

    $ cd
    $ mkdir -p kdo/projetos
    $ cd kdo/projetos
    $ git clone git@github.com:username/objectos-dojo.git objectos-dojo
    $ git clone git@github.com:username/objectos-dojo.git objectos-dojo-pages
    $ cd objectos-dojo-pages
    $ git checkout gh-pages