package com.proxym.cli.commands;

import com.proxym.cli.service.ServiceManager;
import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.serviceaccess.ExportFormService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import picocli.CommandLine;


@CommandLine.Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")
@Singleton
public class ExportCommand implements Runnable {

   // @CommandLine.Option(names = {"-s", "--service"})
    //private String serviceName;

    @CommandLine.Parameters
    private String serviceName;



    private ExportFormService exportFormService ;
    //dependency injection
    private ServiceObject serviceObject;
    private  ServiceManager serviceManager;
    @Inject
    public ExportCommand(ServiceManager serviceManager){
        this.serviceManager= serviceManager;

    }




    public void run() {

         serviceObject = serviceManager.findByName(serviceName);
        System.out.println(serviceObject.getName());
        exportFormService.export(serviceObject);

    }
}
