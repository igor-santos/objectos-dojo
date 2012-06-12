---
layout: post-alpha
title: "Erros em Serviços de Cache"
author: "Marcos Piazzolla"
user: "MarcosPiazzolla"
date: "2012-06-06"
published: true 
partof: faq-crud-web
num: 1
outof: 1
---

## Erros em Serviços de Cache

Para este FAQ considere a seguinte amostra de código

	@Test
	public class TesteDeServicoDeCacheDeCliente extends TesteDeIntegracaoWeb {
	
	  private static final String URL = "api/admin/cache/cliente";
	
	  @Inject
	  private CacheDeCliente cache;
	
	  public void servico_deve_invalidar_cache() {
	    ProdutoKey produtoKey = new ProdutoKey("PROD_1024");
	
	    cache.porProduto(clienteKey);
	
	    List<Long> antes = transform(cache.getStats(), new ToLoadSuccessCount());
	
	    Map<String, String> cookies = login("admin");
	    WebResponse response = webClientOf(URL, cookies).post("");
	    assertThat(response.status(), equalTo(HttpServletResponse.SC_OK));
	
	    cache.porProduto(clienteKey);
	
	    List<Long> res = transform(cache.getStats(), new ToLoadSuccessCount());
	
	    for (int i = 0; i < res.size(); i++) {
	      assertThat(res.get(i), equalTo(antes.get(i) + 1));
	    }
	  }
	
	}

## Erros Comuns

###java.lang.AssertionError:Expected: &lt;2L&gt; got: &lt;1L&gt;

Verifique em seu teste se o método `webClientOf(URL, cookies).post("")` está sendo chamado da maneira 
correta, não invocando outro método por engano, como:`webClientOf(URL, cookies).get()`.

	    Map<String, String> cookies = login("admin");
	    WebResponse response = webClientOf(URL, cookies).post("");
	    assertThat(response.status(), equalTo(HttpServletResponse.SC_OK));

Caso contrário o seguinte _stacktrace_ aparecerá: 

    java.lang.AssertionError: 
    Expected: <2L>
    got: <1L>

  	  at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:21)
	  at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:8)
	  at br.com.objectos.dojo.cache.TesteDeServicoDeCacheDeCliente.servico_deve_invalidar_cache(TesteDeServicoDeCacheDeCliente.java:70)

Assim percebemos que o erro estava na chamada incorreta ao método `webClientOf(URL, cookies).post("")`.