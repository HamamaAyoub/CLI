package com.proxym.cli.service;

import com.proxym.cli.servicedao.ServiceRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import javax.transaction.Transactional;

@Singleton
public class ServiceManager implements IServiceManager {

    private  ServiceRepository serviceRepository;
    private  ServiceObject serviceObject;

    public ServiceManager(ServiceRepository serviceRepository,ServiceObject serviceObject) {
        this.serviceRepository = serviceRepository;
        this.serviceObject=serviceObject;


    }


    @Override
    public ServiceObject findByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Transactional
    public ServiceObject save(ServiceObject serviceObject) {
        return serviceRepository.save(serviceObject);
    }

}
