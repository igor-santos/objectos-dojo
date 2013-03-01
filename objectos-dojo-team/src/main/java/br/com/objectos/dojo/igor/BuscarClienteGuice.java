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

import br.com.objectos.comuns.relational.jdbc.NativeSql;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author igor.santos@objectos.com.br (Igor Vinicius Pereira dos Santos)
 */
class BuscarClienteGuice implements BuscarCliente {

	private final Provider<NativeSql> sqlProvider;
		
	@Inject
	public BuscarClienteGuice(Provider<NativeSql> sqlProvider) {
		this.sqlProvider = sqlProvider;
	}

	@Override
	public List<Cliente> todos() {
		return null;
	}

}