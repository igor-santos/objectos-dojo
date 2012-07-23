---
layout: post-alpha
title: "Implementando equals e hashCode"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-03-22
published: true 
partof: faq-crud-entidade
num: 4
---

##Introdução

É muito comum ao desenvolver algum tipo de aplicação que interaja com o banco de dados a criação de
classes do tipo entidade que representem algo do mundo real, como por exemplo: pessoas, clientes,
fornecedores, etc.

Lembrando que toda classe do tipo entidade possui atributos e métodos. E dois métodos muito importantes
que uma classe deve ter são: `equals()` e `hashCode()`, aqui mostrarei como utilizar o eclipse para a
criação destes métodos de forma mais legível e organizada.

##Criando equals e hashCode

Vamos começar criando nossa classe do tipo entidade:

    public class Pessoa {

      private final String nome;
      private final String sobrenome;

      public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
      }

      public String getNome() {
        return nome;
      }

      public String getSobrenome() {
        return sobrenome;
      }

    }

Em seguida devemos criar os métodos `equals` e `hashCode`, para isso o eclipse ajuda e bastante na
implementação de ambos. Siga para `Source > generate hashCode and equals...`, em seguida a seguinte 
tela irá aparecer:

![hash-code-equals-eclipse] (http://i1245.photobucket.com/albums/gg582/img_repo/hashCode-equals/hasCode-equals.png)

Para uma melhor distribuição do código marque as opções `Generate method comments`, `Use instanceof to compare types`
e `Use blocks in 'if' statements`, marcando estas três opções que aparecem na imagem acima, a implementação
do equals e do hashCode feita pelo eclipse estará bem mais organizada.