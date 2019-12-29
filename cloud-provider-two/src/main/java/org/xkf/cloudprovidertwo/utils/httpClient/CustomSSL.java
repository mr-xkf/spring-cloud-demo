/**
 * FileName: CustomSSL
 * Author:   13235
 * Date:     2019/11/17 0:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovidertwo.utils.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2019/11/17
 * @since 1.0.0
 */
public class CustomSSL {
    public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(
                new File(""), "".toCharArray()
        ).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1"}, null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(connectionSocketFactory).build();
        HttpGet httpGet = new HttpGet("");
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        StatusLine statusLine = execute.getStatusLine();
        HttpEntity entity = execute.getEntity();
        EntityUtils.toString(entity);
        EntityUtils.consume(entity);

    }

    public void   httpGet() throws IOException {
        CloseableHttpClient aDefault = HttpClients.createDefault();
        HttpPost post = new HttpPost();
        post.setURI(URI.create(""));
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("", ""));
        nameValuePairs.add(new BasicNameValuePair("", ""));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("utf-8")));
        CloseableHttpResponse execute = aDefault.execute(post);
        execute.getStatusLine().getStatusCode();
        HttpEntity entity = execute.getEntity();
        EntityUtils.toString(entity);
        EntityUtils.consume(entity);

    }

    public void httpCookie() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(new BasicCookieStore());
        HttpGet httpGet = new HttpGet();
        CloseableHttpResponse execute = httpClient.execute(httpGet, httpClientContext);
        execute.getStatusLine().getStatusCode();
        HttpEntity entity = execute.getEntity();
        EntityUtils.consume(entity);
    }
  public void  credentialsProvider() {
      HttpPost post = new HttpPost();

      BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
      CloseableHttpClient httpClientBuilder = HttpClientBuilder.create().setMaxConnPerRoute(5).setMaxConnTotal(5)
              .build();

      FutureRequestExecutionService futureRequestExecutionService = new FutureRequestExecutionService
              (httpClientBuilder, Executors.newFixedThreadPool(5));

  }

}
