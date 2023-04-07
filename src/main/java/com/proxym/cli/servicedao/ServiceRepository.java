package com.proxym.cli.servicedao;

import com.proxym.cli.service.ServiceObject;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Repository
@Singleton
public interface ServiceRepository extends JpaRepository<ServiceObject,String> {
    List<ServiceObject> findByName(String name);
}
