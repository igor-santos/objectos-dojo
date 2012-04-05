---
layout: post
title: "Implementando Forms Create: Testes"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-05"
published: true
partof: procedimento-crud-forms
num: 0
---

## <a id="TOPO"> </a> Introdução
Quase todos os sites possuem páginas que podem ser exibidas somente a usuários cadastrados. Estes
cadastros são realizados através de formulários preenchidos pelo usuário no próprio site que
lhe concederá algumas permissões não disponíveis antes do cadastro como por exemplo, 
consultas da _Nota Fiscal Paulista_ no site da _Secretaria da Fazenda_, realizar compras em um site 
de vendas e até mesmo um _upload_ de seus videos à um site como o _YouTube_.

Os responsáveis por realizarem os cadastros no "site", isto é, no banco de dados, são conhecidos 
aqui como __Forms__. São os _forms_ que gerenciam estes cadastros no banco de dados do sistema.

## Antes de iniciar 
Este item exige conhecimentos sobre:

- [URL](http://pt.wikipedia.org/wiki/URL)
- [SiteBricks](http://sitebricks.org)

## Especificação

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>
   <td class="tac col2em">
    <a id="topo_0_0"><input type="checkbox" /></a>
   </td>
   <td>
	<a href="#0_0">Quais dados devem ser cadastrados?</a>  
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <a id="topo_0_1"><input type="checkbox" /></a>
   </td>
   <td>
	<a href="#0_1">Como seria a URL?</a>  
   </td>
 </tr>
</table>

### <a id="0_0"> </a>Quais dados devem ser cadastrados?
Semelhante ao artigo [Implementando Consultas: Testes]({{ site.baseurl }}/procedimento/crud-entidade/02.0-implementando-consultas-testes.html),
isto dependerá de cada cliente/situação. Podemos ter diversas situações como cadastrar um novo aluno 
em uma universidade, cadastrar um usuário em um _e-commerce_, cadastrar um número de celular para 
concorrer aos prêmios daquele site, entre outras mais. Lembrando que um cadastro talvez exiga 
campos obrigatórios ou não como um nome e CEP respectivamente.

Contudo, os dados que devem ser cadastrados dependerá muito PARA QUE eles servirão futuramente. Por
exemplo:

Precisamos do endereço no cadastro se quisermos comprar um eletrodoméstico pela internet?<br>
R: Sim! Precisamos para realizar a entrega do produto.

Precisamos do endereço no cadastro se quisermos comprar um _E-Book_?<br>
R: Não! Neste caso é necessário/obrigatório o e-mail do cliente para enviar o _link_ do _E-Book_.

Citaremos o exemplo de cadastro de alunos que ingressaram em uma universidade.<br>
Para tal exemplo, cadastraremos os seguintes dados:

- Nome
- Matricula
- Data de criação

Selecionamos estes dados (supondo que já exista a tabela e o banco de dados com estes 
atributos mais o ID), pois através deles os funcionários da universidade poderão realizar futuras 
consultas como: 

- Todos os alunos da universidade em ordem alfabética;
- Alunos com débitos em mensalidades (através da matrícula);
- Alunos que ingressaram na univerdade no ano de 2011.

Nota 1: Não entraremos em detalhes quanto ao endereço, idade, grau de escolaridade, etc.<br>
Nota 2: Os cadastrados deste tipo (aluno, funcionário, livro, etc) são geralmente efetuados por um
administrador do sistema que deve ter permissão/autenticação para estas funções.<br>  

### <a id="0_1"> </a>Como seria a URL?
Ao realizar um cadastrado (preencher o formulário e clicar em _ok_ por exemplo), podemos ter duas 
situações:

1. Redirecionar o usuário para a mesma URL, `universidade/alunos`;
2. Redirecionar o usuário para uma nova URL, `universidade/alunos/alunos_cadastrados`;

A primeira situação seria interessante para realizar um novo cadastro imediatamente.<br>
Já a segunda situação, seria interessante caso já quisessemos visualizar todos os alunos cadastrados
até aquele momento.

Na dúvida, confirme com seu líder de projeto como será definida a URL!
  
## Implementação

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>
   <td class="tac col2em">
    <a id="topo_0_3"><input type="checkbox" /></a>
   </td>
   <td>
	<a href="#0_3">Preparar o teste e definir a URL</a>  
   </td>
 </tr>
  <tr>
   <td class="tac col2em">
    <a id="topo_0_4"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_4">Preparar o método de teste: acesso a um usuário não autenticado</a>
   </td>
 </tr>
   <tr>
   <td class="tac col2em">
    <a id="topo_0_5"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_5">Preparar o método de teste: acesso a um usuário não autorizado</a>
   </td>
 </tr>
</table>

### <a id="0_3"> </a>Preparar o teste e definir a URL
O teste poderá ter diversos métodos para cada situação. Podemos ter métodos de testes para
usuários não autenticado/autorizado (veja logo mais), testes de NÃO gravar dados com login 
existente (no caso de "cadastro de usuário"), testes de NÃO gravar conta corrente 
existente (no caso de um banco), entre outros.

Crie a classe `TesteDeFormDeAlunoCreate` no pacote `ui.api.crud` do diretório de teste de seu
projeto `/src/test/java`

	public class TesteDeFormDeAlunoCreate {
	}
	
Adicione a _Annotation_ `@Test` e torne esta classe uma subclasse de `TesteDeIntegracaoWeb`

	@Test
	public class TesteDeFormDeAlunoCreate extends TesteDeIntegracaoWeb {
	}
	
Importante: Utilize a classe `TesteDeIntegracaoWeb` do projeto correto, ou seja, o mesmo do
`TesteDeFormDeAlunoCreate`, pois haverá várias classes com este mesmo nome!	
	

### <a id="0_4"> </a>Preparar o método de teste: acesso a um usuário não autenticado

### <a id="0_5"> </a>Preparar o método de teste: acesso a um usuário não autorizado

Para mais informações acesse os códigos nos links abaixo:

[TesteDeFormDeAlunoCreate.java]()<br>
[ModuloUniversidadeUI.java]()<br>

Siga para o próximo passo. Os Forms! <a href="{{ site.baseurl }}/procedimento/crud-forms/" class="btn btn-success">Continuar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>