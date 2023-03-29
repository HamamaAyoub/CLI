package com.proxym.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")
public class ImportCommand implements Runnable {
    @Option(names = {"-d", "--datatype"}, defaultValue = "none")
    private String datatype;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(ImportCommand.class, args);
    }

    public void run() {
        // business logic here
        System.out.println("your data type is :  " + datatype);
    }
}
