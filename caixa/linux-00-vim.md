---
layout: post-alpha
title: "Vim"
author: "Marcos Piazzolla"
user: "mpiazzolla"
published: true
partof: linux
num: 0
outof: 0
---

## Um pouco de História 

Antes de falarmos sobre "VIM" vamos falar um pouco de seu antecessor o "VI". "VI" é a sigla para "Visual Interface". A 
origem desse nome se deve ao seguinte fato: quando o "VI" foi criado (começo da década de 80), não era comum existirem 
editores de textos como nos dias de hoje. Naquela época, você digitava um 
texto mas não podia vê-lo! Isso mesmo! Em 1992, foi criado o "VIM" (VI IMitator), um clone fiel ao "VI", 
porém com muitas outras funcionaliades, que só foram sendo adicionadas. Algum tempo depois, o "VIM" passou
a ser chamado de "VI IMproved" (VI melhorado).

## Mas afinal de contas o que é VIM ? 

O "VIM" é um editor de textos muito poderoso, ele pode: abrir vários arquivos ao mesmo tempo, possui 
sistema de autocorreção, auto-identação, seleção visual, macros, seleção vertical de texto, uso de 
expressões regulares, sintaxe colorida, e muito mais. Ele não é exclusivo do Unix, ou seja, pode ser
executado em outras plataformas, como Amiga, Mac OS, Sun, Windows entre outras.

## Por que aprender a utilizar o VIM ?

Muitas pessoas que tiveram experiências ruins ou que nunca tiveram contato com o Vim fazem esta pergunta com muita
frequência e na maioria dos casos acabam não obtendo uma resposta que satisfaça sua pergunta. Mas afinal de contas, por
que aprender a utilizar este editor bem complicado, sendo que existem editores mais "agradáveis" como o próprio Gedit?
Simples, existem situações em que não é possível contar com a ajuda de um ambiente gráfico ou até mesmo com o auxílio
de uma IDE, como por exemplo ao fazer manutenção em um arquivo de configuração do sistema operacional quando o mesmo não
carrega, logo o acesso ao sistema é através da linha de comando e com isso não se pode contar com ajuda de aplicativos 
gráficos como o próprio Gedit, a única opção seria utilizar o Vim. Um outro caso muito interessante seria a manutenção 
em arquivos fonte de uma aplicação quando não se conta com nenhum tipo de IDE no sistema e também pelo fato do acesso 
aos mesmos arquivos ser restrito em ambiente gráfico, sobrando apenas o acesso pela linha de comando.
Saber utilizar o Vim em situações como essa seria de grande ajuda, por isso é interessante conhecer um pouco sobre esta
ferramenta.

## Utilizando o editor para desenvolvimento de Software

Para os desenvolvedores o VIM só falta programar por conta pŕopria, já que o mesmo oferece suporte a
diversas linguagens de programação que vão desde as mais famosas como Java, Python, C/C++, Perl, Clipper e
SQL até linguagens mais antigas como Simula e Z8A. O Vim identifica a linguagem, colorindo o código,
tornando imediatamente visível qualquer erro cometido pelo programador.

## Utilizando o VIM 

Mãos á obra! Vamos começar a utilizar o VIM e entender suas funcionalidades com um simples exemplo.
Antes de tudo vamos abrir o terminal:

**Aplicativos >> Acessórios >> Terminal**

Assim que o terminal abrir vamos baixar um pequeno arquivo que será utilizado em nosso aprendizado, é só digitar:

    $ wget http://www.objectos.com.br

Com isso nós obtemos o arquivo fonte index.html, que será utilizado nos próximos exemplos, para uma visualização do 
arquivo basta digitar 

    $ vi index.html

Para mover o cursor, pressione as teclas h, j, k, l conforme indicado. 

             ^
             k             Dicas:   A tecla h está à esquerda e move para  a esquerda.
       < h       l >                A tecla l está à direita e move para a direita.
             j                      A tecla j se parece com uma seta para baixo.
             v

Mova o cursor pela tela até que você se sinta confortável.
          
## Modos do VIM 

