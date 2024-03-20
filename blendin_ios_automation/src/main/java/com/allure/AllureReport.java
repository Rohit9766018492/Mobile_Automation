package com.allure;

import com.appium.BaseTestAndroidAppium;
import com.utilities.DeviceRepo;
import com.utilities.ReportUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.io.FileHandler;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class AllureReport extends BaseTestAndroidAppium {

    public static Logger LOG = LogManager.getLogger(AllureReport.class);
    public static File srcFolder = new File("C:\\Project\\blendin_ios_automation\\allure-report");
    public static String allureGenerate = "cmd /c start cmd.exe /k allure generate --clean --single-file C:\\Project\\blendin_ios_automation\\allure-results";
    int filesCopied;
    public String allureReportStoredPath = "D:\\allure-reports";

    public String buildDescription = "AppDetails: Google News v1.0.0.1"
            +"\n" + "DeviceName: "+ DeviceRepo.getCurrentDeviceDetails().getDeviceName()
            +"\n" + "OSVersion: " + DeviceRepo.getCurrentDeviceDetails().getPlatformOSVersion()
            +"\n" + "PlatformName: " + DeviceRepo.getCurrentDeviceDetails().getPlatformName();

    public AllureReport() {
        this.filesCopied = 0;
    }

    public void createFileOfBuildDescription () {
        LOG.info("------- Start Collecting Build Details -------");
        String jenkinsDescription = Paths.get(".\\allure-results", "environment.properties").toAbsolutePath().normalize().toString();
        File jenkinsDescriptionFile = new File(jenkinsDescription);
        try {
            new ReportUtils().createFile(jenkinsDescription);
            Writer bw = new BufferedWriter(new FileWriter(jenkinsDescriptionFile));
            bw.write(buildDescription);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGeneratingAllureReport() {
        createFileOfBuildDescription();
        File f = new File(allureReportStoredPath);
        ArrayList<String> l = new ArrayList<>(Arrays.asList(f.list()));
        LOG.info("------- List of folders is :: " + l +" -------");
        int highestNumber = Integer.parseInt(String.valueOf(l.size()));
        LOG.info("------- Highest folder number is :: "+ highestNumber +" -------");
        int newFolderName = highestNumber + 1;
        LOG.info("------- New folder number is :: "+ newFolderName +" -------");
        File f2 = new File(allureReportStoredPath + "\\" + newFolderName);
        System.out.println("allure Report Path: " + f2);

        if (!f2.exists()) {
            f2.mkdir();
            LOG.info("------- Your new folder has been created successfully -------");
        } else {
            LOG.info("------- New folder is not created -------");
        }
        try {
            LOG.info("------- Serving allure report -------");
            Runtime.getRuntime().exec(allureGenerate);
            new BaseTestAndroidAppium().wait(10000);
            LOG.info("------- Generate allure report -------");
            FileHandler.copy(srcFolder, f2);
            File oldName = new File(f2 + "\\" + "index.html");
            File newName = new File(f2 + "\\" + "allure_report.html");
            oldName.renameTo(newName);
            LOG.info("------- Allure report ID is : " + f2 + "\\" + "allure_report.html -------");
            Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanDirFilesOnly() {
        String path = "C:\\Project\\blendin_ios_automation\\allure-results";
        int count = 0;
        LOG.info(" Deleting files.....");
        File dir = null;
        try {
            dir = new File(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (dir.isDirectory()) {
                File[] list = dir.listFiles();
                for (int i = 0; i < list.length; i++) {
                    File toBeDeleted = list[i];
                    toBeDeleted.delete();
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info(" Deleting completed (" + count + " files)!");
    }

    public double randomFileName() {
        int max = 10, min = 0;
        double fileName = Math.random()*(max-min+1)+min;
        return fileName;
    }

    public void copy(File srcFolder, File destFolder) {
        LOG.info(" Copying files.......");
        //make sure source exists
        if (!srcFolder.exists()) {
            LOG.info("Directory does not exist.");
            //just exit
            System.exit(0);
        } else {
            try {
                copyFolderWithFiles(srcFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
                //error, just exit
                System.exit(0);
            }
        }
        LOG.info(" Done (" + filesCopied + " files)");
    }

    public void copyFolderWithFiles(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                //LOG.info("Directory copied from " + src + "  to " + dest);
            }
            //list all the directory contents
            String files[] = src.list();
            System.out.println("List --->>> " + Arrays.toString(files));
            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolderWithFiles(srcFile, destFile);
            }
        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[500000000];
            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            filesCopied++;
            //LOG.info("File copied from " + src + " to " + dest);
        }
    }

    public void allureReport(String pathOfAllureResultsFolder) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c start cmd.exe /k allure generate --clean --single-file "+pathOfAllureResultsFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
