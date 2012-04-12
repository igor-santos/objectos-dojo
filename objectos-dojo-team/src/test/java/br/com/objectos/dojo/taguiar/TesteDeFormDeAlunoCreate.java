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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

import br.com.objectos.comuns.sitebricks.form.FormResponseJson;
import br.com.objectos.comuns.sitebricks.form.QueryString;

import com.google.inject.Inject;
import com.google.sitebricks.client.WebResponse;
import com.google.sitebricks.client.transport.Json;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Test
public class TesteDeFormDeAlunoCreate extends TesteDeIntegracaoWeb {

  public static final String URL = "api/faculdade/crud/curso/direito/aluno";

  @Inject
  private BuscarAluno buscarAluno;

  public void acesso_a_usuario_nao_autenticado_deve_ser_negado() {
    WebResponse response = webClientOf(URL).post("");

    assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
  }

  public void acesso_a_usuario_nao_autorizado_deve_ser_negado() {
    Map<String, String> cookies = login("user");
    WebResponse response = webClientOf(URL, cookies).post("");

    assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
  }

  public void form_deve_gravar_aluno_no_bd() {
    String nome = "Robson de Souza";
    String matricula = "20120001";
    String curso = "Direito";
    LocalDate dataDeCriacao = new LocalDate();

    List<Aluno> antes = buscarAluno.porCurso(curso);
    assertThat(antes.size(), equalTo(900));

    String url = new QueryString(URL)
        .param("nome", nome)
        .param("matricula", matricula)
        .param("curso", curso)
        .param("dataDeCriacao", dataDeCriacao)
        .get();

    Map<String, String> cookies = login("admin");
    WebResponse response = webClientOf(url, cookies).post("");

    FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
    assertThat(json.isValid(), is(true));

    List<Aluno> res = buscarAluno.porCurso(curso);
    assertThat(res.size(), equalTo(901));

    Aluno r900 = res.get(900);
    assertThat(r900.getNome(), equalTo(nome));
    assertThat(r900.getMatricula(), equalTo(matricula));
    assertThat(r900.getCurso(), equalTo(curso));
    assertThat(r900.getDataDeCriacao(), equalTo(dataDeCriacao));

    String redirectUrl = json.getRedirectUrl();
    assertThat(redirectUrl, containsString("faculdade/curso/direito/aluno"));
  }

}