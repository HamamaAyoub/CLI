package com.proxym.cli.service;
import io.micronaut.http.annotation.*;

@Controller("/services")
public class ServiceController {
    private final ServiceSrv serviceSvr;

    public ServiceController(ServiceSrv serviceService) {
        this.serviceSvr = serviceService;
    }

    @Post("/")
    public ServiceObject create(@Body ServiceObject serviceObject) {
        return serviceSvr.save(serviceObject);
    }
}
