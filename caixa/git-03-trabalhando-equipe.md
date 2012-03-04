---
layout: post
title: "Git :: Trabalhando em equipe"
author: "Hellen Carla Paixão Escarate"
published: true
tags:
- git
- aprendizes
---

## Introdução

Como já mencionado anteriormente, quando trabalha-se em um projeto "um pouco maior que aquele trabalho de faculdade", é
normal que um grupo de pessoas esteja participando desse. Assim, você não será a única pessoa editando códigos,
arquivos de configuração, scripts SQL, etc. Ainda que as boas práticas de desenvolvimento de software estimulem o "be
decoupling", por vezes a situação de duas ou mais pessoas trabalharem no mesmo trecho de código ocorrerá. A princípio
pode parecer difícil mas acredite: isto ocorrerá.

São nesses momentos que os conflitos surgem. Conflitos no contexto de controle de versão, que fique claro isto; não
aqueles conflitos entre países, nem aqueles com seu par afetivo (para isto cada um no seu quadrado, já dizia algum
filósofo). Ainda que conflitos, de qualquer natureza, não sejam desejáveis, é preciso saber lidar com estes.

Assim, o objetivo deste kata é simular um conflito, e o mais importante: aprender como resolvê-lo.

## Conflitos
Assume-se aqui que você esteja acompanhando a sequência de katas e que, portanto, já possui o fork do seu projeto
importado na sua máquina. Caso contrário, volte aos katas e pratique-os.

Conflitos (em sistemas de controle de versão) surgem quando trabalhos feitos de maneira paralela encontram-se em um
ponto futuro. Na verdade o correto é dizer **conlitos podem surgir**. Complicado? Por isso mesmo, o melhor a fazer é
sentir os conflitos na prática. 

### Uma tarefa: arquivos de configuração
Suponha que foi dada a você a tarefa de configurar algum sistema ou serviço. Suponha também que este trabalho envolve
editar arquivos de configuração em formato texto. Como você precisará realizar testes e para evitar que uma configuração
incorreta interrompa o trabalho do restante de sua equipe, o melhor a fazer é realizar as alterações inicialmente em um
branch separada.

Assim, o primeiro passo é criar uma nova branch **configuracoes\_conflituosas**. 

(Caso não se lembre como criar uma branch, volte para o kata anterior).

Antes de iniciar certifique-se de que esteja no fork do seu projeto:

    cd ~/kdo/projetos/objectos-dojo

Dentro do seu fork, certifique-se de que esteja dentro do seu módulo

    cd objectos-dojo-login

Substitua `login` apropriadamente.

<div class="alert-message block-message info">
O módulo deve ter sido criado no kata anterior.
</div>

O arquivo hipotético de configuração chamar-se-á (quem usa mesóclise nos dias de hoje?) `conflitos.txt` (agradável,
não?). Terminada a diversão, é hora de trabalho. Suponha que a mega-configuração-master requer que três linhas sejam
adicionadas no arquivo de configuração. Edite o arquivo `conflitos.txt` com o
Vim e adicione o conteúdo.

    o: config importante
    o: config mais importante ainda
    o: esta config não é tão importante assim...

Satisfeito com seu trabalho, você sincroniza suas alterações com seu repositório Git local através de um commit. 

<div class="alert-message block-message info">
Novamente, caso não se lembre como fazer um <strong>commit</strong> volte aos katas anteriores e pratique-os.
</div>

### Be open: receba alterações de sua equipe 
Realizados todos os testes e com confiança de que as novas configurações não irão interromper o trabalho de ninguém da sua
equipe, você prepara-se então para disponibilizar seu trabalho.

    git checkout master

Sua ideia agora é, antes de disponibilizar seu trabalho, receber o trabalho feito por outros membros da equipe para que
o seu próprio fique corretamente sincronizado.

Mas nem tudo são flores.

