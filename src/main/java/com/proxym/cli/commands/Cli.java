package com.proxym.cli.commands;



import com.proxym.cli.servicedao.ServiceRepository;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import picocli.CommandLine;

@CommandLine.Command(subcommands = {ExportCommand.class },version = "1.0")
@Service
@Singleton
public class Cli implements Runnable {
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public void run() {
        System.out.println(serviceRepository);
    }
}
