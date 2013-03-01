/*
 * ModuloDeTesteObjectosDojo.java criado em 01/03/2013
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.dojo;

import br.com.objectos.comuns.relational.jdbc.RelationalJdbcModuleBuilder;
import br.com.objectos.comuns.sql.PropertiesJdbcCredentialsProvider;

import com.google.inject.AbstractModule;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class ModuloDeTesteObjectosDojo extends AbstractModule {

  @Override
  protected void configure() {
    PropertiesJdbcCredentialsProvider credentials;
    credentials = new PropertiesJdbcCredentialsProvider(getClass());

    install(new RelationalJdbcModuleBuilder()
        .withC3P0(credentials)
        .withMysql()
        .build());
  }

}