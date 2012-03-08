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
package br.com.objectos.dojo.taguiar.string;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Test
public class TesteEmail {

  public void enviar_mensagem_sem_metodo_string_format() {

    String para = "teste@teste.com.br";
    String cc = "teste2@teste2.com.br";
    String msg = "Testando String.format";

    Email email = new Email();

    String mensagem = String.format("Para: %s\nCc: %s\nMensagem: %s", para, cc, msg);
    email.enviarMensagem(mensagem);

    assertEquals(email.receberMensagem(), "Para: teste@teste.com.br\n"
        + "Cc: teste2@teste2.com.br\n" + "Mensagem: Testando String.format");
  }

}
