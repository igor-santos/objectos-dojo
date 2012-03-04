# TDD - Test Driven Development

O Test Driven Development, em português, Desenvolvimento Dirigido a Testes, é uma técnica de desenvolvimento de Software muito usada em metodologias de desenvolvimento ágil. 

**Como funciona?**

1 - Pensar no que será feito

2 - Criar um teste

3 - Fazer esse teste falhar

4 - A partir da falha do teste, você começa a implementar o código. 

**Qual é a idéia disso?**

A idéia é te forçar a pensar primeiro, antes de desenvolver algo.  

Vamos pensar por exemplo, na maneira mais convencional de se programar, você "sai codificando" pra depois pensar no que pode dar errado e implementar os testes. 

Com o TDD, você primeiro pensa nas funcionalidades do que você quer implementar criando um teste, e em seguida esse teste vai falhar, porque o método não existe, não existe código criado ainda. Então em cima disso você passa implementar seu código. 

O TDD é uma ferramenta que pode te dar 100% de garantia no código, se realmente seu teste for implementado de mandeira correta, podemos evitar "dores de cabeça" futuras, e ter segurança no que estamos fazendo.

Nunca se esqueça desse ciclo iterativo: 

TESTE > CODIGO > REFATORAÇÃO > 

**Praticando**

Os exercícios que vamos usar como exemplo pra treinar o TDD foram tirados do livro PROGRAMMING IN SCALA, que na verdade não trata exatamente de TDD, mas foram exemplos tão bons que resolvemos aproveitá-los nesse tutorial.  

Então, agora é hora de praticar, seguindo os passos que aprendemos acima: 

**1 - Pensar no que queremos fazer:** 

Queremos que um objeto retorne na forma fracionária.

**2 - Criar um teste:** 

Vamos primeiro criar uma classe chamada TesteDeTDD.

A partir disso vamos criar um método com o nome do que esse teste faz: objetoRacionalToStringDeveImprimirFormaFracionaria. 

```java
public class TreinoDeTDD {
  public void objetoRacionalToStringDeveImprimirFormaFracionaria() {
  }
}
```

Agora então, usando o **Ctrl + 2 L**, vamos atribuir um valor para a variável, como 1, 5 por exemplo.
Usando o assertEquals vamos verificar se o valor esperado ("1/5", pois queremos que ele imprima na forma de fração) é o que foi atribuído à variável.

```java
public class TreinoDeTDD {
  public void objetoRacionalToStringDeveImprimirFormaFracionaria() {
    Racional umQuinto = new Racional(1, 5);
    assertEquals(umQuinto.toString(), "1/5");
  }
}
```

Observe que nesse trecho de código contém erros.  

**3 - Fazer o teste falhar**

Automaticamente esse teste vai falhar porque o método e a classe não existem. 


**4 - A partir do teste começamos a implementar o código:**

Observe, que se clicarmos em cima do erro com o cursor, aparecem opções, e podemos escolher **create new class**.

Também podemos criar uma nova classe usando uma tecla de atalho. Basta colocar o cursor em cima da palavra com erro, e usar **Ctrl 1 (um)**. 

clique em **ENTER**, e na sequência vai abrir uma janela já com o nome da classe.

Se você observar na janela aberta, a primeira opção **Source folder**,  aparece como objectos-dojo-team/src/test/java. Vamos clicar então em **Browse** e mudar para **objectos-dojo-team/src/main/java**. 

Podemos ver que a classe Racional foi criada. Agora vamos voltar na classe TreinoDeTDD e podemos ver que ainda contém erros: 

Se levarmos o cursor em cima do erro, uma mensagem será exibida de que o contrutor não está definido

Você pode clicar direto na segunda opção **Create constructor 'Racional (int, int)'** e observar que o construtor foi criado na classe Racional. Para ficar mais claro, mude as variáveis (que estão como i e j) para **numerador** e **denominador**. 

```java
public class Racional {
  public Racional(int numerador, int denominador) {
  }
}
```

Então, vamos o rodar esse código (na classe TreinoDeTDD), mas como se trata de um teste, verifique se acima da classe você já colocou a anotação **@Test**. 

Agora usando **ALT SHIFT X N** rode o código e veja se ele passou. 

