---
layout: post-alpha
title: "GuiceConfigurationException: Converter e Json"
author: "Caio C. Petreanu"
published: true
partof: faq-crud-forms
num: 0
outof: 0
---

## GuiceConfigurationException: Não foi feito bind no Converter do Sitebricks

### Sintoma

+ Não há implementação ou bind para o Converter do Sitebricks

+ Aparecem erros relacionados ao método jasoJacksonTransport do Json

### Causa

+ Não foi feito bind para o Converter no Bootstrap do Módulo

+ Para aqueles que possuirem acesso, existem mais detalhes do problema na Wiki do projeto

### Solução(ões)

+ Adicionar a permissão para o perfil do usuário com os devidos cuidados

+ Implementar um AdminOnlyModule.class para o projeto. Se necessário tomar como base Módulos previamente implementados

+ Implementar o Bind do Converter no Bootstrap do Módulo específico do projeto
 
+ Mais informações na Wiki do projeto
