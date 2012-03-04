---
layout: post-alpha
title: "javac :: jar :: java"
author: "Hellen Carla Paixão Escarate"
published: true
partof: java
num: 0
---

## Introdução

Entender os comandos javac, jar e java pode parecer algo muito trivial, mas faz toda a diferença quando os erros
aparecem, principalmente aqueles erros na qual a mensagem de erro fala que não foi possível localizar uma classe, a qual
você sabe que existe.  

### Praticando 

É importante deixar claro que nesse tutorial o Eclipse será usado apenas como um editor de texto, pois 
todos os procedimentos serão executados através do terminal. 

No tutorial sobre o git foi necessário criar um diretório com o seu login (dentro do projeto objectos-dojo), portanto
todos os procedimentos aqui executados devem ser feitos nesse diretório (objectos-dojo-seulogin).

Antes de começar, crie uma branch e faça todo esse procedimento na mesma (em caso de dúvidas consulte o tutorial sobre
branches). 

No eclipse, dentro do diretório que foi citado acima, crie um novo arquivo que pode ser chamado de *Aplicacao.java*, 
esse arquivo será uma classe: 

    public class Aplicacao{
       public static void main(String[] args){
       System.out.println("Katas são essenciais");   
       }
    }

Agora no terminal, compile a classe que foi criada: 

    $ javac Aplicacao.java 

E então rode usando o comando java:  

    $ java Aplicacao
    Katas são essenciais

Ok, o próximo passo é criar um pacote.

No terminal, dentro do diretório que está sendo usado para executar esses exercícios (objectos-dojo/objectos-dojo) deve
ser criado o diretório dojo: 

    $ mkdir dojo

Depois é necessário dizer para a classe o pacote em que ela está contida: 

    package dojo; 

    public class Aplicacao{
       public static void main(String[] args){
       System.out.println("Katas são essenciais");   
       }
    }

Como foi feita essa alteração, é necessário compilar novamente a classe Aplicacao: 

    $ javac Aplicacao.java 

E executar:

    $ java Aplicacao

Depois de executado o comando acima, certamente uma mensagem de erro vai aparecer: 

    $ java Aplicacao
    Exception in thread "main" java.lang.NoClassDefFoundError: Aplicacao (wrong name: dojo/Aplicacao)
        at java.lang.ClassLoader.defineClass1(Native Method)
   	at java.lang.ClassLoader.defineClassCond(ClassLoader.java:631)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:615)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:141)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:283)
	at java.net.URLClassLoader.access$000(URLClassLoader.java:58)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:197)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
    could not find the main class: aplicacao.  program will exit.

Porque essa mensagem de erro aparece? O que fazer quando isso acontece? 

Pode parecer óbvia a frase que segue, mas a primeira coisa a ser feita é ler o que diz a mensagem de erro, antes de
entrar em "pânico". Em seguida entender o que pode ter acontecido. Nesse caso, o que fiz a mensagem de erro?  

    could not find the main class: aplicacao.  program will exit.

O que foi feito antes de ser executado o último comando?

