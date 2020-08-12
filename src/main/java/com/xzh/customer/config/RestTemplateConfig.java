package com.xzh.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.ProxySelector;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class RestTemplateConfig {

    // 实现负载均衡,默认轮训
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setConnectionManager(poolingClientConnectionManager())
                .setDefaultRequestConfig(requestConfig())
                .setRoutePlanner(systemDefaultRoutePlanner())
                .setKeepAliveStrategy(connectionKeepAliveStrategy())
                .build();
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingClientConnectionManager() {
        PoolingHttpClientConnectionManager poolHttpConnManager =
                new PoolingHttpClientConnectionManager(timeToLive, TimeUnit.MILLISECONDS);
        poolHttpConnManager.setMaxTotal(maxTotal);
        poolHttpConnManager.setDefaultMaxPerRoute(maxPerRoute);
        return poolHttpConnManager;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
    }

    /**
     * 设置代理
     */
//    @Bean
//    public DefaultProxyRoutePlanner defaultProxyRoutePlanner() {
//        HttpHost proxy = new HttpHost(httpsProxyHostName, httpsProxyPort);
//        return new DefaultProxyRoutePlanner(proxy);
//    }

    @Bean
    public SystemDefaultRoutePlanner systemDefaultRoutePlanner() {
        return new SystemDefaultRoutePlanner(ProxySelector.getDefault());
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException exception) {
                        log.error(exception.getMessage());
                    }
                }
            }
            return keepAlive;
        };
    }

    @Value("${httpClient.pool.timeToLive}")
    private int timeToLive;
    @Value("${httpClient.pool.maxTotal}")
    private int maxTotal;
    @Value("${httpClient.pool.maxPerRoute}")
    private int maxPerRoute;
    @Value("${httpClient.pool.socketTimeout}")
    private int socketTimeout;
    @Value("${httpClient.pool.connectTimeout}")
    private int connectTimeout;
    @Value("${httpClient.pool.connectionRequestTimeout}")
    private int connectionRequestTimeout;
    @Value("${httpClient.pool.keepAlive}")
    private int keepAlive;
//    @Value("${httpClient.proxy.https.hostname}")
//    private String httpsProxyHostName;
//    @Value("${httpClient.proxy.https.port}")
//    private int httpsProxyPort;
//    @Value("${httpClient.proxy.https.schema}")
//    private String httpsProxySchema;
}
