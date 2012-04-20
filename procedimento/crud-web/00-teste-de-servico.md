---
layout: post-alpha
title: "Teste de Serviço"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-20"
published: true
partof: procedimento-crud-web
num: 0
outof: 0
---

##<a id="topo"> </a> Introdução

Serviços são componentes importantes responsáveis em listar na tela do usuário  os resultados de
uma determinada consulta sql, ou seja o usuário clica em um link e em seguida é redirecionado para
uma página de listagem de uma entidade, o componente responsável pela exibição dos dados na tela é
uma classe conhecida como Serviço.

## Acesso rápido

Para acessar os tópicos do artigo siga o checklist abaixo:

<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      Implementando a classe de teste
    </td>
    <td>
      <a href="#0_0">help!</a>
    </td>    
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Definindo uma URL
    </td>
    <td>
      <a href="#0_1">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      Criando os métodos de teste
    </td>
    <td>
      <a href="#0_2">help!</a>
    </td>
  </tr>
</table>

##<a id="0_0"> </a> Implementando a classe de teste

Antes mesmo de começar deve-se pensar em o que deve ser testado. O objetivo deste teste é verificar
se um determinado usuário do sistema pode ou não acessar a listagem de alguma entidade no sistema,
comece criando a classe de teste no pacote __br.com.pacote.ui.api__ e não se esqueça de extender o
__TesteDeIntegracaoWeb__.

	@Test
	public class TesteDeServicoDeAluno extends TesteDeIntegracaoWeb {
		
	}
<div class="alert alert warning">
	Dica: Cada projeto possui um TesteDeIntegracaoWeb pronto, portanto não se preocupe em ter que
	implementá-lo!
</div>

###<a id="0_1"> </a> Definindo uma URL

Para que seja possível acessar o serviço é preciso definir uma URL no teste que redirecione o teste
ao serviço, como neste teste serão listados Alunos de um Curso, basta definir uma URL que nos 
informe quantos Aluno existem em um Curso, por exemplo:

	private static final String URL = "api/bd/faculdade/curso/matematica";

Para poder criar esta URL analize o mini-arquivo do projeto e veja quais cursos existem no mesmo,
escolha cursos que tenham mais de um registro.

<div class="alert alert warning">
	Não se esqueça de que o Curso definido na URL será 	capturado e utilizado como parâmetro de uma
	consulta para encontrar os Alunos deste Curso, por isso cuidado na escolha de seus dados!
</div>

Após as alterações acima abra o módulo de seu projeto: __ModuloFaculdadeUI__ e adicione no método 
`bindApi()` a url definida no teste

	private void bindApi() {
	  at("/api/bd/faculdade/curso/:curso").serve(ServicoDeAluno.class);
	}

Como Curso é um dado que pode variar constantemente, definimos um caracter coringa para Curso, atente
a sua sintaxe: `curso/:curso`, sempre que for preciso definir um caracter coringa utilize a sintaxe 
acima.

Um erro de compilação surgirá dizendo que a classe __ServicoDeAluno__ não existe, crie a mesma no
diretório `scr/main/java` no pacote `br.com.faculdade.ui.api`.

<div class="alert alert warning">
	Dica: Diferente do teste aqui é preciso adicionar a barra no início da url para evitar erros na
	hora de executar o teste.
</div>

##<a id="0_2"> </a> Criando os métodos de teste

Agora que definimos uma url que contém uma série de Alunos, podemos dar início ao teste em si, crie
o primeiro método que verifica se um usuário não autenticado pode acessar a listagem de Alunos de um
Curso.

	public void usuario_nao_autenticado_nao_deve_listar() {
	  WebResponse response = webClienteOf(URL).get();
	  
	  assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
	}

Em seguida será preciso criar o teste que verifica se um usuário autenticado pode acessar a listagem
de Alunos de um Curso e qual será o resultado esperado desta listagem, atente ao método.

	public void usuario_autenticado_deve_listar(){
	  Map<String, String> cookies = login("faculdade");
	  
	  WebResponse response = webClientOf(URL, cookies).get();
	  assertThat(response.status(), equalTo(HttpServletResponse.OK));
	  
	}

Atenção ao teste de usuário autenticado, aqui é preciso informar o login do usuário para que seja 
realizado o processo de autenticação, sempre que realizar um teste de serviço o login terá o nome
do projeto.

De volta ao teste logo abaixo do `assert` realizado verifique se as informações da listagem obtidas
através da __ConsultaDeAluno__ estão de acordo com as informações do mini-arquivo.

    WebResponse response = webClientOf(URL, cookies).get();
    assertThat(response.status(), equalTo(HttpServletResponse.SC_OK));

	List<String> res = new ToTableRow(response).get();
	assertThat(res.size(), equalTo(4));
	assertThat(res.get(0), equalTo("Nome Matrícula"));
	assertThat(res.get(1), equalTo("Isaac Newton 1234"));
	assertThat(res.get(2), equalTo("Gauss Seidel 5678"));
	assertThat(res.get(3), equalTo("Siméon Poisson 91011"));

<p>Leia mais uma vez! <a href="#topo" class="btn btn-warning">Revisar!</a></p>

<p> Seguir em frente ? <a href="{{ site.baseurl }}/procedimento/crud-web/01-implementando-servico.html" 
class="btn btn-success">Implementando Serviços</a></p> 