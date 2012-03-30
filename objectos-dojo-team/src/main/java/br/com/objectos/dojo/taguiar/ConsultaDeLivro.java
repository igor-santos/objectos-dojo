/*
* Copyright 2011 Objectos, Fábrica de Software LTDA.
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License. You may obtain a copy of
* the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations under
* the License.
*/
package br.com.objectos.dojo.taguiar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.objectos.comuns.base.Construtor;
import br.com.objectos.comuns.relational.jdbc.NativeSql;
import br.com.objectos.comuns.relational.jdbc.ResultSetWrapper;
import br.com.objectos.comuns.relational.search.Page;
import br.com.objectos.comuns.relational.search.ResultSetLoader;
import br.com.objectos.comuns.sitebricks.RequestWrapper;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
public class ConsultaDeLivro {

  private final Provider<NativeSql> sqlProvider;

  @Inject
  public ConsultaDeLivro(Provider<NativeSql> sqlProvider) {
    this.sqlProvider = sqlProvider;
  }

  public List<ConsultaDeLivroDTO> list(RequestWrapper wrapper) {
    Page page = wrapper.getPage();
    Integer localizacao = wrapper.integerParam("localizacao");

    return sqlProvider.get()

    .add("select *")

    .add("from BIBLIOTECA.LIVRO as LIVRO")

    .add("where 1 = 1").addIf("and LIVRO.LOCALIZACAO = ?").paramNotNull(localizacao)

    .add("order by").add("LIVRO.TITULO")

    .andLoadWith(new DTOLoader())

    .listPage(page);
  }

  private class DTOLoader implements ResultSetLoader<ConsultaDeLivroDTO> {
    @Override
    public ConsultaDeLivroDTO load(ResultSet resultSet) throws SQLException {
      ResultSetWrapper rs = new ResultSetWrapper(resultSet);
      return new Loader(rs).novaInstancia();
    }
  }

  private class Loader implements ConsultaDeLivroDTO, Construtor<ConsultaDeLivroDTO> {

    private final ResultSetWrapper rs;

    public Loader(ResultSetWrapper rs) {
      this.rs = rs;
    }

    @Override
    public ConsultaDeLivroDTO novaInstancia() {
      return new DTO(this);
    }

    @Override
    public String getTitulo() {
      return rs.getString("TITULO");
    }

    @Override
    public int getLocalizacao() {
      return rs.getInt("LOCALIZACAO");
    }

    @Override
    public String getAutorPrincipal() {
      return rs.getString("AUTO_PRINCIPAL");
    }

    @Override
    public LocalDate getPublicacao() {
      return rs.getLocalDate("PUBLICACAO");
    }

  }

  private class DTO implements ConsultaDeLivroDTO {

    private final String titulo;
    private final int localizacao;
    private final String autoPrincipal;
    private final LocalDate publicacao;

    public DTO(ConsultaDeLivroDTO construtor) {
      this.titulo = construtor.getTitulo();
      this.localizacao = construtor.getLocalizacao();
      this.autoPrincipal = construtor.getAutorPrincipal();
      this.publicacao = construtor.getPublicacao();
    }

    @Override
    public String getTitulo() {
      return titulo;
    }

    @Override
    public int getLocalizacao() {
      return localizacao;
    }

    @Override
    public String getAutorPrincipal() {
      return autoPrincipal;
    }

    @Override
    public LocalDate getPublicacao() {
      return publicacao;
    }

  }

}