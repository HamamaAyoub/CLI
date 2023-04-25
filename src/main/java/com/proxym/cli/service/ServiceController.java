package com.proxym.cli.service;
import io.micronaut.http.annotation.*;

@Controller("/services")
public class ServiceController {
    private final ServiceManager serviceSvr;

    public ServiceController(ServiceManager serviceService) {
        this.serviceSvr = serviceService;
    }

    @Post("/")
    public ServiceObject create(@Body ServiceObject serviceObject) {
         return  serviceObject;
    }
}
