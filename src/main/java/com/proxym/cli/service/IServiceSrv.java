package com.proxym.cli.service;

import java.util.List;

public interface IServiceSrv {
    public List<ServiceObject> findByName(String name);
    public ServiceObject save(ServiceObject serviceObject);
}
