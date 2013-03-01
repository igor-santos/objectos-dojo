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

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.cpetreanu.DeprecatedModuloDeTesteObjectosDojo;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.inject.Inject;

/**
 * @author igor.santos@objectos.com.br (Igor Vinicius Pereira dos Santos)
 */
@Test
@Guice(modules = DeprecatedModuloDeTesteObjectosDojo.class)
public class TesteDeBuscarCliente {
	
	@Inject
	private BuscarCliente buscarCliente;
	
	@Inject
	private SqlUnit sqlUnit;

	@BeforeClass
	public void prepararSqlUnit() {
		sqlUnit.loadEntitySet(ClientesFalso.class);
	}
	
	public void buscar_todos() {
		List<Cliente> contra = ClientesFalso.getTodos();
		List<String> prova = transform(contra, new ClienteToString());
	
		List<Cliente> list = buscarCliente.todos();
		List<String> res = transform(list , new ClienteToString());
		
		assertThat(res.size(), equalTo(3));
		assertThat(res, equalTo(prova));
	}
	
	private class ClienteToString implements Function<Cliente, String> {
		@Override
		public String apply(Cliente input) {
			return Objects.toStringHelper(input)
					.addValue(input.getId())
					.addValue(input.getNome())
					.addValue(input.getTelefone())
					.addValue(input.getEndereco())
					.addValue(input.getCpf())			
					.toString();						
		}
	}	
	
}