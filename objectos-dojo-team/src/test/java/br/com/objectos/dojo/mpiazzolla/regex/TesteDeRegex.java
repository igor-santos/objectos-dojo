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
package br.com.objectos.dojo.mpiazzolla.regex;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

/**
 * @author afonso.filgueiras@objectos.com.br (Afonso J. Filgueiras)
 */
@Test
public class TesteDeRegex {

  public void expressao_regular_deve_validar_propriedade() {
    Pessoa pessoa = new Pessoa("login-inválido");

    Pattern expressaoRegular = Pattern.compile("[a-zA-Z-]*");
    Matcher matcher = expressaoRegular.matcher(pessoa.getLogin());

    assertThat(matcher.matches(), is(false));
  }

}