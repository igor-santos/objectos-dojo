package br.com.objectos.dojo.cpetreanu;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

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

  int getId();

  String getMatricula();

  String getNome();

  LocalDate getDataNascimento();

  DateTime getDataAdmissao();

  DateTime getDataDemissao();

}