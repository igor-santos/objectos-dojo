---
layout: post-alpha
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

## public static String format(String format, Object... args);

Vejamos o seguinte exemplo (enviar e receber e-mails):

	public class Email {
	
	  private String mensagem;
	
	  public String receberMensagem() {
	    return mensagem;
	  }
	
	  public void enviarMensagem(String mensagem) {
	    this.mensagem = mensagem;
	  }
	
	}	

	@Test
	public class TesteEMail {
	
	  public void enviar_mensagem_sem_metodo_string_format() {
	
	    String para = "teste@teste.com.br";
	    String cc = "teste2@teste2.com.br";
	    String msg = "Testando String.format";
	
	    Email email = new Email();
	
	    String mensagem = String.format("Para: %s\nCc: %s\nMensagem: %s", para, cc, msg);
	    email.enviarMensagem(mensagem);
	
	    assertEquals(email.receberMensagem(), "Para: teste@teste.com.br\n"
	        + "Cc: teste2@teste2.com.br\n" + "Mensagem: Testando String.format");
	  }
	
	}	
	
Note que com este método temos a vantagem de apenas alterar o conteúdo das variáveis __para__
__cc__ e __msg__ e a mensagem será enviada corretamente no formato que definimos:

> Para: teste@teste.com.br<br>
> Cc: teste2@teste2.com.br<br>
> Mensagem: Testando String.format<br>

Isto é feito pelo conjunto de caracteres __%s__. Para saber mais padrões de formatação além de String, isto é,
números decimais, datas, entre outros, consulte [aqui](http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#detail).




    