Antes mesmo de começar a fazer n-coisas com o VIM, vamos aprender um pouco sobre os modos de operação do editor. Mas 
afinal de contas o que são estes modos de operação? São modos onde o VIM opera de diferentes formas, famos falar um
pouco deles.

### Modo Normal 

Neste modo podemos colar o que está no "buffer" (Uma espécie de área de transferência). Podemos ter um buffer para 
cada letra do alfabeto, também é possível apagar linhas, e colocar trechos no buffer. Quando se inicia o Vim já estamos
neste modo; caso esteja em outro modo basta pressionar ESC.

Para acessar:

    Esc

### Modo Insert ou Inserção 

Neste modo é feita a inserção de texto, para entrar neste modo basta pressionar a tecla i de insert ou a tecla a de
append.

Para acessar:

    i ou a

### Modo Visual

Neste modo podemos selecionar blocos verticais de texto. É exibido um destaque visual. É uma das melhores formas de se 
copiar conteúdo no Vim.           

Para acessar (a partir do modo normal):

    v - seleção de caracteres
    V (maiúsculo) - seleção de linhas inteiras

Agora que sabemos dos modos do VIM podemos começar a praticar, voltando ao VIM, lá no arquivo index.html, vamos começar
a praticar, para isso vamos utilizar exemplos bem dinâmicos que representem uma situação do dia-dia de quem desenvolve 
em alguma linguagem de programação no VIM ou utiliza o mesmo para outros fins. Vamos logo ao exemplo:

Ao obsevar o título da página html você logo percebeu que não era bem isso que deveria estar marcado como título de sua
página e logo de cara pretende mudar o mesmo para que atenda as suas necessidades, sem problemas! Com o VIM podemos 
alterar o título rapidamente a partir do comando:

    Shift + d

Basta mover o cursor até o ponto desejado e pressionar **Shift + d**, com isso toda a parte na frente do cursor será
apagada, nos permitindo inserir o novo título da página, uma outra opção seria utilizar o comando **dd** que apaga toda
a linha, mas este não seria muito vantajoso para nós neste exemplo por isso adotaremos **Shift + d**. Atente ao exemplo:

    <html>
      <head> 
         <title> | Objectos Fabrica de Softare </title>
      </head>
    </html>

Quando movimentamos o cursor e pressionamos **Shift + d**, toda a linha após o cursor será apagada, logo podemos fazer
a alteração desejada.

    <html>
      <head> 
         <title> Objectos - Pagina Principal </title>
      </head>
    </html>

Seguimos para o próximo exemplo. No mesmo arquivo html desejamos adicionar mais um arquivo *css* para adicionar novos
tipos de efeito a nossa página, claro não vamos ficar copiando todo aquele código html para informar a inclusão de
mais um arquivo, para evitar todo o processo de copiar na mão o código html, por que não utilizamos o VIM? 

Basta utilizar a funcionalidade **Shift + v**

Com isso podemos selecionar uma linha desejada ou até mesmo um bloco de código caso desejado, mas por hora nosso objetivo
é selecionar apenas uma linha e colá-la abaixo para que possamos modificá-la de acordo com as nossas necessidades.
Atente ao exemplo:

     <html>
     <head>
       <title> Objectos - Fabrica de Software </title>
     | <link rel="stylesheet" media="screen" href="/style/750_3_12.css">
       <link rel="stylesheet" media="screen" href="/style/750_5_5.css">
       <link rel="stylesheet" media="screen" href="/style/sheet.css">
          <body>....</body>
    </head>
    </html>

Basta movermos o cursor para a linha desejada e então é só pressionar **Shift + v** e pronto toda a linha será
selecionada! Mas e agora como copiamos o conteudo selecionado?? É bem simples basta utilzar a opção: **yy** 

Enquanto o texto desejado estiver selecionado basta pressionar **yy** que o VIM copia o texto, em seguida é so colar o 
texto no local desejado utilizando a opção: **p** 

"p" vem de Paste (colar), para colar basta movermos o cursor do VIM para o local desejado e pressionar **p**, assim 
teremos uma cópia da linha anterior, preste atenção:

    <html>
    <head>
      <title> Objectos - Fabrica de Software </title>
    | <link rel="stylesheet" media="screen" href="/style/750_3_12.css">
    | <link rel="stylesheet" media="screen" href="/style/750_3_12.css"> <!-cópia da linha->
      <link rel="stylesheet" media="screen" href="/style/750_5_5.css">
      <link rel="stylesheet" media="screen" href="/style/sheet.css">
         <body>....</body>
    </head>
    </html>

