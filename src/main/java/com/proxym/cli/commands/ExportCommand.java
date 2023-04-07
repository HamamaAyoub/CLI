package com.proxym.cli.commands;

import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.service.ServiceSrv;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.List;


@CommandLine.Command(name = "export", description = "exporting file from plateforme",
    mixinStandardHelpOptions = true, version = "1.0")

public class ExportCommand implements Runnable {



    @CommandLine.Option(names = {"-s", "--service"})
    private String serviceName;

   // @CommandLine.Parameters
    //private String dataType;

   /* private ExportService exportService;

    public ExportCommand(ExportService exportService) {
        this.exportService = exportService;

    }*/
@Inject
ServiceSrv serviceSrv;
    // todo: dpendency injection to verify using micronaut

    public void run() {
        List<ServiceObject> serviceList = serviceSrv.findByName(serviceName);
        ServiceObject serviceObject = (ServiceObject) serviceList.get(0);
        System.out.println(serviceObject.getName());
    }
}
