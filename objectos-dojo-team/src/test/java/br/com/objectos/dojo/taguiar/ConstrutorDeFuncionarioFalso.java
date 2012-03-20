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
public class ConstrutorDeFuncionarioFalso implements Funcionario.Construtor {
  
  private int id;
  
  private String nome;
  
  private String matricula;
  
  private LocalDate dataDeNascimento;
  
  private LocalDate dataDeAdmissao;
  
  private LocalDate dataDeDemissao;
  
  private Contrato regimeDeContratacao;
  
  @Override
  public Funcionario novaInstancia() {
    FuncionarioJdbc impl = new FuncionarioJdbc(this);
    impl.setId(id);
    return impl;
  }
  
  public ConstrutorDeFuncionarioFalso id(int id) {
    this.id = id;
    return this;
  }
  
  public ConstrutorDeFuncionarioFalso nome (String nome) {
    this.nome = nome;
    return this;
  }
  
  public ConstrutorDeFuncionarioFalso matricula (String matricula) {
    this.matricula = matricula;
    return this;
  }
  
  public ConstrutorDeFuncionarioFalso dataDeNascimento (LocalDate dataDeNascimento) {
    this.dataDeNascimento = dataDeNascimento;
    return this;
  }
  
  public ConstrutorDeFuncionarioFalso dataDeAdmissao (LocalDate dataDeAdmissao) {
    this.dataDeAdmissao = dataDeAdmissao;
    return this;
  }
  
  public ConstrutorDeFuncionarioFalso dataDeDemissao (LocalDate dataDeDemissao) {
    this.dataDeDemissao = dataDeDemissao;
    return this;
  }
  
  public ConstrutorDeFuncionarioFalso regimeDeContratacao (Contrato regimeDeContratacao) {
    this.regimeDeContratacao = regimeDeContratacao;
    return this;
  }     

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getMatricula() {
    return matricula;
  }

  @Override
  public LocalDate getDataDeNascimento() {
    return dataDeNascimento;
  }
  
  @Override
  public LocalDate getDataDeAdmissao() {
    return dataDeAdmissao;
  }
  
  @Override
  public LocalDate getDataDeDemissao() {
    return dataDeDemissao;
  }
  
  @Override
  public Contrato getRegimeDeContratacao() {
    return regimeDeContratacao;
  }             

}