Mas espere nosso exemplo ainda não acabou, lembra que desejavamos adicionar um link de um novo arquivo css em nossa
página html, pois bem agora chegou a hora de mofificar a linha que acabamos de colar para isso podemos navegar pelo
código utilizando a função: **w** que vem de Word (palavra), ela move o cursor do editor palavra por palavra, ou seja 
é só pressionar **w** até o cursor do VIM chegar no local desejado, quando chegarmos lá é só apagar e modificar o código
que foi copiado.

    <html>
    <head>
      <title> Objectos - Fabrica de Software </title>
      <link rel="stylesheet" media="screen" href="/style/750_3_12.css">
    | <link rel="stylesheet" media="screen" href="/style/novoEstilo.css"> <!-cópia da linha->
      <link rel="stylesheet" media="screen" href="/style/750_5_5.css">
      <link rel="stylesheet" media="screen" href="/style/sheet.css">
         <body>....</body>
    </head>
    </html>

Já que conseguimos fazer todas as alterações necessárias que tinhamos em mente devemos salvar todo o conteúdo do arquivo
para que as alterações sejam efeutuadas, mas como salvamos um documento pelo VIM?
É muito simples, basta mudar o VIM para modo NORMAL pressionando ESC, em seguida basta digitar:

    : w

Com isso VIM nos dará um feedback indicando que as alterações no documento foram salvas, atente a saída do VIM abaixo:

    "index.html" 375L, 20898C gravado(s)   

Finalmente conseguimos alterar o nosso arquivo html, mas precisamos sair do VIM já que não há mais necessidades de 
utiliza-lo. Para sair do editor basta trocar o VIM para modo NORMAL (pressionando **ESC** ) e digitar: **: q** , com 
isso saímos do editor, lembrando que podemos utilizar a opção : **: wq** que além de sair do editor salva todas as 
alterações antes de fechar o VIM.


## Trocando elementos 

Para esta sessão vamos utilizar o arquivo index.html que fora utilizado anteriormente nos exemplos acima, vamos pensar
no seguinte caso:
Se observarmos no final da página temos o endereço de email, e por questões particulares o email mudou, logo precisamos 
editar o email no arquivo html, se tentarmos editar o arquivo por qualquer outro editor perderíamos muito tempo 
procurando o endereço de email para alterá-lo, agora pense quanto tempo perderíamos se existissem mais vinte arquivos 
como este onde teríamos de procurar o email e alterá-lo, bastante tempo não? 
O VIM possui uma funcionalidade muito interessante para busca e alteração de elementos, onde substituimos um elemento
por outro, com isso poderíamos trocar o email sem ao menos procurá-lo no editor!
Trocando o VIM para modo normal e utilizando o comando:

    : %s/emailAntigo/emailNovo/g

Com isso o VIM trocará todos os emails pelo novo endereço de email, imagine quanto tempo perderiamos se procurassemos o 
email palavra por palavra! Faça o teste tente mudar o email utilizando o VIM, lembrando que o email a ser trocado DEVE
existir no arquivo fonte caso contrário nada irá ocorrer! Atente ao exemplo abaixo:

    : %s/contato@objectos.com.br/novoEmail@objectos.com/g

## As Funcionalidades "SET" do VIM 

O VIM possui uma série de funcionalidades para cada um de seus modos, vamos falar agora um pouco das funções "set" que
pertencem ao modo comando do VIM, lembrando que para que o VIM esteja em modo de COMANDO basta pressionar a tecla
**ESC**.

### Set Paste

