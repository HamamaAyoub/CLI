package com.proxym.cli.commands;


import com.proxym.cli.commands.ExportCommand;
import picocli.CommandLine;

@CommandLine.Command(subcommands = {ExportCommand.class },version = "1.0")

public class Cli implements Runnable {


    @Override
    public void run() {

    }
}
