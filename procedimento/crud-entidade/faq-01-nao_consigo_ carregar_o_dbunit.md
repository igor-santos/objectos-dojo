---
layout: post-alpha
title: "Não consigo carregar o DBUnit"
author: "Caio C. Petreanu"
published: true
partof: faq-crud-entidade
num: 1
outof: 1
---

## Erros ao carregar o DBUnit nos Testes

### Sintoma

+ Não foi possível carregar o serviço do DBUnit (favor rever as configurações).

### Causa

+ Dependências necessárias de outros projetos precisam ser compiladas em conjunto a este.
  
### Solução(ões)

+ Compilar o projeto pelo maven e suas dependências de outros projetos (-am):
 
`mvn-ci -am -pl :(nome do seu projeto)`
