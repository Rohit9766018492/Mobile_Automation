package com.test;

import com.allure.AllureReport;
import com.appium.BaseTestAndroidAppium;
import org.locatorsandmethods.GoogleNewsLocatorsAndMethods;
import org.testng.Assert;
import org.testng.annotations.*;
import com.utilities.ListenerTest;

@Listeners(ListenerTest.class)
public class GoogleNewsTest {

    private final String ID = "id";
    private final String XPATH = "xpath";
    private final String CLASSNAME = "className";
    private final AllureReport allureReport = new AllureReport();
    public GoogleNewsLocatorsAndMethods googleNewsLocatorsAndMethods = new GoogleNewsLocatorsAndMethods();
    public BaseTestAndroidAppium baseTestAndroidAppium = new BaseTestAndroidAppium();

    @BeforeSuite
    public void beforeSuite() {

    }

    @Test(description = "Search button is visible")
    public void testSearchButtonVisibleAfterLaunchingApplication() {
        baseTestAndroidAppium.launchGoogleNewsApplication();
        baseTestAndroidAppium.mobileAlertHandle();
        baseTestAndroidAppium.isElementDisplayed(ID, googleNewsLocatorsAndMethods.searchButton);
    }

    @Test(description = "Verify the title of Google News Application")
    public void testVerifyTitleOfGoogleNewsApplication() {
        baseTestAndroidAppium.isElementEnabled(XPATH, googleNewsLocatorsAndMethods.headingOfApplication);
        baseTestAndroidAppium.isElementDisplayed(XPATH, googleNewsLocatorsAndMethods.headingOfApplication);
    }

    @Test(description = "Scroll the page till element not visible")
    public void testScrollPageTillElement(){
        googleNewsLocatorsAndMethods.scrollUpAndClick(XPATH, googleNewsLocatorsAndMethods.fullCoverageOfThisStory, 5);
    }

    @Test(description = "Search Functionality")
    public void testSearchFunctionality() {
        baseTestAndroidAppium.findElementAndClick(XPATH, googleNewsLocatorsAndMethods.backButton);
        baseTestAndroidAppium.scrollDown();
        baseTestAndroidAppium.findElementAndClick(ID, googleNewsLocatorsAndMethods.searchButton);
        baseTestAndroidAppium.sendKeys(ID, googleNewsLocatorsAndMethods.searchTextField, "news");
        baseTestAndroidAppium.clickEnterUsingSendKeys();
        baseTestAndroidAppium.wait(3000);
        String searchText = baseTestAndroidAppium.findElementAndGetText(XPATH, googleNewsLocatorsAndMethods.searchValueAfterText);
        System.out.println("Searched Text: " + searchText);
        Assert.assertEquals(searchText, "news", "Expected text is not there...");
    }

    @Test(description = "Swipe Functions")
    public void swipeRight() {
        baseTestAndroidAppium.findElementAndClick(XPATH, googleNewsLocatorsAndMethods.backButton);
        baseTestAndroidAppium.wait(2000);
        baseTestAndroidAppium.findElementAndClick(XPATH, googleNewsLocatorsAndMethods.headLinesButton);
        baseTestAndroidAppium.wait(3000);
        googleNewsLocatorsAndMethods.swipeLeftAndClick(XPATH, googleNewsLocatorsAndMethods.sportsButton, 9);
        googleNewsLocatorsAndMethods.swipeRightAndClick(XPATH, googleNewsLocatorsAndMethods.latestButton, 9);
    }
}
