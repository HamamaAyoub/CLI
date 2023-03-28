package com.proxym.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "hello", description = "i'm greeting you from the cli",
    mixinStandardHelpOptions = true, version = "1.0")
public class HelloCommand implements Runnable {
    @Option(names = {"-n", "--name"}, defaultValue = "world")
    private String name;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(HelloCommand.class, args);
    }

    public void run() {
        // business logic here
        System.out.println("hello " + name);
    }
}
