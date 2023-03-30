package com.proxym.cli;

import com.proxym.cli.commands.ExportCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import org.apache.commons.io.IOUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

public class app {

public static void main(String[] args) throws Exception {
    new CommandLine(new ExportCommand()).execute(args);
    }}
