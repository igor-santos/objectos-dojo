---
layout: post-alpha
title: "Implementado Form Create: Form"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-04-04"
published: true
partof: procedimento-crud-forms
num: 0
---

##Introdução

Após implementar o teste de seu form, podemos partir para sua implementação e verificar se de fato o
form realiza com sucecsso a criação de uma entidade no banco. Será realizado um passo a passo, informando
o que é preciso ser feito e como deve ser feito para a implementação do form.

Antes de iniciar a leitura deste artigo é de suma importância que você tenha implementado de forma
correta o <a href="">teste</a> do form.

##Alterando o método post 

Lembrando que ao implementar o form sempre devemos começar pelo método `post`, que trata a solicitação
realizada no teste para o form. Se você está seguindo este artigo desde a implementação do teste no 
artigo anterior, provalvelmente deve ter criado a classe do form e o método `post` que estará bem
parecido com este:
	
	@Post
	public Reply<?> post(Request request, @Named("curso") String _curso) {
		return Reply.saying().ok();
	}

###O que é @Named?

Repare que o método post é composto por dois parâmetros, o primeiro um objeto Request e o segundo
uma String anotada com `@Named`, esta anotação serve para informar ao método post que o tipo de 
dado após esta declaração é um parâmetro capturado da URL do form. O nome do parâmetro da anotação
equivale ao coringa definido na URL do módulo quando o teste foi implementado, ou seja este parâmetro
é o coringa que definimos no módulo na hora de realizar o teste.

Lembrando que quanto mais coringas forem definidos na URL do form, mais parâmetros o form deve
capturar.

###De volta a implementação do form

Pois bem, remova o retorno do método e por hora deixe que o método retorne `null`, em seguida será 
preciso converter o parâmetro Request do método para um RequestWrapper, para isso utilizamos uma 
classe auxiliar que faz esta conversão, procure pela classe __NomeDoProjetoRequestWrapper__, 
instancie esta classe e envie o parâmetro do método a ela:

	@Post
	public Reply<?> post(Request request, @Named("curso") String _curso) {
		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);
		
		return null;
	}

Após adicionar o FaculdadeRequestWrapper, devemos lembrar que para cadastrar um Aluno é preciso
fornecer o curso do mesmo, por isso o form captura o parâmetro __curso__ e como o mesmo é único é
possível conseguir um objeto do tipo Curso utilizando um buscador. Basta declarar `BuscarCurso` no
início da classe:

	private BuscarCurso buscar;

Com isso é so buscar um Curso através do parâmetro curso:

	@Post
	public Reply<?> post(Request request, @Named("curso") String _curso) {
		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);
		
		Curso curso = buscarCurso.porCodigo(_curso);
		
		return null;
	}

Em seguida deve-se capturar os parâmetros que foram ajustados na QueryString no teste do form, estes
serão os dados a serem gravados no banco, para realizar esta tarefa será necessário a implementação 
de uma inner class chamada Construtor.

<div class="alert alert-warning">
	Dica: Para cada tipo de form a ser implementado o método acima pode mudar, por exemplo, se o form 
	for de atualização(Update) então o método se chamará put e será marcado com a anotação @Put. Ou
	seja para cada operação CRUD utiliza-se um método HTTP para a operação específica. 
</div>

###Implementando a inner class Construtor

Todos os parâmetros que fornecemos na QueryString do teste estão contidos no objeto Request, que
é parâmetro do método `post`, após realizarmos a conversão do Request, criamos uma classe que busca
todos os parâmetros do QueryString e os adiciona em um objeto do tipo Aluno que será persistido 
posteriormente, vamos a implementação da inner class Construtor:

	private class Construtor implements Aluno.Construtor {
		
		private final RequestWrapper wrapper;
		private final Curso curso;
		
		public Construtor(RequestWrapper wrapper, Curso curso) {
			this.wrapper = wrapper;
			this.curso = curso;
		}
		
		@Override
		public Aluno novaInstancia() {
			return new AlunoJdbc(this);
		}
		
		@Override
		public Curso getCurso() {
			return curso;
		}
		
		@Override
		public String getNome() {
			return wrapper.param("nome");
		}
		
		@Override
		public String getMatricula() {
			return wrapper.param("matricula");
		}
		
		@Override
		public DateTime getDataDeCriacao() {
			return new DateTime();
		}
		
	}
	
