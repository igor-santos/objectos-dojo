---
layout: post-alpha
title: "Stack Traces"
author: "Marcos Piazzolla"
published: true
partof: java
num: 1
---

## Stack Traces

Muitas vezes quando desenvolvemos um aplicativo existem situações onde o inusitado acontece, a aplicação para e uma
mensagem assustadora de erro acaba surgindo no terminal, a mensagem é tão grande que logo entramos em desespero não 
sabendo por onde começar a leitura do "erro", estas mensagens que são "assustadoras" são conhecidas como Stack Traces e
pra falar a verdade elas não são tão assustadoras assim, seu objetivo é apenas dar ao desenvolvedor um feedback do que 
aconteceu na aplicação mas muitas vezes o tamanho da Stack Trace acaba assustando. As Stack Traces são nossas amigas e
aparecem para nos ajudar, vamos deixar de lado o medo e entender de vez o que uma Stack Trace nos mostra.
Mas antes mesmo de começar a tratar de Stack Traces e forçar erros para que as mesmas ocorram, vamos falar de um 
conceito que será de grande ajuda para o entendimento do que foi dito acima, vamos falar sobre a pilha de execuções.
	
## Pilha de Execuções 

Vamos utilizar um exemplo para esclarecer este conceito de pilha de execução, atente a amostra de código abaixo:

    package com.objectos.stacktraces;  
	
    class Stack {
       public static void main(String[] args) {
       metodoA();
       }

       static void metodoA() {
       System.out.println("Método A");
       metodoB();
       }

       static void metodoB() {
       System.out.println("Método B");
       }
    }

Todo programa java possui a tal "pilha de execuções", mas afinal de contas o que é "esta pilha de execuções" ? A Grosso
modo é onde todas as chamadas de métodos em um programa java estão, vamos analisar o trecho acima. 
Quando o programa acima for compilado e executado o mesmo irá realizar uma série de chamadas aos seus respectivos
métodos, logo o java organiza as chamadas aos métodos em uma estrutura muito conhecida na área de desenvolvimento de
software, a pilha. Assim quando o programa acima for executado todas as chamadas de métodos serão movidas para a pilha e
executadas do topo da pilha até a base, no caso o método principal de nosso programa acima.

A pilha de execução do programa acima ficaria da seguinte forma:
Inicio do programa, no método principal é efetuada a chamada ao métodoA() logo o mesmo segue ao topo da pilha, seguindo
o fluxo do programa o metodoA() é executado e acaba fazendo uma chamada ao metodoB() que por sua vez assume o topo, o
metodoB() é executado e acaba deixando a pilha, dando passagem ao metodoA() que novamente assume o topo, após a execução
do método o fluxo volta ao método principal que também estava na pilha, lembrando que a pilha armazena todas as chamadas
de métodos (main() também é um método). Assim a pilha de execuções acaba junto com o programa que por sua vez acaba
imprimindo:

    Método A
    Método B

Agora que sabemos o que é a pilha de execuções e como as coisas funcionam em um programa Java, podemos dar continuidade
ao que estávamos discutindo anteriormente. As Stack Traces.

## Como as Stack Traces ocorrem ?

As Stack Traces surgem toda vez que o fluxo de um programa na pilha de execuções é interrompido por uma exceção, assim a
mensagem de erro é lançada apontando todo o fluxo da pilha de execuções e os possíveis pontos onde a exceção ocorreu. 

Mas como algo assim pode ocorrer?

Isso é bem simples, lembrando que exceções podem ocorrer por conta de simples erros que cometemos como por exemplo:
tentar dividir um valor por zero, acessar um índice inexistente de um vetor ou até mesmo tentar acessar uma variável de
referência que não aponte para um objeto.

Em situações como essas exceções são lançadas e Stack Traces acabam aparecendo.
Vamos forçar uma exceção e nos aprofundar um pouco mais sobre o assunto. Para isso vamos reaproveitar o exemplo acima e
adicionar o bloco responsável pela exceção.

    package com.objectos.stacktraces;

    class Stack {
       public static void main(String[] args) {
       metodoA();
       }

       static void metodoA() {
          System.out.println("Método A");
	  metodoB();
       } 

       static void metodoB() {
          int x = 2, y = 0;
	  int z = x / y;
	  System.out.println(z);//Exceção!!!!!!!!
	  System.out.println("Método B");
       }
    }

