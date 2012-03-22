---
layout: post-alpha
title: "Asserts de propriedades compostas do bd no teste"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-03-22
published: true 
partof: faq-crud-entidade
num: 3
outof: 3
---

##Asserts de propriedades compostas do bd no teste

Para o seguite FAQ, considere esta amostra de código abaixo:

Códido java:

    public interface Pessoa {
      int getId;
      
      String getNomeCompleto();
    }

    public class PessoaImpl implements Pessoa {
      private int id;
      private final String nome;
      private final String sobrenome;
      
      Pessoa(int id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
      }
      
      public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
      }
      
      public int getId() {
        return id;
      }
      
      public String getNomeCompleto() {
        return String.format("%s %s", nome, sobrenome);
      }
    }

Dados do mini-arquivo:
    
    <?xml version="1.0" encoding="UTF-8"?>
	  <dataset>
		<EMPRESA_XPTO.PESSOA ID="1"
		 NOME="Alexander" 
		 SOBRENOME="Andersen" /> 
		 
		<EMPRESA_XPTO.PESSOA ID="2" 
		NOME="Willian" 
		SOBRENOME="Smith" />
		
	 </dataset>
    
Repare que os getters das propriedades nome e sobrenome na classe PessoaImpl não existem, assim fica
dificil implentar o assert para estas propriedades na hora do teste.

##Como eu faço o teste sendo que os getters de nome e sobrenome não esxistem?

Muito bem observado, de fato não existe um getNome() ou um getSobrenome(), mas ao observarmos 
atentamente a classe PessoaImpl percebemos que existe um método __getNomeCompleto()__ que retorna
uma String com ambas propriedades que deverão ser verificadas em nosso teste. Logo nosso teste
ficaria desta forma:
    
    @Test
    @Guice(modules = {ModuloDeTesteDePessoas.class})
    public class TesteDeBuscarPessoa() {
      
      @Inject
      private BuscarPessoa buscarPessoa;
      
      @Inject
      private DBUnit dbUnit;
      
      @BeforeClass
      public void carregarDadosDeTeste() {
        dbUnit.loadDefaultDataSet();
      }
      
      public void busca_por_id_deve_funcionar() {
        int id = 1;
        
        Pessoa res = buscarPessoa.porId(id);
        
        assertThat(res.getId, equalTo(1));
        assertThat(res.getNomeCompleto(), equalTo("Alexander Andersen"));
      }
    }
