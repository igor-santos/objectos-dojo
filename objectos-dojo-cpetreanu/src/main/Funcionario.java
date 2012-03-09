package main;

import java.util.Date;

/*
 * Funcionario.java criado em 08/03/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
public interface Funcionario {

  String getMatricula();
  
  String getNome();

  LocalDate getDataNascimento();

  DateTime getDataAdmissao()
  
  DateTime getDataDemissao()

}