Você pode observar que o teste ainda não passou porque falta implementar o código. 
Vamos então implementar o trecho do teste que faltava. 

```java
public class Racional {
  int numerador;
  int denominador;

  public Racional(int numerador, int denominador) {
  }

  @Override
  public String toString() {
    return ("1/5");
  }
}
```

Rode o teste novamente e veja se passou. 

Certo, agora vamos criar um outro teste com a finalide de mostrar em forma de fração qualquer número, não apenas o 1/5 testado anteriormente. 

Da mesma forma, crie um outro método dentro da mesma classe, sempre pensando na sua funcionalidade. Podemos dar o nome de qualquerObjetoRacionalDeveRetornarNaFormaFracionaria. 

```java
public void qualquerObjetoRacionalDeveImprimirNaFormaFracionaria() {
    Racional doisTercos = new Racional(2, 3);
    assertEquals(doisTercos.toString(), "2/3");
}
```

Em seguida, rode o teste pra ver se funciona. 

Não funcionou, porque falta implementar o código! Até o momento o valor que estavamos recebendo era "1/5", então precisamos fazer que quarquer variável imprima na forma fracionária.

```java
public class Racional {
  Integer numerador;
  Integer denominador;

  public Racional(int numerador, int denominador) {
    this.numerador = numerador;
    this.denominador = denominador;
  }

  @Override
  public String toString() {
    return numerador.toString() + "/" + denominador.toString();
  }
}
```

Ok, agora tente o rodar o teste novamente e ele vai passar. 

Muito bem, como estamos lidando com números racionais, eles tem algumas características, certo? 

Quais são as características de um número racional?

Isso mesmo, uma delas, é que eles devem ser números inteiros, ok? Já deixamos definido que nosso Objeto Racional recebe dois valores inteiros, então está ok.  

Além de ser um inteiro, um número racional não pode ter o denominador igual a zero, afinal, é uma lei da matemática, portanto vamos precisar criar essa restrição.

E como fazer isso? 

Vamos criar um teste com essa funcionalidade, colocando exatamente isso no nome do método. Vamos então atribuir o valor zero no lugar do denominador. 

```java
  public void objetoRacionalToStringNaoPodeInstanciarComDenominadorZero() {
    new Racional(5, 0);
  }
```

Se você rodar esse teste ele certamente vai passar, o que não era pra acontecer, pois o denominador precisa ser diferente de zero. 

Sendo assim, precisamos então implementar o código. Mas como fazer isso? O que eu preciso fazer para que o denominador não aceite o zero? Precisamos então dizer que se ele for igual a zero alguma coisa deve acontecer. 

Podemos colocar então uma anotação @Test antes do método, e dizer que essa anotação recebe um argumento, uma exceção esperada. 

```java
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void objetoRacionalToStringNaoPodeInstanciarComDenominadorZero() {
    new Racional(5, 0);
  }
```

Tente rodar agora novamente, veja se o teste passa. 

Pois é, não passou. 

Então, o teste não passou, precisamos implementar algo mais no código. 


```java
public Racional(int numerador, int denominador) {
    if (denominador == 0) {
      throw new IllegalArgumentException();
    } else {
      this.numerador = numerador;
      this.denominador = denominador;
    }
}
```

Agora o teste funciona.

Muito bem, agora precisamos adicionar um campo que retorna a soma de dois números racionais, pra isso, criaremos um novo método com o teste, como nos exemplos anteriores. 

```java
public void objetoRacionalToStringDeveAdicionarCampoQueRetornaASoma() {
  Racional umMeio = new Racional(1, 2);
  Racional doisTercos = new Racional(2, 3);

  Racional resultado = umMeio.soma(doisTercos);
  assertEquals(resultado.toString(), "7/6");
}
```
  
Certo, você observou que nesse teste aparece erro no método soma, porque na verdade ele ainda não foi criado, e essa é a idéia do TDD. Por isso vamos agora implementar o código. 

Se você deixar o cursor do mouse em cima do método soma, que aparece com erro, vai listar a opção de clicar com o mouse em **create method soma...**, clique nessa opção e automaticamente será criado o método. 

Então vamos implementar pra que esse método crie um novo Racional que retorna a soma. 

