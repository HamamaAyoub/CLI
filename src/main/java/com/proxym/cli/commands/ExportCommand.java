package com.proxym.cli.commands;

import com.proxym.cli.service.IServiceSrv;
import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.service.ServiceSrv;
import com.proxym.cli.serviceaccess.ExportFormService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import picocli.CommandLine;

import java.util.List;


@CommandLine.Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")
@Singleton
public class ExportCommand implements Runnable {


    private final ExportFormService exportFormService;
    @Inject
    public ExportCommand(ExportFormService exportFormService){
        this.exportFormService= exportFormService;

    }



    @CommandLine.Option(names = {"-s", "--service"})
    private String serviceName;

   // @CommandLine.Parameters
    //private String dataType;

   /* private ExportService exportService;

    public ExportCommand(ExportService exportService) {
        this.exportService = exportService;

    }*/


@Inject
private IServiceSrv serviceSrv;
    // todo: dpendency injection to verify using micronaut

    public void run() {
      /* List<ServiceObject> serviceList = serviceSrv.findByName(serviceName);
        ServiceObject serviceObject = (ServiceObject) serviceList.get(0);
        System.out.println(serviceObject.getName()); */

        ServiceObject serviceObject = new ServiceObject();
        serviceObject.setName("test");
        serviceObject.setPlateforme("http://localhost:8080");
        serviceObject.setRequiredData("employees");
        serviceObject.setStatus("enabled");

        exportFormService.export(serviceObject);


    }
}
