package com.proxym.cli.service;

import java.util.List;

public interface IServiceManager {
    public ServiceObject findByName(String name);
    public void save(ServiceObject serviceObject);
    public boolean existsByName(String name);
    public List<ServiceObject> findAll();
}
