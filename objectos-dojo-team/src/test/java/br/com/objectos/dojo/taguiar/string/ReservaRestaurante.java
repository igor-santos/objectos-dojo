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
public class ReservaRestaurante {

  public void fazer_reserva_no_restaurante_com_metodo_string_format() {
    String nome = "Smith";
    int qtdDePessoas = 4;
    double preco = 100.90;

    String formatar = "Nome: %s\nQuantidade de pessoas: %d\nPreço: R$%.2f";
    String resultado = "Nome: Smith\nQuantidade de pessoas: 4\nPreço: R$100,90";

    String reserva = String.format(formatar, nome, qtdDePessoas, preco);

    assertEquals(reserva, resultado);
  }

}