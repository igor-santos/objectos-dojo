---
layout: post-alpha
title: "Implementando Buscador: Testes"
author: "Caio C. Petreanu"
published: true
partof: procedimento-crud-entidade
num: 0
---

## Introdução<a id="topo"> </a>

Fazer um passo a passo com breves explicações, códigos, erros mais comuns e Checklist para a construção de Testes de Buscadores.

## Checklist

### Especificação

<table class="table table-striped">
 <tr>
   <td><a id="topo_0_0"><input type="checkbox" /></a></td>
   <td><a href="#0_0">Quais os atributos de Funcionário que devemos testar?</a></td>
 </tr>
 <tr>
   <td><a id="topo_0_1"><input type="checkbox" /></a></td>
   <td><a href="#0_1">Quais métodos de busca haverão em BuscadorFuncionario?</a></td>
 </tr>
</table>

### Implementação

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

### Ler códigos!

<table class="table table-striped">
 <tr>
   <td><a href="">Exemplo completo: Um Funcionário por Superior</a></td>
 </tr>
 <tr>
   <td><a href="">Exemplo completo: Muitos Funcionários por Superior</a></td>
 </tr>
 <tr>
   <td><a href="">Mini arquivo: mini-empresa.xml</a></td>
 </tr>
</table>

## Passo a passo

+ Quais<a id="0_0"> </a>os atributos de Funcionário que devemos testar?

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
          DEMISSAO=""
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

E também na sua Interface:

      public interface Funcionario {
    
        String getMatricula();
    
        String getNome();
    
        LocalDate getDataNascimento();
    
        Superior getSuperior();
        
        DateTime getDataAdmissao()
        
        DateTime getDataDemissao()
    
      }

Portanto Funcionário conhece:

      int id;
      int matricula;
      String nome;
      LocalDate dataNascimento;
      Superior superior;
      DateTime dataAdmissao;
      DateTime dataDemissao;

<p><a href="#topo_0_0">Voltar</a></p>

+ Quais<a id="0_1"> </a>métodos de busca haverão em BuscadorFuncionario?

Verifique a especificação. Neste caso, existem dois buscadores elegíveis: porId() e porMatricula().

<p><a href="#topo_0_1">Voltar</a></p>

+ Estruturar<a id="1_0"> </a>o código com as Anotações, Buscadores, DBUnit, métodos e funções necessários

Para estruturar o teste fazemos uso dos seguintes código:

    @Test    
    @Guice(modules = { ModuloDeTeste.class })
    public class TesteDeBuscarFuncionario {
    
    }

Primeiro fazemos o link com o Módulo para usar de seus recursos e estrutura definidos, que serão necessários para realizar o teste (banco de dados ou arquivos .xml, autenticação no banco, etc).  

    @Test    
    @Guice(modules = { ModuloDeTeste.class })
    public class TesteDeBuscarFuncionario {
    
      @Inject
      private BuscarFuncionario buscarFuncionario;
      
    }


Na sequência, declaramos (e usamos o Injector para associar ao SiteBricks) os Buscadores necessários de Funcionario e de suas dependências (neste caso seu relacionamento com Superior).

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
    

Por fim, "injetamos" uma instâcia do DBUnit para podermos carregar o DefaultDataSet contendo o arquivo .xml contendo dados necessários de registros nas tabelas no banco. Caso o teste utilize apenas FuncionariossFalso e SuperioresFalso, não haverá necessidade deste trecho.

<p><a href="#topo_1_0">Voltar</a></p>

+ Implementar<a id="1_1"> </a>o teste responsável por verificar cada atributo do Funcionário

Para começar, devemos eleger o teste que irá verificar a listagem dos atributos de Funcionário. Utilizando o busca_por_id(), comece declarando um funcionário recebido pelo Buscador:

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(3);
      }

Na sequencia, faça os asserts para cada atributo. Não dê importância ainda para o conteúdo deles.

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(3);
    
        assertThat(res.getId(), equalTo(0));
        assertThat(res.getMaticula(), equalTo(null));
        assertThat(res.getNome(), equalTo(null));
        assertThat(res.getDataNascimento(), equalTo(null));
        assertThat(res.getSuperior().getId(), equalTo(0));
        assertThat(res.getDataAdmissao(), equalTo(null));
        assertThat(res.getDataDemissao(), equalTo(null)));
      }

Por fim, faça __cuidadosamente__ a correspondência com os dados que constam no mini arquivo.

      public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(3);
    
        assertThat(res.getId(), equalTo(3));
        assertThat(res.getMaticula(), equalTo("T0033000"));
        assertThat(res.getNome(), equalTo("Briann Adams"));
        assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980,6,01)));
        assertThat(res.getSuperior().getId(), equalTo(3));
        assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004,12,10,9)));
        assertThat(res.getDataDemissao(), equalTo(new DateTime(2012,1,3,12,30)));
      }

1. Você verá que buscarFuncionario.porId() não está implementado ainda. Crie a classe, deixe ela vazia para não dar erros de compilação e vamos em frente!
1. É importantíssimo se certificar que os valores nos asserts vão de acordo com as fontes de dados usadas. Sempre revise-os para ter certeza de que está implementando-os corretamente em todos os Testes!

<p><a href="#topo_1_1">Voltar</a></p>

+ Implementar<a id="1_2"> </a>os testes dos demais Buscadores

Para os demais casos, seguem alguns exemplos detalhados para facilitar seu entendimento:

* <a href="#var_0">Variação 1: Buscador para relacionamentos</a>

* <a href="#var_1">Variação 2: Buscadores com relacionamento que retornam Listas</a>

<p><a href="#topo_1_2">Voltar</a></p>

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
        assertThat(res.getSuperior().getId(), equalTo(3));
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

Exemplo:

    public void busca_por_id() {
        Funcionario res = buscarFuncionario.porId(2)

        assertThat(res.getMatricula(), equalTo("F0050001"));
        assertThat(res.getNome(), equalTo("Priscilla Cardoso"));

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

### Solução C: Verificar por alguma propriedade do Funcionário relacionado:

      public void busca_por_superior() {
        Superior superior = buscarSuperior.porId(3);
        Funcionario esperado = buscarFuncionario.porSuperior(superior);
        Funcionario buscado = buscarFuncionario.porId(3);
        Superior superiorId = buscado.getSuperior();
    
        assertThat (esperado.getSuperior().getId(), equalTo(superiorId));
      }

<p><a href="#1_2">Voltar: Demais Buscadores</a></p>

## Variação<a id="var_1"> </a>2: Buscadores com relacionamento que retornam Listas

Neste exemplo, Funcionário relaciona-se com Superior. Será feito portanto um teste para o Buscador.

      public void busca_por_superior() {
        Superior superior = buscarSuperior.porId(1);
        Funcionario res = buscarFuncionario.porSuperior(superior);
    
        assertThat(res.getId(), equalTo(1));
    }

O uso de inner classes como Funções está vinculado a resultados de queries em que há uma lista de registros, ao invés de apenas um registro.

Digamos que Superior poderá se relacionar com vários Funcionários. E adicionemos um novo funcionário, conforme:

          <DATABASE.FUNCIONARIO
          ID="4"
          MATRICULA="T0033001"
          NOME="Roberto Williams Cardoso"
          DATA_NASCIMENTO="1979-02-02"
          SUPERIOR_ID="2"
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

<p><a href="#topo">Voltar: Topo</a></p>

Vamos seguir em frente? <a href="{{ site.baseurl }}/procedimento/crud-entidade/01-implementando_buscador_buscadores.html" class="btn btn-success">Implementando Buscadores!</a>