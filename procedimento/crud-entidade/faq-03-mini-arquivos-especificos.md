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
funcionalidade de um filtro de uma consulta SQL, mas infelizmente o único mini-arquivo existente para
esta consulta possui apenas um registro, o que acabaria deixando o teste incompleto por conta da falta
de dados a serem testados, com isso não temos nenhuma garantia de que o filtro funcionaria da maneira
correta.

Em casos como esse a solução seria criar um mini-arquivo específico, simplesmente por que, é inviável 
confiar em um teste de um recurso como um filtro que possui pouquíssimos a serem testados e mais
não há nenhum tipo de garantia da funcionalidade do recurso. 

### Crie o mini-arquivo

Para criar um mini-arquivo basta criar um  novo arquvo xml no diretório `src/test/resources/dbunit`, 
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

### Criando a classe do mini-arquivo

Sempre que um mini-arquivo for criado é necessário criar uma classe que represente este 
mini-arquivo, que devera ser análoga a esta:

    public class MiniPetShopModificadoXML extends DataSupplier {

        @Override
         public String getFilename() {
             return "mini-pet-shop-modificado.xml";
        }

    }
    
Assim concluímos a criação de nosso mini-aquivo.

### Chamando o novo mini-arquivo em seu teste

Para chamar o mini-arquivo recém criado basta utilizar o DBUnit declarado em sua classe de teste:

    @Inject
    private DBUnit dbUnit;

    @BeforeClass
    public void prepararClasse() {
        dbUnit.loadDefaultDataSet();
        dbUnit.load(new MiniPetShopModificadoXML());
    }
		