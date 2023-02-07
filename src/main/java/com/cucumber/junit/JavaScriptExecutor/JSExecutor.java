package com.cucumber.junit.JavaScriptExecutor;

import com.cucumber.junit.driver.WDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSExecutor {
    public static JavascriptExecutor JSExecutor = (JavascriptExecutor) WDriver.getDriver();

    public static String getPageURLJavaScript() {
        return JSExecutor.executeScript("return document.URL;").toString();
    }

    public static void executeHighlightingJavaScript(WebElement element) {
        JSExecutor.executeScript("arguments[0].style.background='green'", element);
    }
}