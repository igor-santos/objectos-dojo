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

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.objectos.comuns.relational.jdbc.GeneratedKeyCallback;
import br.com.objectos.comuns.relational.jdbc.Insert;

/**
 * @author igor.santos@objectos.com.br (Igor Vinicius Pereira dos Santos)
 */
public class ClienteJdbc implements Cliente {

	private int id;
	private final String nome;
	private final String telefone;
	private final String endereco;
	private final String cpf;

	public ClienteJdbc(Construtor construtor) {
		nome = construtor.getNome();
		telefone = construtor.getTelefone();
		endereco = construtor.getEndereco();
		cpf = construtor.getCpf();
	}

	@Override
	public Insert getInsert() {
		return Insert.into("OBJ_BASE.CLIENTE")
				
				.value("NOME", nome)
				.value("TELEFONE", telefone)
				.value("ENDERECO", endereco)
				.value("CPF", cpf)
				
				.onGeneratedKey(new GeneratedKeyCallback() {
					
					@Override
					public void set(ResultSet rs) throws SQLException {
						id = rs.next() ? rs.getInt(1) : 0;
					}
				});
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