package com.utilities;

import com.entities.Device;

public class DeviceRepo {

    public static ReadConfig readConfig = new ReadConfig();
    public static Device getCurrentDeviceDetails() {
        String deviceName = readConfig.getDeviceName();
        Device deviceEntity = Device.get();
        switch (deviceName){
            case "Pixel 4":
                deviceEntity.setDeviceName("Pixel 4");
                deviceEntity.setPlatformName("Android");
                deviceEntity.setPlatformOSVersion("13");
                deviceEntity.setDeviceType("Phone");
                deviceEntity.setDeviceUDID("9A201FFAZ00127");
                deviceEntity.setAutomationName("UiAutomator2");
                break;

            case "Pixel 6 Pro":
                deviceEntity.setDeviceName("Pixel 6 Pro - MA1925");
                deviceEntity.setPlatformName("Android");
                deviceEntity.setPlatformOSVersion("14");
                deviceEntity.setDeviceType("Phone");
                deviceEntity.setDeviceUDID("1C091FDEE0095R");
                deviceEntity.setAutomationName("UiAutomator2");
                break;

            case "Galaxy A31":
                deviceEntity.setDeviceName("Galaxy A31");
                deviceEntity.setPlatformName("Android");
                deviceEntity.setPlatformOSVersion("12");
                deviceEntity.setDeviceType("Phone");
                deviceEntity.setDeviceUDID("RZ8N821XZLJ");
                deviceEntity.setAutomationName("UiAutomator2");
                break;
        }

        return deviceEntity;
    }
}
