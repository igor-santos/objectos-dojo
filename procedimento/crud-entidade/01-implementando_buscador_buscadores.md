---
layout: post-alpha
title: "Implementando Buscador: Buscadores"
author: "Caio C. Petreanu"
published: true
partof: procedimento-crud-entidade
num: 1
outof: 1
---

# Introdução<a id="topo"> </a>
<p></p>

Seguimos agora para a implementação do Buscador.

# Especificação

## Checklist

<div class="alert alert-box">Perguntas cujas respostas você precisa se certificar para implementar o Guice com sucesso.</div>

<table class="table table-striped">
 <tr>
   <td><a id="topo_know_0"><input type="checkbox" /></a></td>
   <td>O TesteDeBuscarFuncionario foi devidamente implementado?</td>
   <td><a href="#know_0">help!</a></td>
 </tr>
 <tr>
   <td><a id="topo_know_1"><input type="checkbox" /></a></td>
   <td>Os métodos da Interface são o suficiente, e não mais que o suficiente?</td>
   <td><a href="#know_1">help!</a></td>
 </tr>
</table>

## Passo a passo

### O<a id="know_1"> </a>TesteDeBuscarFuncionario foi devidamente implementado?

### Os<a id="know_1"> </a>métodos da Interface são o suficiente, e não mais que o suficiente?

# Implementação

## Checklist

<table class="table table-striped">
 <tr>
   <td><a id="topo_impl_0"><input type="checkbox" /></a></td>
   <td><a href="#impl_0">Adicionando as notações na Interface (BuscarFuncionario)</a></td>
 </tr>
 <tr>
   <td><a id="topo_impl_1"><input type="checkbox" /></a></td>
   <td><a href="#impl_1">Criar a classe (BuscarFuncionário)Guice</a></td>
 </tr>
 <tr>
   <td><a id="topo_impl_2"><input type="checkbox" /></a></td>
   <td><a href="#impl_2">Declarar o SqlProvider e definí-lo no Construtor</a></td>
 </tr>
 <tr>
   <td><a id="topo_impl_3"><input type="checkbox" /></a></td>
   <td><a href="#impl_3">Implementar o método newSelect()</a></td>
 </tr>
 <tr>
   <td><a id="topo_impl_4"><input type="checkbox" /></a></td>
   <td><a href="#impl_4">Implementar os métodos de Filtro, como públicos</a></td>
 </tr>
 <tr>
   <td><a id="topo_impl_5"><input type="checkbox" /></a></td>
   <td><a href="#impl_5">Verificar se a Inteface está correta</a></td>
 </tr>
 <tr>
   <td><input type="checkbox" /></td>
   <td>Clique <a href="{{ site.baseurl }}/procedimento/crud-entidade/02-implementando_buscador_loaders.html">aqui</a> e vamos implementar o Loader!</td>
 </tr>
</table>

## Passo a passo

### Adicionando<a id="impl_0"> </a>as notações na Interface (BuscarFuncionario)

Primeiramente abra a interface do Buscador, que no nosso caso é BuscarFuncionario:

<p></p>

      public interface BuscarFuncionario {
      
        Funcionario porId(int id);
      
      }

E nela adicione o @ImplementedBy para fazer um _bind_ do Framework do Buscador com o BuscarFuncionarioGuice.

      @ImplementedBy(BuscarFuncionarioGuice.class)
      public interface BuscarFuncionario {
      
        Funcionario porId(int id);
      
      }

Você verá um alerta de erro de compilação. Ele dirá que a classe Guice não tem implementação. Para resolver, siga para o <a href="#impl_1">próximo passo</a>.

<p><a href="#topo_impl_0">Voltar</a></p>

### Criar<a id="impl_1"> </a>a classe (BuscarFuncionário)Guice

<p></p>

Dê um {Ctrl + '1' e ENTER} para criá-la, e vamos na sequência começar a implementar seu código, que por enquanto estará assim:

      public class BuscarFuncionarioGuice {
      
      }

