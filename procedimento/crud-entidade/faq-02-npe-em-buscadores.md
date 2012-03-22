---
layout: post-alpha
title: "NPEs em buscadores e consultas"
author: "Marcos Piazzolla"
user: "mpiazzolla"
date: "2012-03-09"
partof: faq-crud-entidade
num: 1
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
    
## Erros comuns

### Foi adicionada a anotação @Guice logo abaixo da anotação @Test ? 

Verifique se a classe de teste está corretamente anotada com `@Guice`  

    @Test
    @Guice(modules = {ModuloDeTesteEmpresaXPTO.class})
    public class TesteDeBuscarCliente {
    }
    
Caso contrário é possível que o seguinte _stacktrace_ apareça

    FAILED CONFIGURATION: @BeforeClass prepararClasse
    java.lang.NullPointerException
    
      at br.com.objectos.dojo.TesteDeBuscarCliente.prepararClasse(TesteDeBuscarCliente.java:42)
      at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)

Conforme o _stacktrace_ a NPE acontece logo no método `@BeforeClass`, na linha 
em que o DBUnit é chamado

    @BeforeClass
    public void prepararDBUnit() {
      dbUnit.loadDefaultDataSet();
    }
 	

### Você adicionou a anotação @Inject logo após declarar o Buscador em seu teste ?
 
Verifique se foi adicionada a anotação `@Inject` antes de declarar o buscador na sua classe de teste

    @Inject
      private BuscarCliente buscarCliente;
		 
      public void busca_por_id_deve_funcionar() {
        int id = 10;
		
        Cliente res = buscarCliente.porId(id);
      }
	
Caso não adicionada a anotação o seguinte _stacktrace_ aparecerá: 

    FAILED: busca_por_id_deve_funcionar
    java.lang.NullPointerException
    
      at br.com.objectos.dojo.TesteDeBuscarCliente.busca_por_id_deve_funcionar(TesteDeBuscarCliente.java:47)
      at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
      at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:56)
 
De acordo com o _stacktrace_ a NPE acontece exatamente quando o buscador é chamado em seus métodos
de testes

      public void busca_por_id_deve_funcionar() {
        int id = 10;

        Cliente res = buscarCliente.porId(id);
 
Lembrando que uma das causas das NullPointers é tentar acessar propriedades de uma instância onde seu
valor é _null_: [Atente a primeira das causas no javadoc] (http://docs.oracle.com/javase/6/docs/api/java/lang/NullPointerException.html)
, sem a presença da anotação `@Inject` uma NPE será lançada logo que se chamar o buscador:
  

### Os seus dados de teste estão razoáveis?

Verifique se os dados utilizados em seu teste existem no mini-arquivo

Mini-arquivo

	<?xml version="1.0" encoding="UTF-8"?>
	<dataset>
		<XPT_ELETRONICOS.CLIENTE ID="1"
		 NOME="João Dias" 
		 TELEFONE="1234-5674" />
		 ENDERECO="Rua das Figueiras, 33" 
		 BAIRRO="Limão"
		 CIDADE="São Paulo"
		 
		<XPT_ELETRONICOS.CLIENTE ID="2"
		 NOME="Pedro de Souza" 
		 TELEFONE="7778-9874" />
		 ENDERECO="Avenida das Pedras, 1024" 
		 BAIRRO="Vila Nova"
		 CIDADE="São Paulo"
		
		<XPT_ELETRONICOS.CLIENTE ID="3"
		 NOME="Carlos da Silva" 
		 TELEFONE="8987-5689" />
		 ENDERECO="Rua Torre Azul, 345" 
		 BAIRRO="Vila das Torres"
		 CIDADE="São Paulo"
	</dataset>

Classe de teste

      public void busca_por_id_deve_funcionar() {

        int id = 10;
       
        Cliente res = buscarCliente.porId(id);
        assertThat(res, is(notNullValue()));
     }	

Caso os dados utilizados no teste não existam no mini-arquivo, o seguinte _stacktrace_ aparecerá

    FAILED: busca_por_id_deve_funcionar
    java.lang.NullPointerException
    
      at br.com.objectos.dojo.TesteDeBuscarCliente.busca_por_id_deve_funcionar(TesteDeBuscarCliente.java:62)
      at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
      at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
		
De acordo com o _stacktrace_ a NPE ocorre exatamente na linha onde o resultado do buscador é acessado,
ou seja nos asserts

      public void busca_por_id_deve_funcionar() {
        int id = 10;
        
        Cliente res = buscarCliente.porId(id);
        assertThat(res, is(notNullValue()));
        
        assertThat(res.getId, equalTo(id));
        assertThat(res.getNome(), equalTo("Godofredo Diaz"));
      }

Como não existe nada no mini-arquivo com id especificado no teste o buscador irá retorna _null_, em
seguida os testes irão falhar pois tentam acessar propriedades de algo que é _null_. 
