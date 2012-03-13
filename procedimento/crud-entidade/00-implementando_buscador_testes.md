---
layout: post-alpha
title: "Implementando Buscador: Testes"
author: "Caio C. Petreanu"
published: true
partof: procedimento-crud-entidade
num: 0
---

# Introdução<a id="topo"> </a>

Qualquer aplicação que busque por informações em uma base persistente precisa implementar o conceito do que chamamos de Buscador.

# Especificação

<div class="alert alert-block">Perguntas cujas respostas você deve saber extrair para implementar o Teste. Sem elas a probabilidade da implementação ser um fracasso ou de baixa qualidade será alta. </div>

## Checklist

<table class="table table-striped">
 <tr>
   <td><a id="topo_0_0"><input type="checkbox" /></a></td>
   <td>Quais os atributos de Funcionário que devemos testar?</td>
   <td><a href="#0_0">help!</a></td>
 </tr>
 <tr>
   <td><a id="topo_0_1"><input type="checkbox" /></a></td>
   <td>Quais métodos de busca haverão em BuscarFuncionario?</td>
   <td><a href="#0_1">help!</a></td>
 </tr>
</table>

## Passo-a-passo

### Quais<a id="0_0"> </a>os atributos de Funcionário que devemos testar?

Busque valores dos atributos no <a href="#mini_0">mini-projeto.xml</a> (nome padrão).

E, se necessário, na sua Interface:

      public interface Funcionario {
    
        int getId();
    
        String getMatricula();
    
        String getNome();
    
        LocalDate getDataNascimento();
    
        DateTime getDataAdmissao();
        
        DateTime getDataDemissao();
    
      }

Portanto, Funcionário conhece:

      int id;
      int matricula;
      String nome;
      LocalDate dataNascimento;
      DateTime dataAdmissao;
      DateTime dataDemissao;

<p><a href="#topo_0_0">Voltar</a></p>

### Quais<a id="0_1"> </a>métodos de busca haverão em BuscarFuncionario?

Verifique a especificação. Neste caso, existem dois buscadores elegíveis: porId() e porMatricula().

<p><a href="#topo_0_1">Voltar</a></p>

# Implementação

<div class="alert alert-block">O procedimento para implementar esses Testes, uma vezs que você tenha as especificações necessárias. </div>

## Checklist

<table class="table table-striped">
 <tr>
   <td><a id="topo_1_0"><input type="checkbox" /></a></td>
   <td><a href="#1_0">Estruturar o código com as Anotações, Buscadores, DBUnit, métodos e funções necessários</a></td>
 </tr>
 <tr>
   <td><a id="topo_1_1"><input type="checkbox" /></a></td>
   <td><a href="#1_1">Implementar o teste responsável por verificar cada atributo do Funcionário</a></td>
 </tr>
 <tr>
   <td><a id="topo_1_2"><input type="checkbox" /></a></td>
   <td><a href="#1_2">Implementar os testes dos demais Buscadores</a></td>
 </tr>
</table>

## Passo-a-passo

### Estruturar<a id="1_0"> </a>o código com as Anotações, Buscadores, DBUnit, métodos e funções necessários

Primeiramente, criamos a classe de Teste.

    public class TesteDeBuscarFuncionario {
    
    }

A partir dela, colocamos nela a notação @Test:

    @Test
    public class TesteDeBuscarFuncionario {
    
    }

E fazemos uma relação com o Módulo __do projeto__ para fazer uso de suas configurações no Framework.

    @Test
    @Guice(modules = { ModuloDeTeste.class })
    public class TesteDeBuscarFuncionario {
    
    }

<div class="alert alert-info">Geralmente, para cada projeto serão utilizados diferentes Módulos. Portanto atente-se a isso quando for implementar seus Testes!</div>

Em seguida, declaramos o Buscador de Funcionário.

    @Test    
    @Guice(modules = { ModuloDeTeste.class })
    public class TesteDeBuscarFuncionario {
    
      @Inject
      private BuscarFuncionario buscarFuncionario;
      
    }

Por fim, decalramos uma instâcia do DBUnit para podermos carregar o DefaultDataSet contendo o arquivo .xml contendo dados necessários de registros nas tabelas no banco de dados.

    @Test    
    @Guice(modules = { ModuloDeTeste.class })
    public class TesteDeBuscarFuncionario {
    
      @Inject
      private BuscarFuncionario buscarFuncionario;
      
      @Inject
      private DBUnit dbUnit;
    
      @BeforeClass
      public void prepararDBUnit() {
        dbUnit.loadDefaultDataSet();
      }
    
    }
    

<p><a href="#topo_1_0">Voltar</a></p>

### Implementar<a id="1_1"> </a>o teste responsável por verificar cada atributo do Funcionário

Para começar, devemos eleger o teste que irá verificar a listagem dos atributos de Funcionário. Utilizando o busca_por_id(), comece declarando um Funcionário recebido pelo Buscador:

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(3);
      }

