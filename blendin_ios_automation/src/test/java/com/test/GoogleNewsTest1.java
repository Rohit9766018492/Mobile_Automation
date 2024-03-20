package com.test;

import com.allure.AllureReport;
import com.appium.BaseTestAndroidAppium;
import org.locatorsandmethods.GoogleNewsLocatorsAndMethods;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.utilities.ListenerTest;

@Listeners(ListenerTest.class)
public class GoogleNewsTest1 {

    private final String ID = "id";
    private final String XPATH = "xpath";
    private final String CLASSNAME = "className";

    public GoogleNewsLocatorsAndMethods googleNewsLocatorsAndMethods = new GoogleNewsLocatorsAndMethods();
    public BaseTestAndroidAppium baseTestAndroidAppium = new BaseTestAndroidAppium();

    @Test(description = "01. Application Google New Launch Successfully")
    public void launchGoogleNewsApplication1() {
        baseTestAndroidAppium.launchGoogleNewsApplication();
        baseTestAndroidAppium.mobileAlertHandle();
        baseTestAndroidAppium.findElementAndClick(ID, googleNewsLocatorsAndMethods.searchButton);
        baseTestAndroidAppium.wait(2000);
        baseTestAndroidAppium.sendKeys(ID, googleNewsLocatorsAndMethods.searchTextField, "news");
        baseTestAndroidAppium.wait(2000);
        baseTestAndroidAppium.clickEnter();
        baseTestAndroidAppium.wait(5000);
    }

    @Test(description = "2. Clicking on suggestion after searching text")
    public void verifyClickOnSuggestionAfterSearchingText() {

        baseTestAndroidAppium.findElementAndClick(XPATH, googleNewsLocatorsAndMethods.searchTextField);
        baseTestAndroidAppium.wait(2000);
        baseTestAndroidAppium.sendKeys(ID, googleNewsLocatorsAndMethods.searchTextField, "test");
        baseTestAndroidAppium.wait(2000);
        baseTestAndroidAppium.clickEnter();
    }
}
