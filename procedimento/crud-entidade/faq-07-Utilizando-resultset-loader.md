---
layout: post-alpha
title: "Utilizando ResultSet em Loader"
author: "Tiago Aguiar"
user: "TiagoAguiar"
date: 2012-06-21
published: true 
partof: faq-crud-entidade
num: 6
outof: 6
---

## Introdução

Você já deve ter visto vários códigos _Java_ utilizando um objeto _ResultSet_ nos _Loaders_.

	public class ImovelLoader implements ResultSetLoader<Imovel> {
	
	  // Atributos e Construtores
	
	  @Override
	  public Imovel load(ResultSet resultSet) {
	    ResultSetWrapper rs = new ResultSetWrapper(prefix, resultSet);
	    return new Loader(rs).novaInstancia();
	  }
	
	  private class Loader implements Imovel.Construtor {
	
	    private final ResultSetWrapper rs;
	
	    public Loader(ResultSetWrapper rs) {
	      this.rs = rs;
	    }
	
	    @Override
	    public Imovel novaInstancia() {
	      ImovelJPA impl = new ImovelJPA(this);
	      impl.setId(rs.getInt("ID"));
	      return impl;
	    }
	
	    @Override
	    public Cliente getCliente() {
	      ResultSet resultSet = rs.getResultSet();
	      return new ClienteLoader().load(resultSet);
	    }
	
	  }
	
	} 
	
Neste exemplo, utilizamos o `clienteLoader` passando o `resultSet` como argumento para o método
`load()`. Com isto, conseguimos ter um objeto _Cliente_ com seus atributos já populados (de acordo
com o banco de dados).

Há uma outra forma de obter tais atributos. Utilizando um `objetoVazio`. Vejamos:

	class NotificacaoLoader implements ResultSetLoader<Notificacao> {
	
	  @Override
	  public Notificacao load(ResultSet resultSet) throws SQLException {
	    ResultSetWrapper rs = new ResultSetWrapper("NOTIFICACAO", resultSet);
	    return new Loader(rs).novaInstancia();
	  }
	
	  private class Loader implements Notificacao.Construtor {
	
	    private final ResultSetWrapper rs;
	
	    public Loader(ResultSetWrapper rs) {
	      this.rs = rs;
	    }
	
	    @Override
	    public Notificacao novaInstancia() {
	      NotificacaoJdbc impl = new NotificacaoJdbc(this);
	      impl.setId(rs.getInt("ID"));
	      return impl;
	    }
	
	    @Override
	    public Usuario getRemetente() {
	      ResultSet resultSet = this.rs.getResultSet();
	      ResultSetWrapper rs = new ResultSetWrapper("REMETENTE", resultSet);
	
	      int id = rs.getInt("ID");
	      String login = rs.getString("LOGIN");
	      return new UsuarioVazioId(id, login);
	    }
	
	  }
	
	}
	
Note que agora utilizamos o `new UsuarioVazioId(id, login)` ao invés de `new UsuarioLoader().load(resultSet);`	
Desta forma, populamos um objeto com os dados (vindo do banco de dados) `ID` e `LOGIN`, os ÚNICOS
dados que interessaria para esta aplicação.

Os _objetos vazios_ podem ser utilizados em outras aplicações como os _Forms_, por exemplo.

Para melhor entendimento sobre a busca, veja o método (Query) contido no buscador.	
	
	private NativeSql newSelect() {
      return sqlProvider.get()

        .add("select *")
        .add("from BD.NOTIFICACAO as NOTIFICACAO")
        .add("join BD.USUARIO as REMETENTE")
        .add("on NOTIFICACAO.REMETENTE_ID = REMETENTE.ID")

        .andLoadWith(new NotificacaoLoader());
    } 