<div class="alert alert-error">Você verá que buscarFuncionario.porId() não está implementado ainda. Crie a Interface, deixe ela vazia para não dar erros de compilação e vamos em frente!</div>

E, em seguida, faça os asserts para cada atributo. Não dê importância neste momento para o conteúdo deles.

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(3);
    
        assertThat(res.getId(), equalTo(0));
        assertThat(res.getMatricula(), equalTo(null));
        assertThat(res.getNome(), equalTo(null));
        assertThat(res.getDataNascimento(), equalTo(null));
        assertThat(res.getDataAdmissao(), equalTo(null));
        assertThat(res.getDataDemissao(), equalTo(null)));
      }

Por fim, faça __cuidadosamente__ a correspondência com os dados que constam no mini arquivo:

<p><div id="mini_0"> </div></p>

      <?xml version="1.0" encoding="UTF-8"?>
      <dataset>
    
          <!-- Funcionário -->
          <DATABASE.FUNCIONARIO
          ID="1"
          MATRICULA="F0050000"
          NOME="Renato Augusto Machado"
          DATA_NASCIMENTO="1970-01-20"
          ADMISSAO="2001-02-06 18:30"
          DEMISSAO=""
          />
          
          <DATABASE.FUNCIONARIO
          ID="2"
          MATRICULA="F0050001"
          NOME="Priscilla Cardoso"
          DATA_NASCIMENTO="1989-05-29"
          ADMISSAO="2002-02-06 18:30"
          DEMISSAO="2005-09-16 10:20"
          />
          
          <DATABASE.FUNCIONARIO
          ID="3"
          MATRICULA="T0033000"
          NOME="Briann Adams"
          DATA_NASCIMENTO="1980-06-01"
          ADMISSAO="2004-12-10 09:00"
          DEMISSAO="2012-01-03 12:30"
          />
    
      </dataset>
      
Para o teste:

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(3);
    
        assertThat(res.getId(), equalTo(3));
        assertThat(res.getMatricula(), equalTo("T0033000"));
        assertThat(res.getNome(), equalTo("Briann Adams"));
        assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980, 6, 1)));
        assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004, 12, 10, 9, 0)));
        assertThat(res.getDataDemissao(), equalTo(new DateTime(2012, 1, 3, 12, 30)));
      }

<div class="alert alert-block">É importantíssimo certificar-se que os valores nos asserts vão de acordo com as fontes de dados usadas. Sempre revise-os para ter certeza de que está implementando corretamente seus Testes!</div>

<p><a href="#topo_1_1">Voltar</a></p>

### Implementar<a id="1_2"> </a>os testes dos demais métodos do Buscador

Para os demais casos, seguem alguns exemplos detalhados para facilitar o entendimento:

* <a href="#var_0">Variação 1: Buscador para relacionamentos</a>

* <a href="#var_1">Variação 2: Buscadores para métodos que retornam Listas</a>

<p><a href="#topo_1_2">Voltar</a></p>

## Variação 1: Buscador para relacionamentos

Não existe necessidade de testar todas as propriedades das entidades relacionadas.

### Solução<a id="var_0"> </a>A: Usando o Buscador da Classe relacionada:

    @Inject
    private BuscarCinema buscarCinema;
    
    @Inject
    private BuscarIngresso buscarIngresso;
    
    public void busca_por_ingresso() {
        Ingresso ingresso = buscarIngresso.porId(50);
        Cinema cinema = buscarCinema.porIngresso(ingresso);
        
        assertThat(cinema.getId(), equalTo(2));
    }

### Solução B: Usando a Classe Falsa:

      @Inject
      private BuscarCaixaEletronico buscarCaixa;
    
      public void busca_por_supermercado() {
        Supermercado supermercado = SupermercadosFalsos.EXTRA_SAO_CAETANO;
    
        CaixaEletronico caixa = buscarCaixa.porSupermercado(supermercado);
        assertThat(caixa.getId(), equalTo(1001708));
      }

### Solução C: Verificar a busca pelo relacionamento por uma entidade extraída:

      public void busca_por_superior() {
        Funcionario esperado = buscarFuncionario.porId(3);
        Superior superior = esperado.getSuperior();
        
        Funcionario res = buscarFuncionario.porSuperior(superior);
        
        assertThat (res.getSuperior().getId(), equalTo(1));
      }

<p><a href="#1_2">Voltar: Demais Buscadores</a></p>

## Variação<a id="var_1"> </a>2: Buscadores para métodos que retornam Listas

Neste exemplo, Funcionário relaciona-se com Superior. Atualizemos o novo mini-empresa.xml para:

