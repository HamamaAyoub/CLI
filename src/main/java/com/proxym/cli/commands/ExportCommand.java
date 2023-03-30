package com.proxym.cli.commands;

import org.apache.commons.io.IOUtils;
import picocli.CommandLine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@CommandLine.Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")
public class ExportCommand implements Runnable {

    @CommandLine.Option(names = {"-a", "--api-endpoint"}, defaultValue = "http://localhost:8080")
    private String apiEndpoint;

    @CommandLine.Parameters
    private String dataType;



    public void run() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create( apiEndpoint + "/api/" + dataType ))
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(httpResponse -> {
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

    }
}