```java
public Racional soma(Racional numero) {
  int numerador = numero.numerador;
  int denominador = numero.denominador;

  int somaNumerador = this.numerador * denominador + this.denominador * numerador;
  int somaDenominador = this.denominador * denominador;

  return new Racional(somaNumerador, somaDenominador);
}
```

Agora na classe TreinoDeTDD rode o teste.  

Podemos observar que o teste passou, agora teste também com outros valores pra ver se o teste passa.

No nosso próximo passo, deve ser possível construir um racional passando apenas um inteiro. Como no exercícios anteriores vamos criar um método e fazer o teste com essa idéia. 

```java
public void deveSerPossivelConstruirUmRacionalPassandoApenasUmInteiro() {
  Racional um = new Racional(1);
  assertEquals(um.toString(), "2/1");
}
```

Certo, depois de criado o teste, podemos observar que aparecem erros, pois não foi definido um objeto Racional que recebe apenas um inteiro, então você pode fazer como nos exercícios anteriores, colocar o cursor em cima do erro e escolher a opção **Create constructor...**, e automáticamente será criado o construtor

```java
public Racional(int i) {
}
```

Agora que o construtor foi criado, tente rodar o teste. 

Porque o teste não passou? 

Porque ainda falta alguma coisa nesse construtor.

então vamos implementar o código.  

```java
public Racional(int i) {
  this(i, 1);
}
```

Muito bem, agora teste novamente e veja se o teste passou.

Teste com outros valores pra ver se realmente o teste não vai passar.  

Legal, vamos então para o próximo exercício, onde precisamos retornar um racional equivalente na forma reduzida. 

```java
public void retornarEquivalenteNaFormaReduzida() {
  Racional onzeSetimos = new Racional(66, 42);
  assertEquals(onzeSetimos.toString(), "11/7");
}
```

Tente rodar esse código, você vai observar que não passa. 

Porque certamente falta implementar o código. 

O que precisamos fazer pra que ele retorne o racional equivalente na forma reduzida?

```java
public Racional(int numerador, int denominador) {
  if (denominador == 0) {
    throw new IllegalArgumentException();
  } else {
     // mdc - mínimo divisor comum 
     int g = mdc(Math.abs(numerador), Math.abs(denominador));
     // mdc - mínimo divisor comum     
     this.numerador = numerador;
     this.denominador = denominador;
  }
}
```

```java
// exercicio que retona o equivalente na forma reduzida 
private int mdc(int a, int b) {
  return b == 0 ? a : mdc(b, a % b);
}
// exercício que retorna o equivalente na forma reduzida

public Racional(int i) {
  this(i, 1);
}
```

Agora rode o teste. 

Mesmo assim não passou,  não é mesmo?

Então, falta algum detalhe, vamos pensar. 

Faltou dividir o numerador e denominador pela variável que retorna o valor do mínimo divisor comum (mdc). 

```java
      this.numerador = numerador / g;
      this.denominador = denominador / g;
```

Crie um teste agora, como o anterior, mas testando com racionais negativos. 

Nosso último exercício desse tópico, será multiplicar racionais, precisamos testar se a multiplicação retorna corretamente. 

Então, como nos exercícios anteriores, criamos um método pra testar essa funcionalidade. 

```java
 public void multiplicacaoDeveRetornarCorretamente() {

    Racional umMeio = new Racional(1, 2);
    Racional tresQuintos = new Racional(3, 5);

    Racional resultado = umMeio.multiplica(tresQuintos);
    assertEquals(resultado.toString(), "3/10");

  }
```

Podemos observar que esse trecho de código apresenta erros, pois o método multiplica não foi criado ainda, e essa é a idéia do TDD. À partir desse erro, vamos então construir o método (conforme falamos anteriormente, você pode colocar o mouse em cima da palavra que apresenta erro e aparece a opção **Create method...**

```java
public Racional multiplica(Racional outro) {
  return new Racional (numerador * outro.numerador, denominador * outro.denominador);
}
```

Agora rode o teste, e verifique se o teste passou. 

Force o erro com outros números.  


**REFERÊNCIAS:**

ORDESKY, Martin. SPOON, Lex. VENNERS, Bill. PROGRAMMING IN SCALA. Artima, Second Edition, Dec. 2010. 

_Artigo escrito por Hellen Escarate_













 






 





















  









 










 







    



