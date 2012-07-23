---
layout: post-alpha
title: "Usando DbUnit e Banco de Dados"
author: "Tiago Aguiar"
user: "taguiar"
date: 2012-07-23
published: true 
partof: faq-crud-entidade
num: 7
outof: 7
---

##Introdução
Para testarmos um _software_ em desenvolvimento, utilizamos algumas ferramentas que foram criadas
justamente para estes propósitos como o _DbUnit_ e o _TestNG_.

Focando mais ao _DbUnit_ (que pode popular as tabelas de seu banco de dados através de arquivos com
extensão _xml_) veremos como fazê-lo acessar o banco de dados.

## Falha ao carregar dados no banco

Suponhamos que o módulo ja esteje configurado e queremos executar o seguinte teste buscador:

	@Test
	@Guice(modules = { MeuModuloDeTeste.class })
	public class TesteDeBuscarUsuario {
	
	  @Inject
	  private BuscarUsuario buscarUsuario;
	
	  @Inject
	  private DBUnit dbUnit;
	
	  @BeforeClass
	  public void prepararDBUnit() {
	    dbUnit.loadDefaultDataSet();
	  }
	  
	  // métodos a serem testados
	  
Com a execução deste teste, notamos o seguinte _stacktrace_:

	23/07/2012 13:50:53 com.google.inject.internal.MessageProcessor visit
	INFO: An exception was caught and reported. Message: java.lang.ClassNotFoundException: @jdbc.driver@
	java.lang.ClassNotFoundException: @jdbc.driver@
		at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
		at java.security.AccessController.doPrivileged(Native Method)
		at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
		at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
		at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
		at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
		at java.lang.Class.forName0(Native Method)
		at java.lang.Class.forName(Class.java:169)
		at org.dbunit.JdbcDatabaseTester.<init>(JdbcDatabaseTester.java:104)
		at org.dbunit.JdbcDatabaseTester.<init>(JdbcDatabaseTester.java:79)
		at br.com.objectos.comuns.testing.dbunit.DbunitModuleBuilder$JdbcModuleBuilder$1.configure(DbunitModuleBuilder.java:141)
	...
	
Não conseguimos executar o teste, isto é, preparar os dados pois ainda precisamos executar alguns 
procedimentos/configurações.

## Troubleshooting - configurações
### jdbc.properties

Verifique se existe o arquivo `jdbc.properties` no diretório `src/test/resources`. Este arquivo deve
conter as seguintes propriedades.

	jdbc.driver=@jdbc.driver@
	jdbc.url=@jdbc.baseUrl@/@jdbc.db@
	jdbc.user=@jdbc.user@
	jdbc.password=@jdbc.password@
	
### pom.xml - properties

No `pom.xml` é preciso definir as propriedades descritas anteriormente. Vejamos:

	<properties>
	  <jdbc.driver>com.mysql.jdbc.Driver</jdbc.driver>
	  <jdbc.baseUrl>jdbc:mysql://localhost</jdbc.baseUrl>
	  <jdbc.db>MEU_BD</jdbc.db>
	  <jdbc.user>meu_usuario</jdbc.user>
	  <jdbc.password>minha_senha</jdbc.password>
	</properties>
	
Esta _tag_ `<properties></properties>` deve estar dentro da tag `<project></project>`. Isto vale para
qualquer arquivo `.properties` que você desejar configurar através do `pom.xml`.		

### pom.xml - test resources

Ainda no `pom.xml` é preciso adicionar as seguintes _tags_:

	<testResources>
	  <testResource>
	    <directory>${basedir}/src/test/resources</directory>
	  </testResource>
	  <testResource>
	    <directory>${basedir}/src/test/resources</directory>
        <includes>
	      <include>jdbc.properties</include>
	    </includes>
        <filtering>true</filtering>
	  </testResource>
	</testResources>
	
Esta _tag_ `<testResources></testResources>` deve estar dentro da tag `<build></build>` que,
por sua vez, também esta dentro de `<project> </project>`.

Agora basta compilar o projeto pelo terminal do _linux_ com `mvn-ci` e executar o teste novamente.

Desta forma, a classe que se utiliza destas propriedades conseguem obter os valores que precisamos
(contidos no `pom.xml`).

Método responsável por "pegar" as credenciais para acessar o banco de dados. Classe `PropertiesJdbcCredentialsProvider`

	// Outros métodos

	public JdbcCredentials get() {
	  try {
	
	    URL url = Resources.getResource(contextClass, "/jdbc.properties");
	    InputSupplier<InputStream> inputSupplier = Resources.newInputStreamSupplier(url);
	    InputStream input = inputSupplier.getInput();
	    Properties properties = new Properties();
	    properties.load(input);
	
	    return new JdbcCredentialsBuilder()//
	        .driverClass(properties.getProperty("jdbc.driver")) //
	        .url(properties.getProperty("jdbc.url")) //
	        .user(properties.getProperty("jdbc.user")) //
	        .password(properties.getProperty("jdbc.password")) //
	        .get();
	
	  } catch (IOException e) {
	    throw Throwables.propagate(e);
	  }
	
	}