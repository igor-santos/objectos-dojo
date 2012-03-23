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

import org.joda.time.LocalDate;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
public class FuncionariosFalso {
  
  private FuncionariosFalso() {}
  
  public static final Funcionario FUNCIONARIO_1 = nova()
      .id(1)
      .nome("Alexandre")
      .matricula("201200001")
      .dataDeNascimento(new LocalDate(1984, 1, 1))
      .dataDeAdmissao(new LocalDate(2012, 1, 1))
      .regimeDeContratacao(Contrato.CLT)
      .novaInstancia();
  
  public static final Funcionario FUNCIONARIO_2 = nova()
      .id(2)
      .nome("Anderson")
      .matricula("201200002")
      .dataDeNascimento(new LocalDate(1985, 1, 1))
      .dataDeAdmissao(new LocalDate(2012, 2, 1))
      .regimeDeContratacao(Contrato.CLT)
      .novaInstancia();
      
  private static ConstrutorDeFuncionarioFalso nova() {
    return new ConstrutorDeFuncionarioFalso();
  }
  
}