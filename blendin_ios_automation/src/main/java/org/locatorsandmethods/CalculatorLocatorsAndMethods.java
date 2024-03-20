package org.locatorsandmethods;

import com.appium.BaseTestAndroidAppium;
import io.appium.java_client.android.AndroidDriver;

public class CalculatorLocatorsAndMethods extends BaseTestAndroidAppium {

    public String two = "com.google.android.calculator:id/digit_2";
    public String three = "com.google.android.calculator:id/digit_3";
    public String plus = "com.google.android.calculator:id/op_add";
    public String equals = "com.google.android.calculator:id/eq";
    public String textView = "android.widget.TextView";

    public CalculatorLocatorsAndMethods() {

        AndroidDriver driver = this.driver;
    }
}