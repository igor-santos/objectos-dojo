---
layout: post
title: Criando e testando Scripts Flyway
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-03-14"
published: true
partof: procedimento-crud-entidade
num: 1
outof: 1 
---

##1º: Importando o projeto

A princípio devemos ter o projeto em nosso computador. Utilizaremos um projeto fictício chamada _objectos-tests_ para
descrever os procedimentos (utilize o nome de seu projeto no lugar de _objectos-tests_):

Execute o seguinte comando para clonar o projeto _objectos-tests_.

	$ cd ~/kdo/projetos/
	$ git clone git@github.com:objectos/objectos-tests.git
	$ cd objectos-tests/
	$ git branch
	
Note que estamos na branch _master_. Devemos trabalhar em uma nova branch neste padrão: 
_login-de-rede-objectos-XX-nome da entidade_ (onde _XX_ representa o próximo número de branch)

##Erro:
Provavelmente o Eclipse mostrará um erro no arquivo POM.XML do projeto.
Vejamos:

POM.XML de _objectos-tests_:

	<parent>
		<artifactId>objectos-comuns-parent</artifactId>
		<groupId>br.com.objectos</groupId>
		<version>2.0.5</version>
	</parent>

Isto ocorre porque há algumas dependências que devem ser carregadas no projeto (nossas dependências
estão contidas neste projeto _objectos-comuns-parent_).

##Solução:

Siga os seguintes procedimentos:

###1 - No Eclipse:
	1.1 - Selecionar todos os projetos;
	1.2 - Clicar com o direito -> Clicar em 'Refresh';
	1.3 - Esperar o Eclipse terminar o procedimento;
	
###2 - No terminal:
	
	$ cd ~/kdo/projetos/objectos-tests
	$ mvn-ci
	
###3 - Voltando ao Eclipse:
	3.1 - Selecionar todos os projetos;
 	3.2 - Na barra de menu -> Project -> Clean -> Selecione 'Clean all projects' -> Clique em Ok;
	3.3 - Clicar com direito no projeto -> Maven -> Update Project Configuration. 
	
Isto deve resolver o problema.

##2º: Criando Entidades no Java

###_Importante: Este item exige conhecimentos sobre:_
 - [Interfaces](http://en.wikipedia.org/wiki/Interface_%28Java%29)
 - [Annotations](http://docs.oracle.com/javase/1.5.0/docs/guide/language/annotations.html)

Antes de criarmos qualquer Script Flyway DEVEMOS criar as entidades e seus respectivos campos no
Java que serão utilizados futuramente.

- Crie uma __package__ chamada __br.com.objectos.tests.empresa__ no diretório __objectos-tests/src/main/java/__.
- Crie uma __interface__ chamada __Funcionario__.


		public interface Funcionario {
			
			interface Construtor extends br.com.objectos.comuns.base.Construtor<Funcionario> {
			
			    String getNome();
				    
			    String getMatricula();
				
			    LocalDate getDataDeNascimento();
				
			    DateTime getDataDeAdmissao();
				    
			    DateTime getDataDeDemissao();
				    
			    Contrato getRegimeDeContratacao();
				
			}
			
			int getId();
				
			String getNome();
				    
			String getMatricula();
				
			LocalDate getDataDeNascimento();
				
			DateTime getDataDeAdmissao();
				    
			DateTime getDataDeDemissao();
				    
			Contrato getRegimeDeContratacao();
				
		}
		
O que fizemos aqui foi definir os campos da entidade Funcionario e um Construtor que 
servirá para instanciar objetos FALSOS (veremos isto mais adiante).

- Crie uma classe __FuncionarioJdbc__ que implemente a interface já definida.


		public class FuncionarioJdbc implements Funcionario {
		
		  private int id;
		
		  @NotNull	 			
		  private final String nome;
		
		  @NotNull			
		  private final String matricula;
		
		  @NotNull
		  private final LocalDate dataDeNascimento;
		  
		  @NotNull
		  private final DateTime dataDeAdmissao;
		  
		  private final DateTime dataDeDemissao;
		  
		  @NotNull
		  private final Contrato regimeDeContratacao;
		
		  public FuncionarioJdbc(Construtor construtor) {
		    nome = construtor.getNome();
		    matricula = construtor.getMatricula();
		    dataDeNascimento = construtor.getDataDeNascimento();
		    dataDeAdmissao = construtor.getDataDeAdmissao();
		    dataDeDemissao = construtor.getDataDeDemissao();
		    regimeDeContratacao = construtor.getRegimeDeContracao();
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
		  public DateTime getDataDeAdmissao() {
		    return dataDeAdmissao;
		  }  
		
		  @Override
		  public DateTime getDataDeDemissao() {
		    return dataDeDemissao;
		  }  
		
		  @Override
		  public Contrato getRegimeDeContratacao() {
		    return regimeDeContratacao;
		  }
		
		}

É nesta classe que definimos se os campos devem ser nulos, vazios, ter valor mínimo de caracteres,
entre outros. Definimos os campos como __not null__ (exceto o campo da data de demissão) e criamos
o método __setId(int id)__.
 
_Nota: TODOS os objetos DEVEM ter um método getter e um setter para o id_.

- Por fim, crie a __enum Contrato__.

		public enum Contrato {
		
		  CLT,
		  
		  ESTAGIO
		
		}

##3º Criando Scripts Flyway

###_Importante: Este item exige conhecimentos sobre:_
- [Script Flyway](http://dojo.objectos.com.br/procedimento/crud-entidade/99-bd-scripts-flyway.html)

A princípio iremos criar o arquivo __VTESTS.000.Funcionario.sql__ no diretório __objectos-tests/src/main/resources/__ até
que seja devidamente revisado.

	drop table if exists NOME_DO_BANCO.FUNCIONARIO;
		
	create table NOME_DO_BANCO.FUNCIONARIO (
		
	ID integer not null auto_increment,
	NOME varchar(60) not null,
	MATRICULA varchar(60) not null,
	DATA_NASCIMENTO date not null,
	DATA_ADMISSAO datetime not null,
	DATA_DEMISSAO datetime,
	REGIME_CONTRATACAO tinyint not null,
		
	primary key(ID),
	unique(MATRICULA)
		
	) ENGINE=InnoDB;

Com o script flyway criado, utilize o [phpMyAdmin](http://www.phpmyadmin.net/home_page/index.php) para testá-lo 
localmente em seu computador. 

	drop table if exists OBJ_BASE.EMAIL;

	create table OBJ_BASE.EMAIL (

	ID integer not null auto_increment,
	PESSOA_FISICA_ID integer not null,
	ENDERECO varchar(60) not null,
	DATA_CRIACAO datetime not null,

	primary key(ID)

	) ENGINE=InnoDB;
	
Siga para o próximo passo. Os Objetos Falsos e seus atributos no XML! <a href="{{ site.baseurl }}/procedimento/crud-entidade/02-criando-objetos-falsos.html" class="btn btn-success">Continuar!</a>