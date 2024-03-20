package com.entities;

public class Device {
    private String deviceName = "default";
    private String displayResolution = "default";
    private String platformVersion = "default";
    private String platformName = "default";
    private String appActivity = "default";
    private String appPackage = "default";
    private String automationName = "default";
    private String deviceType = "default";
    private String deviceUDID;

    private Device() {   }

    public static Device get() {
        return new Device();
    }


    // +++++++++++++++++ Display Resolution +++++++++++++++++
    public String getDisplayResolution() {
        return this.displayResolution;
    }

    public Device setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
        return this;
    }

    // +++++++++++++++++ Device Name +++++++++++++++++
    public String getDeviceName() {
        return this.deviceName;
    }

    public Device setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    // +++++++++++++++++ Platform Name +++++++++++++++++
    public String getPlatformName() {
        return this.platformName;
    }

    public Device setPlatformName(String platformName) {
        this.platformName = platformName;
        return this;
    }

    // +++++++++++++++++ Platform OS Version +++++++++++++++++
    public String getPlatformOSVersion() {
        return platformVersion;
    }

    public Device setPlatformOSVersion(String platformVersion) {
        this.platformVersion = platformVersion;
        return this;
    }

    // +++++++++++++++++ Device Type Phone or Tablet +++++++++++++++++
    /**
     * Set string "phone" or "tablet"
     * @param phoneOrTablet
     */

    public String getDeviceType() {
        return deviceType;
    }
    public Device setDeviceType(String phoneOrTablet) {
        this.deviceType = phoneOrTablet;
        return this;
    }

    // +++++++++++++++++ Device UDID +++++++++++++++++
    public String getDeviceUDID() {
        return deviceUDID;
    }

    public void setDeviceUDID(String deviceUDID) {
        this.deviceUDID = deviceUDID;
    }

    // +++++++++++++++++ App Activity Name +++++++++++++++++
    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    // +++++++++++++++++ App Package Name +++++++++++++++++
    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }
    // +++++++++++++++++ App Package Name +++++++++++++++++
    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

}
