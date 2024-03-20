package com.utilities;

import com.allure.AllureReport;
import com.appium.BaseTestAndroidAppium;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ListenerTest extends DeviceScreenShots implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.print("---- TestNGListener onTestStart ---- "+ result.getName() + "\n");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.print("---- TestNGListener onTestSuccess ---- "+ result.getName() + "\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.print("---- TestNGListener onTestFailure ---- "+ result.getName() + "\n");
        if(result.getStatus() == ITestResult.FAILURE) {
            File screenshot = takeScreenshot();
            try {
                Allure.addAttachment("screenshot", FileUtils.openInputStream(screenshot));
                Allure.description(result.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.print("---- TestNGListener onTestSkipped ---- "+ result.getName() + "\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.print("---- TestNGListener onTestFailedButWithinSuccessPercentage ---- "+ result.getName() + "\n");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.print("---- TestNGListener onStart ---- "+ context.getName() + "\n");
        BaseTestAndroidAppium.launchAndroidApplication();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.print("---- TestNGListener onFinish ---- "+ context.getName() + "\n");
    }
}
