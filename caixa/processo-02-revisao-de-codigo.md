No tópico anterior, você praticou exercícios sobre integração contínua, e antes disso, praticou o dojo de TDD. 

Nosso passo atual será fazer a revisão de código do TreinoDeTDD. 

Mas pra que serve a revisão de código?

A idéia da revisão de código é atingir a qualidade máxima do código, ou seja, aquele código que você escreveu (anteriormente no exercício de TDD, por exemplo) pode estar funcionando corretamente, mas é fundamental ver os pontos que podem ser melhorados ou corrigidos.  

Além de melhorar a qualidade do código, a revisão de código também melhora a habilidade dos programadores. Por isso é fundamental que você esteja aberto a críticas, pois no momento que o seu código for revisado é uma oportunidade de aprender e melhorar ainda mais. 

**Pull Request**

Depois de fazer uma alteração no projeto, seu objetivo é enviar essa alteração para o repositório oficial, pois até o momento essas alterações estão apenas na sua branch. 

Nesse caso, você criou duas novas classes, Racional e TreinoDeTDD, e essas alterações precisam ser enviadas ao responsável pelo projeto (ou equipe), que vai avaliar o seu código e ver se ele pode entrar na versão oficial do projeto. 

Então agora vamos fazer isso na prática. 

Entre no github e escolha a branch onde você fez essas alterações, conforme a imagem abaixo: 

![escolha da branch](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/1escolhaabranch.png?raw=true)

Na sequência, clique no botão **Pull Request**

![clicar em pull request](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/2clicarempullrequest.png?raw=true)

Em seguida, é necessário colocar uma mensagem do que foi alterado, deixando claro para o responsável pelo projeto (ou equipe)o que realmente foi feito e solicitando a revisão de código.

![inserindo mensagem](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/3mensagempullrequest.png?raw=true)

Depois que você escreveu a mensagem, clique em **Send pull request**.

Dessa forma outras pessoas podem avaliar seu código sugerindo melhorias e fazendo correções. 

Como foi dito anteriormente nesse tutorial, é uma oportunidade de melhorar suas habilidades. 

Quando o pull request for enviado, a sua equipe ficará sabendo através de e-mail, mas é importante que você peça pessoalmente assim que terminar o código, e enviar o pull request. 

Agora que você enviou o pull request, vamos observar como isto funciona.

Clique agora em **Pull Requests**, no botão que fica no menu, depois do botão Network. 

![clicar no botao](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/4clicarempullrequest.png?raw=true)

Agora clique no nome da branch em negrito para ver maiores detalhes: 

![detalhe nome](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/5clicarnonome.png?raw=true)

Podemos observar que aparece o nome da branch e a mensagem que você colocou, que vai facilitar para outras pessoas entenderem melhor o que foi feito no projeto. 

Observe também que aparece um sinal de "+", e **hescarate added some commits**. 

Podemos então ver os commits efetuados. 

![entarda](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/9openpullrequest.png?raw=true) 

Um pouco mais abaixo dessa mesma tela, podemos ver os comentários do que precisa ser revisado no código.  

![comentario1](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/7funcionamentopullrequest.png?raw=true)

![comentario2](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/6funcionamentopullrequest.png?raw=true)

Depois que você receber os comentários do seu código, vá no Eclipse e faça as alterações solicitadas, e então feche esse pull request, clicando em **Close Pull Request**, (bem no final da página).

![close pull request](https://github.com/objectos/objectos-dojo-img/blob/master/revisaodecodigo/10closepullrequest.png?raw=true)

Agora abra novamente o pull request informando que as alterações foram feitas, solicitando novamente a revisão de código. 


**REFERÊNCIAS**

http://en.wikipedia.org/wiki/Code_review

http://handbook.thoughtbot.com/communication/feature-branch-code-reviews/

http://labs.moip.com.br/2010/10/20/guia-pratico-contribuindo-com-projetos-do-moip-no-github-remotes-fetches-pull-requests-e-issues/

_Esse artigo foi escrito por Hellen Escarate_







