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

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.testng.annotations.Test;

import br.com.objectos.comuns.sitebricks.form.FormResponseJson;
import br.com.objectos.comuns.sitebricks.form.QueryString;

import com.google.inject.Inject;
import com.google.sitebricks.client.WebResponse;
import com.google.sitebricks.client.transport.Json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Test
public class TesteDeFormDeAlunoUpdate extends TesteDeIntegracaoWeb {

  private static final String URL = "api/crud/faculdade/curso/direito/aluno/11";

  @Inject
  private BuscarAluno buscarAluno;

  public void usuario_nao_autenticado_deve_ser_negado() {
    WebResponse response = webClientOf(URL).put("");

    assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
  }

  public void usuario_nao_autorizado_deve_ser_negado() {
    Map<String, String> cookies = login("user");
    WebResponse response = webClientOf(URL, cookies).put("");

    assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
  }

  public void form_deve_gravar_aluno_no_bd() {
    int id = 11;
    String nome = "Luiz de Souza";

    Aluno antes = buscarAluno.porId(id);
    assertThat(antes, is(notNullValue()));

    String url = new QueryString(URL)
        .param("nome", nome)
        .get();

    Map<String, String> cookies = login("admin");
    WebResponse response = webClientOf(url, cookies).put("");

    FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
    assertThat(json.isValid(), is(true));

    Aluno res = buscarAluno.porId(id);
    assertThat(res.getNome(), equalTo(nome));

    String redirectUrl = json.getRedirectUrl();
    assertThat(redirectUrl, containsString("faculdade/curso/direito/aluno/11"));
  }

}