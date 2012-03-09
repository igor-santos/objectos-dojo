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
package br.com.objectos.dojo.dificuldades;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;

import org.testng.annotations.Test;

import com.google.common.base.Objects;

/**
 * @author afonso.filgueiras@objectos.com.br (Afonso J. Filgueiras)
 */
@Test
public class TesteDeToString {

  public void teste_de_to_string_ADD() {
    TesteDeStringAdd res = new TesteDeStringAdd();

    assertThat(res, hasToString("TesteDeStringAdd{afonso=AFONSO, marcos=MARCOS, hellen=null}"));
  }

  public void teste_de_to_string_ADD_VALUE() {
    TesteDeStringAddValue res = new TesteDeStringAddValue();

    assertThat(res, hasToString("TesteDeStringAddValue{AFONSO, MARCOS, null}"));
  }

  private class TesteDeStringAdd {
    String afonso = "AFONSO";
    String marcos = "MARCOS";
    String hellen = null;

    @Override
    public String toString() {
      return Objects.toStringHelper(this)
          .add("afonso", afonso)
          .add("marcos", marcos)
          .add("hellen", hellen)
          .toString();
    }
  }

  private class TesteDeStringAddValue {
    String afonso = "AFONSO";
    String marcos = "MARCOS";
    String hellen = null;

    @Override
    public String toString() {
      return Objects.toStringHelper(this)
          .addValue(afonso)
          .addValue(marcos)
          .addValue(hellen)
          .toString();
    }
  }

}
