package com.proxym.cli.servicedao;

import com.proxym.cli.service.ServiceObject;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import jakarta.inject.Singleton;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
@Singleton
public interface ServiceRepository extends JpaRepository<ServiceObject,String> {

    @Query(value = "SELECT s FROM Service s WHERE s.name = :name")
    List<ServiceObject> findByName(String name);
}
