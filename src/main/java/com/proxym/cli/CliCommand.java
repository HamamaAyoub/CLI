package com.proxym.cli;

import com.proxym.cli.commands.ExportCommand;
import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.servicedao.ServiceRepository;
import io.micronaut.configuration.picocli.PicocliRunner;

import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "cli", description = "...",
        mixinStandardHelpOptions = true,subcommands = {ExportCommand.class },version = "1.0")



public class CliCommand implements Runnable {


    public static void main(String[] args) throws Exception {
        PicocliRunner.run(CliCommand.class, args);
    }

    public void run() {

    }
}
