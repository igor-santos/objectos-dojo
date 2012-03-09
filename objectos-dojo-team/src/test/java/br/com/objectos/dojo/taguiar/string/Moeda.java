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

import static org.testng.Assert.assertEquals;

import java.util.Locale;

import org.testng.annotations.Test;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Test
public class Moeda {

  public void conversor_monetario_com_metodo_string_format() {
    double real = 1000000.50;
    double dolar = 12000000.50;

    Locale brasil = new Locale("pt", "BR");
    Locale eua = new Locale("en", "US");

    String realConvertido = String.format(brasil, "R$%,.2f", real);
    String dolarConvertido = String.format(eua, "$%,.2f", dolar);

    assertEquals(realConvertido, "R$1.000.000,50");
    assertEquals(dolarConvertido, "$12,000,000.50");
  }

}