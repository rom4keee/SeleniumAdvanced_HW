package com.cucumber.junit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

import static com.cucumber.junit.driver.WDriver.getDriver;

public class BasePage extends PageFactory {
    public BasePage(){
        PageFactory.initElements(getDriver(),this);
    }

    public String getPageUrl() {
        return getDriver().getCurrentUrl();
    }
    private static final Map<String, String> expectedUrlList;

    static {
        expectedUrlList = new HashMap<>();
        expectedUrlList.put("Search results", "https://www.bookdepository.com/search?searchTerm=Thinking+in+java&search=Find+book");
        expectedUrlList.put("Basket page", "https://www.bookdepository.com/basket");
        expectedUrlList.put("Checkout", "https://www.bookdepository.com/payment/guest");
    }

    public static String getExpectedUrlTitle(String string) {
        return expectedUrlList.get(string);
    }

    public void exitFrame() {
        getDriver().switchTo().defaultContent();
    }

    public Select getNewSelect(WebElement element) {
        return new Select(element);
    }

    public void switchToFrame(WebElement frameName) {
        getDriver().switchTo().frame(frameName);
    }
}
