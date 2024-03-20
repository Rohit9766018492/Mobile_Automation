package com.utilities;

import com.allure.AllureReport;
import com.allure.XmlSuiteNames;
import com.appium.BaseTestAndroidAppium;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ReportUtils {

    public AllureReport allureReport = new AllureReport();
    public BaseTestAndroidAppium baseTestAndroidAppium = new BaseTestAndroidAppium();

    public File createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public void createReportMessageBody() {
        try {
            File f = new File(allureReport.allureReportStoredPath);
            ArrayList<String> l = new ArrayList<>(Arrays.asList(f.list()));
            int highestNumber = Integer.parseInt(String.valueOf(l.size()));
            String reportMessagePath = Paths.get(".").toAbsolutePath().normalize()+File.separator+"reportMessage.html";
            System.out.println("Path: " + reportMessagePath);
            File htmlMessageFile = new File(reportMessagePath);
            htmlMessageFile.delete();
            createFile(reportMessagePath);
            Writer bw = new BufferedWriter(new FileWriter(htmlMessageFile));
            bw.write("<html><head><title>New Page</title></head><body><p><p><b><u>AUTOMATED REPORT</u></b></p>" +
                    "\n <p> Allure Report for current test run with history trend: "+
                    "\n <a href="+allureReport.allureReportStoredPath+"\\"+highestNumber+">Allure Report Url for Automation Execution</a>" +
                    "\n <p> Automated suite executed: <b>'"+ XmlSuiteNames.suiteName + "'</b></p>" +
                    "\n <p> Included suite(s) executed: <b>'"+ XmlSuiteNames.suiteList + "'</b></p>" +
                    "\n <p><u> Environment used: </u>"+
                    "\n <br> Device Name: "+ DeviceRepo.getCurrentDeviceDetails().getDeviceName() + "<b> " +
                    "\n <br> (Android OS Version: " + DeviceRepo.getCurrentDeviceDetails().getPlatformOSVersion() + ")</b>" +
                    "\n <br><br> Automation execution history: "+
                    "\n <a>Automation Execution Results</a>\n"+
                    "<br><br>\n" +
                    "Best Regards,<br>\n" +
                    "Automation Team<br>\n" +
                    "<br><sup>[THIS IS AN AUTOMATED MESSAGE - PLEASE DO NOT REPLY DIRECTLY TO THIS EMAIL]</sup></body></html></body></html>");
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//    public void createReportMessageBodyOld() {
//        String jobName = AllureHelper.jenkinsJobName;
//        String reportMessagePath = Paths.get(".").toAbsolutePath().normalize().toString()+"\\reportMessage.html";
//        File htmlMessageFile = new File(reportMessagePath);
//        try {
//            String getBuildNumber = "cmd /c curl -X POST http://da-uiauto02.gma.sw.rim.net:8080/job/" + jobName + "/lastBuild/buildNumber";
//
//            int lastBuildNumber = Integer.parseInt(org.apache.commons.io.IOUtils.toString(Runtime.getRuntime().exec(getBuildNumber).getInputStream()));
//
////            int lastBuildNumber = Integer.parseInt(org.apache.commons.io.IOUtils.toString(Runtime.getRuntime().exec(getBuildNumber).getInputStream(),Charset.defaultCharset()));
//            createFile(reportMessagePath);
//
//
//            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlMessageFile));
//
//            bw.write("<html><head><title>New Page</title></head><body><p><p><b><u>AUTOMATED REPORT</u></b></p>\n" +
//                    "\n <p> Device Name: <b> " +Property.getDeviceType().replace("adb:", "") + "</b></p>" +
//                    "\n <p> Build executed on: <b> "+ DownloadBBContent.getMaxiOSBuild(BaseSeetest.getBuildAccessReleaseUrl()) + "</b></p>" +
//                    "\n <p> Branch executed on: <b> " + WebDriverUtils.get().getBBABuildBranch() + "</b></p> " +
//                    "\n <p> Allure Report with history trend: "+
//                    "\n <a href=\"http://da-uiauto02.gma.sw.rim.net:8080/job/" + AllureHelper.jenkinsJobName + "/" + lastBuildNumber +"/allure/\">Allure report for mobile (iOS)</a></p></body></html>");
//            bw.close();
//
//
////            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlMessageFile));
////            bw.write("<html><head><title>New Page</title></head><body><p><p><b><u>AUTOMATED REPORT</u></b></p>" +
////                    "\n <p> Automated suite(s) executed: <b>'"+ SuiteConfig.suiteList + "'</b></p>" +
////                    "\n <p> GC/UEM: "+
////                    "\n <a href=\"" + new UrlRepo().getGC_UEM_Url().getUrlAddress() + "\">" + Property.getGC_UEM_Type() + "</a></p>\n"+
////                    "\n <p> Device Name: <b> " +Property.getDeviceType().replace("adb:", "") + "</b></p>" +
////                    "\n <p> Build executed on: <b> "+ WebDriverUtils.get().getGAVersion() + "</b></p>" +
////                    "\n <p> Branch executed on: <b> " + WebDriverUtils.get().getGABuildBranch() + "</b></p> " +
////                    "\n <p> Automation execution history: "+
////                    "\n <a href=\"https://wikis.rim.net/display/goodGMAGD/BlackBerry+Access+Automation+Execution+Results+-+Mobile\">BlackBerry Access Automation Execution Results</a></p>\n"+
////                    "\n <p> Allure Report with history trend: "+
////                    "\n <a href=\"http://da-uiauto02.gma.sw.rim.net:8080/job/" + AllureHelper.jenkinsJobName + "/" + lastBuildNumber +"/allure/\">Allure report for mobile (Android)</a></p></body></html>");
////            bw.close();
//
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void createAllureEnvironmentPropertiesFile(){
//        String envPropPath = Paths.get("target" , "allure-results"+File.separator+"environment.properties").toAbsolutePath().normalize().toString();
//        File envPropertiesFile = new File(envPropPath);
//        try {
//            envPropertiesFile.delete();
//            createFile(envPropPath);
//            Writer bw = new BufferedWriter(new FileWriter(envPropertiesFile));
//            bw.write("BB_Access_build=" + WebDriverUtils.get().getBBAVersion()+ " " + WebDriverUtils.get().getBBABuildBranch() + "\n" +
//                    "BB_Work_build=" +  WebDriverUtils.get().getBBWVersion()+ "\n" +
//                    "Device=" + DeviceRepo.getCurrentDeviceEntity().getDeviceType() + " " +
//                    DeviceRepo.getCurrentDeviceEntity().getDisplayName() +
//                    " (OS " + DeviceRepo.getCurrentDeviceEntity().getOSVersion()  + ")" + "\n" +
//                    "GC/UEM=" + Property.getGC_UEM_Type() + " " + new UrlRepo().getGC_UEM_Url().getUrlAddress());
//
//            bw.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//

//    public void sendLogs() {
//        WebDriverUtils.get().launch(true);
//        new MobileLoginPage().enterPassword(UserRepo.getValidPassword()).confirm()
//                .clickSettings().clickLogs().clickSendLogs().clickSettings().clickDone();
//    }
//
//    public static void main(String[] args) {
////        new ReportUtils().createReportMessageBody();
////        new ReportUtils().createAllureEnvironmentPropertiesFile();
//        System.out.println(new DownloadBBContent().getArtifactAccessReleaseUrl());
//    }
}


