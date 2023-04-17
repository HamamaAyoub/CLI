package com.proxym.cli.serviceaccess;

import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.servicedao.ServiceRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
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


@Singleton
public class ExportFormService {


    @Inject
    private ServiceRepository repository;

    Logger log = LoggerFactory.getLogger(ExportFormService.class);



    public void export(ServiceObject targetService, Integer dataPerRequest) {
        String apiEndpoint = targetService.getUri();
        String dataType = targetService.getRequiredData();
        HttpResponse<String> httpResponse ;


        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint + "/api/" + dataType));

            if (dataPerRequest != null) {
                int pageSize = dataPerRequest;
                int pageNumber = 0;
                requestBuilder.uri(URI.create(apiEndpoint + "/api/" + dataType + "/" + pageNumber + "/" + pageSize));
            }

            HttpRequest request = requestBuilder.build();
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != 200) {
                log.error("Api returned error code: {} , Api response body:{} ", httpResponse.statusCode(), httpResponse.body());
                System.exit(-1);
            }

            String responseType = httpResponse.headers().map().get("content-type").get(0); //returns : application.xml
            String extension = responseType.replace("application/", ".");
            String filename = "export-" + LocalDateTime.now().toString().replace(":", "_") + extension;
            try {
                IOUtils.write(httpResponse.body().getBytes(), new FileOutputStream(filename));
            } catch (IOException e) {
                log.error("Failed to export response to disk", e);
            }

            log.warn("Service : " + dataType + " is exported successfully to : " + apiEndpoint);
            //System.out.println(dataType + " exported successfully to : "+apiEndpoint );
        } catch (ConnectException e) {
            log.error("could not connect to api server: {}", apiEndpoint, e);
            System.exit(-1);
        } catch (IOException | InterruptedException e) {
            log.error("unexpected error during api call", e);
            System.exit(-1);
        }
    }
}


