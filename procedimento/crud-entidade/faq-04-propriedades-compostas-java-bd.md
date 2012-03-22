---
layout: post-alpha
title: "Implementado Buscadores: Propriedades Compostas"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-03-22
published: true 
partof: faq-crud-entidade
num: 3
outof: 3
---

##Introdução

Sempre que um teste é implementado é de suma importância a verificação de todas as propriedades que
vem do banco de dados, existem alguns <a href="{{ site.baseurl }}/procedimento/crud-entidade/01.0-implementando_buscador_testes.html#0_0">casos</a>
onde o inevitável acontece e logo estamos em uma situação onde não é possível realizar um assert
para uma propriedade em particular pois a interface desta entidade não possui um getter para esta
propriedade, o exemplo abaixo retrata esta situação com o método __getNomeCompleto()__.

##Códigos de Amostra

Para este FAQ, considere as listagens seguintes.

Códido Java:

    public interface Pessoa {
      int getId();  
      
      String getNomeCompleto();
    }  
    
<br />

    public class PessoaJdbc implements Pessoa {
      private int id;
      private final String nome;
      private final String sobrenome;
      
      public PessoaJdbc(int id, String nome, String sobrenome) {
        this.id = id;
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
    
mini-arquivo:
    
    <?xml version="1.0" encoding="UTF-8"?>
      <dataset>
        <DATABASE.PESSOA ID="1"
          NOME="Alexander" 
          SOBRENOME="Andersen" /> 
	  	 
        <DATABASE.PESSOA ID="2" 
          NOME="Willian" 
          SOBRENOME="Smith" />
      </dataset>  

##Implementando o teste

Repare que não existem getters para as propriedades `nome` e `sobrenome` em Pessoa. Sem eles a 
possibilidade de realizar um assert para estas propriedades é muito baixa, correto?

Realmente não existe um `getNome()` ou um `getSobrenome()`, mas ao observarmos atentamente a implementação
de Pessoa percebemos que existe um método chamado __getNomeCompleto()__ que retorna uma String.

Mas como esta String irá nos ajudar a fazer o nosso teste?
  
Se observarmos a implementação do método __getNomeCompleto__ logo de cara percebemos uma chamada ao 
método [format](http://docs.oracle.com/javase/6/docs/api/java/lang/String.html#format%28java.lang.String,%20java.lang.Object...%29)
da classe String, com ele é possível concatenar (juntar) duas Strings.

Concluindo, toda vez que o método __getNomeCompleto()__ for chamado em nosso teste estamos testando
indiretamente as propriedades `nome` e `sobrenome` de Pessoa que estão no BD. Depois de concluirmos o 
que o método __getNomeCompleto__ faz, podemos dar continuidade ao nosso teste.

      public void busca_por_id_deve_funcionar() {
        int id = 1;
        
        Pessoa res = buscarPessoa.porId(id);
        
        assertThat(res.getId, equalTo(1));
        assertThat(res.getNomeCompleto(), equalTo("Alexander Andersen"));
      }

Agora sim! Tudo está mais claro, incluindo o nosso teste, repare que ao efetuarmos uma busca por id, um
objeto do tipo Pessoa virá do banco de dados, lembrando que o mesmo terá um `nome` e um `sobrenome`.
Para acessar seu nome completo basta chamar __getNomeCompleto()__.