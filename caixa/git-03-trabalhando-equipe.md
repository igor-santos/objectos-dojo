---
layout: post-alpha
title: "Trabalhando em equipe"
author: "Hellen Carla Paixão Escarate"
published: true
tags:
- git
- aprendizes
partof: git
num: 3
---

## Introdução
Como já mencionado anteriormente, quando trabalha-se em um projeto, é comum que um grupo de pessoas esteje participando. 
Assim, você não será a única pessoa editando códigos, arquivos de configuração, scripts SQL, etc. 
A situação de duas ou mais pessoas trabalharem no mesmo trecho de código ocorrerá. A princípio, pode parecer difícil, mas acredite, isto ocorrerá.

São nesses momentos que os conflitos surgem.

##  Conflitos
### Refatorando um arquivo
Assume-se aqui que você já possui o _fork_ do seu projeto importado na sua máquina. Caso contrário, vá aos artigos anteriores 
e pratique-os.

Imagine que você recebeu a tarefa de refatorar uma classe (em JAVA) chamada `ImovelJPA` onde
o objetivo era alterar um determinado trecho de código de uma classe privada `Construtor` para
uma classe privada `ConstrutorDeEndereco`.

Antes:

	public class ImovelJPA implements Imovel {
	
	  // Construtores e métodos omitidos
	 
	  private class Construtor implements Propriedades {
		
	    // Métodos omitidos
	  
	  }
	
	}

Depois:
	
	public class ImovelJPA implements Imovel {
	 
	  // Construtores e métodos omitidos
	 
	  private class ConstrutorDeEndereco implements Endereco.Construtor {
		
		  // Métodos omitidos
	  
	  }
	
	}    
 
Como você já deve saber, devemos realizar as alterações inicialmente em uma _branch_ separada.

Assim, o primeiro passo é criar uma nova _branch_ **refatorar\_imovel**. 

Antes de iniciar certifique-se de estar no diretório do projeto correto. Por exemplo:

    cd ~/kdo/projetos/objectos-dojo

Neste momento você deduz que há a necessidade de fazer uma alteração na _interface_ `Imovel` no qual
se encontra com a estrutura a seguir:

	public interface Imovel {
	
	  interface Propriedades {
	
	    String getEndereco();
	
	    String getNumero();
	
	    String getComplemento();
	
	    String getCidade();
	
	    State getEstado();
	
	    String getCep();
	
	  }
	
	  interface Construtor extends br.com.objectos.comuns.base.Construtor<Imovel>, Propriedades {
	
	    Cliente getCliente();
	
	    String getHash();
	
	  }
	  
	  // Demais métodos omitidos	
	
	}

A alteração seria justamente a _interface_ `Propriedades`. Ok, então iremos alterar.

    public interface Imovel {

	  interface Construtor extends br.com.objectos.comuns.base.Construtor<Imovel>, Endereco {
	
	    Cliente getCliente();
	
        String getHash();
	
	  }
	
	  // Demais métodos omitidos	
	
    }

Satisfeito com seu trabalho, você sincroniza suas alterações com seu repositório Git através de um `add`, `commit` e `push`. 

### Recebendo as alterações de sua equipe 
Com as alterações realizadas, um membro da equipe notou que a sua alteração estava errada, isto é, a interface `Propriedades`
não deveria ser apaga, ela não faz parte do escopo do Endereco, é uma outra funcionalidade que deve ser mantida (não 
entraremos em detalhes nesta funcionalidade). Logo, o outro membro da equipe faz as alterações necessárias neste arquivo.

No dia seguinte, você retorna ao trabalho e "traz" as atualizações do projeto (a partir da _branch master_).

Até agora foi realizado os seguintes passos:

1. Você criou uma _branch_ de trabalho `refatorar_imovel`
2. Você refatorou
3. Outra pessoa da sua equipe editou e adicionou o arquivo a _branch master_

Você agora irá sincronizar o trabalho dos demais membros com o seu próprio:

	$ git checkout refatorar_imovel
	$ git fetch origin
	$ git merge origin/master

O resultado será algo como

    Auto-merging src/main/java/br/com/projeto/Imovel.java
    CONFLICT (add/add): Merge conflict in src/main/java/br/com/projeto/Imovel.java
    Automatic merge failed; fix conflicts and then commit the result.

Surge então o conflito.

### Entendendo e solucionando o conflito
Os conflitos foram ocasionados justamente pela inserção da interface `Propriedades` e `Endereco`.
Em curtas palavras seria um mesmo arquivo com alterações diferentes por pessoas diferentes.

