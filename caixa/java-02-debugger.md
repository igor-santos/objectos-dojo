---
layout: post-alpha
title: "Debugger"
published: true
partof: java
num: 2
---

# Introdução

Por mais capacitado que seja um desenvolvedor existem momentos onde é necessário analisar uma aplicação mais a fundo, pois alguns componentes da mesma começaram a se
comportar de forma muito estranha, para isso o desenvolvedor possui um recurso muito interessante chamado "Debugger" ou depurador. Com isso o desenvolvedor pode 
resolver possíveis falhas em uma aplicação de maneira bem dinâmica.

# O que é um depurador ?

Antes de começarmos a praticar devemos entender primeiro o que é um depurador, o depurador é uma ferramenta muito conhecida na área de desenvolvimento de software.
O uso desta ferramenta esta voltado para a correção de possíveis falhas em uma aplicação. Estas falhas, ou melhor bugs, acabam ocorrendo por conta de modificações feitas 
pelo desenvolvedor ao decorrer da aplicação. O depurador consiste em controlar o fluxo de execução de uma aplicação através de estruturas conhecidas como BREAKPOINTS
ou pontos de parada. Os BREAKPOINTS permitem a execução da aplicação até um certo ponto, permitindo ao desenvolvedor a análise de varivaveis e métodos ao decorrer da 
aplicação. 

# Por que utilizar o depurador ?

È possível fazer todo o processo de verificação de funcionalidade de componentes sem a ajuda de um depurador, mas se levarmos em conta o tempo que seria gasto, o 
desenvolvedor acabaria se prejudicando, e muito. Ao utilizar uma ferramenta de depuração seja pelo Netbeans ou até mesmo pelo Eclipse, o desenvolvedor tem um melhor
feedback em relação a aplicação, lembrando que o próprio desenvolvedor tem controle sobre o fluxo de execução da aplicação através dos BREAKPOINTS, tudo isso contribui
e muito para a localização de falhas. Como o desenvolvedor tem controle sobre o fluxo de execução é possível iterar entre estruturas de repetição e também inspecionar 
métodos utilizando as funcionalidade Step into, Step over e Step return. Assim a iteração entre componentes é bem mais dinâmica.
Algo muito importante que deve ser levado em conta quanto ao depurador é que além de ajudar a resolver falhas em um aplicativo o mesmo acaba nos forçando a entender 
como uma aplicação realmente funciona, isso por conta do desenvolvedor ter controle em realção ao fluxo de execução da aplicação, assim o mesmo pode entender o que 
acontece dentro de uma aplicação e também como acontece, pelo depurador é possível analisar até mesmo o conteúdo de expressões lógicas de estruturas if/else e até 
mesmo de laços de repetição. Então sabemos agora quão importante é utilizar um depurador e seus beneficios.    

# Quando utilizar o depurador ?

Esta é uma boa pergunta, aqueles que tiveram pouco contato ou até mesmo nunca tiveram contato com um depurador acabam pensando que utilizar um depurador pode ser 
algo que não lhe traga nenhum benefício, tanto didático como em seu dia-dia (trabalhando), por isso acabam não compreendendo o poder desta ferramenta e os beneficios 
que a mesma pode trazer. Vamos voltar a questão. O momento certo para utilizar o depurador é definido de acordo com a necessidade do desenvolvedor, algumas vezes nem 
é preciso utilizar o depurador para se corrigir bugs, pois os mesmos podem ser facilmente visualizados e corrigidos. Porém existem casos onde o bug se encontra entre 
várias tomadas de decisões, laços de repetições e outros tipos de estruturas que acabam apresentando uma lógica um pouco mais complexa de se compreender, com isso não 
é possível resolver a falha sem uma ferramenta específica, no caso o depurador. 
Então deve-se utilzar o depurador de acordo com as necessidades do desenvolvedor e também deve-se considerar o nível de complexibilidade do que será depurado.

# Como utilizar o depurador ?