Observe que para acessar as propriedades que definimos no teste utilizamos o método `param` de RequestWrapper,
podem existir casos onde será necessário extrair diferentes tipos de valores como double ou boolean por exemplo,
para isso utilize os outros métodos da classe RequestWrapper.

###Para outros tipos de dados utilize os seguintes métodos:

Para datas do tipo LocalDate:

	wrapper.localDateParam("localDate");
	 
Para tipos de ponto flutuante:

	wrapper.doubleParam("doubleParam");

Para tipos inteiros:

	wrapper.integerParam("intParam");

Para tipos longos:

	wrapper.longParam("longParam");

Para enums:

	wrapper.enumParam(MinhaEnum.class, "enumParam");

<div class="alert alert-warning">
	Dica: Sempre que for preciso implementar a inner class Construtor, a mesma deve SEMPRE implementar
	a interface Construtor da entidade a ser gravada no banco.
</div>

##De volta ao método post

Após realizar a implementação do método `post` é preciso criar o objeto a ser gravado no banco de dados
e efetuar a gravação do mesmo, para isso crie uma instância da classe Construtor, chamando o método 
`novaInstancia()` que criará um objeto com todas as informações definidas no QueryString no teste do 
form.

	@Post
	public Reply<?> post(Request request) {
		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);

		Aluno pojo = new Construtor(request).novaInstancia();
		
		return null;
	}

Agora é preciso gravar nosso objeto no banco de dados, para isso utilizaremos uma implementação da interface
__Forms__, por isso, declare uma propriedade deste tipo no início da classe:

	private Forms forms;

<div class="alert alert-warning">
	Dica: Deixe a propriedade marcada como private ainda e não gere o construtor da classe, pois serão adicionadas novas
	propriedades ao decorer do form. Defina o construtor apenas quando finalizar o form.
</div>

Após declarar Forms, altere o retorno do método e chame o método `newFormsFor(pojo)` de forms e passe para 
o mesmo o <a href="http://pt.wikipedia.org/wiki/Plain_Old_Java_Objects">pojo</a> que foi gerado pela 
inner class criada anteriormente.

	Aluno pojo = new Construtor(request).novaInstancia();
		
	return newFormsFor(pojo);
	
Em seguida serão criadas outras duas inner classes responsáveis em efetuar a criação da entidade no banco de
dados e o redireciomento para a página de detalhes da entidade após o cadastro da mesma.

##Criando Actions

Chame o método `withCreateAction` logo abaixo de `newFormsFor(pojo)` e já defina a primeira inner class:
 __AlunoCreateAction__

	return newFormsFor(pojo)
	
		.withCreateAction(new AlunoCreateAction());

Um erro de compilação irá aparecer, isso por que a classe ainda não existe, basta cria-lá logo abaixo do método
`post`, lembrando que a mesma deve implementar a interface `Form.CreateAction<Entidade>`, deve-se especificar
no lugar de "Entidade" o tipo de entidade a ser persistida no banco, no nosso caso Aluno.

	private class AlunoCreateAction implements Form.CreateAction<Aluno> {
		@Override
		public Aluno execute(Aluno pojo) {
			return null;
		}
	}
	
Declare um `NativeSqlFactory` logo abaixo de `Form` no início da classe, esta declaração será responsável em 
nos auxiliar no processo de gravação da entidade no banco de dados. Em seguida será necessario chamar o método 
`insert` que grava a entidade no banco.

	private class AlunoCreateAction implements Form.CreateAction<Aluno> {
		@Override
		public Aluno execute(Aluno pojo) {
			sqlFactory.insert(pojo).insert();
			return pojo;
		}
	}

Ao implementar o seguinte bloco de código um erro de compilção surgirá, dizendo que o método `insert` não pode
ser chamado pois Aluno não implementa `Insertable`, esta interface possui o método `insert` responsável em 
gravar uma entidade no banco de dados, vamos fazer uma pausa no form e realizar as alterações em Aluno.

##Implementando Insertable

Abra a interface de Aluno e implemente a interface `Insertable`, salve a alteração. Todas as implementações de 
Aluno além de AlunoJdbc reclamarão que não há implementação para o método `getInsert`, faça as devidas correções
e adicione o `getInsert` em AlunoJdbc logo abaixo do construtor da classe.

	public Insert getInsert() {
		return null;
	}
	