Foi criado um novo diretório, e em seguida, adicionado na classe a informação de que ela pertence ao pacote dojo, porém,
a classe não foi movida para o diretório. Uma coisa importante que deve estar claro aqui é o conceito de pacote. Pacote
é um conceito físico, por isso quando foi executado o comando javac a classe foi compilada com sucesso. Porém, quando
foi executado o comando java (a máquina virtual foi chamada, e vai tentar localizar essa classe, mas não foi obtido 
sucesso. Afinal, o que aconteceu? A máquina virtual não consegiu localizar a classe no diretório físico que representa
o pacote, por isso da mensagem: "Could not find the main class: Aplicacao."

Na maioria das vezes quando ocorre esse erro de "Exception", é porque a classe não foi encontrada, não existe, ou ainda,
não foi informado corretamente para a máquina virtual onde ela está localizada.  

Então, para que o erro seja corrigido , é necessário mover a classe para dentro do pacote:

    $ mv Aplicacao.* dojo

Agora, rode novamente:

    $ java Aplicacao

É provável que depois de executar o comando acima, apareça novamente um erro:  

    Exception in thread "main" java.lang.NoClassDefFoundError: Aplicacao
    Caused by: java.lang.ClassNotFoundException: Aplicacao
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
    Could not find the main class: Aplicacao.  Program will exit.

Mas porque esse erro aparece novamente, se a classe foi movida para o diretório correto?

Um detalhe importante que precisa ficar claro nesse tutorial, é que as classes tem nomes completos, nesse caso, é
necessário se referir a classe com o nome do pacote no qual ela está contida:

    $ java dojo.Aplicacao
    Katas são essenciais

Agora não ocorreu nenhum erro. 

O próximo passo é gerar o .jar

    $ jar cvf Aplicacao.jar

Com certeza, depois que o comando acima foi executado aparece a mensagem abaixo: 

    $ jar cvf Aplicacao.jar
    sinalizador 'c' requer que os arquivos de manifesto ou entrada sejam especificados!
    Uso: arquivos jar {ctxui}[vfm0Me] [jar-file] [manifest-file] [entry-point] [-C dir] ...
    Opções:
    -c  cria novo arquivo de armazenamento
    -t  lista o sumário do arquivo de armazenamento
    -x  extrai arquivos nomeados (ou todos) do arquivo de armazenamento
    -u  atualizar o arquivo de armazenamento existente
    -v  gera saída detalhada na saída padrão
    -f  especifica o nome do arquivo do arquivo de armazenamento
    -m  inclui as informações do manifesto do arquivo de manifesto especificado
    -e  especifica o ponto de entrada do aplicativo para aplicativo independente 
        empacotando em um arquivo jar executável
    -0  armazena somente; não usa compactação ZIP
    -M  não cria um arquivo de manifesto para as entradas
    -i  gera informações de índice para os arquivos especificados
    -C  altera para o diretório e inclui o arquivo seguinte
    Se nenhum arquivo for um diretório, então é processado repetidamente.
    O nome do arquivo de manifesto, o nome do arquivo de armazenamento e o nome do ponto de entrada são
    especificados na mesma ordem dos sinalizadores  'm', 'f' e 'e'.

    Exemplo 1: para arquivar dois arquivos de classe em um arquivo de armazenamento denominado classes.jar: 
    jar cvf classes.jar Foo.class Bar.class 
    Exemplo 2: use um arquivo de manifesto existente 'mymanifest' e arquive todos os
    arquivos no diretório foo/ na 'classes.jar': 
    jar cvfm classes.jar mymanifest -C foo/ .

<div class="alert-message block-message info">
<p> 
Antes de procurar no Google uma solução, o mais indicado é consultar o manual do comando, no próprio terminal, usando
nesse caso o comando 'man java'.
</p>
</div>

Mas afinal, o que significa essa mensagem? 

Para melhor entendimento, imagine a seguinte situação:

Foi criado uma aplicacao, e enviada por e-mail. Portando, o que foi enviado foi um arquivo.jar. A pessoa que recebeu o 
arquivo, abriu o mesmo, e a maquina virtual vai executar esses arquivos. Mas como a maquina virtual pode saber qual a
classe de inicialização? Através de um passe de mágica?  

    could not find the main class: aplicacao.  program will exit.

Claro que não, é necessário que seja informado para a máquina virtual por qual classe iniciar. Por isso faz sentido a
mensagem de erro acima: "sinalizador 'c' requer que os arquivos de manifesto ou entrada sejam especificados!"

E como especificar esses arquivos? 

É necessário então criar um arquivo que vai guardar essa informação: 

    $ vi MANIFAST.MF

Dentro do arquivo vamos especificar qual será a classe de inicialização: 

    Main-Class: dojo.Aplicacao

E agora então pode ser executado o comando: 

    $ jar cvfm Aplicacao.jar MANIFAST.MF .
    added manifest
    adding: dojo/(in = 0) (out= 0)(stored 0%)
    adding: dojo/Aplicacao.java(in = 137) (out= 121)(deflated 11%)
    adding: dojo/Aplicacao.class(in = 438) (out= 304)(deflated 30%)
    adding: .gitignore(in = 0) (out= 0)(stored 0%)
    adding: MANIFAST.MF(in = 27) (out= 29)(deflated -7%)

Observe que depois de executado o comando acima não aparece nenhuma mensagem de erro.

Agora tente rodar o .jar:

    $ java -jar Aplicacao.jar 
    Katas são essenciais 

O próximo passo é excluir o arquivo Aplicacao.jar, MANIFAST.MF e o pacote dojo, e criar dois novos diretórios: app e
lib. 

Dentro de app crie o diretório dojo. 

No eclipse, dentro de app crie novamente um arquivo Aplicacao.java, que será um classe. 


    package dojo;
    import componente.Soma; 

    public class Aplicacao {
       public static void main(String [] args(){
       new.Soma () .get (); 
       }
    }

Crie também um outro arquivo, Soma.java, que também será uma classe: 

    package componente; 

    public class Soma{
       public void get (){
       System.out.println ("Katas são essenciais"); 
       }
    }

Com as classes criadas, é necessário compilar as mesmas: 

    $ javac app/dojo/Aplicacao.java 

Depois de executado o comando acima, aparece uma mensagem de erro:  

    app/dojo/Aplicacao.java:2: package componente does not exist
    import componente.Soma;
    ^
    app/dojo/Aplicacao.java:6: cannot find symbol
    symbol  : class Soma
    location: class dojo.Aplicacao
    new Soma ().get (); 
    ^
    2 errors

Mas o que houve de errado?

Na mensagem de erro diz que o pacote componente (que foi importado na classe Aplicacao) não existe. Mas na verdade o
pacote existe (liste o que há nos diretórios e confirme). E então, o porque dessa mensagem?

Antes disso é importante pensar no que significa compilar. Quando executamos o comando javac estamos chamando o 
compilador. E afinal qual é a função dele?

O compilador faz uma verificação do código, e nesse caso (do erro acima), como o compilador pode saber se realmente
existe a classe componente.Soma?

Muitas vezes ao usar o comando javac (ou java) será necessário procurar por outras classes que são necessárias para 
completar a operação, pois o compilador precisa saber se essa outra classe que foi declarada (usando o import) realmente
existe, e então, um dos lugares que o compilador vai procurar é no classpath. E o que seria isso? Classpath significa 
"caminho de busca de classes", nesse caso podemos declarar o classpath como uma opção de linha de comando. 

Mas precisa ficar claro uma questão: nessa mensagem de erro acima, qual arquivo o compilador não está encontrando? Ou 
que arquivo o compilador precisa carregar? O componente.Soma. 

E o que precisa ser declarado no Classpath?

No Classpath deve ser declarado à partir de que diretório será encontrado o arquivo (componente.Soma) que o compilador
está buscando. O que seria um diretório anterior a ele, nesse caso é o diretório lib. 

É necessário entrar no diretório app: 

    $ cd app/

E então executar o comando abaixo: 

    $ javac -cp ../lib/ dojo/Aplicacao.java 

No comando acima foi feito o uso dos dois pontos (..) para dizer que é necessário voltar um diretório, e então ir para 
o diretório /lib/.

O próximo passo é executar: 

    $ java -cp .:../lib/ dojo.Aplicacao 
    Katas são essenciais

Depois de compilar e executar as classes, é necessário gerar o .jar.

Para que o conceito de classpath fique claro, é interessante que seja gerado o .jar das classes separadamente. 

Então primeiro pode ser gerado o .jar da classe componente.Soma, 

    cd lib

    $ jar cvf Soma.jar .

O "." serve para indicar que o arquivo .jar será gerado com todos os arquivos que estão dentro do diretório atual (lib). 

O próximo passo será gerar o .jar da classe dojo.Aplicacao, visto que já foi criado o arquivo MANIFEST.MF que contém
a seguinte instrução: 

    Main-Class: dojo.Aplicacao
 
Portanto será gerado o arquivo .jar à partir do diretório app: 

    app $ jar cvfm Aplicacao.jar MANIFEST.MF .

Em seguida aparecerá a seguinte mensagem de que o manifesto foi adicionado: 

    manifesto adicionado
    adicionando: dojo/(dento = 0) (fora= 0)(armazenado 0%)
    adicionando: dojo/Aplicacao.java(dento = 145) (fora= 115)(vazio 20%)
    adicionando: dojo/Aplicacao.class(dento = 324) (fora= 245)(vazio 24%)
    adicionando: MANIFEST.MF(dento = 27) (fora= 29)(vazio -7%)

Se for verificado os arquivos contidos nesse diretório, certamente aparecerá o Aplicacao.jar 

    app $ ls -l 
    total 8
    -rw-r--r-- 1 hescarate users 1224 Set  9 13:35 Aplicacao.jar
    -rw-r--r-- 1 hescarate users   27 Set  9 10:38 MANIFEST.MF
    drwxr-xr-x 2 hescarate users  112 Set  9 11:45 dojo

Agora execute os arquivos .jar, usando o comando "java -cp", e aplicando o que foi aprendido nesse tutorial.  

## Conclusão 

É fundamental que todos os conceitos vistos nesse tutorial estejam claros e bem entendidos, afinal, qual o propósito de
aprender a compilar, executar e criar arquivos .jar através do terminal, se através de uma IDE (Integrated Development 
Enviroment) como o Eclipse tudo isso pode ser gerado automaticamente?

E se em algum caso o Eclipse não estiver disponível? Se por exemplo você estiver configurando um servidor de
aplicacação?

Acima de tudo isso, o mais importante é que tenha sido entendido os conceitos básicos de cada comando visto aqui. Quando
todas as coisas estão funcionando, tudo parece claro. Mas por exemplo, quando aparecer algum erro como "Exception in
thread "main" java.lang.NoClassDefFoundError:" e "Could not find the main class:" fique entendido o porque desse erro, e
fique claro de ser resolvido, pois esse é o diferencial de um verdadeiro programador.  

## Referências 

* man java

* man jar

* [Criando arquivos jar executáveis - acessado em 09/09/2011 às 11h19.](http://csdl.ics.hawaii.edu/~johnson/613f99/module
s/04/jar-files.html)

* SIERRA. Kathy, BLATES, Bert. CERTIFICAÇÃO SUN PARA PROGRAMADOR JAVA 6 - GUIA DE ESTUDO (EXAME 310-065). Alta Books,
2006. 

Artigo escrito por Hellen Escarate


