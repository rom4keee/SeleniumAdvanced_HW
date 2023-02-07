package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.WDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class DriverHooks {
    @Before
    public void setupDriver(){
        WDriver.getDriver().manage().deleteAllCookies();

    }

    @After
    public void quitDriver(){
        WDriver.quitDriver();
    }
}