Neste meio tempo, no entanto, o gerente de projetos um tanto quanto atrapalhado, passou a mesma tarefa para outro
integrante da sua equipe. Ou alguém da sua equipe, impaciente com sua demora (isto nunca acontece), resolveu realizar
ele mesmo as alterações. Por motivos didáticos, você mesmo simulará isto. 

Certifique-se novamente que esteja na branch master:

    git checkout master

Verifique que, nesta branch, suas alterações não existem:

    cat conflitos.txt

Edite novamente o arquivo e adicione as configurações. Porém, como as alterações estão sendo simuladas para que tenham
sido feitas por outra pessoa, o conteúdo é um pouco diferente:

    o: config importante
    x: config ainda muito importante. MESMO!
    o: esta config não é tão importante assim...

Depois de alterado o arquivo, não esqueça de salvar (se você tiver alguma dificuldade consulte o tutorial sobre o VIM),
adicionar o arquivo e fazer o commit.

Deve ficar muito claro aqui o que foi simulado:

1. Criou-se um branch de trabalho `configuracoes_conflituosas`
2. Você codificou
3. Outra pessoa da sua equipe editou e adicionou as mesmas configurações e 'enviou-as' para a branch master
4. Você recebeu (em sua branch master) as alterações do item anterior
5. Você agora irá sincronizar o trabalho dos demais membros com o seu próprio

Certo, volte para a branch **configuracoes\_conflituosas**. Um rebase será feito.

    $ git rebase master
    First, rewinding head to replay your work on top of it...
    Applying: alterando a segunda linha do arquivo conflitos.txt
    Using index info to reconstruct a base tree...
    Falling back to patching base and 3-way merge...
    Auto-merging conflitos.txt
    CONFLICT (content): Merge conflict in conflitos.txt
    Failed to merge in the changes.
    Patch failed at 0001 alterando a segunda linha do arquivo conflitos.txt

    When you have resolved this problem run "git rebase --continue".
    If you would prefer to skip this patch, instead run "git rebase --skip".
    To restore the original branch and stop rebasing run "git rebase --abort".

Uh-oh. E agora?

### Solucionando o conflito
Certo, precisamos então aprender a solucionar o conflito.

O primeiro passo é editar o arquivo com o conflito:
 
    o: config importante
    <<<<<<< HEAD
    x: config ainda muito importante. MESMO!
    =======
    o: config mais importante ainda
    >>>>>>> Paz: sem conflitos
    o: esta config não é tão importante assim...

Observe abaixo apenas o que está em conflito (a parte entre sinais de maior e menor), é o que vamos editar: 

    <<<<<<< HEAD
    x: config ainda muito importante. MESMO!
    =======
    o: config mais importante ainda
    >>>>>>> Paz: sem conflitos

A parte com os sinais de 'menor' `<<<` indica as alterações recebidas e qual a
branch de origem. A parte com os sinais de 'maior' `>>>` indica seu trabalho
original, ou a branch que está recebendo as alterações.

Quando chegarmos nesse ponto, precisamos entender qual foi o erro, e o que gerou o conflito. Nesse caso, estamos
trabalhando com texto e fica simples de resolver, mas logo mais vamos estar fazendo esse mesmo procedimento com código
de produção, e não podemos simplesmente sair excluindo a linha de código que outra pessoa fez, e então vamos precisar
ver com a outra pessoa o que deve ser feito, em alguns casos.  

Neste exemplo, vamos assumir que a versão correta final seja uma mistura das duas versões. Edite o arquivo para que
conteúdo seja: 

    o: config importante
    o: config mais importante ainda. MESMO!
    o: esta config não é tão importante assim...

Certo, depois que alteramos o arquivo o que deve ser feito?

Execute agora o status:

    $ git status
    # Not currently on any branch.
    # Unmerged paths:
    #   (use "git reset HEAD <file>..." to unstage)
    #   (use "git add/rm <file>..." as appropriate to mark resolution)
    #
    #	both modified:      conflitos.txt
    # no changes added to commit (use "git add" and/or "git commit -a")

