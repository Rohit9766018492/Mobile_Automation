package com.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    public String deviceName, platformName, platformVersion, appPackage, appActivity;
    public void getValuesFromConfig() {

        Properties property = new Properties();
        FileInputStream file = null;

        try {
            file = new FileInputStream("C:\\Project\\blendin_ios_automation\\src\\main\\resources\\config.properties");
            property.load(file);
        } catch (Exception e){
            e.printStackTrace();
        }

        deviceName = property.getProperty("deviceName");
        platformVersion = property.getProperty("platformVersion");
        platformName = property.getProperty("platformName");
        appActivity = property.getProperty("appActivity");
        appPackage = property.getProperty("appPackage");
    }

    public String getDeviceName() {
        getValuesFromConfig();
        return deviceName;
    }
    public String getPlatformVersion() {
        getValuesFromConfig();
        return platformVersion;
    }
    public String getPlatformName() {
        getValuesFromConfig();
        return platformName;
    }
    public String getAppActivity() {
        getValuesFromConfig();
        return appActivity;
    }
    public String getAppPackage() {
        getValuesFromConfig();
        return appPackage;
    }
}
