/*
 * Copyright 2013 Objectos, Fábrica de Software LTDA.
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
package br.com.objectos.dojo.igor;

/**
 * @author igor.santos@objectos.com.br (Igor Vinicius Pereira dos Santos)
 */
public class ConstrutorDeClienteFalso implements Cliente.Construtor {

	private String nome;
	private String telefone;
	private String endereco;
	private String cpf;
			
	@Override
	public Cliente novaInstancia() {
		return new ClienteJdbc(this);		
	}	
	
	public ConstrutorDeClienteFalso nome(String nome) {
		this.nome = nome;
		return this;
	}

	public ConstrutorDeClienteFalso telefone(String telefone) {
		this.telefone = telefone;
		return this;
	}

	public ConstrutorDeClienteFalso endereco(String endereco) {		
		this.endereco = endereco;
		return this;
	}

	public ConstrutorDeClienteFalso cpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getTelefone() {
		return telefone;
	}

	@Override
	public String getEndereco() {
		return endereco;
	}

	@Override
	public String getCpf() {
		return cpf;
	}

}