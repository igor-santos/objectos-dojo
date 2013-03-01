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

import java.util.List;

import com.google.common.collect.ImmutableList;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;

/**
 * @author igor.santos@objectos.com.br (Igor Vinicius Pereira dos Santos)
 */
public class ClientesFalso implements EntitySet {

	public static final Cliente CLIENTE_01 = nova()
			.nome("Igor")
			.telefone("01234-5678")
			.endereco("rua sem endereço,456")
			.cpf("123.456.789-00")
			.novaInstancia();

	public static final Cliente CLIENTE_02 = nova()
			.nome("Augusto")
			.telefone("89784-5875")
			.endereco("rua dado,6")
			.cpf("123.852.258-00")
			.novaInstancia();

	public static final Cliente CLIENTE_03 = nova()
			.nome("Felipe")
			.telefone("09876-4567")
			.endereco("alameda dois,987")
			.cpf("987.645.321.99")
			.novaInstancia();
	
	private static List<Cliente> todos = ImmutableList.<Cliente> builder()
			.add(CLIENTE_01)
			.add(CLIENTE_02)
			.add(CLIENTE_03)
			
			.build();
			
	@Override
	public void load(SqlUnit sqlUnit) {
		sqlUnit.batchInsert(todos);
	}

	@Override
	public void truncate(Truncate truncate) {
		truncate.table("OBJ_BASE.CLIENTE");
	}
	
	private static ConstrutorDeClienteFalso nova() {
		return new ConstrutorDeClienteFalso();
	}

}