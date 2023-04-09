package com.proxym.cli.service;

import com.proxym.cli.servicedao.ServiceRepository;
import jakarta.inject.Singleton;


import javax.transaction.Transactional;
import java.util.List;
@Singleton
public class ServiceSrv implements IServiceSrv {

    public ServiceSrv(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    private final ServiceRepository serviceRepository;

    @Override
    public ServiceObject findByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Transactional
    public ServiceObject save(ServiceObject serviceObject) {
        return serviceRepository.save(serviceObject);
    }

}
