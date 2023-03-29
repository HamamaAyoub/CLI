package com.proxym.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import org.apache.commons.io.IOUtils;
import picocli.CommandLine.Command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;


@Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")
public class ImportCommand implements Runnable {

    //@Option(names = {"-d", "--datatype"}, defaultValue = "none")
    //private String datatype;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(ImportCommand.class, args);
    }

    public void run() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/employees"))
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

    }
}
