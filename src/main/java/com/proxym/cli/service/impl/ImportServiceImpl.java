package com.proxym.cli.service.impl;

import com.proxym.cli.service.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImportServiceImpl implements ImportService {

    Logger log = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Override
    public void importData(String apiEndpoint, String datatype,String filePath) {


        try {
            URL url = new URL(apiEndpoint+"/api/"+ datatype);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
        } catch (IOException e) {
            log.error("url is malformed",e);
        }



    }
}
