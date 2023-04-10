package com.proxym.cli.commands;

import com.proxym.cli.service.ServiceManager;
import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.serviceaccess.ExportFormService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import picocli.CommandLine;

import javax.persistence.EntityNotFoundException;


@CommandLine.Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")
@Singleton
public class ExportCommand implements Runnable {

   @CommandLine.Option(names = {"-b", "--batch"})
    private int dataSize;

    @CommandLine.Parameters
    private String serviceName;


    private ExportFormService exportFormService ;
    //dependency injection
    private  ServiceManager serviceManager;
    @Inject
    public ExportCommand(ServiceManager serviceManager, ExportFormService exportFormService){
        this.serviceManager= serviceManager;
        this.exportFormService=exportFormService;

    }

    public void run() {

        ServiceObject service =serviceManager.findByName(serviceName);
        if(service == null) {
            throw new EntityNotFoundException("No service found with name: " + serviceName);
        }
        exportFormService.export(service);
    }


}
