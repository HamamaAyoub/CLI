package com.proxym.cli.service;

import com.proxym.cli.servicedao.ServiceRepository;
import jakarta.inject.Singleton;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Singleton
public class ServiceSrv implements IServiceSrv {

    public ServiceSrv(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceObject> findByName(String name) {
        return null;
    }

    @Transactional
    public ServiceObject save(ServiceObject serviceObject) {
        return serviceRepository.save(serviceObject);
    }

}