<p></p>

      <?xml version="1.0" encoding="UTF-8"?>
      <dataset>
    
          <!-- Funcionário -->
          <DATABASE.FUNCIONARIO
          ID="1"
          MATRICULA="F0050000"
          NOME="Renato Augusto Machado"
          DATA_NASCIMENTO="1970-01-20"
          SUPERIOR_ID="1"
          ADMISSAO="2001-02-06 18:30"
          />
          
          <DATABASE.FUNCIONARIO
          ID="2"
          MATRICULA="F0050001"
          NOME="Priscilla Cardoso"
          DATA_NASCIMENTO="1989-05-29"
          SUPERIOR_ID="2"
          ADMISSAO="2002-02-06 18:30"
          DEMISSAO="2005-09-16 10:20"
          />
          
          <DATABASE.FUNCIONARIO
          ID="3"
          MATRICULA="T0033000"
          NOME="Briann Adams"
          DATA_NASCIMENTO="1980-06-01"
          SUPERIOR_ID="3"
          ADMISSAO="2004-12-10 09:00"
          DEMISSAO="2012-01-03 12:30"
          />
          
          <!-- Superior -->
          <DATABASE.SUPERIOR
          ID="1"
          POSICAO="M"
          />
          
          <DATABASE.SUPERIOR
          ID="2"
          POSICAO="M"
          />
          
          <DATABASE.SUPERIOR
          ID="3"
          POSICAO="G"
          />
    
      </dataset>

Será portanto adicionado um novo Teste para a nova busca.

      public void busca_por_superior() {
        Superior superior = buscarSuperior.porId(1);
        Funcionario res = buscarFuncionario.porSuperior(superior);
    
        assertThat(res.getId(), equalTo(1));
      }

O uso de _inner_ classes como Funções está vinculado a resultados de _queries_ em que há uma lista de registros, ao invés de apenas um registro.

Digamos que Superior poderá se relacionar com vários Funcionários. E adicionemos um novo funcionário, conforme:

<p></p>

          <DATABASE.FUNCIONARIO
          ID="4"
          MATRICULA="T0033001"
          NOME="Roberto Williams Cardoso"
          DATA_NASCIMENTO="1979-02-02"
          SUPERIOR_ID="2"
          ADMISSAO="2009-12-01 11:10"
          />

Sabendo que na especificação a ordenação será feita pelo FUNCIONARIO.ID asc, o busca_por_superior() será implementado assim::

      @Inject
      private BuscarFuncionario buscarFuncionario;
    
      @Inject
      private BuscarSuperior buscarSuperior;
    
      public void busca_por_superior() {
        Superior superior = buscarSuperior.porId(2);
        List<Funcionario> res = buscarFuncionario.porSuperior(superior);
    
        List<Integer> ids = transform(res, new ToId());
        assertThat(ids.get(0), equalTo(2));
        assertThat(ids.get(1), equalTo(4));
      }
    
      private class ToId implements Function<Funcionario, Integer> {
        @Override
        public int apply(Funcionario input) {
          return input.getId();
        }
     }

<p><a href="#1_2">Voltar: Demais Buscadores</a></p>

O método busca_por_superior() é o responsável por verificar que os Funcionários recebidos do Superior sejam os esperados.

## O que pode estar errado no seu código que o Teste tem por objetivo capturar?

O relacionamento com outras classes, ou o teste dele, não está correto.

Os valores no banco de dados que não coincidem com aqueles nos asserts:

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(1);
    
        assertThat(res.getId(), equalTo(1));
        assertThat(res.getMaticula(), equalTo("T0033000"));
        assertThat(res.getNome(), equalTo("Brian Adamms"));
        assertThat(res.getDataNascimento(), equalTo(1980,6,01));
        assertThat(res.getDataAdmissao(), equalTo(new DateTime(2001,12,10,9)));
        assertThat(res.getDataDemissao(), equalTo(new DateTime(2012,1,3,12,30)));
      }

Erros de conversão (long, double, int, Enum, DateTime, LocalDate, etc):

    rs.getId("FUNCIONARIO.DATA_NASCIMENTO")

Erros no "suffix" usado pelo buscador:

    this.suffix = "SUPRIOR"

Erro de integridade dos dados no banco:

    "FK_FUNCIONARIO_ID = 5" não possui correnspondência em FUNCIONARIO.ID

Erro de acesso aos dados do banco:

    rs.getString("FUNCINARIO_NOME");

Erro de @Inject:

    private Funcionario funcionario;

## Testes Incompletos

São Testes em que propriedades ou colaborações (relacionamentos) não são testados. Exemplo:

    public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(2)

        assertThat(res.getMatricula(), equalTo("F0050001"));
        assertThat(res.getNome(), equalTo("Priscilla Cardoso"));
    }

<p><a href="#topo">Voltar: Topo</a></p>

Seguir em frente? <a href="{{ site.baseurl }}/procedimento/crud-entidade/01-implementando_buscador_buscadores.html" class="btn btn-success">Buscadores!</a>

### Ler códigos!

<table class="table table-striped">
 <tr>
   <td>Exemplo: <a href="https://github.com/objectos/objectos-dojo/blob/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/cpetreanu/TesteDeBuscarFuncionario.java">TesteDeBuscarFuncionario.java</a></td>
 </tr>
 <tr>
   <td>Mini arquivo: <a href="https://github.com/objectos/objectos-dojo/blob/master/objectos-dojo-team/src/test/resources/mini-funcionario.xml">mini-empresa.xml</a></td>
 </tr>
</table>