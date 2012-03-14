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

import br.com.objectos.comuns.relational.jdbc.NativeSql;

import com.google.inject.Provider;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
public class BuscarSuperiorGuice implements BuscarSuperior {

  private final Provider<NativeSql> sqlProvider;

  public BuscarSuperiorGuice(Provider<NativeSql> sqlProvider) {
    this.sqlProvider = sqlProvider;
  }

  @Override
  public Superior porId(int id) {
    return newSelect()

        .add("where SUPERIOR.ID = ?").param(id)

        .single();
  }

  private NativeSql newSelect() {
    return sqlProvider.get()

        .add("select *")
        .add("from DATABASE.SUPERIOR")

        .andLoadWith(new SuperiorLoader());
  }

}
