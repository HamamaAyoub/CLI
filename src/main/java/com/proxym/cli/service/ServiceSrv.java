package com.proxym.cli.service;

import com.proxym.cli.servicedao.ServiceRepository;

import javax.transaction.Transactional;
import java.util.List;

public class ServiceSrv implements IServiceSrv {

    public ServiceSrv(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    private final ServiceRepository serviceRepository;
    @Transactional
    public ServiceObject save(ServiceObject serviceObject) {
        return serviceRepository.save(serviceObject);
    }
    @Transactional
    public List<ServiceObject> findByName(String name) {
        return serviceRepository.findByName(name);
    }
}