Opção muito útil para quem programa em alguma linguagem ou modifica constantemente arquivos de configuração xml ou até
mesmo arquivos fonte html, a idéia do comando **:set paste** é ajudar o programador ou desiner com a organização de seu 
código, vamos tomar como base um designer web, por exemplo. É muito comum o mesmo utilizar com muita frequência os
comandos copiar e colar para adicionar novas funcionalidades gráficas a um site. Vamos pensar no seguinte caso: 
Precisamos adicionar alguns componentes gráficos em algumas páginas html utilizando o recurso de copiar e colar do VIM 
mas toda vez que selecionamos o código do componente e colamos o mesmo no arquivo fonte o código aparece com uma
identação ruim, daí temos que identar todo o código na mão, uma tremenda perda de tempo!
Mas o VIM nos fornece uma solução muito eficiente que é a opção **:set paste** . Quando acionamos esta opção todo e
qualquer trecho de código que for colado no VIM será auto-identado, incrivel não?
Vamos fazer o teste utilizando um pequeno trecho de código html já fornecido, e colar o mesmo em um documento vazio, 
ativando antes a opção **:set paste** .

Antes de tudo copie o seguinte trecho de código:

    <html>
     <head>
       <meta charset='utf-8'>
       <meta http-equiv="X-UA-Compatible" content="chrome=1">
       <script type="text/javascript">var NREUMQ=[];NREUMQ.push(["mark","firstbyte",new Date().getTime()]);</script>
          <title>VIM - GitHub</title>
       <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
       <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />

Vamos criar um documento html vazio pelo Terminal

    $ vi Exemplo.html

No VIM, vamos colar primeiro no documento sem ativar a opção **:set paste** , basta clicar com botão direito e 
selecionar colar, o resultado seria o mesmo se fizessemos este procedimento de outra forma, preste atenção no resultado:

    <meta charset='utf-8'>
                                 <meta http-equiv="X-UA-Compatible" content="chrome=1">
                                     <script type="text/javascript">var NREUMQ=[];NREUMQ.push(["mark","firstbyte",new
				     Date().getTime()]);</script>
                                             <title>VIM - GitHub</title>
                                                 <link rel="search" type="application/opensearchdescription+xml" href="/
						 opensearch.xml" title="GitHub" />

                                                     <link rel="fluid-icon" href="https://github.com/fluidicon.png"
						     title="GitHub" />

Agora vamos utilzar a opção **:set paste** e analisar o que acontece. Para acionar a função basta mudar o VIM para o
modo NORMAL pressionado **ESC** e em seguida mudar para o modo COMANDO digitando:

    :set paste

Agora vamos colar o mesmo trecho e ver o que acontece:

    <meta charset='utf-8'>
        <meta http-equiv="X-UA-Compatible" content="chrome=1">
        <script type="text/javascript">var NREUMQ=[];NREUMQ.push(["mark","firstbyte",new Date().getTime()]);</script>
           <title>VIM - GitHub</title>
        <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />

        <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />

Incrivel, não? Faça o teste e compare os resultados você mesmo.
 
### Set Autoindent

Como o próprio nome diz esta função auto identa o seu código a partir da linha anterior, por exemplo: 
Ao ajustarmos esta função, toda vez que iniciarmos uma quebra de linha o VIM irá identar(organizar) a próxima linha
baseando-se na linha anterior, vamos ao nosso exemplo.
Vamos entender esta funcionalidade utilizando parte do arquivo index.html, o mesmo arquivo que foi utilizado acima em
nossos exemplos.

    <ul>
           <li><a class="logo" href="#">Topo</a></li>
         <li><a class="visao" href="#visao">Vis&atilde;o</a></li>
           <li><a class="servicos" href="#servicos">Servi&ccedil;os</a></li>  
              <li><a class="como" href="#como">Como?</a></li>  <!-autoindent foi ajustado a partir daqui-> 
              <li><a class="quem" href="#quem">Quem?</a></li>   
              <li><a class="blog" href="#blog">Blog</a></li>
              <li><a class="contato" href="#contato">Contato</a></li>
    </ul>

Como pode ver o arquivo acima está mal identado, quando utilizamos a opção **:set autoindent** o editor irá identar cada
linha se baseando na linha superior (a partir da chamda **set autoindent** ), então podemos identar nosso código se
baseando na linha superior, veja o exemplo acima.

### Set Smartindent

