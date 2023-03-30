package com.proxym.cli.commands;

import com.proxym.cli.service.ExportService;
import com.proxym.cli.service.impl.ExportServiceImpl;
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

    private ExportService exportService = new ExportServiceImpl(); // todo: inject using framework ?

    public void run() {
        exportService.export(apiEndpoint, dataType);
    }
}
