---
layout: post-alpha
title: "AdminOnly: Permissão POST para _admin não funciona"
author: "Caio C. Petreanu"
published: true
partof: faq-crud-web
num: 0
---

## AdminOnly: Permissão de POST para usuário admin não passa nos TestesDeForm

### Sintoma

+ Acessos não funcionam para o login '(nome do projeto)_admin'

### Causa

+ Não foi feita a implementação de AdminOnly.class no projeto

+ Haviam erros de preenchimento dos dados no .xml que contém usuários, perfis e permissões associados a essa autenticação

### Solução(ões)

+ Adicionar a permissão para o perfil do usuário com os devidos cuidados

+ Implementar um AdminOnlyModule.class para o projeto. Se necessário tomar como base Módulos previamente implementados

+ Para aqueles que possuirem acesso, mais informações [aqui](https://github.com/objectos/riskoffice/wiki/%282012-03-02%29-An%C3%A1lise-de-Permiss%C3%A3o-e-Json-em-FormCreate-%28bug_fix%29\ "(2012 03 02) Análise de Permissão e Json em FormCreate (bug_fix)")