As coisas começam a ficar interessantes neste ponto. A função **smartindent** faz uma identação mais "inteligente", ela
não precisa se basear em uma linha para criar toda a identação como fora mostrado acima. O **:set smartindent** já faz
toda a identação, poupando a pessoa que está editando um documento ou até mesmo um arquivo fonte, para acionar a função
basta que o editor esteja no modo COMANDO **(pressionando a tecla ESC)** , em seguida basta digitar:

    :set smartindent

Agora seguimos ao nosso exemplo:

    <!- a chamda a função smartindent é feita logo no inicio do arquivo->
    <ul>
          <li><a class="logo" href="#">Topo</a></li> <!-a identação já está pronta->
          <li><a class="visao" href="#visao">Vis&atilde;o</a></li>
          <li><a class="servicos" href="#servicos">Servi&ccedil;os</a></li>  
          <li><a class="como" href="#como">Como?</a></li>  
          <li><a class="quem" href="#quem">Quem?</a></li>   
          <li><a class="blog" href="#blog">Blog</a></li>
          <li><a class="contato" href="#contato">Contato</a></li>
    </ul>

Observando o trecho acima percebemos que o VIM não irá se basear em nenhuma linha para identar o código, ou seja toda a 
identação do arquivo em questão será feita no início do arquivo.

### Set Softtabstop

Quando estamos editando um arquivo fonte é muito comum utilizarmos a tecla **TAB** para identarmos nosso código, ou seja
toda vez que pressionamos **TAB** o cursor segue alguns espaços á frente, com isso identamos nosso arquivo fonte. Com o
VIM podemos utilizar a função **:set softtablestop = numeroDeEspaçosAAvançar** , com isso toda vez que pressionarmos a 
tecla **TAB** o VIM irá avançar o cursor de acordo com o número de caracteres especificados no comando **set
softtablestop**, atente ao nosso exemplo prático:

    <a href="http://www.fgcreek.com/">Fog Creek Software</a>,
                  <a href="http://www.google.com"> <!-linha com onde a funcionalidade de TAB foi alterada a partir 
		  desta linha->                               
                  <a href="https://www.hipchat.com/">HipChat</a>

Observe bem a linha com o comentário, a partir desta linha a função **set softtablestop=20** foi acionada, logo toda
vez que pressionarmos **TAB** o cursor irá avançar vinte espaços. Uma boa escolha para nos ajudar a identar nosso 
código.

### Set Shiftwidth

Uma outra forma de identarmos nossos arquivos é através da opção **shiftwidth=AlgumValor** que só pode ser utilizada 
acionando a função **:set shiftround** com isso a função estará habilitada. Mas afinal de contas o que faz esta função
**shiftwidth** ??? 
Após ativarmos **shiftround** habilitamos o acesso a função **Shift + >>** que avança o cursor o número de posições de 
acordo com o valor ajustado na função **shiftround=AlgumValor** assim toda vez que **Shift + >>** for pressionado o
VIM irá avançar o cursor e tudo o que estiver a sua frente. Não devemos esquecer que a chamada **Shift + >>** só pode 
ser efetuada enquanto o VIM estiver no modo "NORMAL" (basta pressionar **ESC**) em seguida é só mover o cursor e
aproveitar mais um dos beneficios do VIM. Vamos ao próximo exemplo para uma melhor compreensão de tudo o que foi comentado.

Quando ajustamos **:set shiftwidth=2** pelo VIM, veja o que acontece:

    <html>
       <body>
        Antes do ShiftWidth
           Movendo dois espaços com ShiftWidth=2
       </body>
    </html> 

Realmente o cursor avança duas casas como especificado na função a acima, faça o teste tente utilizar valores ferentes
e veja o que acontece!

### Set Expandtab

A função **expandtab** é responsável em transformar todas as tabulações de um documento do VIM em espaços comuns, ao 
utilizarmos esta funcionalidade em um arquivo convencional ou até mesmo em um arquivo fonte ou de configuração nada
ocorrerá, exceto que as tabulações serão transformadas em espaços convencionais.
Que vantagens existem em efetuar tal alteração?
Aparentemente nenhuma, certo? 
Errado, se o mesmo arquivo for aberto em outro editor as tabulações podem ser distorcidas e o texto pode ficar
totalmente desorganizado.Logo com a função **expandtab** pode-se transformar as tabulações em espaços comuns e evitar 
futuros problemas por conta das tabulações.
Para utilizar esta funcionalidade basta que o VIM esteja em modo COMANDO, em seguida é só digitar:

    :set expandtab