O depurador pode ser utilizado através da linha de comando pelo terminal, utilizando o jdb (Java debugger) ou então a partir de uma IDE, lembrando que ao se utilizar uma 
IDE temos uma melhor visualização do que está realmente acontecendo graças ao ambiente gráfico. Diferente do terminal onde não é possível ter um nível muito alto de 
dinamismo por conta da ausencia de uma interface amigável. Por questões didáticas vamos utilizar o Eclipse para efetuar o debug de um aplicativo. 
Muito bem, agora que sabemos o que é um depurador e quando utilizamos o mesmo, vamos ao nosso exercício, onde será possível ver na prática tudo o que foi comentado acima. 
Antes de começarmos a depuração devemos ter em mente que é preciso ter duas coisas muito importantes. A primeira seria estipular um ponto de parada (BREAKPOINT) para 
controlar o fluxo da execução do aplicativo a segunda seria onde colocar o nosso "BREAKPOINT". Vamos praticar um pouco.

Vamos pensar no seguinte caso: 
É preciso implementar uma aplicação que imprima ao usuário os caracteres da tabela ASCII, algo bem simples. Vamos abrir o Eclipse e implementar nossa aplicação 
que no final das contas acabará ficando desta maneira:

*Classe Debugging*

	package com.objectos.depurador;
		  
	/**
	 * @author marcos.piazzolla@objectos.com.br (Marcos Piazzolla)
	 */
	public class Debugging {
	
	  void tabelaAscii() {
	    byte b = 0;
	    int i = 0;
	
	    while (i < 255) {
	      System.out.printf("Nº: %d Valor: %c\n", i, b);
	      i++;
	      b++;
	    }
	  }
	
	}

*Classe Principal*

	package com.objectos.depurador;
	  
	/**
	 * @author marcos.piazzolla@objectos.com.br (Marcos Piazzolla)
	 */
	public class TestDebugging {
	  public static void main(String[] args) {
	
	    Debugging d = new Debugging();
	    d.tabelaAscii();
	
	  }
	}

A principio tudo parece estar certo, ok ? 
Mas quando executamos nossa aplicação nos deparamos com a seguinte mensagem:

  Nº: 128 Valor: Exception in thread "main" java.util.IllegalFormatCodePointException: Code point = 0xffffff80
  	at java.util.Formatter$FormatSpecifier.printCharacter(Unknown Source)
  	at java.util.Formatter$FormatSpecifier.print(Unknown Source)
  	at java.util.Formatter.format(Unknown Source)
  	at java.io.PrintStream.format(Unknown Source)
  	at java.io.PrintStream.printf(Unknown Source)
  	at com.objectos.depurador.Debugging.tabelaAscii(Debugging.java:19)
  	at com.objectos.depurador.TestDebugging.main(TestDebugging.java:16)

Temos um Stack Trace na tela, podemos observar a pilha de execução do programa e acompanhar o fluxo de execução para encontrar o erro e acabar com essa exceção de uma
vez por todas, mas quando observamos os pontos do Stack Trace que indicam onde a exceção ocorreu em nosso código, justamente a chamada ao método tabelaAscii() e a 
própria execução do método tabelaAscii(). Percebemos que nada está errado, ou aparenta estar errado. Não podemos simplesmente utilizar uma trativa de exceções para
resolver este problema, pois com isso o fluxo da aplicação sempre iria parar na iteração de número 128, lembrando que a tabela ascii possui 255 caracteres, logo nossa
aplicação ficaria devendo a exibição de 127 caracteres para quem a utilizasse. 
Uma alternativa seria analisar o fluxo de execução da aplicação em busca de respostas quanto ao que realmente está acontecendo na aplicação, para isso podemos utilizar 
o depurador ou debugger do próprio Eclipse para nos ajudar a entender o que está acontecendo em nossa aplicação. Para isso basta estipular um BREAKPOINT para controlar
a execução da aplicação. 
Para criar um BREAKPOINT basta dar um clique duplo na barra lateral onde o Eclipse faz a numeração das linhas ou então pressionar o botão direito na mesma barra e 
marcar a opção "Toggle Breakpoint", basta fazer como na imagem abaixo:

