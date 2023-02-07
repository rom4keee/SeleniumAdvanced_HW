package com.cucumber.junit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPopUp extends BasePage{

    @FindBy (xpath = "/html/body/div[11]/div/div/div[2]/div/div[1]/a[2]")
    private WebElement basketCheckoutButton;

    public WebElement getBasketCheckoutButton(){
        return basketCheckoutButton;
    }

    public void clickOnBasketCheckoutButton() {
        basketCheckoutButton.click();
    }

}
