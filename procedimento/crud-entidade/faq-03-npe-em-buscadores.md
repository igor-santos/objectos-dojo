---
layout: post-alpha
title: "NPEs em buscadores e consultas"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
published: true 
partof: faq-crud-entidade
num: 3
---

## NPEs em buscadores e consultas

Para este FAQ, considere o seguinte bloco de código:

    @Test
    @Guice(modules = {ModuloDeTesteEmpresaXPTO.class})
    public class TesteDeBuscarCliente {
      @Inject
	  private BuscarCliente buscarCliente;
	  
	  @Inject
	  private DBUnit dbUnit;
	  
	  @BeforeClass
	  public void prepararDBUnit() {
	    dbUnit.loadDefaultDataSet();
	  }
	  
      public void busca_por_id_deve_funcionar() {
        int id = 10;
        
		Cliente res = buscarCliente.porId(id);
		assertThat(res, is(notNullValue()));
		
		assertThat(res.getId, equalTo(id));
		assertThat(res.getNome(), equalTo("Godofredo Diaz"));
		assertThat(res.getTelefone(), equalTo("(11) 1234 - 6789"));
		assertThat(res.getEndereco(), equalTo("Avenida do Oratório, 5000"));
		assertThat(res.getBairro(), equalTo("Vila Industrial"));
		assertThat(res.getCidade(), equalTo("São Paulo"));
      }
      
    }
	
### Foi adicionada a anotação `@Guice` logo abaixo da anotação `@Test` ? 

	RemoteTestNG starting
	FAILED CONFIGURATION: @BeforeClass prepararClasse
		java.lang.NullPointerException
		at br.com.objectos.dojo.TesteDeBuscarCliente.prepararClasse(TesteDeBuscarCliente.java:42)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)

A npe acontecerá exatamente onde não foi definido a anotação `@Guice` em sua classe de teste:
 	
		@Test
		@Guice (modules = {ModuloDeTesteEmpresaXPTO.class})
			public class TesteDeBuscarCliente {
		}
 
 
### Você adicionou a anotação `@Inject` logo após declarar o seu Buscador em seu teste ?
 
 	FAILED: busca_por_id_deve_funcionar
	java.lang.NullPointerException
	
	 	at br.com.objectos.dojo.TesteDeBuscarCliente.busca_por_id_deve_funcionar(TesteDeBuscarCliente.java:47)
 		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:56)
 
Lembrando que uma das causas das NullPointers é tentar acessar propriedades de uma instância onde seu
valor é "null": [Atente a primeira das causas no javadoc] (http://docs.oracle.com/javase/6/docs/api/java/lang/NullPointerException.html)
, sem a presença da anotação `@Inject` uma NPE será lançada logo que se chamar o buscador:
  
		@Inject
		private BuscarCliente buscarCliente;
		 
		public void busca_por_id_deve_funcionar() {
			int id = 10;
		
			Cliente res = buscarCliente.porId(id);
		}
	

### Os seus dados de teste estão razoáveis?

 	FAILED: busca_por_id_deve_funcionar
	java.lang.NullPointerException
	
	 	at br.com.objectos.dojo.TesteDeBuscarCliente.busca_por_id_deve_funcionar(TesteDeBuscarCliente.java:62)
 		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
		
Existe no mini-arquivo um cliente com o `id = 10`? Agora sim, percebemos o quão importantes são os dados
a serem utilizados em seu teste: 
		
		public void busca_por_id_deve_funcionar() {
		
			int id = 10;
		
			Cliente res = buscarCliente.porId(id);
			assertThat(res, is(notNullValue()));//epa!!! aqui não pode ser null
		}
