package com.proxym.cli.commands;

import com.proxym.cli.service.ServiceManager;
import com.proxym.cli.service.ServiceObject;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import picocli.CommandLine;

import javax.persistence.PersistenceException;


@CommandLine.Command(name = "statusChange", description = "managing service status",
    mixinStandardHelpOptions = true, version = "1.0")
@Singleton
public class ServiceStatusCommand implements Runnable {


    private ServiceManager serviceManager;
    @Inject
    public ServiceStatusCommand(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

  @CommandLine.Parameters
    private String newStatus;


   @CommandLine.Parameters
    private  String serviceName;



    @Override
    public void run() {
        ServiceObject serviceObject = serviceManager.findByName(serviceName);
        boolean currentStatus = serviceObject.getStatus();
        if("enable".equals(newStatus)) {
            if(currentStatus) {
                System.out.println("Service is already enabled");
            } else {
                serviceObject.setStatus(true);
                serviceManager.save(serviceObject);
                System.out.println("Service enabled successfully");
            }
        } else if("disable".equals(newStatus)) {
            if(!currentStatus) {
                System.out.println("Service is already disabled");
            } else {
                serviceObject.setStatus(false);
               //try {
                   serviceManager.save(serviceObject);

               //}catch (PersistenceException e){
                 //  System.out.println("exeption");
               //}
                System.out.println("Service disabled successfully");
            }
        }
    }
}
