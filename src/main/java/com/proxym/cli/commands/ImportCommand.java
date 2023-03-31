package com.proxym.cli.commands;


import picocli.CommandLine;

@CommandLine.Command(name = "import", description = "importing file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")


public class ImportCommand implements Runnable {

    @CommandLine.Option(names = {"-a", "--api-endpoint"}, defaultValue = "http://localhost:8080")
    private String apiEndpoint;

    @CommandLine.Parameters
    private String dataType;

    @Override
    public void run() {

    }
}
