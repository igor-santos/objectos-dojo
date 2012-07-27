---
layout: post-alpha
title: "Script - Especificação"
author: "Marcos Piazzolla"
published: true
partof: processo
num: 4
outof: 4
---

##Introdução

Sempre que é passado algo novo pelo seu cliente, alguma rotina ou utilitário que precisa ser
implementado, é de suma importância seguir uma série de passos que irão ajudar a obter um resultado
final bem melhor onde o fator tempo pode ser aproveitado ao máximo afim de refinar a produtividade,
este artigo aborda estes passos que devem ser seguidos sempre que algo precisa ser feito.

##Objetivos

Vamos tomar como base uma Consulta de Produtos que deve ser implementada, quais são os passos a
serem seguidos pelo desenvolvedor antes de começar a implementar seu código?

Lembrando que o objetivo deste artigo não é gerar um procedimento para resolução de consultas e sim
gerar um procedimento que atenda qualquer tipo de problema a ser resolvido seja ele, buscador,
consulta, form, etc.

##Passo 1 : Especificação - Qual o problema do mundo real a ser resolvido ?

Primeiramente se perguntar - Qual problema será resolvido com a implementação desta consulta?
É importante que o desenvolvedor chegue em respostas análogas a: "Esta consulta lista todos os produtos
que o cliente tem em estoque, com esta consulta é possível implementar uma página onde o cliente
tem um feedback de todos os seus produtos em estoque, facilitando o controle interno dos produtos..."

Com esta pergunta inicial o desenvolvedor já é introduzido ao problema, não a todo problema
mas o mesmo já tem conhecimento/entendimento do que realmente está acontecendo, isso ajuda
e muito nos passos que estão voltados a implementação de uma possível solução do problema.

##Passo 2 : Por que eu estou fazendo isso ?

Pode até parecer estranho mas esta pergunta pode evitar grandes problemas no futuro, coisas como
mover classes para pacotes indevidos, exibir propriedades incorretas em consultas, implementação
incorreta de formulários entre outros problemas que poderiam ser citados.

A partir do momento que o desenvolvedor começa a se questionar quanto ao que ele esta fazendo ou
deve fazer, o mesmo vai tendo mais clareza quanto ao problema a ser resolvido, atente que com
apenas duas perguntas já sabemos o real problema do cliente e se a solução adotada atende ou não
as necessidades do problema.

##Passo 3 : Este problema já foi resolvido anteriormente ?

Com base nos pasos anteriores o desenvolvedor já esta a par do real problema ou de grande parte do
mesmo e já está apto a implementar uma solução, mas antes disso é importante realizar uma terceira
pergunta que pode resolver de vez o problema ou gerar um procedimento que impeça a repetição do mesmo
problema, a pergunta a ser feita é : "Por acaso este problema já foi resolvido anteriormente ?" 
- Caso sim, reaproveite o procedimento e resolva de vez o problema, caso contrário documente o
processo de resolução do problema afim de minimizar as chances de repetição do problema.

##Passo 4 : Thread em ativo

Através dos três passos citados acima as chances de diminuir repetições com um problema em particular
diminuem muito, mas é de suma importância que os "por quês", não parem no passo 3, o ideal é que eles
continuem durante todo o processo, assim o próprio desenvolvedor acaba se corrigindo quanto a
implementações duvidosas que podem estar incorretas.