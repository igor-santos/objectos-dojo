---
layout: post
title: "Teste de Form Create"
author: "Hellen Escarate"
user: "hescarate"
published: true
tags: 
- procedimento
- form create
---

## FormCreate

### Caso de uso

Tomado como exemplo o cadastro de alunos para uma determinada instituição, será necessário implementar o
FormDeAlunosCreate. 

Mas antes de partir para o código, é importante analisar o que será feito.
1 - Como seria essa url?
2 - Que dados devem ser cadastrados pelo cliente?

### Procedimento: 

1- Criar a classe TesteDeFormDeAlunosCreate, o pacote deve ser ui.api.crud, no diretório de teste. 

2- Inserir a anotação @Test

3- Estender o TesteDeFormAbstratoCreate. Caso não exista, é necessário criá-lo. 

4- Feito o item 3, pode ser observado erro de compilação, então deve ser usado CRTL+1 p/ adicionar os
métodos não implementados.

5- No método que foi implementado depois de usar o CTRL+1, insira a URL:

```
  @Override
    String URL() {
        return "api/crud/instituicao_ensino/1/alunos";
    }
```


6- Se agora for rodado o teste, na aba "results of running" (no eclipse) devem aparecer quatro testes. Dois que
passaram e dois que não passaram. No console aparecerá o erro 404, pois a URL não foi encontrada, então o
próximo passo é adicionar a URL no Módulo. 


```
private void bindApiCrud() {
    at("/api/crud/instituicao_ensino/:id/alunos").serve(FormDeAlunosCreate.class);
}
``` 

7- Feito isso, deve aparecer erro de compilação, pois o FormDeAlunosCreate não existe, e como bons programadores tem
boas práticas, codificamos por TDD, portanto, use CRTL+1 e escolha "Create class FormDeAlunosCreate".
Um detalhe importante é atentar para o pacote correto, deve ser o mesmo pacote do Teste porém no diretório main.  

(se não souber o que é isso, veja o artigo sobre TDD). 