Com isso todas as tabulações serão transformadas em espaços comuns, seria como se a barra de espaço(BACKSPACE), fosse 
utilizada para criar as tabulações, com isso o problema das tabulações em outros editores foi resolvido.

### Set Tabstop

Esta função do VIM é responsavél em controlar o número de colunas que a tecla **TAB** utiliza quando é acionada, por
exemplo quando acionamos **TAB** em um arquivo pelo VIM o cursor do editor avança um determinado número de espaços,
podemos controlar o valor de casas que serão movidas através da função: **:set tabstop=NúmeroDeCasasAAvançar**, 
fornecemos um valor para a função e assim todas as vezes que a tecla **TAB** for acionada o cursor irá se mover de
acordo com o número de casas que foi estipulado na função. Vamos a um exemplo simples:

    <ul class="nav">
        |<li><a class="contato" href="#contato">Contato</a></li> <!-pressionando TAB antes da função tabstop->
    </ul>

Agora atente para o que acontece quando a função **tabstop** é chamada, para efetuar esta chamada basta que o VIM estej
a no modo COMANDO, em seguida é só digitar:

    :set tabstop=10 

    <ul class="nav">
         |<li><a class="contato" href="#contato">Contato</a></li> <!-pressionando TAB depois da função tabstop->
    </ul>

Como podemos perceber, o cursor avança um total de dez posições quando a tecla **TAB** é pressionada, tente praticar 
utilizando valores diferentes para a função **tabstop** .

## O arquivo vimrc

Afinal de contas o que é esse tal de vimrc? O vimrc é um arquivo de configuração do VIM que permite a inserção de 
funções customizadas do próprio VIM ou seja toda vez que o VIM for encerrado todas as macros que foram especificadas no
próprio editor também serão encerradas, ou seja se o texto foi ajustado para se auto identar, logo quando o VIM for
encerrado a mesma função não funcionara em uma nova sessão do VIM, por isso existe o arquivo vimrc que armazena uma
série de funcionalidades ao VIM todas as vezes que ele for inicializado, com isso o editor se comportará de acordo com
as necessidades de quem o utiliza.  

## Algumas dicas para se aprender mais sobre o VIM

Para quem está começando a ver o VIM uma boa sugestão de aprender mais sobre o editor é utilizando o **vimtutor**, um 
tutorial sobre o VIM que se encontra no próprio VIM, para acessá-lo basta digitar no terminal:

    $ vimtutor

Em seguida um documento contendo várias lições e dicas referente ao VIM será aberto. No próprio site do 
[VIM](http://www.vim.org/) é possível encontrar a documentação do editor e algumas referências de livros sobre o VIM. Na
internet existem [wikis](http://vim.wikia.com/wiki/Vim_Tips_Wiki) e [sites](http://aurelio.net/vim/) bem interessantes 
que possuem várias dicas e lições para se aprofundar no VIM, fica a dica!

## Referências

* [Wikipedia](http://pt.wikipedia.org/wiki/Vim)

* [Gerando Ribeiro](http://geraldo-ribeiro.sites.uol.com.br/vim.htm)

* [Infowester](http://www.infowester.com/linuxvi.php)

* [Aurélio](http://aurelio.net/vim/vim-medio.txt)

* [Under-linux](http://under-linux.org/wiki/Tutoriais/Aplicativos/VIM-1)

* [Wikibooks](http://pt.wikibooks.org/wiki/Vim/Modos_de_opera%C3%A7%C3%A3o)

* [Itlife](http://www.itlife.com.br/2009/10/02/porque-usar-vim/)

* [Bemylifeeasy](http://sites.google.com/site/bemylifeeasy/Home/vimrc)

* [Henrique](http://henriquegogo.wordpress.com/2009/08/31/disseram-me-ide-entao-eu-vim/)

* [Dicas-l](http://www.dicas-l.com.br/arquivo/vim_expandtab.php)

*Artigo escrito por Marcos Piazzolla*
