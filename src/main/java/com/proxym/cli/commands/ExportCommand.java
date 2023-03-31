package com.proxym.cli.commands;

import com.proxym.cli.service.ExportService;
import com.proxym.cli.service.impl.ExportServiceImpl;
import picocli.CommandLine;




@CommandLine.Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")


public class ExportCommand implements Runnable {


    @CommandLine.Option(names = {"-a", "--api-endpoint"}, defaultValue = "http://localhost:8080")
    private String apiEndpoint;

    @CommandLine.Parameters
    private String dataType;

    private ExportService exportService = new ExportServiceImpl(); // todo: inject using framework

    public void run() {
        exportService.export(apiEndpoint, dataType);
    }
}
