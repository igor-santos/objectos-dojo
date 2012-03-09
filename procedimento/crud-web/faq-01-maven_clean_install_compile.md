---
layout: post-alpha
title: "Maven: clean, install e compile"
author: "Caio C. Petreanu"
published: true
partof: faq-crud-web
num: 1
outof: 1
---

## compile, clean e install

### Sintoma  

+ Tempo desperdiçado tentando entender erros de stacktrace no terminal

+ Faltam dependências na compilação do projeto

### Causa

+ Falta de entendimento do uso correto dos comandos do Maven

### Solução(ões)  

Esses comandos executam plugins do Maven 2, que tem diferentes funcionalidades para diferentes fases de build e deploy do projeto.  

+ Para que serve mvn clean?

Ele remove do diretório do projeto, ou dos projetos, todos os dados de build.

+ E o mvn install?

Ele instala o artefato gerado pelo projeto (.jar ou .war) no repositório local

+ E o mvn compile?

Usando apenas o compile, todos os códigos-fonte da aplicação são compilados

+ Certo, mas e quanto ao -ci? Ah, e também tem o -am.

O -ci é o alias para: mvn -DskipTests=true clean install". O seja, ele roda os dois plugins (clean e install) e pula os testes (@Test). Já o -am
