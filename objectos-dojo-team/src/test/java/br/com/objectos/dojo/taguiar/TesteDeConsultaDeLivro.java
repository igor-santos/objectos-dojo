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

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.joda.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.sitebricks.FakeRequestWrapper;
import br.com.objectos.comuns.testing.dbunit.DBUnit;
import br.com.objectos.dojo.cpetreanu.ModuloDeTesteObjectosDojo;

import com.google.common.base.Function;
import com.google.inject.Inject;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Test
@Guice(modules = ModuloDeTesteObjectosDojo.class)
public class TesteDeConsultaDeLivro {
  
  @Inject
  private ConsultaDeLivro consulta;
  
  @Inject
  private DBUnit dbUnit;
    
  @BeforeClass
  public void prepararDBUnit() {
    dbUnit.loadDefaultDataSet();
  }
  
  public void ordenacao_padrao_por_titulo() {
    FakeRequestWrapper wrapper = new FakeRequestWrapper();
    
    List<ConsultaDeLivroDTO> res = consulta.list(wrapper);
    assertThat(res.size(), equalTo(2));
    
    List<String> titulos = transform(res, new ToTitulo());
    assertThat(titulos.get(0), equalTo("Java - Como Programar"));
    assertThat(titulos.get(1), equalTo("Scjp Sun Certified Programmer For Java 6"));
    
    List<Integer> localizacoes = transform(res, new ToLocalizacao());
    assertThat(localizacoes.get(0), equalTo(123));
    assertThat(localizacoes.get(1), equalTo(133));
    
    List<String> autores = transform(res, new ToAutorPrincipal());
    assertThat(autores.get(0), equalTo("Deitel"));
    assertThat(autores.get(1), equalTo("Bates, Bert"));
    
    List<LocalDate> publicacoes = transform(res, new ToPublicacao());
    assertThat(publicacoes.get(0), equalTo(new LocalDate(2010, 1, 1)));
    assertThat(publicacoes.get(1), equalTo(new LocalDate(2008, 1, 1)));
  }
  
  public void filtro_por_localizacao() {
    FakeRequestWrapper wrapper = new FakeRequestWrapper();
    wrapper.put("localizacao", 123);
  
    List<ConsultaDeLivroDTO> res = consulta.list(wrapper);
    assertThat(res.size(), equalTo(1));
    assertThat(res.get(0).getLocalizacao(), equalTo(123));
  }
  
  private class ToTitulo implements Function<ConsultaDeLivroDTO, String> {
    @Override
    public String apply(ConsultaDeLivroDTO input) {
      return input.getTitulo();
    }
  }
  
  private class ToLocalizacao implements Function<ConsultaDeLivroDTO, Integer> {
    @Override
    public Integer apply(ConsultaDeLivroDTO input) {
      return input.getLocalizacao();
    }
  }
  
  private class ToAutorPrincipal implements Function<ConsultaDeLivroDTO, String> {
    @Override
    public String apply(ConsultaDeLivroDTO input) {
      return input.getAutorPrincipal();
    }
  }
  
  private class ToPublicacao implements Function<ConsultaDeLivroDTO, LocalDate> {
    @Override
    public LocalDate apply(ConsultaDeLivroDTO input) {
      return input.getPublicacao();
    }
  }

}