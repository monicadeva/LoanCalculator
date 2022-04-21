package com.loancalculator.utilities;

import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    Properties pro;

    public ReadConfig() {
        DOMConfigurator.configure("log4j.xml");
        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApplicationURL() {
        return pro.getProperty("baseURL");
    }

    public String getLoanAmount() {
        return pro.getProperty("loanAmt");
    }

    public String getLoanPeriod() {
        return pro.getProperty("loanPeriod");
    }

    public String getChromePath() {
        return pro.getProperty("chromepath");
    }

    public String getFirefoxPath() {
        return pro.getProperty("firefoxpath");
    }

    public String getEdgePath() {
        return pro.getProperty("edgepath");
    }
}
