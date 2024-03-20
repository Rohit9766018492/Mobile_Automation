package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locatorsandmethods.CalculatorLocatorsAndMethods;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.utilities.ListenerTest;

@Listeners(ListenerTest.class)
public class CalculatorTest extends CalculatorLocatorsAndMethods {

    private static final Logger logger = LogManager.getLogger(CalculatorTest.class);

    @Test(description = "Launch Calculator")
    public void launchCalculator() {
        logger.info("Starting Application For Test Execution");
        launchAndroidApplications();
        Assert.assertTrue(isElementDisplayed("id", three),  "Three button is not available in calculator");
        Assert.assertTrue(isElementDisplayed("id", plus), "Plus button is not available in calculator");
        Assert.assertTrue(isElementDisplayed("id", two),  "Two button is not available in calculator");
        Assert.assertTrue(isElementDisplayed("id", equals), "Equal button is not available in calculator");
        logger.info("Starting Application For Test Execution 2");
        findElementAndClick("id", three);
        findElementAndClick("id", plus);
        findElementAndClick("id", two);
        findElementAndClick("id", equals);
        String text = findElementAndGetText("className", textView);
        logger.info("Starting Application For Test Execution 3");
        System.out.println("Value is: " + text);
        Assert.assertEquals(text, "5", "Added numbers are different...");
        logger.info("Starting Application For Test Execution 4");
        tearDown();
    }

    @Test
    public void random(){
        Assert.assertTrue(true);
    }
    @Test
    public void random1(){
        Assert.assertTrue(false);
    }
}