<div class="alert alert-box">As classes Guice serão acessadas hierarquicamente pelo Framework. Portanto, <b>não se esqueça desse detalhe: a classe não será pública, portanto seu escopo deverá ser o seguinte:</b></div>

      class BuscarFuncionarioGuice {
      
      }

<p>E ela fará contrato com BuscarFuncionario</p>

      class BuscarFuncionarioGuice implements BuscarFuncionario {
      
      }

<div class="alert alert-error">Ao terminar a implementação, haverá erros de compilação sugerindo que sejam implementados os métodos na Interface. É necessário seguir essa sugestão, portanto use {CTRL + '1' e ENTER} sobre o nome da classe para escolher essa opção do menu pop-up.</div>

<p><a href="#topo_impl_1">Voltar</a></p>

### Declarar<a id="impl_2"> </a>o SqlProvider e definí-lo no Construtor

<p></p>

Devemos declará-lo da seguinte maneira:

      private final Provider<NativeSql> sqlProvider;

Para depois usando o {ALT + 'S', e 'A'}, criarmos o Construtor da classe. Conforme:

      @Inject
      public BuscarFuncionarioGuice(Provider<NativeSql> sqlProvider) {
        super();
        this.sqlProvider = sqlProvider;
      }

Não se esqueça de remover o __super()__. Fazendo assim, a classe ficará por enquanto desse jeito:

      class BuscarFuncionarioGuice implements BuscarFuncionario {
    
        private final Provider<NativeSql> sqlProvider;
    
        @Inject
        public BuscarFuncionarioGuice_Buscadores(Provider<NativeSql> sqlProvider) {
          this.sqlProvider = sqlProvider;
        }
    
      }

<p><a href="#topo_impl_2">Voltar</a></p>

### Implementar<a id="impl_3"> </a>o método newSelect()

<div class="alert alert-info">Esse método é responsável por promover uma consulta completa, ou seja, com todas as colunas da entidade no Banco de Dados, e com todos os relacionamentos dela.</div>

Começamos implementando a assinatura do método e seu retorno.

<div class="alert alert-error">Atenção: Esse método será <b>privado</b>!</div>

<p></p>

      private NativeSql newSelect() {
        return sqlProvider.get()
    
          .andLoadWith(new FuncionarioLoader());
      }

<div class="alert alert-block">Para dar continuidade ao flow, caso não haja implementação do Loader da classe, <b>apenas crie uma e deixe-a com o mínimo possível de código</b>, como o exemplo abaixo.</div>

      public class FuncionarioLoader implements ResultSetLoader<Funcionario> {
    
        @Override
        public Funcionario load(ResultSet resultSet) throws SQLException {
          return null;
        }
    
      }

<div class="alert alert-block">O Loader deve implementar o ResultSetLoader&lt;?&gt; para ser parametrizado na chamada <b>andLoadWith()</b>. Por isso o mínimo de código possível capaz de não deixar erros de compilação é o código acima.</div>

Depois do Loader, preencheremos a Query inicial:

      private NativeSql newSelect() {
        return sqlProvider.get()
    
          .add("select *")
          .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
    
          .andLoadWith(new FuncionarioLoader());
      }

E fazemos os JOINs (que podem ser INNER, OUTTER, RIGHT ou LEFT) necessários para listar da colunas dos relacionamentos. No nosso caso, será um JOIN _default_ (OUTTER) com Superior.

      private NativeSql newSelect() {
        return sqlProvider.get()
    
          .add("select *")
          .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
    
          .add("join DATABASE.SUPERIOR as SUPERIOR")
          .add("on FUNCIONARIO.SUPERIOR_ID = SUPERIOR.ID")
    
          .andLoadWith(new FuncionarioLoader());
      }

A classe estará agora com essa cara:

      class BuscarFuncionarioGuice implements BuscarFuncionario {
    
        private final Provider<NativeSql> sqlProvider;
    
        @Inject
        public BuscarFuncionarioGuice(Provider<NativeSql> sqlProvider) {
          this.sqlProvider = sqlProvider;
        }
    
        private NativeSql newSelect() {
          return sqlProvider.get()
    
              .add("select *")
              .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
    
              .add("join DATABASE.SUPERIOR as SUPERIOR")
              .add("from FUNCIONARIO.SUPERIOR_ID as SUPERIOR.ID")
    
              .andLoadWith(new FuncionarioLoader());
        }
    
      }

