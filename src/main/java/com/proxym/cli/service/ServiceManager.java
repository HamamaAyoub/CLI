package com.proxym.cli.service;

import com.proxym.cli.servicedao.ServiceRepository;
import io.micronaut.data.annotation.Id;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ServiceManager implements IServiceManager {

    private  ServiceRepository serviceRepository;
    private  ServiceObject serviceObject;

    public ServiceManager(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    @Override
    public ServiceObject findByName(String name) {
        return serviceRepository.findByName(name);
    }

    @Transactional
    public void save(ServiceObject serviceObject) {
        serviceRepository.save(serviceObject);
    }

    @Override
    public boolean existsByName(String name) {
        return serviceRepository.existsByName(name);
    }

    @Override
    public List<ServiceObject> findAll() {
        return serviceRepository.findAll();
    }

}
