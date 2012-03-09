---
layout: post
title: Método String.format
author: "Tiago Aguiar"
published: true
partof: java
num: 3
outof: 3
---

## Introdução

O método _format_ da classe [java.lang.String] (http://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
tem por finalidade retornar um conjunto de caracteres 
formatados. Essa formatação dependerá de uma _String_ especificadora e/ou um objeto _Locale_
passados através de argumentos.

Temos uma sobrecarga do método format:

    1. public static String format(String format, Object... args);
    2. public static String format(Locale l, String format, Object... args);

## String.format(String format, Object... args);

Vejamos o seguinte exemplo (fazer uma reserva em um restaurante):

	
	@Test
	public class ReservaRestaurante {
	
	  public void fazer_reserva_no_restaurante_com_metodo_string_format() {
	    String nome = "Smith";
	    int qtdDePessoas = 4;
	    double preco = 100.90;
	
	    String formatar = "Nome: %s\nQuantidade de pessoas: %d\nPreço: R$%.2f";
	    String resultado = "Nome: Smith\nQuantidade de pessoas: 4\nPreço: R$100,90";
	
	    String reserva = String.format(formatar, nome, qtdDePessoas, preco);
	
	    assertEquals(reserva, resultado);
	  }
	
	}	
	
Note que com este método temos a vantagem de apenas alterar o conteúdo das variáveis __nome__,
__qtdDePessoas__ e __preco__ e a mensagem será enviada corretamente no formato que definimos:

> Nome: Smith<br>
> Quantidade de pessoas: 4<br>
> Preço: R$100,90<br>

Isto é feito pelo conjunto de caracteres __%s__ (para String), __%d__ (para decimal) e __%.2f__ 
(para elementos fracionários onde o elemento _.%2f_ indica duas casas decimais, _%.3f_ três casas
decimais e assim por diante).

## String.format(Locale l, String format, Object... args);

Vejamos o seguinte exemplo (moeda nacional e internacional):

	@Test
	public class Moeda {
	
	  public void conversor_monetario_com_metodo_string_format() {
	    double real = 1000000.50;
	    double dolar = 12000000.50;
	
	    Locale brasil = new Locale("pt", "BR");
	    Locale eua = new Locale("en", "US");
	
	    String realConvertido = String.format(brasil, "R$%,.2f", real);
	    String dolarConvertido = String.format(eua, "$%,.2f", dolar);
	
	    assertEquals(realConvertido, "R$1.000.000,50");
	    assertEquals(dolarConvertido, "$12,000,000.50");
	  }
	
	}

Note que desta vez o objeto _Locale_ definido o padrão de formatação monetária de cada país
(Estados Unidos e Brasil).

> R$1.000.000,50<br>
> $12,000,000.50<br>

_Você já deve ter visto esta diferença em calculadoras de computadores em inglês e em português_

Para saber mais padrões de formatação além de String e número decimal consulte 
[aqui](http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax).

Para mais informações acesse os códigos nos links abaixo:

[ReservaRestaurante](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/string/ReservaRestaurante.java)<br>
[Moeda](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/string/Moeda.java)




    