<p><a href="#topo_impl_3">Voltar</a></p>

### Implementar<a id="impl_4"> </a>os métodos de Filtro, como públicos

<p></p>

O método buscarFuncionario.porId() previamente usado na classe TesteDeBuscarFuncionario, será agora transformado via Java em consulta SQL. Devido a implementação da Interface do Buscador, já devemos ter o seguinte bloco:

      @Override
      public Funcionario porId(int id) {
        return null;
      }

<div class="alert alert-info">Perceba que ele já virá com a notação de sobrescrita @Override.</div>

Agora ajuste o retorno dele para um retorno single() da consulta na forma de Funcionario, conforme abaixo.

      @Override
      public Funcionario porId(int id) {
        return newSelect()
    
            .add("where FUNCIONARIO.ID = ?").param(id)
    
            .single();
      }

<div class="alert alert-block">Sabendo que ID é único para cada Registro e Objeto de Funcionário, podemos afirmar que esse filtro nunca retornará uma listagem.</div>

Por fim, é hora de ajustarmos o filtro da consulta newSelect() para o propósito de porId(). Para isso usaremos a cláusula WHERE do SQL:

      @Override
      public Funcionario porId(int id) {
        return newSelect()
    
            .add("where FUNCIONARIO.ID = ?").param(id)
    
            .single();
      }

A classe BuscarFuncionarioGuice está agora com essa implementação:

<a id="guice"> </a>     class BuscarFuncionarioGuice implements BuscarFuncionario {
    
        private final Provider<NativeSql> sqlProvider;
    
        @Inject
        public BuscarFuncionarioGuice(Provider<NativeSql> sqlProvider) {
          this.sqlProvider = sqlProvider;
        }
    
        @Override
        public Funcionario porId(int id) {
          return newSelect()
    
              .add("where FUNCIONARIO.ID = ?").param(id)
    
              .single();
        }
    
        private NativeSql newSelect() {
          return sqlProvider.get()
    
              .add("select *")
              .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
    
              .add("join DATABASE.SUPERIOR as SUPERIOR")
              .add("from FUNCIONARIO.SUPERIOR_ID as SUPERIOR.ID")
    
              .andLoadWith(new FuncionarioLoader());
        }
    
      }

<p><a href="#topo_impl_4">Voltar</a></p>

### Verificar<a id="impl_5"> </a>se a Inteface está correta

Verifique na Interface se os métodos dela correspondem com os <b>métodos públicos</b> implementados no <a href="#guice">Guice</a>.

     @ImplementedBy(BuscarFuncionarioGuice.class)
      public interface BuscarFuncionario {
    
        Funcionario porId(int id);
    
      }

<p><a href="#topo_impl_5">Voltar</a></p>

## Erros Mais Comuns

blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 

## Variação A: .addIf() e o .paramNotNull() nos filtros da Consulta

blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 

## Variação B: Múltiplos Filtros em um único método

blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 

## Variação C: Extraindo parâmetro(s) de Filtro de Objetos recebidos

blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 
blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá 

<p><a href="#topo">Voltar: Topo</a></p>

<p>Vamos seguir em frente? <a href="{{ site.baseurl }}/procedimento/crud-entidade/02-implementando_buscador_loaders.html" class="btn btn-success">Loaders!</a></p>
<p>...Ou voltar? <a href="{{ site.baseurl }}/procedimento/crud-entidade/00-implementando_buscador_testes.html" class="btn btn-success">Testes!</a></p>

### Ler códigos!

<table class="table table-striped">
 <tr>
   <td><a href="">BuscarFuncionario.java</a></td>
   <td><a href="">BuscarFuncionarioGuice.java</a></td>
 </tr>
 <tr>
   <td><a href="">FuncionarioLoader.java</a></td>
   <td><a href="">mini-empresa.xml</a></td>
 </tr>
</table>