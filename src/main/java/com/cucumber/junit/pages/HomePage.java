package com.cucumber.junit.pages;

import com.cucumber.junit.driver.WDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public static final String BOOK_DEPOSITORY_URL = "https://www.bookdepository.com/";

    public void openBookDepositoryWebsite(){
        WDriver.getDriver().get(BOOK_DEPOSITORY_URL);
    }

    public void openInitialHomePage(){
        WDriver.getDriver().get(BOOK_DEPOSITORY_URL);
    }

    @FindBy (xpath = "/html/body/div[1]")
    WebElement cookieBanner;

    @FindBy (xpath = "btn btn-sm btn-yes")
    WebElement cookiesButton;

    @FindBy (xpath = "//input[@name = 'searchTerm']")
    WebElement searchField;

    @FindBy (className = "header-search-btn")
    WebElement searchButton;

    public WebElement getMenuSearchField(){
        return searchField;
    }

    public WebElement getSearchButton(){
        return searchButton;
    }

    public WebElement getCookieBanner(){
        return cookieBanner;
    }
    public WebElement getCookiesButton() {
        return cookiesButton;
    }

    public void searchForBook(String string) {
        Actions builder = new Actions(WDriver.getDriver());
        Action searchBySearchTerm = builder
                .moveToElement(searchField)
                .click()
                .sendKeys(string)
                .moveToElement(searchButton)
                .click()
                .build();
        searchBySearchTerm.perform();
    }
}
