package com.appium;

import com.allure.AllureReport;
import com.google.common.collect.ImmutableMap;
import com.utilities.DeviceRepo;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTestAndroidAppium {

    public static AndroidDriver driver;
    public static Logger log = LogManager.getLogger(BaseTestAndroidAppium.class);
    public Properties prop;

    private String path = "C:\\Program Files\\nodejs\\node.exe C:\\Users\\rahul.ranpura_mplify\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\appium.js";

    public BaseTestAndroidAppium() {
        prop = new Properties();
        try {
            InputStream file = new FileInputStream("C://Project//blendin_ios_automation//src//main//resources//config.properties");
            prop.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void launchAndroidApplications() {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceRepo.getCurrentDeviceDetails().getDeviceName());
        dc.setCapability(MobileCapabilityType.UDID, DeviceRepo.getCurrentDeviceDetails().getDeviceUDID());
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, DeviceRepo.getCurrentDeviceDetails().getPlatformName());
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, DeviceRepo.getCurrentDeviceDetails().getPlatformOSVersion());
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, DeviceRepo.getCurrentDeviceDetails().getAutomationName());
        dc.setCapability(MobileCapabilityType.NO_RESET, false);
        dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);

        // Calculator Application
        dc.setCapability("appPackage", prop.getProperty("appPackage"));
        dc.setCapability("appActivity", prop.getProperty("appActivity"));


        // Sending capabilities to android device
        try {
            URL url = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver(url, dc);
            log.info("!!!!!!! Application Started Successfully !!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @BeforeTest
    public static void launchAndroidApplication() {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceRepo.getCurrentDeviceDetails().getDeviceName());
        dc.setCapability(MobileCapabilityType.UDID, DeviceRepo.getCurrentDeviceDetails().getDeviceUDID());
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, DeviceRepo.getCurrentDeviceDetails().getPlatformName());
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, DeviceRepo.getCurrentDeviceDetails().getPlatformOSVersion());
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, DeviceRepo.getCurrentDeviceDetails().getAutomationName());

        // Sending capabilities to android device
        try {
            URL url = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver(url, dc);
            log.info("!!!!!!! Android Driver Session Started Successfully !!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {
        if (driver != null) {
            log.info("!!!!!!! Application Ended Successfully !!!!!!!");
            driver.quit();
        }
    }

    public void launchGoogleNewsApplication() {
//        System.out.println("--->>> " + driver.getContext());
        if(driver.getContext().contains("NATIVE_APP")){
            forceRestartApplication();
        } else {
            wait(2000);
            driver.activateApp(prop.getProperty("appPackage"));
            wait(2000);
        }
    }

    // ++++++++++++++++++++++ Click Enter Method ++++++++++++++++++++++
    public void clickEnter() {
        log.info("---- Clicking Enter ----");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void clickEnterUsingSendKeys() {
        log.info("---- Clicking Enter ----");
        wait(2000);
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    // ++++++++++++++++++++++ Click On Allow Button Method +++++++++++++++++++++
    @Step("Click on Allow button of notification")
    public void allowNotificationPermission() {
        wait(2000);
        String text = driver.switchTo().alert().getText();
        if(text.contains("notifications")) {
            driver.switchTo().alert().accept();
            log.info("---- Allow the Google Notification ----");
        } else {
            log.info("---- Not clicked on Allow button of Google Notification ----");
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            log.info("ALERT IS PRESENT !! ");
            return true;
        } catch (Exception e) {
            log.info("ALERT IS NOT PRESENT !! ");
            return false;
        }
    }

    public void mobileAlertHandle() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
            log.info("ACCEPTING ALERT !!");
        }
        else
        {
            log.info("ALERT IS NOT PRESENT !!");
        }
    }

    // ++++++++++++++++++++++ Find Elements By GetText and Click Methods +++++++++++++++++++++
    public void findElementAndClick(String method, String locator) {
        log.info("------- Perform Click on given element -------");
        if (method.equalsIgnoreCase("id")) {
            driver.findElement(By.id(locator)).click();
        } else if (method.equalsIgnoreCase("name")) {
            driver.findElement(By.name(locator)).click();
        } else if (method.equalsIgnoreCase("xpath")) {
            driver.findElement(By.xpath(locator)).click();
        } else if (method.equalsIgnoreCase("className")) {
            driver.findElement(By.className(locator)).click();
        } else if (method.equalsIgnoreCase("tagName")) {
            driver.findElement(By.tagName(locator)).click();
        } else if (method.equalsIgnoreCase("linkText")) {
            driver.findElement(By.linkText(locator)).click();
        } else if (method.equalsIgnoreCase("partialLinkText")) {
            driver.findElement(By.partialLinkText(locator)).click();
        } else if (method.equalsIgnoreCase("cssSelector")) {
            driver.findElement(By.cssSelector(locator)).click();
        }
    }

    public String findElementAndGetText(String method, String locator) {
        log.info("------- Get Text of given element -------");
        String text = new String();
        if (method.equalsIgnoreCase("id")) {
            text = driver.findElement(By.id(locator)).getText();
        } else if (method.equalsIgnoreCase("name")) {
            text = driver.findElement(By.name(locator)).getText();
        } else if (method.equalsIgnoreCase("xpath")) {
            text = driver.findElement(By.xpath(locator)).getText();
        } else if (method.equalsIgnoreCase("className")) {
            text = driver.findElement(By.className(locator)).getText();
        } else if (method.equalsIgnoreCase("tagName")) {
            text = driver.findElement(By.tagName(locator)).getText();
        } else if (method.equalsIgnoreCase("linkText")) {
            text = driver.findElement(By.linkText(locator)).getText();
        } else if (method.equalsIgnoreCase("partialLinkText")) {
            text = driver.findElement(By.partialLinkText(locator)).getText();
        } else if (method.equalsIgnoreCase("cssSelector")) {
            text = driver.findElement(By.cssSelector(locator)).getText();
        }
        return text;
    }

    // ++++++++++++++++++++++ Is Elements Display, Enabled, Selected Methods +++++++++++++++++++++
    public boolean isElementDisplayed(String method, String locator) {
        wait(2000);
        try {
            if(method.equalsIgnoreCase("id")) {
                log.info("------- Checking for element ID is enabled -------");
                return driver.findElement(By.id(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("name")) {
                log.info("------- Checking for element NAME is enabled -------");
                return driver.findElement(By.name(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("xpath")) {
                log.info("------- Checking for element XPATH is enabled -------");
                return driver.findElement(By.xpath(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("className")) {
                log.info("------- Checking for element CLASS NAME is enabled -------");
                return driver.findElement(By.className(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("tagName")) {
                log.info("------- Checking for element TAG NAME is enabled -------");
                return driver.findElement(By.tagName(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("linkText")) {
                log.info("------- Checking for element LINK TEXT is enabled -------");
                return driver.findElement(By.linkText(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("partialLinkText")) {
                log.info("------- Checking for element PARTIAL LINK TEXT is enabled -------");
                return driver.findElement(By.partialLinkText(locator)).isDisplayed();
            } else if (method.equalsIgnoreCase("cssSelector")) {
                log.info("------- Checking for element CSS SELECTOR is enabled -------");
                return driver.findElement(By.cssSelector(locator)).isDisplayed();
            }

        } catch (Exception e) {
            // Log or handle the exception as needed
            return false;
        }
        return false;
    }

    public boolean isElementEnabled(String method, String locator) {

        wait(2000);
        try {
            if (method.equalsIgnoreCase("id")) {
                log.info("------- Checking for element ID is enabled -------");
                return driver.findElement(By.id(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("name")) {
                log.info("------- Checking for element NAME is enabled -------");
                return driver.findElement(By.name(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("xpath")) {
                log.info("------- Checking for element XPATH is enabled -------");
                return driver.findElement(By.xpath(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("className")) {
                log.info("------- Checking for element CLASS NAME is enabled -------");
                return driver.findElement(By.className(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("tagName")) {
                log.info("------- Checking for element TAG NAME is enabled -------");
                return driver.findElement(By.tagName(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("linkText")) {
                log.info("------- Checking for element LINK TEXT is enabled -------");
                return driver.findElement(By.linkText(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("partialLinkText")) {
                log.info("------- Checking for element PARTIAL LINK TEXT is enabled -------");
                return driver.findElement(By.partialLinkText(locator)).isEnabled();
            } else if (method.equalsIgnoreCase("cssSelector")) {
                log.info("------- Checking for element CSS SELECTOR is enabled -------");
                return driver.findElement(By.cssSelector(locator)).isEnabled();
            }
        }
        catch(Exception e){
            return false;
        }
        return false;
    }

    public boolean isElementSelected(String method, String locator) {
        try {
            if (method.equalsIgnoreCase("id")) {
                log.info("------- Checking for element ID is enabled -------");
                return driver.findElement(By.id(locator)).isSelected();
            } else if (method.equalsIgnoreCase("name")) {
                log.info("------- Checking for element NAME is enabled -------");
                return driver.findElement(By.name(locator)).isSelected();
            } else if (method.equalsIgnoreCase("xpath")) {
                log.info("------- Checking for element XPATH is enabled -------");
                return driver.findElement(By.xpath(locator)).isSelected();
            } else if (method.equalsIgnoreCase("className")) {
                log.info("------- Checking for element CLASS NAME is enabled -------");
                return driver.findElement(By.className(locator)).isSelected();
            } else if (method.equalsIgnoreCase("tagName")) {
                log.info("------- Checking for element TAG NAME is enabled -------");
                return driver.findElement(By.tagName(locator)).isSelected();
            } else if (method.equalsIgnoreCase("linkText")) {
                log.info("------- Checking for element LINK TEXT is enabled -------");
                return driver.findElement(By.linkText(locator)).isSelected();
            } else if (method.equalsIgnoreCase("partialLinkText")) {
                log.info("------- Checking for element PARTIAL LINK TEXT is enabled -------");
                return driver.findElement(By.partialLinkText(locator)).isSelected();
            } else if (method.equalsIgnoreCase("cssSelector")) {
                log.info("------- Checking for element CSS SELECTOR is enabled -------");
                return driver.findElement(By.cssSelector(locator)).isSelected();
            }
        }
        catch(Exception e){
            return false;
        }
        return false;
    }

    // ++++++++++++++++++++++ Send Keys to Text Field Method +++++++++++++++++++++
    public void sendKeys(String method, String locator, String sendText) {
        log.info("------- Passing text "+ sendText +" in text field -------");
        wait(2000);
        if (method.equalsIgnoreCase("id")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("name")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("xpath")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("className")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("tagName")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("linkText")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("partialLinkText")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        } else if (method.equalsIgnoreCase("cssSelector")) {
            driver.findElement(By.id(locator)).sendKeys(sendText);
        }
        log.info("---->>>> Entering Text into search bar: "+sendText+" <<<<----");
    }

    // ++++++++++++++++++++++ Wait Methods ++++++++++++++++++++++
    public void setImplicitWaitInSeconds(long timeOutInSecond) {
        log.info("------- Implicit wait for element in seconds: "+ timeOutInSecond +"-------");
        driver.manage().timeouts().implicitlyWait(timeOutInSecond, TimeUnit.SECONDS);
    }
    public void setImplicitWaitInMilliSeconds(long timeOutInMilliSeconds) {
        log.info("------- Implicit wait for element in milliseconds: "+ timeOutInMilliSeconds +"-------");
        driver.manage().timeouts().implicitlyWait(timeOutInMilliSeconds, TimeUnit.MILLISECONDS);
    }

    public static void waitForElementInSeconds(By locator, int timeOutInSecond) {
        log.info("------- Explicit wait for element in seconds: "+ timeOutInSecond +"-------");
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void wait(int milliseconds) {
        double time = milliseconds/1000;
        log.info("------- Waiting for : " + time + " seconds -------");
        try {
            Thread.sleep(milliseconds);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // ++++++++++++++++++++++ Scrolling Methods ++++++++++++++++++++++
    public void scrollAndClickOnElement(String Text)
    {
        log.info("------- Scroll page till element, Once found then click on it -------");
        wait(3000);
        MobileElement scrollableElement = (MobileElement) driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                        "scrollIntoView(new UiSelector().textContains(\""+Text+"\").instance(0))");
        scrollableElement.click();
    }

    public void scrollWhileElementNotVisible(String Text)
    {
        log.info("------- Scroll page till element not found -------");
        MobileElement scrollableElement = (MobileElement) driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                        "scrollIntoView(new UiSelector().textContains(\""+Text+"\").instance(0))");

    }

    public void scrollUp() {
        log.info("------- Start Scrolling Up -------");
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.10);
        PointerInput f1 = new PointerInput(PointerInput.Kind.TOUCH, "f1");
        Sequence s = new Sequence(f1, 1)
                .addAction(f1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(f1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(f1, Duration.ofMillis(100)))
                .addAction(f1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY))
                .addAction(f1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singleton(s));
        log.info("------- End Scrolling Up -------");
    }

    public void scrollDown() {
        log.info("------- Start Scrolling Down -------");
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.10);
        int endX = startX;
        int endY = size.getHeight() / 2;
        PointerInput f1 = new PointerInput(PointerInput.Kind.TOUCH, "f1");
        Sequence s = new Sequence(f1, 1)
                .addAction(f1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(f1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(f1, Duration.ofMillis(100)))
                .addAction(f1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY))
                .addAction(f1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singleton(s));
        log.info("------- Start Scrolling Down -------");
    }

    public void swipeLeft() {
        log.info("------- Start Scrolling Left -------");
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = (int) (size.getWidth() * 0.10);
        int endY = startY;
        PointerInput f1 = new PointerInput(PointerInput.Kind.TOUCH, "f1");
        Sequence s = new Sequence(f1, 1)
                .addAction(f1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(f1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(f1, Duration.ofMillis(100)))
                .addAction(f1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY))
                .addAction(f1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singleton(s));
        log.info("------- End Scrolling Left -------");
    }

    public void swipeRight() {
        log.info("------- Start Scrolling Right -------");
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.10);
        int startY = size.getHeight() / 2;
        int endX = size.getWidth() / 2;
        int endY = startY;
        PointerInput f1 = new PointerInput(PointerInput.Kind.TOUCH, "f1");
        Sequence s = new Sequence(f1, 1)
                .addAction(f1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(f1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(f1, Duration.ofMillis(100)))
                .addAction(f1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY))
                .addAction(f1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singleton(s));
        log.info("------- End Scrolling Right -------");
    }


    public void scrollPageVerticallyTillElementFound(String xpath, boolean click){
        boolean value = false;
        try {
            value = driver.findElement(By.xpath(xpath)).isDisplayed();
            System.out.println("value value ::: " +value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0; i<30; i++) {
            if (value) {
                if(click){
                    driver.findElement(By.xpath(xpath)).click();
                }
            } else {
                driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))." +
                        "scrollForward()"));
                try {
                    value = driver.findElement(By.xpath(xpath)).isDisplayed();
                    System.out.println("value value ::: " +value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }
    public void scrollPageViewHorizontallyTillElementFound(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                "setAsHorizontalList().scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
    }
    public void scrollTopList(String xpath){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(1))." +
                "setAsHorizontalList().scrollIntoView(new UiSelector().textContains(\""+xpath+"\").instance(1))");
    }

    // ++++++++++++++++++++++ Start & Stop Appium Server Methods ++++++++++++++++++++++
    public void startServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = Runtime.getRuntime().exec(path);
//            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a localhost -p 4723");
//            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"appium:noReset\"\": \"\"false\"\"}\"\"");
            if (process!=null) {
                System.out.println("Appium Server Started Successfully");
            } else {
                System.out.println("Unable to start Appium server");
            }
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ++++++++++++++++++++++ Screen Rotation Methods ++++++++++++++++++++++

    public void rotateDeviceToLandscapeMode() {
        log.info("------- Change Device Orientation to Landscape -------");
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void rotateDeviceToPortraitMode() {
        log.info("------- Change Device Orientation to Portrait -------");
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void getDeviceOrientationToPortrait() {
        log.info("------- Change Device Orientation from Landscape to Portrait -------");
        String orientation = String.valueOf(driver.getOrientation());
        if(!orientation.equalsIgnoreCase("portrait")){
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    // ++++++++++++++++++++++ Drag and Drop Method ++++++++++++++++++++++
    public void dragAndDrop(String srcPath, String destinationPath)
    {
        WebElement element = driver.findElement(By.xpath(srcPath));
        WebElement drop= driver.findElement(By.xpath(destinationPath));

        TouchActions act = new TouchActions((MobileDriver)driver);
        act.longPress(element).moveToElement(drop).release().perform();
    }


    // ++++++++++++++++++++++ Application Restart and B/F Ground Methods ++++++++++++++++++++++
    public void forceRestartApplication() {
        wait(2000);
        driver.terminateApp("com.google.android.apps.magazines");
        wait(2000);
        driver.activateApp("com.google.android.apps.magazines");
    }



}

