---
layout: post-alpha
title: "Git :: Fiz um merge incorreto. E agora, como resolvo?"
author: "Caio Catanzaro Petreanu"
published: true
partof: git
num: 4
outof: 4
---

## Fiz um merge em uma branch que eu não deveria fazer, e agora?

Por acidente, você pode acabar fazendo merges ou pulls de uma branch para outra completamente distinta.

Mandando um pull request com essas atualizações não pode ocorrer! São um RISCO e DE FORMA ALGUMA podem ser aceitas e integradas no projeto original!

<div class="alert alert-info">
  O pull nada mais é que um fetch e um merge no mesmo comando.
</div>

### 1. Mas e agora, como posso resolver isso?

Antes de continuarmos para as soluções, que tal simularmos casos de merges errados?

__Observação__: Como existe vasta base de conhecimento dos comandos git na Web (vide algumas em [Referências][0]), 
a continuação este artigo foca-se em quando e como usar certas soluções a partir de um novo caso.

#### Antes de começar:

* Faça um novo repositório chamado __repo__ no Github

* Coloque-o diretório /tmp/repo

#### Criando o cenário

Crie uma branch chamada eu_vc_00_buscar_funcionario e entre nela.

      $ git checkout -b eu_vc_00_buscar_acionista
      Switched to a new branch 'eu_vc_00_buscar_acionista'

E execute os comandos abaixo para criar os arquivos:

* TesteDeBuscarAcionista
* BuscarAcionista

<p></p>

      $ touch TesteDeBuscarAcionista.java
      $ touch BuscarAcionista.java

Adicione os arquivos, faça um commit e dê um push:

      $ git add BuscarAcionista.java
      $ git add TesteDeBuscarAcionista.java
      
      $ git commit -m "Implementado: TesteDeBuscarAcionista e BuscarAcionista"
      
      [eu_vc_00_buscar_funcionario 9f9ebd9] Implementado: TesteDeBuscarAcionista e BuscarAcionista
       0 files changed, 0 insertions(+), 0 deletions(-)
       create mode 100644 BuscarAcionista.java
       create mode 100644 TesteDeBuscarAcionista.java
       
      $ git push origin eu_vc_00_buscar_funcionario
      Counting objects: 3, done.
      Delta compression using up to 3 threads.
      Compressing objects: 100% (2/2), done.
      Writing objects: 100% (2/2), 299 bytes, done.
      Total 2 (delta 0), reused 0 (delta 0)
      To git@github.com:cpetreanu/repo.git
       * [new branch]      eu_vc_00_buscar_funcionario -> eu_vc_00_buscar_funcionario

Agora, antes de mais nada volte para a master.

      $ git checkout master
      Switched to branch 'master'

Perfeito! Agora crie uma branch chamada joao_maria_00_form_aeroporto_create e entre nela.

      $ git checkout -b joao_maria_00_form_aeroporto_create
      Switched to a new branch 'joao_maria_00_form_aeroporto_create'

E crie, da mesma forma:

      $ touch TesteDeFormDeAeroportoCreate.java
      $ touch FormDeAeroportoCreate.java
      $ touch FormDeAeroportoCreateGuice.java

Faça os adds, commit e push:

      $ git add TesteDeFormDeAeroportoCreate.java
      $ git add FormDeAeroportoCreate.java
      $ git add FormDeAeroportoCreateGuice.java
      
      $ git commit -m "Web: Implementando FormDeAeroportoCreate"
      [joao_maria_00_form_aeroporto_create 7688f2d] Web: Implementando FormDeAeroportoCreate
       0 files changed, 0 insertions(+), 0 deletions(-)
       create mode 100644 FormDeAeroportoCreate.java
       create mode 100644 FormDeAeroportoCreateGuice.java
       create mode 100644 TesteDeFormDeAeroportoCreate.java
      
      $ git push origin joao_maria_00_form_aeroporto_create
      Counting objects: 3, done.
      Delta compression using up to 3 threads.
      Compressing objects: 100% (2/2), done.
      Writing objects: 100% (s2/2), 308 bytes, done.
      Total 2 (delta 0), reused 0 (delta 0)
      To git@github.com:cpetreanu/repo.git
       * [new branch]      joao_maria_00_form_aeroporto_create -> joao_maria_00_form_aeroporto_create

Por fim, dentro da branch joao_maria_00_form_aeroporto_create, faça merge com eu_vc_00_buscar_funcionario (e também um ``git status``, se preferir), ``git commit -m`` e ``git push origin eu_vc_00_buscar_funcionario``. Veja:

     $ git merge eu_vc_00_buscar_acionista
     Merge made by recursive.
      0 files changed, 0 insertions(+), 0 deletions(-)
      create mode 100644 BuscarAcionista.java
      create mode 100644 TesteDeBuscarAcionista.java
     
     $ git commit -m "Merge acidental"
     # On branch joao_maria_00_form_aeroporto_create
     # Your branch is ahead of 'origin/joao_maria_00_form_aeroporto_create' by 2 commits.
     #
     nothing to commit (working directory clean)
     
     $ git push origin joao_maria_00_form_aeroporto_create
     Counting objects: 4, done.
     Delta compression using up to 3 threads.
     Compressing objects: 100% (2/2), done.
     Writing objects: 100% (2/2), 339 bytes, done.
     Total 2 (delta 1), reused 0 (delta 0)
     To git@github.com:cpetreanu/repo.git
        7688f2d..0606a81  joao_maria_00_form_aeroporto_create -> joao_maria_00_form_aeroporto_create

Considerando que os trabalhos nas branchs ``joao_maria_00_form_aeroporto_create`` e ``eu_vc_00_buscar_funcionario`` são completamente distintos, ou seja. não podem intercalar-se; verifique lá no Github, sua branch agora contém arquivos que não deveriam estar lá!

