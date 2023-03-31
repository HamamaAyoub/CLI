package com.proxym.cli.service.impl;

import com.proxym.cli.service.ExportService;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.concurrent.CompletionException;

public class ExportServiceImpl   implements ExportService {
    @Override
    public void export(String apiEndpoint, String dataType) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( apiEndpoint + "/api/" + dataType ))
                .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(httpResponse -> {
                    if(httpResponse.statusCode() != 200){
                        System.out.println("Api returned error code: " + httpResponse.statusCode());
                        System.out.println("Api response body: " + httpResponse.body());
                        System.exit(-1);
                    }
                    String responseType = httpResponse.headers().map().get("content-type").get(0);
                    String extension = responseType.replace("application/",".");
                    String filename = "export-" + LocalDateTime.now().toString().replace(":","_") + extension;
                    try {
                        IOUtils.write(httpResponse.body().getBytes(),new FileOutputStream(filename));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }).join();

            System.out.println("file exported successfully");
        }catch (CompletionException e){
            if(e.getCause() instanceof ConnectException){
                System.out.println("could not connect to api server: " + apiEndpoint);
                System.exit(-1);
            }
            throw e;
        }
    }
}
