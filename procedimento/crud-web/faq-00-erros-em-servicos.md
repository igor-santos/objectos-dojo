---
layout: post-alpha
title: "Erros em Serviços"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-03-09"
published: true 
partof: faq-crud-web
num: 0
outof: 1
---

## Erros em Serviços

Para este FAQ considere a seguinte a seguinte amostra de código

      @Test
      public class TesteDeServicoProduto extends TesteDeIntegracaoWeb {

        private final String URL = "api/bd/produto";

        public void usuarios_nao_autenticados_nao_devem_listar_itens() {
          WebResponse response = webClientOf(URL).get();

          assertThat(response.status(), equalTo(HttpServletResponse.SC_UNAUTHORIZED));
        }

        public void usuarios_autenticados_devem_listar_itens() {
          Map<String, String> cookies = login("alberto");

         WebResponse response = webClientOf(URL, cookies).get();
         assertThat(response.status(), equalTo(HttpServletResponse.SC_OK));

         List<String> res = new ToTableRow(response).get();
         assertThat(res.size(), equalTo(2));
         assertThat(res.get(0), equalTo("Produto Código"));
         assertThat(res.get(1), equalTo("Pizza PIZ"));
         assertThat(res.get(2), equalTo("Refrigerante REF"));
       }
    
    }

## Erros Comuns

### Erro 404: A URL foi definida corretamente ?

Verifique se no inicio da classe de teste a URL foi definida corretamente, sem nenhum `/` no inicio

    @Test
      public class TesteDeProduto extends TesteDeIntegracaoWeb {

      private final String URL = "api/bd/produto";
    }
    
Caso contrário o seguinte _stacktrace_ aparecerá:

Primeiro teste:

    java.lang.AssertionError: 
    Expected: <200>
    got: <404>

	  at br.com.objectos.dojo.TesteDeServicoProduto.usuario_autenticados_devem_listar_itens(TesteDeServicoProduto.java:42)
	  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	  
Segundo teste:

    java.lang.AssertionError: 
    Expected: <401>
    got: <404>

	  at br.com.objectos.dojo.TesteDeServicoProduto.usuarios_nao_autenticados_nao_devem_listar_itens(TesteDeServicoProduto.java:35)
	  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)

Assim percebemos que o erro estava na URL que foi definida no teste

    private final String URL = "/api/bd/produto";

Portanto é só remover a barra no inicio da URL

### BindException: Address already in use - O Jetty ou o jekyll estão sendo utilizados no terminal ?

Verifique no terminal se o Jetty ou o jekyll estão em uso, se sim o seguinte _stacktrace_ aparecerá

    java.net.BindException: Address already in use
	  at sun.nio.ch.Net.bind(Native Method)
	  at sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:126)
	  at sun.nio.ch.ServerSocketAdaptor.bind(ServerSocketAdaptor.java:59)
	  at org.eclipse.jetty.server.nio.SelectChannelConnector.open(SelectChannelConnector.java:172)
	  at org.eclipse.jetty.server.AbstractConnector.doStart(AbstractConnector.java:300)

De acordo com o _stacktrace_ a exceção ocorre por conta das aplicações em uso no terminal, basta
encerrá-las para que o problema seja resolvido.

### Erro 401 - O login fornecido no teste está correto ? 

Verifique se o login fornecido no teste está correto

    public void usuarios_autenticados_devem_listar_itens() {
      Map<String, String> cookies = login("alberto");

      WebResponse response = webClientOf(URL, cookies).get();
      assertThat(response.status(), equalTo(HttpServletResponse.SC_OK));
    }

Caso contrário, o seguinte _stacktrace_ aparecerá:

    java.lang.AssertionError: 
    Expected: <200>
    got: <401>

	  at br.com.objectos.dojo.TesteDeIntegracaoWeb.login(TesteDeIntegracaoWeb.java:98)
	  at br.com.objectos.dojo..TesteDeIntegracaoWeb.login(TesteDeIntegracaoWeb.java:91)

De acordo com o _stacktrace_ o erro 401, ocorrerá exatamente na linha onde foi fornecido o login 
na classe de teste

    public void usuario_autenticados_devem_listar_itens() {
      Map<String, String> cookies = login("alberto");
    }
    
Caso o login estiver incorreto, basta substituir o mesmo pelo login correto.




