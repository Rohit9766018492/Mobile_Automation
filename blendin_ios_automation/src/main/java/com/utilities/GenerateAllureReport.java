package com.utilities;

import com.allure.AllureReport;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class GenerateAllureReport implements IReporter {

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        new AllureReport().startGeneratingAllureReport();
        new ReportUtils().createReportMessageBody();
    }

}