Observamos então que é necessário adicionar o arquivo, pra que ele seja marcado como resolvido, então, faça isso. 

    $ git add conflitos.txt

E qual é o próximo passo?

Você se recorda que quando executou o comando git rebase, no final do comando apareceram algumas opções, e entre elas a opção abaixo: 

    When you have resolved this problem run "git rebase --continue".

Portanto, agora que resolvemos o problema, vamos executar o comando acima.  

    $ git rebase --continue
    Applying: alterando a segunda linha do arquivo conflitos.txt

Podemos ver que a nossa alteração do arquivo foi aplicada. Pra ter certeza mesmo, execute o comando abaixo: 

    $ cat conflitos.txt
    o: config importante
    o: config mais importante ainda. MESMO!
    o: esta config não é tão importante assim...

O conflito foi solucionado. Ou seja, edições em um mesmo trecho de um mesmo arquivo que originalmente haviam sido feitas
em paralelo foram, agora, incorporadas. Você pode, assim, continuar com o processo:

    $ git checkout master
    $ git merge configuracoes_conflituosas
    $ git push

Pronto, suas alterações foram enviadas para o repositório remoto e (poderão) ser incorporadas ao fork principal
(futuramente). É seguro agora remover a branch de trabalho:

    $ git branch -d configuracoes_conflituosas

### Nem todas alterações geram conflitos

É interessante notar que alterações em um mesmo arquivo não implicam necessarimente em conflitos. Esta seção irá
demonstrar isto.

Certifique-se que você esteja no seu fork do projeto e dentro do seu módulo. Além disso, certifique-se de que esteja na
branch master.

    $ cd ~/kdo/projetos/objectos-dojo
    $ cd objectos-dojo-login
    $ git checkout master

Na branch master, edite um arquivo `config.txt` com o seguinte conteúdo:

    1. config a 
    2. config b 
    3. config c
    4. config d

Novamente faça os procedimentos necessários, salvando o arquivo, adicionado a modificação e fazendo o commit. 

De maneira análoga à seção anterior, crie uma nova branch de trabalho.

    $ git checkout -b config_sem_conflito

Agora na branch de trabalho, altere a última linha do arquivo `config.txt` com o seguinte conteúdo:

    1. config a 
    2. config b 
    3. config c
    4. config d+

Em seguida, volte à branch master e altere o mesmo arquivo, mas ao invés da última linha, altere a primeira linha:

    1. config a- 
    2. config b 
    3. config c
    4. config d

Volte agora para a branch de trabalho. Faça um rebase:

    $ git checkout config_sem_conflitos
    $ git rebase master
    First, rewinding head to replay your work on top of it...
    Applying: configuração MUITO importante
    Using index info to reconstruct a base tree...
    Falling back to patching base and 3-way merge...
    Auto-merging objectos-dojo-mendo/config.txt

E se você verificar o conteúdo do arquivo:

    $ cat config.txt
    1. config a- 
    2. config b 
    3. config c
    4. config d+

Podemos perceber então que ao alterar o mesmo arquivo em seções diferentes, o GIT não reconheceu isso como um
conflito, pois ele tem algoritmos que identificam esse tipo de situação. No caso anterior, como o próprio GIT não
encontrou a solução, foi gerado o conflito, para nós mesmos resolvermos, lembrando que o conflito precisa ser entendido
e não simplemente substituir linhas de  código ou apagar o que seu colega escreveu, é necessário entender o que o
ocasionou o conflito para que ele seja resolvido.

E para terminar, sincronize seu trabalho com o GitHub.

    $ git checkout master
    $ git merge config_sem_conflitos
    $ git push

## Conclusão

Com este kata você um pouco sobre os conflitos no Git, e você deve ter uma base mínima para:

1. Identificar em quais situações conflitos podem ocorrer;
2. Identificar em quais situações conflitos não ocorrem;
3. Sincronizar branches de trabalho com a branch master através do rebase; e
4. Resolver conflitos e continuar o processo do rebase.
