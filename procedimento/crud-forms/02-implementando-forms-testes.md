---
layout: post
title: "Implementando Forms Update: Testes"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-05"
published: true
partof: procedimento-crud-forms
num: 1
outof: 1
---

## <a id="TOPO"> </a> Introdução
Geralmente quando temos algum cadastro em um site, temos a necessidade de realizar uma atualização
em nossos dados como por exemplo, atualização de endereço, telefone, e-mail e etc.<br> 
Em alguns casos, esta atualização é feita no intuito de corrigir um erro no momento da inserção 
daquele dado.

Esta atualização é feita através de um _form_ semelhante ao de criação. Porém, um pré-requisito para
que este _form_ funcione é ter os dados armazenados em um banco de dados.

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
	<a href="#0_0">Quais dados devem ser atualizados?</a>  
   </td>
 </tr>
</table>

### <a id="0_0"> </a>Quais dados devem ser atualizados?
Utilizando o mesmo exemplo do artigo [Implementando Forms Create: Testes]({{base.url}}/procedimento/crud-forms/00-implementando-forms-testes.html)
onde tinhamos um aluno a ser cadastrado em um determinado curso, atualizaremos os seus dados já 
cadastrados.

Para esta aplicação, atualizaremos apenas o nome do aluno pensando no erro de digitação que possa
ocorrer no momento da criação/adicão do mesmo no sistema.

A atualização também deve estar bem clara na especificação, ou seja, os dados que devem ser alterados
devem, de fato, resolver o problema do cliente. Por exemplo, imagine que alguns alunos possuam
uma bolsa de estudos. E todo ano a faculdade precisa atualizar a _renda do grupo familiar_ a pedido
do _MEC_. É fato que este tipo de dado DEVE estar neste _form_, já uma data de nascimento não, 
porque uma data de nascimento nunca será mudada (lembrando que isto não é uma regra, é um caso de uso
específico, neste caso, da faculdade). 
  
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
    <tr>
   <td class="tac col2em">
    <a id="topo_0_6"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_6">Preparar o método de teste: gravar dados no banco de dados</a>
   </td>
 </tr>
</table>

### <a id="0_3"> </a>Preparar o teste e definir a URL

### <a id="0_4"> </a>Preparar o método de teste: acesso a um usuário não autenticado

### <a id="0_5"> </a>Preparar o método de teste: acesso a um usuário não autorizado
    
### <a id="0_6"> </a>Preparar o método de teste: gravar dados no banco de dados

Para mais informações acesse os códigos nos links abaixo:

[.java]()<br>
[.java]()<br>

Siga para o próximo passo. Os Forms! <a href="{{ site.baseurl }}/procedimento/crud-forms/" class="btn btn-success">Continuar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>