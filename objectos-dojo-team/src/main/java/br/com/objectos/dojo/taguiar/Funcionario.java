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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
public interface Funcionario {

  interface Construtor extends br.com.objectos.comuns.base.Construtor<Funcionario> {
 
    String getNome();
    
    String getMatricula();
  
    LocalDate getDataDeNascimento();
  
    DateTime getDataDeAdmissao();
      
    DateTime getDataDeDemissao();
      
    Contrato getRegimeDeContratacao();
 
  }
  
  int getId();
  
  String getNome();
        
  String getMatricula();
    
  LocalDate getDataDeNascimento();
    
  DateTime getDataDeAdmissao();
        
  DateTime getDataDeDemissao();
        
  Contrato getRegimeDeContratacao();
  
}