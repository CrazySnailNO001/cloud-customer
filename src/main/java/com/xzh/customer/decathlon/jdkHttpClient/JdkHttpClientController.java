package com.xzh.customer.decathlon.jdkHttpClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-08 15:55
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/jdk_http_client")
public class JdkHttpClientController {
    @Resource
    private ObjectMapper objectMapper;

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
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @PostMapping("/postFormSync")
    public String postFormSync(@RequestBody(required = false) UserRequestDto userRequestDto) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        String requestBody ="name1=value1&name2=value2";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8763/jdk_http_client/postFormTest"))
                .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .POST(HttpRequest.BodyPublishers.ofString("name="+userRequestDto.getName()+"&age="+userRequestDto.getAge()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }



}