<div class="alert alert-info">
 Obs. Sempre cheque seus arquivos na branch, e seus pull requests com o Diff na página do Github para identificar aquilo que foi mandado para origin, ou que está sendo mandado para o projeto original. Se não souber verificar o problema, de nada adianta saber solucioná-lo!
</div>

Tendo isso em mente, vem a grande pergunta: "Mas e agora, como faço para reverter isso?"

Pois bem. Veja as seção abaixo para entender as maneiras corretas, para que posteriormente você possa corrigir com facilidade esse tipo de erro.

### 2.1 Quando usar o git checkout?



### 2.2 Quando usar o git reset?

#### Adaptando o cenário

Crie uma branch chamada



### 2.3 Quando usar o git revert?

#### Adaptando o cenários

Crie uma branch chamada



### 3. Quando fazer um re-fork

Em alguns casos, quando seu trabalho na branch acabou de começar, existem poucos arquivos no projeto original ou existem uma série de erros na execução dos comandos anteriores, uma opção simples é refazer a fork do projeto original.

Para isso, você pode seguir os seguintes passos:

1. Faça cuidadosamente o backup de todos os arquivos importantes que você criou ou modificou
1. Remova a pasta do projeto da sua máquina, usando um comando como:

    rm -f -R /tmp/repo/

1. Vá no repositório do fork __repo__ em sua página.

1. Clique no botão "Admin", na parte superior direita da página

1. Clique no botão "Delete this repository" ... e confirme a remoção do fork clicando em "I understand, delete this repository"

1. Acesse a página do projeto original e faça novamente um fork e clone ele (se não lembrar como fazer clique [aqui][1])

1. Use os comandos

    mkdir /tmp/repo/
    cd /tmp/repo

Se necessário configure a url do projeto original usando

    $git remote add <alias-da-url> <url-do-projeto-original>

Seu fork estará agora idêntico a úĺtima versão do projeto

1. Entre na branch correta e recupere os backups feitos.

1. Pronto, você acaba de desfazer os comandos errados usando uma das formas mais árduas (tirando os possíveis erros nos comandos citados nas outras seções!)

<div id="referencias"> </div>

### Referências<div id="referencias"> </div>

[objectos-dojo :: Tutorial gh-pages][1]

Crie os seguintes arquivos:

<div class="alert alert-info"><p><b>Observação:</b> Nos comandos abaixo, ao usar cat &gt;&gt; &lt;arquivo&gt;, é concatenado um texto por input (stdin) ao &lt;arquivo&gt;, e ele é fechado usando ^D (que no teclado, significa ctrl pressionado e 'd'). Use copiar e colar no terminal para agilizar as coisas.</p></div>

* TesteDeBuscarFuncionario.java

      $ cat >> TesteDeBuscarFuncionario.java
      /*
      * Copyright 2012 Objectos, Fábrica de Software LTDA.
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
      @Test
      @Guice(modules = { ModuloDeTesteObjectosDojo.class })
      public class TesteDeBuscarFuncionario {
      
        @Inject
        private BuscarFuncionario buscarFuncionario;
      
        @Inject
        private DBUnit dbUnit;
      
        public void prepararDBUnit() {
          dbUnit.loadDefaultDataSet();
        }
      
        public void busca_por_id() {
          Funcionario res = buscarFuncionario.porId(3);
      
          assertThat(res.getId(), equalTo(3));
          assertThat(res.getMatricula(), equalTo("T0033000"));
          assertThat(res.getNome(), equalTo("Briann Adams"));
          assertThat(res.getDataNascimento(), equalTo(new LocalDate(1980, 6, 01)));
          assertThat(res.getDataAdmissao(), equalTo(new DateTime(2004, 12, 10, 9, 0)));
          assertThat(res.getDataDemissao(), equalTo(new DateTime(2012, 1, 3, 12, 30)));
        }
      
      }

* BuscarFuncionario.java

      $ cat >> BuscarFuncionario.java
      /*
      * Copyright 2012 Objectos, Fábrica de Software LTDA.
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
      @ImplementedBy(BuscarFuncionarioGuice.class)
      public interface BuscarFuncionario {
      
        Funcionario porId(int id);
      
      }

* BuscarFuncionarioGuice.java

      $ cat >> BuscarFuncionarioGuice.java
      /*
       * Copyright 2012 Objectos, Fábrica de Software LTDA.
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
      class BuscarFuncionarioGuice implements BuscarFuncionario {
      
        private final Provider<NativeSql> sqlProvider;
      
        @Inject
        public BuscarFuncionarioGuice(Provider<NativeSql> sqlProvider) {
          this.sqlProvider = sqlProvider;
        }
      
        @Override
        public Funcionario porId(int id) {
          return newSelect()
      
              .add("where FUNCIONARIO.ID = ?").param(id)
      
              .single();
        }
        
        private NativeSql newSelect() {
          return sqlProvider.get()
      
              .add("select *")
              .add("from DATABASE.FUNCIONARIO as FUNCIONARIO")
      
              .add("join DATABASE.SUPERIOR as SUPERIOR")
              .add("from FUNCIONARIO.SUPERIOR_ID as SUPERIOR.ID")
      
              .andLoadWith(new FuncionarioLoader());
        }
      
      }

[id]: /path/to/img.jpg "Admin"
[id]: /path/to/img.jpg "Delete this repositoy"
[id]: /path/to/img.jpg "I understand, delete this repository"

[0]: #referencias "Referências"
[1]: ../contribua/00-importar.html "objectos-dojo :: Tutorial gh-pages"