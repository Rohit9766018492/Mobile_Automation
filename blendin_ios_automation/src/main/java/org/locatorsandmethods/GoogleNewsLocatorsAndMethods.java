package org.locatorsandmethods;

import com.appium.BaseTestAndroidAppium;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoogleNewsLocatorsAndMethods extends BaseTestAndroidAppium {

    public String searchButton = "com.google.android.apps.magazines:id/search_button";
    public String searchTextField = "com.google.android.apps.magazines:id/open_search_view_edit_text";
    public String searchValueAfterText = "//*[@text='news']";
    public String backButtonAfterSearch = "//android.widget.ImageButton[@content-desc=\"Back\"]";
    public String headingOfApplication = "//android.view.ViewGroup[@content-desc=\"Google News\"]";
    public String fullCoverageOfThisStory = "//android.widget.TextView[@text=\"Full Coverage of this story\" and @displayed = \"true\"]";
    public String backButton = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    public String headLinesButton = "//android.widget.TextView[@resource-id=\"com.google.android.apps.magazines:id/tab_headlines_text\"]";
    public String forYouButton = "com.google.android.apps.magazines:id/tab_for_you_text";
    public String headLinesScrollView = "//android.widget.HorizontalScrollView[@resource-id=\"com.google.android.apps.magazines:id/toolbar_tabs\"]";
    public String healthButton = "//android.widget.LinearLayout[@content-desc=\"Health\"]";
    public String sportsButton = "//*[@text=\"Sports\"]";
    public String latestButton = "//*[@text=\"Latest\"]";

    public static Logger log = LogManager.getLogger(BaseTestAndroidAppium.class);


    public void scrollUpAndClick(String method, String locator, int loop) {
        log.info("---- Clicking on element after finding it by scrolling ----");
        wait(2000);
        while (loop > 0) {
            if(isElementDisplayed(method, locator)) {
                findElementAndClick(method, locator);
                log.info("!!!! Element Found and Clicked Successfully !!!!");
                break;
            }
            scrollUp();
            log.info("!!!! Element NOT Found !!!!");
            loop --;
        }
    }

    public void scrollDownAndClick(String method, String locator, int loop) {
        log.info("---- Clicking on element after finding it by scrolling ----");
        wait(2000);
        while (loop > 0) {
            if(isElementDisplayed(method, locator)) {
                findElementAndClick(method, locator);
                log.info("!!!! Element Found and Clicked Successfully !!!!");
                break;
            }
            scrollDown();
            log.info("!!!! Element NOT Found !!!!");
            loop --;
        }
    }
    public void swipeLeftAndClick(String method, String locator, int loop) {
        log.info("---- Clicking on element after finding it by scrolling ----");
        wait(2000);
        while (loop > 0) {
            if(isElementDisplayed(method, locator)) {
                findElementAndClick(method, locator);
                log.info("!!!! Element Found and Clicked Successfully !!!!");
                break;
            }
            swipeLeft();
            log.info("!!!! Element NOT Found !!!!");
            loop --;
        }
    }
    public void swipeRightAndClick(String method, String locator, int loop) {
        log.info("---- Clicking on element after finding it by scrolling ----");
        wait(2000);
        while (loop > 0) {
            if(isElementDisplayed(method, locator)) {
                findElementAndClick(method, locator);
                log.info("!!!! Element Found and Clicked Successfully !!!!");
                break;
            }
            swipeRight();
            log.info("!!!! Element NOT Found !!!!");
            loop --;
        }
    }
}
