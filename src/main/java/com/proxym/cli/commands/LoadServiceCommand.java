package com.proxym.cli.commands;
import com.proxym.cli.service.ServiceManager;
import com.proxym.cli.service.ServiceObject;
import com.proxym.cli.serviceaccess.YamlParser;
import jakarta.inject.Singleton;
import picocli.CommandLine;
import java.io.File;
import java.util.List;

@CommandLine.Command(name = "load", description = "loading files from path",
    mixinStandardHelpOptions = true, version = "1.0")


@Singleton
public class LoadServiceCommand implements Runnable {


    @CommandLine.Parameters
    private String filePath;

    private ServiceManager serviceManager;


    public LoadServiceCommand(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }


    @Override
    public void run() {
        ServiceObject serviceObject = new ServiceObject();
        File file = new File(filePath);


        if (file.isFile()) {
            serviceObject = YamlParser.getYamlData(filePath);
            if (!serviceManager.existsByName(serviceObject.getName())) {
                serviceManager.save(serviceObject);
                System.out.println("Configuration file is loaded successfully");
            } else {
                System.out.println("Service configuration file " + serviceObject.getName() + " already exists in the database.");
            }


        } else if (file.isDirectory()) {
            List<String> files = YamlParser.getFileNames(filePath);
            if (files.isEmpty()) {
                System.out.println("No configuration files were found in the path directory");
            }

            for (int i = 0; i < files.size(); i++) {
                String singleFilePath = new String(filePath + "/" + files.get(i).toString());
                serviceObject = YamlParser.getYamlData(singleFilePath);
                if (serviceManager.existsByName(serviceObject.getName())) {
                    System.out.println("Service configuration with the name " + serviceObject.getName() + " already exists in the database. Skipping...");
                } else {
                    serviceManager.save(serviceObject);
                    System.out.println("Configuration for " + serviceObject.getName() + " was loaded successfully ");
                }
            }
        }
    }
}
