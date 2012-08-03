/*
* Copyright 2011 Objectos, Fábrica de Software LTDA.
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
package br.com.objectos.dojo.taguiar;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Element;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import br.com.objectos.comuns.sitebricks.Jetty;
import br.com.objectos.comuns.testing.dbunit.DBUnit;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.sitebricks.client.Web;
import com.google.sitebricks.client.Web.FormatBuilder;
import com.google.sitebricks.client.WebClient;
import com.google.sitebricks.client.WebResponse;
import com.google.sitebricks.client.transport.Text;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
public abstract class TesteDeIntegracaoWeb {

  private static final String STD_RESOURCE_DIR = "src/test/resources";

  private final Jetty server;

  private List<WebClient<String>> webClientCache;

  @Inject
  private Injector injector;

  @Inject
  protected DBUnit dbUnit;

  public TesteDeIntegracaoWeb() {
    server = new Jetty(STD_RESOURCE_DIR);
  }

  @BeforeSuite
  public void start() throws Exception {
    server.start();
  }

  @BeforeClass
  public void loadDefaultDataSupplierSet() {
    webClientCache = newArrayList();

    dbUnit.loadDefaultDataSet();
    dbUnit.load(null);
  }

  @AfterSuite
  public void stop() throws Exception {
    server.stop();
  }

  @AfterClass(alwaysRun = true)
  public void closeWebClients() {
    for (WebClient<?> client : webClientCache) {
      client.close();
    }
  }

  protected String getBaseUrl() {
    return server.getBaseUrl();
  }

  protected Map<String, String> login(String login) {
    String senha = login;
    return login(login, senha);
  }

  protected Map<String, String> login(String login, String senha) {
    String url = String.format("api/login?login=%s&senha=%s", login, senha);

    WebResponse response = webClientOf(url).post("");
    assertThat(response.status(), equalTo(HttpServletResponse.SC_OK));

    Map<String, String> headers = response.getHeaders();
    String cookie = headers.get("Set-Cookie");
    Map<String, String> cookies = ImmutableMap.of("Cookie", cookie);

    return cookies;
  }

  protected WebClient<String> webClientOf(String url) {
    Map<String, String> headers = ImmutableMap.of();
    return webClientOf(url, headers);
  }

  protected WebClient<String> webClientOf(String url, Map<String, String> headers) {
    WebClient<String> client = clientOf(url, headers).transports(String.class).over(Text.class);
    webClientCache.add(client);
    return client;
  }

  private FormatBuilder clientOf(String url, Map<String, String> headers) {
    String baseUrl = server.getBaseUrl();
    String _url = Joiner.on("/").skipNulls().join(baseUrl, url);
    return injector.getInstance(Web.class).clientOf(_url, headers);
  }

  protected class ToText implements Function<Element, String> {
    public ToText() {
    }

    @Override
    public String apply(Element input) {
      return input.text();
    }
  }

}