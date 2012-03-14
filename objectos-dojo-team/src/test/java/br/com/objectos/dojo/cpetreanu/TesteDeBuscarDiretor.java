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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.dbunit.DBUnit;

import com.google.inject.Inject;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
@Test
@Guice(modules = { ModuloDeTesteObjectosDojo.class })
public class TesteDeBuscarDiretor {

  private BuscarDiretor buscarDiretor;

  @Inject
  private DBUnit dbUnit;

  @BeforeClass
  public void prepararDBUnit() {
    dbUnit.loadDefaultDataSet();
  }

  public void busca_por_id() {
    Diretor res = buscarDiretor.porId(1);

    assertThat(res.getId(), equalTo(1));
    assertThat(res.getNome(), equalTo("Samuel Freire Jr."));
  }
}
