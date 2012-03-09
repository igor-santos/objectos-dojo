package test;

import main.BuscarFuncionario;
import main.BuscarSuperior;
import main.Funcionario;
import main.Superior;

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
    assertThat(res.getMaticula(), equalTo("T0033000"));
    assertThat(res.getNome(), equalTo("Briann Adams"));
    assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980,6,01)));
    assertThat(res.getSuperior().getId(), equalTo(3));
    assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004,12,10,9)));
    assertThat(res.getDataDemissao(), equalTo(new DateTime(2012,1,3,12,30)));
  }

}
