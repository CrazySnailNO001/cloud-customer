package com.xzh.customer.decathlon.jdkHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-08 15:55
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/jdk_http_client")
@Slf4j
public class JdkHttpClientController {
    @Resource
    private ObjectMapper objectMapper;

    public HttpClient getHttpClient() {
        HttpClient httpClient = HttpClient.newBuilder()
                //http 协议版本 1.1 或者 2
                .version(HttpClient.Version.HTTP_1_1)
                //连接超时时间，单位为毫秒
                .connectTimeout(Duration.ofMillis(5000))
                //连接完成之后的转发策略
                .followRedirects(HttpClient.Redirect.NEVER)
                //指定线程池
                .executor(Executors.newFixedThreadPool(5))
                //代理地址
//                .proxy((ProxySelector.of(new InetSocketAddress("http://www.baidu.com", 8080)))
                //认证，默认情况下 Authenticator.getDefault() 是 null 值，会报错
//                .authenticator(Authenticator.getDefault())
                //缓存，默认情况下 CookieHandler.getDefault() 是 null 值，会报错
//                .cookieHandler(CookieHandler.getDefault())
                .build();
        return httpClient;
    }

    @GetMapping("/getSync")
    public String getSync(@RequestParam(required = false) String data) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:8763/jdk_http_client/getTest?name=%s", data)))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.toString());
        return response.body();
    }

    @GetMapping("/getAsync")
    public String getAsync(@RequestParam(required = false) String data) throws InterruptedException, ExecutionException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:8763/jdk_http_client/getTest?name=%s", data)))
                .GET()
                .build();

        CompletableFuture<String> objectCompletableFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(result -> result);

        String response = objectCompletableFuture.get();

        return response;
    }

    @PostMapping("/postJsonSync")
    public String postJsonSync(@RequestBody(required = false) UserRequestDto userRequestDto) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8763/jdk_http_client/postJsonTest"))
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userRequestDto)))
                //设置超时时间
//                .timeout(Duration.ofMillis(4000))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    //TODO need test
    @PostMapping("/postFormSync")
    public String postFormSync(@RequestBody(required = false) UserRequestDto userRequestDto) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8763/jdk_http_client/postFormTest"))
                .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString("name=" + userRequestDto.getName() + "&age=" + userRequestDto.getAge()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @PostMapping("/postJsonAsync")
    public String postJsonAsync(@RequestBody(required = false) UserRequestDto userRequestDto) throws IOException, InterruptedException, ExecutionException {

//        HttpClient httpClient = HttpClient.newHttpClient();
        HttpClient httpClient = getHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8763/jdk_http_client/postJsonTest"))
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userRequestDto)))
                .build();

        CompletableFuture<String> stringCompletableFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    log.info("=================002 body : {}", body);
                    return body;
                });

        log.info("=================001");

        return stringCompletableFuture.get();
    }


}
