package com.proxym.cli;

import com.proxym.cli.commands.Cli;
import picocli.CommandLine;

public class CliCommand {

public static void main(String[] args) throws Exception {
    new CommandLine(new Cli()).execute(args);
    }}
