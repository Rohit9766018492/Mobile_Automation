package com.allure;

import com.appium.BaseTestAndroidAppium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

public class XmlSuiteNames extends TestListenerAdapter {

    public static String suiteNames;
    public static StringBuilder suiteList = new StringBuilder();
    public static String suiteName;

    public static Logger log = LogManager.getLogger(BaseTestAndroidAppium.class);

    public void onStart(ITestContext context) {
        log.info("---- Fetching Details of Suite ----");
        XmlSuite xmlSuite = context.getSuite().getXmlSuite();
        suiteName = xmlSuite.getParentSuite().getName();
        xmlSuite.setName("[" + suiteName + "]") ;
        String testTagName = context.getName();
        suiteList.append(testTagName).append(", ");
    }
}
