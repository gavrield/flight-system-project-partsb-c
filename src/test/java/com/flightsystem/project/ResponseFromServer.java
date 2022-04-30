package com.flightsystem.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseFromServer {

    public static <T> T getObject(Class<T> tClass, String url) {
        Gson gson = new GsonBuilder().create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder
                (URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (response.statusCode() / 100 == 2) {
            return gson.fromJson(response.body(), tClass);
        }
        return null;
    }

    public static int getStatus(String url) {
        Gson gson = new GsonBuilder().create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder
                (URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return response.statusCode();
    }
}