Para solucionar estes conflitos, você deve ter certeza de quais alterações devem ser mantidas e excluídas, para isso, discuta com
o seu companheiro de equipe o motivo das alterações dele, verifique quais alterações são realmente necessárias
(a sua ou a dele). O que queremos dizer é que, não basta excluir algumas linhas para resolver o conflito, é 
preciso entender os fatos para tomar qualquer decisão.

Neste caso, suponhamos que discutimos as causas e realmente nossas alterações na interface `Imovel` não eram
necessárias, apenas em `ImovelJPA` era necessário.

A parte com os sinais de __menor__ `<<<` indica as alterações recebidas e qual a _branch_ de origem. 
A parte com os sinais de __maior__ `>>>` indica seu trabalho original.

O primeiro passo é editar o arquivo com o conflito (utilizando o Vim).

	public interface Imovel {
	
	  <<<<<<< HEAD
	  interface Propriedades {
	
	    String getEndereco();
	
	    String getNumero();
	
	    String getComplemento();
	
	    String getCidade();
	
	    State getEstado();
	
	    String getCep();
	
	  }
	  	
	  interface Construtor extends br.com.objectos.comuns.base.Construtor<Imovel>, Propriedades {
	  =======
	  interface Construtor extends br.com.objectos.comuns.base.Construtor<Imovel>, Endereco {
	  >>>>>>> Paz: sem conflitos
	  
	    Cliente getCliente();
	
	    String getHash();
	
	  }
		  
	  // Demais métodos omitidos	
		
	}


Como já discutimos o que deve ser mantido, manteremos os itens que se referem a interface `Propriedades`
apagando nossa alteração e as linhas com os sinais de maior e menor.<br>

Nota: Para mais informações sobre __resolver conflitos com Vim__, acesse [aqui](http://dojo.objectos.com.br/caixa/git-05-resolvendo-conflitos-com-o-vi.html).

Após sair do Vim, execute `add`, `commit` (com a mensagem sobre o conflito resolvido) e `push`.

O conflito foi solucionado. Ou seja, edições em um mesmo trecho de um mesmo arquivo que originalmente haviam sido feitas
em paralelo foram, agora, incorporadas. Você pode, assim, continuar com o processo:

    $ git checkout master
    $ git merge refatorar_imovel
    $ git push

Pronto, suas alterações foram enviadas para o repositório remoto e (poderão) ser incorporadas ao fork principal
(futuramente). É seguro agora remover a branch de trabalho:

    $ git branch -d refatorar_imovel

### Nem todas alterações geram conflitos

É interessante notar que alterações em um mesmo arquivo não implicam necessarimente em conflitos. Esta seção irá
demonstrar isto.

Certifique-se que você esteja no seu _fork_ do projeto e dentro do seu módulo. Além disso, certifique-se de que esteja na
_branch master_.

    $ cd ~/kdo/projetos/objectos-dojo
    $ cd objectos-dojo-login
    $ git checkout master

Na _branch master_, edite um arquivo `config.txt` com o seguinte conteúdo:

    1. config a 
    2. config b 
    3. config c
    4. config d

Novamente faça os procedimentos necessários, salvando o arquivo, adicionado a modificação e fazendo o _commit push_. 

De maneira análoga à seção anterior, crie uma nova _branch_ de trabalho.

    $ git checkout -b config_sem_conflito

Agora na _branch_ de trabalho, altere a última linha do arquivo `config.txt` com o seguinte conteúdo:

    1. config a 
    2. config b 
    3. config c
    4. config d+

Em seguida, volte à _branch master_ e altere o mesmo arquivo, mas ao invés da última linha, altere a primeira linha:

    1. config a- 
    2. config b 
    3. config c
    4. config d

Volte agora para a _branch_ de trabalho. Faça um _merge_:

    $ git merge config_sem_conflito
    Already up-to-date.

E se você verificar o conteúdo do arquivo:

    $ cat config.txt
    1. config a- 
    2. config b 
    3. config c
    4. config d+

Podemos perceber então que ao alterar o mesmo arquivo em seções diferentes, o Github não reconheceu isso como um
conflito, pois ele tem algoritmos que identificam esse tipo de situação. No caso anterior, como o próprio Github não
encontrou a solução, foi gerado o conflito, para nós mesmos resolvermos, lembrando que o conflito precisa ser entendido
e não simplemente substituir linhas de  código ou apagar o que outra pessoa escreveu, é necessário entender o que o
ocasionou o conflito para que ele seja resolvido.

E para terminar, sincronize seu trabalho com o GitHub.

    $ git checkout master
    $ git merge config_sem_conflito
    $ git push