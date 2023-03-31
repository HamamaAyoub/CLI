package com.proxym.cli.service.impl;

import com.proxym.cli.service.ExportService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Logger log = LoggerFactory.getLogger(ExportServiceImpl.class);

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
                        log.error("Api returned error code: {} , Api response body:{} " , httpResponse.statusCode()
                            , httpResponse.body());
                        System.exit(-1);
                    }
                    String responseType = httpResponse.headers().map().get("content-type").get(0);
                    String extension = responseType.replace("application/",".");
                    String filename = "export-" + LocalDateTime.now().toString().replace(":","_") + extension;
                    try {
                        IOUtils.write(httpResponse.body().getBytes(),new FileOutputStream(filename));
                    } catch (IOException e){
                        log.error("Failed to export response to disk", e);
                    }
                }).join();
            log.info("file exported successfully");
        }catch (CompletionException e){
            if(e.getCause() instanceof ConnectException){
                log.error("could not connect to api server: {}" , apiEndpoint,e);
                System.exit(-1);
            }
            log.error("unexpected error during api call",e);
            System.exit(-1);
        }
    }
}
