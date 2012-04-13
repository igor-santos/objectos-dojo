---
layout: post-alpha
title: "Erro no Json"
author: "Tiago Aguiar"
user: "taguiar"
published: true
partof: faq-crud-forms
num: 2
outof: 2
---

## Assertion Error em Json

Este tipo de erro acontece no processo de gravação de dados no banco de dados. Por exemplo, um campo
escrito de forma errada, tipos de dados incompatíveis, valores adicionados a colunas que não existam,
entre outros.

Vejamos o _StackTrace_ gerado pelo teste (utilizando o mesmo método do FAQ anterior):

	FAILED: form_deve_gravar_imovel
	java.lang.AssertionError: 
	Expected: is <true>
    got: <false>

	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:21)
	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:8)
	at br.com.objectos.dojo.TesteDeFormDeImovelCreate.form_deve_gravar_imovel(TesteDeFormDeImovelCreate.java:89)
	
Note que a única informação que temos referente ao erro é: `Expected: is <true>` e `got: <false>`.<br>
Com o _Json_ e o _debug_ , podemos localizar a causa deste erro. Para tal, adicione um _breakpoint_ no 
seguinte código:

	assertThat(json.isValid(), is(true));

Com o _breakpoint_ adicionado, aperte `Shift + Alt + D` e depois aperte a tecla `N`.<br>
O _debug_ será executado (mudando a perspectiva do _Eclipse_ de _Java EE_ para _debug_ ).

## Causa do Assertion Error

Agora o erro se torna mais explicito, isto é, podemos verificar o porquê do `got: false`.

Entre na variável `json > errors > elementData` e leia a descrição da exceção. Temos como exemplo, duas
exceções:
	
	java.sql.SQLException: Incorrect integer value: 'CLIENTEB' for column 'CLIENTE_ID' at row 1
	java.sql.SQLException: Field 'HASH' doesn't have a default value
	
Na primeira exceção já podemos deduzir que os tipos de dados não são equivalentes, ou seja, em algum
código, a coluna `CLIENTE_ID` esta tentando receber um tipo de valor diferente de _integer_.<br>

Verificamos no método `getInsert()` da classe `ImovelJPA` a incoerência do código `cliente.getCodigo()`:

	@Override
	  public Insert getInsert() {
	    return Insert.into("RO_ORG.IMOVEL")
	
	        .value("CLIENTE_ID", cliente.getCodigo())
	        .value("ENDERECO", endereco)
	        .value("NUMERO", numero)
	        .value("COMPLEMENTO", complemento)
	        .value("CIDADE", cidade)
	        .value("ESTADO", estado.name())
	        .value("CEP", cep)
	
	        .onGeneratedKey(new GeneratedKeyCallback() {
	          @Override
	          public void set(ResultSet rs) throws SQLException {
	            id = rs.next() ? rs.getInt(1) : null;
	          }
	        });
	  }
	  
Na segunda exceção, fica claro que não foi definido nenhum valor para a coluna `HASH`.

## Solução

Para o primeiro exemplo, altere o método `.value`

	.value("CLIENTE_ID", cliente.getId())

 e para o segundo exemplo, adicione o código abaixo:

	.value("HASH", getHash())

A partir de agora, o teste passará em cada um dos exemplos.	 	  	
	
	PASSED: form_deve_gravar_imovel
	
	===============================================
	    Default test
	    Tests run: 1, Failures: 0, Skips: 0
	===============================================

Importante: Sempre utilize o _debug_ para os _AssertionError_ referente ao _Json_.<br>
Conforme comentamos, ele poderá solucionar diversos problemas referentes a inserção de dados. 