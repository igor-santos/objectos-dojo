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

import java.util.Iterator;
import java.util.List;

import br.com.objectos.comuns.relational.jdbc.NativeSql;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
class BuscarFuncionarioGuice implements BuscarFuncionario {

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

  @Override
  public Funcionario porMatricula(String matricula) {
    return newSelect()

        .add("where FUNCIONARIO.MATRICULA = ?").param(matricula)

        .single();
  }

  @Override
  public List<Funcionario> porSuperior(Superior superior) {
    Integer superiorId = superior.getId();

    return newSelect()

        .add("where SUPERIOR.ID = ?").param(superiorId)

        .list();
  }

  @Override
  public Iterator<Funcionario> iterarPorFuncionario(Superior superior) {
    Integer _superior = superior.getId();

    return newSelect()

        .add("where FUNCIONARIO.SUPERIOR_ID = ?").param(_superior)

        .iterate();
  }

  @Override
  public Funcionario porDiretor(Diretor diretor) {
    int _diretor = diretor.getId();

    return newSelect()

        .add("where FUNCIONARIO.DIRETOR_ID = ? ").param(_diretor)

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
