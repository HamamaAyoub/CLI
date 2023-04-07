package com.proxym.cli.serviceaccess;

import com.proxym.cli.service.ServiceObject;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class yamlParser {


    public static ServiceObject getYamlData(String fileName){
        Yaml yaml = new Yaml();
    InputStream inputStream = yamlParser.class.getResourceAsStream(fileName);
        ServiceObject serviceObject = yaml.loadAs(inputStream, ServiceObject.class);
        return serviceObject;
    }}


