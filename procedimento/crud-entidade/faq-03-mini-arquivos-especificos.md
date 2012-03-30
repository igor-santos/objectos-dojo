---
layout: post-alpha
title: "Mini-Arquivos Específicos"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-03-12
published: true 
partof: faq-crud-entidade
num: 2
---

##Introdução

Algumas vezes ao criar testes de buscar/excluir percebemos que os dados de teste não são suficientes
ou não são razoáveis, um exemplo muito prático disso seria na criação de um teste que verificasse a 
funcionalidade de um filtro em uma consulta SQL, mas infelizmente o único mini-arquivo existente para
esta consulta possui apenas um registro, o que acabaria deixando o teste incompleto por conta da falta
de dados, com isso não temos nenhuma garantia de que o filtro funcionaria da maneira
correta.

Uma alternativa para resolver este tipo de problema seria adicionar mais registros neste mini-arquivo
e realizar o teste do filtro, isso seria o suficiente para garantir a funcionalidade do filtro, o
problema em alterar mini-arquivos, está nos testes que utilizam o mini-arquivo e fazem asserts que
verificam o número de registros na tabela, testes como este irão falhar após a alteração feita no mini-arquivo,
para evitar que o desenvolvedor tenha de corrigir todos estes testes é mais fácil criar um mini-arquivo.

##Crie o mini-arquivo

Para criar um mini-arquivo basta criar um novo arquivo xml no diretório `src/test/resources/dbunit`, 
lembrando que o mini-arquivo deverá ter um nome que indique seu uso, por exemplo:

`mini-petshop-filtro-de-produto.xml` - indica que o mini-arquivo está sendo utilizado em uma
consulta que tenha filtros;

Após criar o mini-arquivo, basta adicionar dados no mesmo, lembrando que não é necessário adicionar inumeras
linhas no novo mini-aquivo, adicione apenas o necessário para satisfazer o teste.

    <?xml version="1.0" encoding="UTF-8"?>
    <dataset>
      <PET_SHOP.PRODUTOS ID="1"
        CODIGO="PROD_A" 
        NOME="Shampoo" 
        FORNECEDOR="ABC" />
      
      <PET_SHOP.PRODUTOS ID="2" 
        CODIGO="PROD_B" 
        NOME="Condicionador" 
        FORNECEDOR="XYZ" />

      <PET_SHOP.PRODUTOS ID="3" 
        CODIGO="PROD_C" 
        NOME="Perfume" 
        FORNECEDOR="XPT" />
    </dataset>

##Crie a classe do mini-arquivo

Sempre que um mini-arquivo for criado é necessário criar uma classe que represente o mesmo, pois quando 
o mini-arquivo for chamado no teste a chamada será feita através da classe que representa o mini-arquivo.
Lembrando que a classe do mini-arquivo deve se encontrar em `src/test/resources/pacote_de_mini_arquivos`,
sua implementação será análoga ao seguinte bloco de código:

    public class MiniPetShopFiltroDeProdutoXML extends DataSupplier {
    
      @Override
      public String getFilename() {
        return "mini-petshop-filtro-de-produto.xml";
      }
      
    }
    
Assim concluímos a criação de nosso mini-aquivo.

##Referencie o novo mini-arquivo em seu teste

Para chamar o mini-arquivo em seu teste basta adicionar no método `prepararDBUnit()` em seu teste a chamada
ao método `load()` e como parâmetro envie uma instância da classe que represente o mini-arquivo recém-criado.

    @Inject
    private DBUnit dbUnit;

    @BeforeClass
    public void prepararDBUnit() {
      dbUnit.loadDefaultDataSet();
      dbUnit.load(new MiniPetShopFiltroDeProdutoXML());
    }

Com isso o mini-arquivo anterior que não tinha um número suficiente de registros para o teste será sobrescrito
pelo mini-arquivo criado anteriormente, possibilitanto que o teste do filtro seja realizado.