[BREAK](https://github.com/MarcosPiazzolla/Imagens/blob/master/Imagens/break_n.png?raw=true)

Por questões didáticas foi inserido um BREAKPOINT na chamada ao método tabelaAscii() na classe principal. Agora que estipulamos nosso ponto de parada podemos dar inicio
ao processo de depuração pressionando a tecla F11 ou então pelo menu *Run >> Debug as >> Java Aplication*. Uma mensagem informando que o Eclipse irá mudar sua perspectiva
será lançada, isso é normal basta clicar em "yes". Logo a interface do Eclipse irá mudar e dois novos painéis surgirão na parte superior.

O painel a esquerda nos mostra o que está sendo executado e a respectiva linha da instrução, no nosso caso estamos na classe TestDebugging, no método main e na linha 16.

[painel_esqerda] (https://github.com/MarcosPiazzolla/Imagens/blob/master/Imagens/debug_04_n.png?raw=true)

O painel mais a direita nos mostra todos os BREAKPOINTS que foram criados e também o estado da váriaveis do bloco que está sendo depurado.

[painel_direita] (https://github.com/MarcosPiazzolla/Imagens/blob/master/Imagens/debug_03_n.png?raw=true)

Se prestarmos atenção o Eclipse destacou a linha onde criamos o BREAKPOINT, ele faz isso para nos mostrar onde vamos começar a depurar nossa aplicação.

## Step Into, Step Over, Step Return

Pois bem, mudamos a perspectiva do Eclipse para Debug, mas ainda não fizemos nada! Antes mesmo de começar a sair por aí depurando é preciso entender um pouco sobre os 
Steps(Step Into, Step Over, Step Reurn). Assim que iniciamos o modo de depuração pelo Eclipse o mesmo acaba destacando a instrução onde marcamos o BREAKPOINT, nos
mostrando onde o processo de depuração deve começar. A partir deste momento podemos fazer várias coisas entre elas destacamos três que são de suma importância:

*Step Into: * Permite entrar em uma instrução ou método, quando acionamos esta função o fluxo de execução passa para dentro do método ou instrução em questão, se
chamarmos esta função no momento em que iniciamos o processo de depuração, na linha que chama o método tabelaAscii() o fluxo de execução irá para "dentro" do método 
assim possibilitando uma análise mais a fundo do método;

*Step Over: * Esta funcionalidade nos permite passar por uma instrução ou método sem analisá-lo, ou seja enquanto o Step Into mudava o fluxo para o bloco em questão,
o Step Over pula para a próxima linha executando o bloco ou instrução;

*Step Return: * Quando chamamos o Step Return o fluxo da aplicação independente de onde estiver irá voltar para o método chamador, o método que invocou todas as
rotinas que estão sendo analisadas. Ou seja, caso chamarmos Step Return em nossa aplicação o fluxo irá voltar para o método tabelaAscii() justamente no ponto onde ocorre
a exceção.

#Depurando a aplicação

Finalmente! Chegou o momento onde vamos dar início ao processo de depuração e corrigir a falha de nossa aplicação. Ao mudar a perspectiva do Eclipse
para "Debug" e criar nosso Breakpoint, o Eclipse automaticamente para de executar a aplicação e destaca o nosso Breakpoint, com isso podemos dar inicio ao processo 
de depuração, para isso vamos utilizar os atalhos dos "Steps". São eles: 

*Step Into* -> F5
*Step Over* -> F6
*Step Return* -> F7

Agora no método que foi destacado pelo Eclipse basta pressionar F5 para que o fluxo de execução mude para dentro do método tabelaAscii(), já no método tabelaAscii()
podemos alternar entre o fluxo utilizando os Steps, vamos utilizar o Step Over (F6) pois não existem estruturas além do laço while para ser inspecionadas. Mesmo 
pressionando Step Into (F5) o fluxo seguiria para a próxima linha pois não existem blocos, apenas declarações. 
A partir do momento que o fluxo de execução passar para o laço de repetição o depurador passará a analisar o fluxo de execução do laço while, para avançar cada iteração
basta pressionar F6 (Step Over). 
Mas teríamos de pressionar F6 127 vezes para que o nosso erro ocoresse?
Isto seria muito trabalhoso, pura perda de tempo, para nossa salvação o Eclipse possui recursos que nos permite seguir direto ao ponto onde o erro aconteceu 
podemos utilizar o *CTRL + R* que segue direto a iteração onde ocorre o erro ou até mesmo o Step Return(F7) que faz o fluxo voltar ao método chamador.

Pois bem, agora que temos conhecimento de como depurar uma aplicação e como seguir pelo fluxo de execução da mesma através dos Steps, devemos analisar o que de fato
está errado em nossa aplicação para que tal erro ocorra. Analisando novamente o código percebemos que nada está errado, mesmo chamando o depurador inúmeras vezes e
analisando o fluxo nada aparenta estar errado, mas quando olhamos as declarações mais afundo percebemos que algo bem estranho acabou passando dspercebido por nós. Atente
a amostra de código de nossa aplicação mostrando o possível suspeito de nossas dores de cabeça:

  void tabelaAcii() {
      byte b = 0;//mas o que este byte está fazendo aqui????
      int i = 0;
  
      while(i < 255) {
        System.out.printf("Nº: %d Valor: %c\n", i, b);
        i++;
        b++;
      }

Quer dizer que a fonte de nossas dores de cabeça era esta simples declaração de "byte"?
Mas afinal de contas quais problemas existem em utilizar um byte para representar os caracteres da tabela ASCII em nossa aplicação?
Aparentemente nenhum certo? Errado, em java, ou melhor em qualquer linguagem de programção devemos tomar cuidado ao escolher tipos numéricos para nos auxiliar em
estruturas de repetição que possuem um número mais elevado de iterações, já respondendo o porque automático que será gerado devemos entender que tipos númericos TEM limites!!!
Ou seja o limite de byte que é 127 foi ultrapassado, então a coisa mais normal a acontecer é o nosso progrma parar!!!
Uma solução prática e eficiênte seria mudar o tipo númerico da declaração byte para algo mais "apropriado", no caso poderíamos utilizar um "int" que é mais qualificado 
para este tipo de situação.
Faça o teste, pare a aplicação com um CTRL + F2 (caso ela ainda esteja sendo depurada) e troque a perspectiva do Eclipse para Java ou Java EE, altere o tipo da
variável "byte b" da classe Debugging para "int b" e observe a saída.
E como "num passe de mágica" nossa aplicação apresenta o que realmente esperávamos, a tabela Ascii. Falhas como esta são bem comuns e acontecem muitas vezes pelo 
fato do programador não ter conhecimento do limite dos tipos numéricos ou até mesmo por falta de atenção, é por isso que existe o depurador. Com ele é possível
ter um feedback de como as coisas estão realmente funcionando em uma aplicação, facilitando o trabalho do programador na busca por falhas como fora mostrado acima.

# Conclusão

Após o término deste artigo o leitor será capaz de:

-Utilizar a ferramenta de depuração e depurar suas próprias aplicações a procura de bugs;

-Utilizar os Steps para depurar a aplicação e percorrer blocos de código;

-Compreender o fluxo de execuções de um programa utilizando o depurador.

## Referências:

http://pt.wikipedia.org/wiki/Depurador

http://lcadfs2.lcad.icmc.usp.br/~junio/mat/depurador.pdf

http://www.infoblogs.com.br/view.action?contentId=208621&Depuracao-no-Eclipse-Debugger.html

http://www.guj.com.br/java/6033-debug-no-eclipse

http://pt.wikipedia.org/wiki/Depuração

http://msdn.microsoft.com/pt-br/library/cc517980.aspx


*Artigo Escrito por Marcos Piazzolla*