Sua implementação é parecida com statement <a href="http://dev.mysql.com/doc/refman/5.5/en/insert.html">INSERT</a>
utilizado nos bancos de dados. Atente para sua implementação:

	public Insert getInsert() {
		return Insert.into("FACULDADE.ALUNO")
			.value("NOME", nome)
			.value("MATRICULA, matricula")
			.value("DATA_CRIACAO", dataCriacao)
			
			.onGenerateKey(new GenerateKeyCallBack() {
				@Override
				public void set(ResultSet rs) throws SQLExecption {
					id = rs.next() ? rs.getInt(1) : null;
				}
			});	
	}

##De volta ao form - RedirectAction

Agora que Aluno é um __Insertable__, podemos continuar a implementar o form e seguir para o próximo action que é
o RedirectAction. O procedimento para defini-lo é praticamente o mesmo que o de AlunoCreateAction. Ainda no 
método `post` adicione a chamada ao método `withRedirectAction(new AlunoRedirectAction())`

	return newFormsFor(pojo)
	
		.withCreateAction(new AlunoCreateAction())
		
		.withRedirectAction(new AlunoRedirectAction());
		
Logo após sua definição um erro de compilação será lançado indicando que esta classe não existe, crie-a abaixo
da inner class __AlunoCreateAction__. Lembrando que a mesma deve implementar a interface `Form.Redirect<Entidade>`,
onde "Entidade" é o tipo de entidade que estamos trabalhando, Aluno.
 
<div class="alert alert-warning"> 
	Dica: Tome cuidado ao nomear seus actions, por padrão seus nomes sempre devem ser: EntidadeCreateAction, ou
	então EntidadeRedirectAction, fique atento a isso!
</div>

Para realizar esta implementação precisamos de um objeto do tipo __Bricks__ que será responsável em capturar
toda a URL base do sistema até o recurso que que deve ser redirecionado. Por isso defina logo acima da
declaração de __Forms__ a propriedade Bricks - __NomeDoProjetoBricks__, seu nome varia de acordo com o
projeto para facilitar sua declaração.

	private FaculdadeBricks bricks;


##Implementando o RedirectAction

Após os esclarecimentos acima implemente o RedirectAction:

	private class AlunoRedirectAction implements Form.Redirect<Aluno> {
		@Override 
		public String getUrl(Aluno pojo) {
			String baseUrl = bricks.getBaseUrl();
			return String.format("%s/faculdade/aluno/%d", baseUrl, pojo.getId());
		}
	} 
	
Pronto, com isso após a criação de cada entidade do tipo Aluno no sistema, o mesmo irá redirecionar o usuário
a página de detalhes da entidade.

<div class="alert alert-warning"> 
	Dica: Existem casos onde após o cadastro o sistema deve redirecionar o usuário para a página 
	atual, pois não existe página de detalhes para a entidade. Em situações como esta, basta alterar o
	rediectUrl para a página atual, em nosso caso basta remover o id de aluno no fim da URL.
</div>

##Terminando o método post

Após finalizar nosso último Action vamos seguir para o método `post` e finalizá-lo. Após adicionar o último 
Action chame o método `create` e finalize o método:

	@Post
	public Reply<?> post(Request request) {
		FaculdadeRequestWrapper wrapper = new FaculdadeRequestWrapper(request);

		Aluno pojo = new Construtor(request).novaInstancia();
		
		return forms.newFormsFor(pojo)

			.withCreateAction(new AlunoCreateAction())
		
			.withRedirectAction(new AlunoRedirectAction())
			
			.create();
	}

##Declarações da classe e Construtor

O form está praticamente pronto, basta apenas organizar as declarações e gerar o construtor da classe. Nas
declarações sempre crie dois blocos de definição de propriedades, o primeiro deve conter apenas propriedades
que trabalhem na parte web do sistema, como Bricks e Forms e a segunda toda a parte que trabalha com o 
banco de dados, atente a declaração correta de propriedades da classe:

	private final FaculdadeBricks bricks;
	private final Forms forms;
	
	private final NativeSqlFactory sqlFactory;
	
Como finalizamos o form podemos marcar as propriedades da classe como final e gerar o construtor:

	@Inject
	public FormDeAlunoCreate(FaculdadeBricks bricks, Forms forms, NativeSqlFactory sqlFactory) {
		this.bricks = bricks;
		this.forms = forms;
		this.sqlFactory = sqlFactory; 
	}
<div class="alert alert-warning">
	Não se esqueça de adicionar a anotação @Inject no construtor da classe para evitar exceções como  
	<a href="http://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html">
	NullPointerExceptions</a>
</div>