Compilamos o programa e então veja só o que acontece:

    Método A
    Exception in thread "main" java.lang.ArithmeticException: / by zero
        at com.objectos.stacktraces.Stack.metodoB(Stack.java:24)
        at com.objectos.stacktraces.Stack.metodoA(Stack.java:19)
        at com.objectos.stacktraces.Stack.main(Stack.java:14)
    Java Result: 1
	
Temos o nosso Stack Trace mostrando a pilha de execução, vamos analisar com calma o que aconteceu e pensar em uma forma
de tratar este problema.
No topo da pilha temos o ponto onde a exceção ocorreu, que foi exatamente no metodoB(), como não tratamos a exceção
(falaremos disso mais adiante) ela acabou passando para o próximo elemento da pilha, o metodoA() que fez a chamada ao
metodoB() o causador de nossos problemas, mas o metodoA() assim como seu antecessor na pilha não trata a exceção
deixando isso a critério do método principal, como o método principal não possui nenhuma tratativa para a exceção
teremos um belo Stack Trace na tela quando o programa for executado.

Falamos muito sobre o nosso Stack Trace acima, mas ainda fica a duvida. Como ele poderá nos ajudar?
O Stack Trace pode, não, ele vai nos ajudar na trativa de exceções e erros que ocorrem ao decorrer da aplicação, como
por exemplo acima, se lermos o Stack Trace podemos perceber que a mensagem de "erro" apareceu na tela por alguém tentar
realizar uma operação matemática ilegal, lendo a primeira linha percebemos isso quando encontramos "ArithmeticException:
/by zero" e de quebra percebemos que fora uma divisão por zero, algo que não existe!

Agora ficou fácil pois encontramos o causador de nossos problemas, o próximo passo é tratar a exceção para que este erro
jamais aconteça novamente.
	
## Corrigindo o problema 

Conseguimos forçar nossa exceção para que uma Stack Trace ocorresse, mas como evitar que algo assim acabe acontecendo?

Isso é bem simples basta utilizar uma tratativa de exceções, um bloco try/catch, que é responsável em capturar uma ou
mais exceções e fazer algo sempre que a exceção ocorrer, em nosso caso vamos mostrar que não existe divisão por ZERO. 
Vamos adicionar um bloco try/catch ao programa acima e analisar o resultado.

    package com.objectos.stacktraces;

    class Stack {
       public static void main(String[] args) {
          metodoA();
       }

       static void metodoA() {
          System.out.println("Método A");
             metodoB();
       }

       static void metodoB() {
          try {		
             int x = 2, y = 0;
             int z = x / y;
             System.out.println(z);
	     catch(ArithmeticException ae ) {
	        System.out.println("Não existe divisão por Zero!!!!");
             }
                   System.out.println("Método B");
              }
       }

Atente para a saída:

    Método A
    Não existe divisão por zero!!!
    Método B
	
Agora as coisas parecem bem melhores, temos nossa exceção tratada, o programa funciona normalmente e uma tratativa de
exceções com uma mensagem bem elegante sempre que algo de errado acontecer. Tudo isso graças aquela mensagem de erro
"medonha" que muitos se apavoram só de ver, percebemos aqui que o Stack Trace é realmente o nosso amigo e com ele
podemos corrigir falhas em nossas aplicações pois o mesmo nos indica todo o fluxo do programa através da pilha de
execuções e os respectivos pontos onde ocorreram as exceções, com isso ele deixou de ser apenas uma mensagem de erro e 
se tornou em um grande aliado nosso.

## Referências

* SIERRA, Kathy. BATES, Berty. Use a Cabeça, Segunda edição

* [Macoli](http://macoli.wordpress.com/2010/02/20/noobs-e-stacktraces/)

* [Java Free](http://javafree.uol.com.br/topic-882405-FAQ-Stack-Trace.html)

*Artigo escrito por Marcos Piazzolla.*		
