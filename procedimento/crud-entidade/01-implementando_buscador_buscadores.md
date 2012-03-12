---
layout: post-alpha
title: "Implementando Buscador: Buscadores"
author: "Caio C. Petreanu"
published: false
partof: procedimento-crud-entidade
num: 1
outof: 1
---

## Introdução<a id="topo"> </a>
<p></p>

>Qualquer aplicação que busque por informações em uma base persistente precisa implementar o conceito do que chamamos de Buscador.

## Checklist

### Especificação

<table class="table table-striped">
 <tr>
   <td><a id="topo_know_0"><input type="checkbox" /></a></td>
   <td><a href="#know_0">O TesteDeBuscarFuncioanario foi devidamente implementado?</a></td>
 </tr>
 <tr>
   <td><a id="topo_know_1"><input type="checkbox" /></a></td>
   <td><a href="#know_1"> etc </a></td>
 </tr>
 <tr>
   <td><a id="topo_know_2"><input type="checkbox" /></a></td>
   <td><a href="#know_2"> etc </a></td>
 </tr>
 <tr>
   <td><a id="topo_know_2"><input type="checkbox" /></a></td>
   <td><a href="#know_2"> etc </a></td>
 </tr>
 <tr>
   <td><a id="topo_know_3"><input type="checkbox" /></a></td>
   <td><a href="#know_3"> etc </a></td>
 </tr>
 <tr>
   <td><a id="topo_know_4"><input type="checkbox" /></a></td>
   <td><a href="#know_4"> etc </a></td>
 </tr>
</table>

### Implementação

<table class="table table-striped">
 <tr>
   <td><a id="topo_impl_0"><input type="checkbox" /></a></td>
   <td><a href="#impl_0">Declarar a classe que implementa o Buscador</a></td>
 </tr>
 <tr>
   <td><a id="topo_impl_1"><input type="checkbox" /></a></td>
   <td><a href="#impl_1">Gerar a Interface no mesmo pacote</a></td>
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
   <td><a href="#impl_4">Implementar os demais métodos filtros como públicos</a></td>
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

### Ler códigos!

<table class="table table-striped">
 <tr>
   <td><a href="">Exemplo completo</a></td>
 </tr>
</table>

## Passo a passo

+ Declarar<a id="impl_0"> </a>a classe que implementa o Buscador

<p></p>

      class BuscarFuncionarioGuice implements BuscarFuncionario {
      
      }

<p><a href="#topo_impl_0">Voltar</a></p>

+ Gerar<a id="impl_1"> </a>a Interface no mesmo pacote

<p></p>

      @ImplementedBy(BuscarFuncionarioGuice.class)
      public interface BuscarFuncionario {
      
        Funcionario porId(int id);
      
        Funcionario porCodigo(String codigo);
        
      }

<p><a href="#topo_impl_1">Voltar</a></p>

+ Declarar<a id="impl_2"> </a>o SqlProvider e definí-lo no Construtor

<p></p>

      private final Provider<NativeSql> sqlProvider;
      
      @Inject
      public BuscarFuncionarioGuice(Provider<NativeSql> sqlProvider) {
        this.sqlProvider = sqlProvider;
      }

<p><a href="#topo_impl_2">Voltar</a></p>

+ Implementar<a id="impl_3"> </a>o método newSelect()

Para dar continuidade ao _flow_, caso não haja implementação do Loader da classe, apenas crie uma e deixe-a vazia.

      private NativeSql newSelect() {
        return sqlProvider.get()
      
          .add("select *")
          .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
      
          .add("join DATABASE.SUPERIOR as PLANO")
          .add("on FUNCIONARIO.SUPERIOR_ID = SUPERIOR.ID")
      
          .andLoadWith(new FuncionarioLoader());
      }

<p><a href="#topo_impl_3">Voltar</a></p>

+ Implementar<a id="impl_4"> </a>os demais métodos de filtros como públicos

<p></p>

      @Override
      public Funcionario porId(int id) {
        return newSelect()
      
            .add("where FUNCIONARIO.ID = ?").param(id)
      
            .single();
      }
      
            @Override
      public Funcionario porCodigo(String codigo) {
        return newSelect()
      
            .add("where FUNCIONARIO.CODIGO = ?").param(codigo)
      
            .single();
      }

<p><a href="#topo_impl_4">Voltar</a></p>

+ Verificar<a id="impl_5"> </a>se a Inteface está correta

<p><a href="#topo_impl_5">Voltar</a></p>

## Exemplo Completo

Interface: BuscarEntidade

      @ImplementedBy(BuscarEntidadeGuice.class)
      public interface BuscarEntidade extends Buscador<Entidade> {
    
        Entidade porKey(EntidadeKey key);
    
      }

Guice: BuscarEntidadeGuice

      class BuscarEntidadeGuice implements BuscarEntidade {
    
        private final Provider<NativeSql> sqlProvider;
    
            @Inject
                public BuscarEntidadeGuice(Provider<NativeSql> sqlProvider) {
                  this.sqlProvider = sqlProvider;
                }
    
            @Override
            public Entidade porId(Serializable id) {
              return newSelect()
    
                  .add("where ENTIDADE.ID = ?").param(id)
    
                  .single();
            }
    
            @Override
            public Entidade porKey(EntidadeKey key) {
              String codigo = key.getCodigo();
              TipoDeEntidade tipo = key.getTipo();
    
              return newSelect()
    
                  .add("where ENTIDADE.TIPO = ?").param(tipo.toSqlValue())
                  .add("and ENTIDADE.CODIGO = ?").param(codigo)
    
                  .single();
            }
    
            private NativeSql newSelect() {
              return sqlProvider.get()
    
                  .add("select *")
                  .add("from RO_ORG.ENTIDADE as ENTIDADE")
    
                  .andLoadWith(new EntidadeLoader());
            }
    
          }

Loader: na continuação deste

## Erros Mais Comuns

<p><a href="#topo">Voltar: Topo</a></p>

<p>Vamos seguir em frente? <a href="{{ site.baseurl }}/procedimento/crud-entidade/02-implementando_buscador_loaders.html" class="btn btn-success">Implementando Loaders!</a></p>
<p>Ou voltar? <a href="{{ site.baseurl }}/procedimento/crud-entidade/00-implementando_buscador_testes.html" class="btn btn-success">Implementando Testes!</a></p>
