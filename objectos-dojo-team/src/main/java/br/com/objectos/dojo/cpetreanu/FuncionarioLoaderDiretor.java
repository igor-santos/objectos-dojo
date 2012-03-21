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
package br.com.objectos.dojo.cpetreanu;

import java.sql.ResultSet;

import br.com.objectos.comuns.base.Construtor;
import br.com.objectos.comuns.relational.jdbc.ResultSetWrapper;
import br.com.objectos.comuns.relational.search.ResultSetLoader;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
public class FuncionarioLoaderDiretor implements ResultSetLoader<Diretor> {

  @Override
  public Diretor load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper(resultSet);
    return new Loader(rs).novaInstancia();
  }

  private static interface IConstrutor extends Construtor<Diretor> {
    int getId();
  }

  private class Loader implements IConstrutor {

    private final ResultSetWrapper rs;

    public Loader(ResultSetWrapper rs) {
      this.rs = rs;
    }

    @Override
    public Diretor novaInstancia() {
      return new DiretorImpl(this);
    }

    @Override
    public int getId() {
      return rs.getInt("FUNCIONARIO.DIRETOR_ID");
    }

  }

  private class DiretorImpl extends DiretorVazio {

    private final int id;

    public DiretorImpl(IConstrutor construtor) {
      this.id = construtor.getId();
    }

    @Override
    public int getId() {
      return id;
    }

  }

}
