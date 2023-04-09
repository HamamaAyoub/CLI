package com.proxym.cli.service;

import jakarta.inject.Singleton;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Singleton
public class ServiceObject {
    @Id
    private String name;
    private String plateforme;
    private String status;
    private String uri;
    private String requiredData;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRequiredData() {
        return requiredData;
    }

    public void setRequiredData(String requiredData) {
        this.requiredData = requiredData;
    }








}
