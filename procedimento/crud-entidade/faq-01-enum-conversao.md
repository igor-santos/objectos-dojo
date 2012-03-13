---
layout: post
title: "Como tratar enums nos loaders?"
author: "Afonso Jardim Filgueiras"
user: "afilgueiras"
published: true 
partof: faq-crud-entidade
num: 1
outof: 1
---

## Conversão de Enum

Dada a seguinte Enum:

	public enum Departamento {
	  ENGENHARIA,
	  FINANCEIRO,
	  CONTABILIDADE
	}

O tipo dessa Enum pode estar definida de duas maneiras no JPA. ORDINAL ou STRING.

No caso de uma Enum do tipo ORDINAL, ela estará gravada da seguinte maneira no banco de dados:

	<EMPRESA.FUNCIONARIO MATRICULA="31256" NOME="AFONSO" DEPARTAMENTO="0"/>
	<EMPRESA.FUNCIONARIO MATRICULA="51476" NOME="HELLEN" DEPARTAMENTO="1"/>
	<EMPRESA.FUNCIONARIO MATRICULA="32569" NOME="MARCOS" DEPARTAMENTO="2"/>

Nesse caso, 0 = ENGENHARIA, 1 = FINANCEIRO e 2 = CONTABILIDADE. Seguindo a ordem que as propriedades são definidas no código.

No caso de uma Enum do tipo STRING, ela estará gravada da seguinte maneira do banco de dados

	<EMPRESA.FUNCIONARIO MATRICULA="31256" NOME="AFONSO" DEPARTAMENTO="ENGENHARIA"/>
	<EMPRESA.FUNCIONARIO MATRICULA="51476" NOME="HELLEN" DEPARTAMENTO="FINANCEIRO"/>
	<EMPRESA.FUNCIONARIO MATRICULA="32569" NOME="MARCOS" DEPARTAMENTO="CONTABILIDADE"/>

Observe que no caso de uma Enum do tipo String, as propriedades gravadas no banco de dados são idênticas as do código.

O teste de enum será parecido com isso:

    public void listar_departamento() {
      List<Funcionario> res = consulta.list();
      List<Departamento> deps = transform(res, new ToDepartamento());
     
      assertThat(deps.size(), equalTo(3));
      assertThat(deps.get(0), equalTo(Departamento.ENGENHARIA));
      assertThat(deps.get(1), equalTo(Departamento.FINANCEIRO));
      assertThat(deps.get(2), equalTo(Departamento.CONTABILIDADE));
    }

    private class ToDepartamento implements Function<Funcionario, Departamento> {
      @Override
      public Departamento apply(Funcionario input) {
        return input.getDepartamento();
      }
    }
  
  
Caso o tipo da Enum esteja definido como ORDINAL, utilizamos o seguinte código para conversão: 

    public Departamento getDepartamento() {
      int value = rs.getInt("FUNCIONARIO.DEPARTAMENTO");
      Departamento[] values = Departamento.values();
      return values[value];
    }


Caso o tipo da Enum esteja definido como STRING, utilizamos o seguinte código para conversão:

    public Departamento getDepartamento() {
      String val = rs.getString("FUNCIONARIO.DEPARTAMENTO");
      Departamento res = Departamento.valueOf(val);
      return res;
    }
    
Para mais informações, acessar os códigos através dos diretórios abaixo: <br>
[objectos-dojo-team/src/main/java/...](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/afilgueiras/enum_jdbc "diretório main")<br>
[objectos-dojo-team/src/test/java/...](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/afilgueiras/enum_jdbc "diretório test")
