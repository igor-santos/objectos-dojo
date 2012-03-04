Depois de você ter praticado o TDD, nosso próximo passo é aprender sobre integração contínua.

“Integração Contínua é uma pratica de desenvolvimento de software onde os membros de um time integram seu trabalho frequentemente, geralmente cada pessoa integra pelo menos diariamente – podendo haver multiplas integrações por dia. Cada integração é verificada por um build automatizado (incluindo testes) para detectar erros de integração o mais rápido possível. Muitos times acham que essa abordagem leva a uma significante redução nos problemas de integração e permite que um time desenvolva software coeso mais rapidamente.” Martin Fowler


**Jenkins**

O Jenkins é um servidor de integração contínua, ele é responsável por fazer automaticamente todos os testes necessários para o funcionamento do sistema nas alterações enviadas por você, por isso é tão importante colocar seu nome de usuário na criação da sua branch. 

Porque usar o Jenkins? 

Eu não poderia rodar todos os testes manualmente na minha branch antes de alterar com a branch principal? 

Você até poderia, mas vamos analisar um exemplo que temos aqui. Temos um projeto que demora quase 30 minutos para executar todos os testes (não apenas testes simples, mas todo o funcionamento do sistema). 

Atualmente, se fala muito em desenvolvimento ágil, e então, seria interessante você perder todo esse tempo até que todos os testes sejam efetivados? Creio que não! Então, o recomendável é que você teste apenas as partes que você modificou e o jenkins se encarrega de fazer todos os testes no sistema pra garantir que tudo esteja funcionando corretamente. 

Outra coisa importante no jenkins é que você e sua equipe ficam sabendo imediatamente sobre o resultado dos testes (o jenkins te notifica por e-mail), dessa forma se houve qualquer problema você pode correr para a correção, antes que alguém crie uma nova funcionalidade no sistema e isso possa gerar erros. 


**Praticando**

Agora que já vimos um pouco sobre integração contínua, vamos ver como isso funciona na prática.

Vamos simular um erro no dojo TesteDeTDD que fizemos anteriormente. 

Observe o trecho de código abaixo:

```java
    if (denominador == 0) {

        throw new IllegalArgumentException();
   
    } else {
    
        int g = mdc(Math.abs(numerador), Math.abs(denominador));
        this.numerador = numerador / g;
        this.denominador = denominador / g;
    }
```

O erro que vamos simular precisa ser um erro de lógica e não de compilação, senão as outras pessoas que estão desenvolvendo o projeto não conseguem trabalhar, devido ao erro de compilação!  

Então nesse trecho de código, vamos tirar a pré-condição: 

```java
        int g = mdc(Math.abs(numerador), Math.abs(denominador));
        this.numerador = numerador / g;
        this.denominador = denominador / g;
```

Agora, rode o TesteDeTDD.

Veja que o teste onde o denominador não deve receber a instância zero falhou. 

![1testefalhou](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/1integracaocont_testefalhou.png?raw=true)

Depois que você modificou o projeto provocando esse erro, adicione essa alteração, faça o commit e envie suas alterações para a brach master usando o push. (Caso tenha dúvidas sobre os comandos siga o tutorial sobre o Git). 

Depois de ter enviado suas alterações para sua branch master, entre no **jenkins**, e clique em **jenkins** (à esquerda acima):  

![entrarnojenkins](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/integragracaocontinua3.png?raw=true)

Escolha o projeto **dojo**

![escolhaoprojetodojo](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/2integracaocontinuajenkis.png?raw=true)

Observe que aparece um ícone amarelo em **s** (Status of the last build), o que significa que a construção no jenkins está instável. Então precisamos descobrir qual parte do projeto introduziu o erro, e pra isso clique no nome do projeto para mais detalhes: 

![clicar nome do projeto](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/integracaocontinua5.png?raw=true)

Na tela que segue você pode observar alguns gráficos e também as opções abaixo: 

![menuintegracaocontinua](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/3integracaocontinua.png?raw=true)

Clique em **Latest Test Result** (inclusive você pode observar que aparece 1 failure / + 1). 

![imagem testeresult](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/4integracaocontinua.png?raw=true)

