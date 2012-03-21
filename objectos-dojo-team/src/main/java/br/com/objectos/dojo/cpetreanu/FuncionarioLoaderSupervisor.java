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
public class FuncionarioLoaderSupervisor implements ResultSetLoader<Superior> {

  @Override
  public Superior load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper(resultSet);
    return new Loader(rs).novaInstancia();
  }

  private static interface IConstrutor extends Construtor<Superior> {
    int getId();
  }

  private class Loader implements IConstrutor {

    private final ResultSetWrapper rs;

    public Loader(ResultSetWrapper rs) {
      this.rs = rs;
    }

    @Override
    public Superior novaInstancia() {
      return new SuperiorImpl(this);
    }

    @Override
    public int getId() {
      return rs.getInt("FUNCIONARIO.SUPERIOR_ID");
    }

  }

  private class SuperiorImpl extends SuperiorVazio {

    private final int id;

    public SuperiorImpl(IConstrutor construtor) {
      this.id = construtor.getId();
    }
    @Override
    public Integer getId() {
      return id;
    }

  }

}
