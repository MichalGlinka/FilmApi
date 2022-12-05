package com.example.filmapi.services;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DataReciver {
    public static final String OMDBAPI_KEY = "apikey=4d8675cf";
    public static final String OMDBAPI_URL = "http://www.omdbapi.com/";

    public String sendRequest(String url, String [] params) {
        if (params != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(url);
            builder.append("?");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i].replace(" ","%20"));
                if (i != (params.length - 1)){
                    builder.append("&");
                }
            }
            url = builder.toString();
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).join();
    }
}
