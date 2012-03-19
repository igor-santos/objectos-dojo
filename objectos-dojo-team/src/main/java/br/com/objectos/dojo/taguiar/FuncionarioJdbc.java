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

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
public class FuncionarioJdbc implements Funcionario {
  
  private int id;

  @NotNull        
  private final String nome;

  @NotNull      
  private final String matricula;

  @NotNull
  private final LocalDate dataDeNascimento;
  
  @NotNull
  private final LocalDate dataDeAdmissao;
  
  private final LocalDate dataDeDemissao;
  
  @NotNull
  private final Contrato regimeDeContratacao;

  public FuncionarioJdbc(Construtor construtor) {
    nome = construtor.getNome();
    matricula = construtor.getMatricula();
    dataDeNascimento = construtor.getDataDeNascimento();
    dataDeAdmissao = construtor.getDataDeAdmissao();
    dataDeDemissao = construtor.getDataDeDemissao();
    regimeDeContratacao = construtor.getRegimeDeContratacao();
  }

  @Override
  public int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
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