---
layout: post-alpha
title: "Mini-Arquivos especifícos"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: 2012-03-12
published: true 
partof: faq-crud-entidade
num: 2
outof: 2
---

## Mini-Arquivos específicos

Algumas vezes ao criar testes de buscar/excluir percebemos que os dados de teste não são suficientes
para que se possa testar o recurso de busca/exclusão que foi implementado, logo é preciso adicionar
mais informações no mini-arquivo. Ao fazer isso alguns testes que utilizam o mini-arquivo alterado
irão falhar por conta das alterações realizadas.

 Uma boa solução para resolver este tipo de problema seria criando um novo mini-arquivo e utilizar 
este mini-arquivo em seu teste.

### Criando mini-arquivos

Para criar um mini-arquivo basta criar um novo arquivo xml no diretório `src/test/resources/dbunit`, 
nosso mini-arquivo será bem parecido com este :

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

    public class MiniPetShopModificado extends DataSupplier {

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
        dbUnit.load(new MiniPetShopModificado());
    }
		