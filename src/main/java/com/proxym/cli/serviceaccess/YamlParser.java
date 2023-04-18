package com.proxym.cli.serviceaccess;

import com.proxym.cli.service.ServiceObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class YamlParser {



    public static ServiceObject getYamlData(String filePath){
        Yaml yaml = new Yaml();
        File file = new File(filePath);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, Object> linkedHashMap = yaml.load(inputStream);
        ServiceObject serviceObject = new ServiceObject();
        serviceObject.setName((String) linkedHashMap.get("name"));
        serviceObject.setPlateforme((String) linkedHashMap.get("plateforme"));
        serviceObject.setStatus((Boolean) linkedHashMap.get("status"));
        serviceObject.setUri((String) linkedHashMap.get("uri"));
        serviceObject.setRequiredData((String) linkedHashMap.get("requiredData"));
        return serviceObject;
    }



//gets the names of yaml files from a folder
public static List<String> getFileNames(String folderPath){
    String yamlExtention =".yaml";
    String yamlShortExtention=".yml";
    List<String> yamlFiles = new ArrayList<>();

    File directoryPath = new File(folderPath);
    String files[] = directoryPath.list();

    for(String s :files){
        if ((s.contains(yamlExtention))||(s.contains(yamlShortExtention))){
            yamlFiles.add(s);
        }
    }
        return yamlFiles;
}






}


