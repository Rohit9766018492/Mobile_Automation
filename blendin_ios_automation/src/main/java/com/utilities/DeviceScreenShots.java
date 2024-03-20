package com.utilities;

import com.appium.BaseTestAndroidAppium;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DeviceScreenShots extends BaseTestAndroidAppium {

    public File takeScreenshot() {
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        String destination = "C:\\Project\\blendin_ios_automation\\screenshots\\"+dateName+".png";
        File targetFile = new File(destination);

        try {
            FileHandler.copy(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srcFile;
    }
}