Podemos ver conforme a imagem acima, em Test Result que aparece o módulo **br.com.objectos:objectos-dojo-team**. Então clique nele para mais informações. 

Na tela que segue, podemos ver mais detalhes como o teste que falhou, e também os pacotes.

![imagem all test result](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/5integracaocontinua3.png?raw=true)

Observe que no pacote **br.com.objectos.dojo.hescarate.tdd.ex1** apresenta 1 em **Fail**, vamos então clicar nesse pacote para ver os detalhes. 

Na sequência aparece a classe TreinoDeTDD, onde vamos clicar também:  

![imagem all tests](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/6integracaocontinua.png?raw=true)

Na tela que segue, como podemos ver abaixo, mostra o resultado dos testes da classe TreinoDeTDD, mostrando o status de cada teste: 

![imagem ](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/7integracaocontinuatestes.png?raw=true)

Se clicarmos em cima do teste que apresenta falhas, podemos ver também a mensagem de erro:

Error Message

Method br.com.objectos.dojo.hescarate.tdd.ex1.TreinoDeTDD.objetoRacionalNaoDeveReceberInstanciaZero() should have thrown an exception of class java.lang.IllegalArgumentException

Nesse dojo que acabamos de fazer sobre integração contínua, nós mesmos simulamos um erro, então, já sabemos onde ele está e como solucionar o problema. Mas no seu cotidiano de trabalho, podem acontecer situações que você não faz idéia do que pode ter dado errado, e então você não precisa perder tempo procurando, pois o jenkis já te mostra o caminho. 

O jenkins te manda uma posição por e-mail imediatamente, assim que executa os testes nas atualizações enviadas por você. Optamos por entrar direto pela página do jenkis no início desse tutorial pra ficar mais didático, mas você pode verificar seu e-mail agora, e ver que consta um link onde você pode acessar mais rápido as informações abrindo direto o último build. 

![email do jenkins](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/8integracaocontinua.png?raw=true)

Agora então precisamos solucionar o erro que provocamos, e enviar as atualizações para nossa branch master.

Depois que você corrigiu o erro, e enviou as atualizações para a branch master, vamos dar mais uma olhada no jenkins. 

Assim que você escolher o projeto dojo e clicar no nome do projeto, observe à esquerda a **Build History**, onde podemos ver um histórico de todos os Builds, conforme a imagem abaixo: 

![buildhistory](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/11buildhistory.png?raw=true)

Podemos observar que o último build está com o ícone em azul.

Clique no último build para ver mais detalhes: 

![mostrandobuild](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/12integracaocontinuabuildhistory.png?raw=true)

Observe que na tela acima, em **Test Result** aparece: (no failures). 

Se você seguir o mesmo procedimento que fizemos, clicar em Test Result > module br.com.objectos:objectos-dojo-team > Package br.com.objectos.dojo.hescarate.tdd.ex1 > Class TreinoDeTDD vai ver que todos os testes passaram desta vez. 

![imagem ](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/13integracaocontinuatodosostestespassaram.png?raw=true)

Agora dê uma olhada no seu e-mail novamente e observe que o jenkins te mandou outro e-mail dizendo agora que a construção voltou para estável:  

![e-mails jenkins2](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/14integracaocontinuaemailjenklins.png?raw=true)

Um outro detalhe é que você mesmo pode rodar o projeto no jenkis, em **build now** no menu à esquerda, conforme a imagem abaixo: 

![build now](https://github.com/objectos/objectos-dojo-img/blob/master/integracaocontinua/15integracaocontinuabuildnow.png?raw=true)

Porém, não é recomendável que você fique rodando o projeto sem necessidade, pois o jenkis está configurado para rodar apenas 2 jobs por vez. No caso do exemplo desse tutorial não vai influenciar muito, pois ele roda bem rápido. Mas evite fazer isso sem necessidade com projetos em produção, até porque, assim que você envia as atualizações o jenkis faz isso automaticamente. 
 

**REFERÊNCIAS:**

Blog Caelum: http://blog.caelum.com.br/integracao-continua/ (acesso em 09/08/2011 14h52) 

_Artigo escrito por Hellen Escarate_


