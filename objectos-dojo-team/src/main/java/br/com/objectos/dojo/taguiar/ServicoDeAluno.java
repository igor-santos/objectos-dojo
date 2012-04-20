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

import java.util.List;

import com.google.inject.name.Named;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.headless.Service;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Service
public class ServicoDeAluno {

  private final ConsultaDeAluno consulta;

  public ServicoDeAluno(ConsultaDeAluno consulta) {
    this.consulta = consulta;
  }

  public Reply<?> get(Request request, @Named("curso") String _curso) {
    FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);

    List<ConsultaDeAlunoDTO> dtos = consulta.list(wrapper, _curso);
    TabelaDeAluno tabela = new TabelaDeAluno(dtos);

    return Reply.with(tabela).template(tabela.getClass());
  }

}