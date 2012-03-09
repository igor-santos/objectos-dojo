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
package br.com.objectos.dojo.treinamento;

import java.util.List;

import org.testng.annotations.Test;

/**
 * @author afonso.filgueiras@objectos.com.br (Afonso J. Filgueiras)
 */
@Test
@SuppressWarnings("null")
public class TesteDeNullPointer {

  public void calling_the_instance_method_of_a_null_object() {
    String a = null;

    try {
      a.charAt(0);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public void accessing_or_modifying_the_field_of_a_null_object() {
    Usuario usuario = null;

    try {
      usuario.setLogin("hescarate");
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public void taking_the_length_of_null_as_if_it_were_an_array() {
    List<String> listaVazia = null;

    try {
      listaVazia.size();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public void accessing_or_modifying_the_slots_of_null_as_if_it_were_an_array() {
    List<String> listaVazia = null;

    try {
      listaVazia.add("Marcos");
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public void throwing_null_as_if_it_were_a_throwable_value() {
    Usuario usuario = new Usuario();

    try {
      usuario.lanca_excecao();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }

  }

  private class Usuario {
    public void setLogin(String login) {
    }

    public void lanca_excecao() {
      throw new NullPointerException();
    }
  }

}
