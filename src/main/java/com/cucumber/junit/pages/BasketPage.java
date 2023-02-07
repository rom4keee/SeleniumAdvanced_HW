package com.cucumber.junit.pages;

import com.cucumber.junit.JavaScriptExecutor.JSExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage{

    @FindBy (xpath = "//p[@class = 'item-total']")
    WebElement itemTotal;

    @FindBy (xpath = "//dl[@class = 'total']/dd")
    WebElement basketTotal;

    @FindBy (xpath = "//div[@class = 'checkout-btns-wrap']/a[@href = '/payment/guest' and text() = 'Checkout']")
    WebElement checkoutButton;

    @FindBy (xpath = "//dl[@class='delivery-text']/dd")
    WebElement deliveryCost;

    public WebElement getItemTotal(){
        JSExecutor.executeHighlightingJavaScript(itemTotal);
        return itemTotal;
    }

    public WebElement getBasketTotal(){
        JSExecutor.executeHighlightingJavaScript(basketTotal);
        return basketTotal;
    }

    public WebElement getCheckoutButton(){
        JSExecutor.executeHighlightingJavaScript(checkoutButton);
        return checkoutButton;
    }

    public WebElement getDeliveryCost() {
        JSExecutor.executeHighlightingJavaScript(deliveryCost);
        return deliveryCost;
    }
}
