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

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
public class ModuloFaculdadeUI extends SubModuloFaculdadeUI {

  @Override
  protected void bindApiCrud() {
    at("api/crud/faculdade/curso").serve(FormDeCursoCreate.class);
    at("api/crud/faculdade/curso/:curso/aluno").serve(FormDeAlunoCreate.class);
    at("api/crus/faculdade/curso/:curso/aluno/:aluno").serve(FormDeAlunoUpdate.class);
  }

  @Override
  protected void bindApi() {
    at("/api/bd/faculdade/curso").serve(ServicoDeCurso.class);
    at("/api/bd/faculdade/curso/:curso/aluno").serve(ServicoDeAluno.class);
  }

  @Override
  protected void bindPages() {
    at("/").show(IndexPage.class);
    at("/faculdade/curso").show(CursoPage.class);
    at("/faculdade/curso/criar").show(CursoCreatePage.class);
  }

  @Override
  protected void bindBricks() {
    embed(FormDeCurso.class).as("FormDeCurso");
  }

}