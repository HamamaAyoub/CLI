package com.proxym.cli.service;
import io.micronaut.http.annotation.*;

@Controller("/services")
public class ServiceController {
    private final ServiceSrv serviceService;

    public ServiceController(ServiceSrv serviceService) {
        this.serviceService = serviceService;
    }

    @Post("/")
    public ServiceObject create(@Body ServiceObject serviceObject) {
        return serviceService.save(serviceObject);
    }
}
