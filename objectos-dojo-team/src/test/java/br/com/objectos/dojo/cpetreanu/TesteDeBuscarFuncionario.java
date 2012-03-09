package br.com.objectos.dojo.cpetreanu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.dbunit.DBUnit;

import com.google.inject.Inject;

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
@Test
@Guice(modules = { ModuloDeTeste.class })
public class TesteDeBuscarFuncionario {

  @Inject
  private BuscarFuncionario buscarFuncionario;

  @Inject
  private DBUnit dbUnit;

  public void prepararDBUnit() {
    dbUnit.loadDefaultDataSet();
  }

  public void busca_por_id() {
    Funcionario res = buscarFuncionario.porId(3);

    assertThat(res.getId(), equalTo(3));
    assertThat(res.getMatricula(), equalTo("T0033000"));
    assertThat(res.getNome(), equalTo("Briann Adams"));
    assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980, 6, 01)));
    assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004, 12, 10, 9, 0)));
    assertThat(res.getDataDemissao(), equalTo(new DateTime(2012, 1, 3, 12, 30)));
  }

}
