---
layout: post
title: "Implementando Forms Update: Testes"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-05"
published: true
partof: procedimento-crud-forms
num: 2
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

### <a id="0_0"> </a>Quais dados devem ser atualizados?
Utilizando o mesmo exemplo do artigo [Implementando Forms Create: Testes]({{base.url}}/procedimento/crud-forms/00-implementando-forms-testes.html)
onde tinhamos um aluno a ser cadastrado em um determinado curso, atualizaremos os seus dados já 
cadastrados.

Para esta aplicação, atualizaremos apenas o nome do aluno pensando no erro de digitação que possa
ocorrer no momento da criação/adição do mesmo no sistema (isto é pode ocorrer com nomes semelhantes
como "Souza" e Sousa", "Luiz" e "Luis" e etc.

A atualização também deve estar bem clara na especificação, ou seja, os dados que devem ser alterados
devem, de fato, resolver o problema. Por exemplo, imagine que alguns alunos possuam
uma bolsa de estudos. E todo ano a faculdade precisa atualizar a _renda do grupo familiar_ a pedido
do _MEC_. É fato que este tipo de dado DEVE estar neste _form_, já uma data de nascimento não, 
porque uma data de nascimento nunca será mudada (lembrando que isto não é uma regra, é um caso de uso
específico da faculdade).

### <a id="0_1"> </a>Como seria a URL?
Geralmente quando fazemos uma atualização de uma entidade, é comum redirecionar a URL a mesma página,
assim podemos visualizar o resultado, ou seja, como ficou a entidade após a atualização feita no 
_form_.

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
Da mesma forma do _Form Create_, criaremos a classe `TesteDeFormDeAlunoUpdate` no pacote 
`ui.api.crud` do diretório de teste do projeto `/src/test/java` e adicione o `@Test`

	@Test
	public class TesteDeFormDeAlunoUpdate {
	}
	
Torne-a uma subclasse de `TesteDeIntegracaoWeb`

	@Test
	public class TesteDeFormDeAlunoUpdate extends TesteDeIntegracaoWeb {
	}
	
Defina a URL a partir de faculdade. Desta vez, iremos indicar o `ID` do aluno que receberá as 
atualizações.

	private static final String URL = "api/crud/faculdade/curso/direito/aluno/11";
	
### <a id="0_4"> </a>Preparar o método de teste: acesso a um usuário não autenticado
Idêntico ao método do _Form Create_ e com o mesmo objetivo.

    public void acesso_a_usuario_nao_autenticado_deve_ser_negado() {
      WebResponse response = webClientOf(URL).put("");

      assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
    }

### <a id="0_5"> </a>Preparar o método de teste: acesso a um usuário não autorizado
Mais um método idêntico ao _Form Create_.

	public void acesso_a_usuario_nao_autorizado_deve_ser_negado() {
	  Map<String, String> cookies = login("user");
	  WebResponse response = webClientOf(URL, cookies).put("");
	
	  assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
	}
    
### <a id="0_6"> </a>Preparar o método de teste: gravar dados no banco de dados
O processo de criação deste método que tem por objetivo atualizar uma entidade no banco de dados é
um pouco mais simples. Geralmente, busca-se a entidade pelo id e a partir desta informação,
podemos alterar qualquer valor contido nela, mas lembre-se, isto não é uma regra que se atribui a
todas as situações.<br>
Defina um _buscador de aluno_ no início da classe.

	@Test
	public class TesteDeFormDeAlunoUpdate extends TesteDeIntegracaoWeb {
	
	  @Inject
	  private BuscarAluno buscarAluno;
	
	}
	
Crie o método `form_deve_gravar_aluno_no_bd()`, busque um aluno por um id, neste caso será 11 e faça
um _assert_ para verificar se este aluno existe.

	public void form_deve_gravar_aluno_no_bd() {
	  int id = 11;
	  
	  Aluno antes = buscarAluno.porId(id);
	  assertThat(antes, is(notNullValue()));
	}

Agora podemos atualizar os dados que, por sinal, os códigos são parecidos ao _Form Create_.

    public void form_deve_gravar_aluno_no_bd() {
      int id = 11;
      String nome = "Luiz de Souza";

      Aluno antes = buscarAluno.porId(id);
      assertThat(antes, is(notNullValue()));

      String url = new QueryString(URL)
          .param("nome", nome)
          .get();

      Map<String, String> cookies = login("admin");
      WebResponse response = webClientOf(url, cookies).put("");

      FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
      assertThat(json.isValid(), is(true));

      Aluno res = buscarAluno.porId(id);
      assertThat(res.getNome(), equalTo(nome));

      String redirectUrl = json.getRedirectUrl();
      assertThat(redirectUrl, containsString("faculdade/curso/direito/aluno/11"));
    }

Conforme comentamos, atualizaremos apenas o campo _nome_, mas você poderá atualizar outros valores
para testá-los se necessário.

Defina a URL no método `bindApiCrud()` da classe `ModuloFaculdadeUI`

	@Override
	protected void bindApiCrud() {
	  at("api/crud/faculdade/curso/:curso/aluno").serve(FormDeAlunoCreate.class);
	  at("api/crud/faculdade/curso/:curso/aluno:aluno").serve(FormDeAlunoUpdate.class);
	}
	
Note que haverá erros de compilação pois a classe `FormDeAlunoUpdate` ainda não existe. Utilize o
atalho `Ctrl + 1` e crie esta classe no pacote `ui.api.crud` do diretório principal do projeto 
`/src/main/java`.

Implementaremos o _Form_ a seguir.	

Para mais informações acesse os códigos nos links abaixo:

[TesteDeFormDeAlunoUpdate.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/TesteDeFormDeAlunoUpdate.java)<br>
[ModuloFaculdadeUI.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/ModuloFaculdadeUI.java)<br>

Siga para o próximo passo. O Form! <a href="{{ site.baseurl }}/procedimento/crud-forms/" class="btn btn-success">Continuar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>