package com.proxym.cli.commands;


import com.proxym.cli.service.ServiceManager;
import com.proxym.cli.service.ServiceObject;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "getservice", description = "listing configuration from database",
    mixinStandardHelpOptions = true, version = "1.0")


@Singleton
public class GetServiceCommand implements Runnable{

    @CommandLine.Option(names = {"-n", "--name"})
    private String serviceName;


    private ServiceManager serviceManager;
    @Inject
    public GetServiceCommand(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void run() {
        List<ServiceObject> services = serviceManager.findAll();
        if (serviceName == null){
        System.out.println("- List of existing services : ");
        for (ServiceObject service : services) {
            System.out.println("+ "+ service.getName());
        }}else {
            ServiceObject serviceObject = serviceManager.findByName(serviceName);
            System.out.println("- Details of "+serviceName+" service file :  ");
            System.out.println("+ name : "+ serviceObject.getName());
            System.out.println("+ Plateforme : "+ serviceObject.getPlateforme());
            if (serviceObject.getStatus() == true){
                System.out.println("+ Status : enabled ");
            }else System.out.println("+ Status : disabled");
            System.out.println("+ Uri : "+ serviceObject.getUri());
            System.out.println("+ Required data : "+ serviceObject.getRequiredData());
        }


    }
}
