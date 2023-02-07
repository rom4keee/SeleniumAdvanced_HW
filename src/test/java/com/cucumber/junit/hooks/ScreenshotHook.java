package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.WDriver;
import io.cucumber.java.Scenario;
import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class ScreenshotHook {
    private static final String PNG_FILE_EXTENSION = "image/png";

    @After
    public void takeScreenshot(Scenario scenario){
        scenario.log(WDriver.getDriver().getCurrentUrl());
        byte [] screenshot = ((TakesScreenshot) WDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,PNG_FILE_EXTENSION, scenario.getName());
    }
}