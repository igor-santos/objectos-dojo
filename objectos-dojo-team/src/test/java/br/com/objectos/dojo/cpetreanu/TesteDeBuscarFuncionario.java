/*
 * Copyright 2012 Objectos, Fábrica de Software LTDA.
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

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.dbunit.DBUnit;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
@Test
@Guice(modules = { DeprecatedModuloDeTesteObjectosDojo.class })
public class TesteDeBuscarFuncionario {

  @Inject
  private BuscarFuncionario buscarFuncionario;

  @Inject
  private BuscarSuperior buscarSuperior;

  @Inject
  private DBUnit dbUnit;

  public void prepararDBUnit() {
    dbUnit.loadDefaultDataSet();
  }

  public void busca_por_id() {
    Funcionario res = buscarFuncionario.porId(3);

    assertThat(res.getId(), equalTo(3));
    assertThat(res.getMatricula(), equalTo("T0033000"));
    assertThat(res.getNome(), equalTo("Briann Adams"));
    assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980, 6, 01)));
    assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004, 12, 10, 9, 0)));
    assertThat(res.getDataDemissao(), equalTo(new DateTime(2012, 1, 3, 12, 30)));
  }

  public void busca_por_matricula() {
    Funcionario res = buscarFuncionario.porMatricula("T0033000");

    assertThat(res.getId(), equalTo(3));
  }

  public void busca_lista_por_superior() {
    Superior superior = buscarSuperior.porId(2);
    List<Funcionario> res = buscarFuncionario.porSuperior(superior);

    List<Integer> ids = transform(res, new ToId());
    assertThat(ids.get(0), equalTo(2));
    assertThat(ids.get(1), equalTo(4));
  }

  public void busca_iterador_por_superior() {
    Superior superior = buscarSuperior.porId(2);

    Iterator<Funcionario> iterator = buscarFuncionario.iterarPorFuncionario(superior);

    List<Funcionario> res = ImmutableList.copyOf(iterator);
    assertThat(res.size(), equalTo(2));

    List<Integer> ids = transform(res, new ToId());
    assertThat(ids.get(0), equalTo(2));
    assertThat(ids.get(1), equalTo(4));
  }

  public void busca_por_diretor() {
    Funcionario esperado = buscarFuncionario.porId(3);
    Diretor diretor = esperado.getDiretor();

    Funcionario res = buscarFuncionario.porDiretor(diretor);

    assertThat(res.getDiretor().getId(), equalTo(1));
  }

  private class ToId implements Function<Funcionario, Integer> {
    @Override
    public Integer apply(Funcionario input) {
      return input.getId();
    }
  }

}