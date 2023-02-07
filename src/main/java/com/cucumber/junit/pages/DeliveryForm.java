package com.cucumber.junit.pages;

import com.cucumber.junit.DTO.UserDTO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DeliveryForm extends CheckoutPage {
    @FindBy (xpath = "//input[@name='delivery-fullName']")
    WebElement fullNameInputField;

    @FindBy (xpath = "//input[@name='delivery-addressLine1']")
    WebElement addressLine1InputField;

    @FindBy (xpath = "//input[@name='delivery-addressLine2']")
    WebElement addressLine2InputField;

    @FindBy (xpath = "//input[@name='delivery-city']")
    WebElement deliveryCityTownInputField;

    @FindBy (xpath = "//input[@name='delivery-county']")
    WebElement deliveryCountyStateInputField;

    @FindBy (xpath = "//input[@name='delivery-postCode']")
    WebElement deliveryPostCode;

    @FindBy (name="deliveryCountry")
    WebElement deliveryCountryDropDown;

    public void setFullName(String fullName) {
        fullNameInputField.sendKeys(fullName);
    }

    public void setCountry(Select dropDown, String deliveryCountry) {
        dropDown.selectByVisibleText(deliveryCountry);
        exitFrame();
    }

    public void setAddressLine1(String addressLine1) {
        addressLine1InputField.sendKeys(addressLine1);
    }

    public void setAddressLine2(String addressLine2) {
        addressLine2InputField.sendKeys(addressLine2);
    }

    public void setDeliveryCityTown(String cityOrTown) {
        deliveryCityTownInputField.sendKeys(cityOrTown);
    }

    public void setDeliveryCountyState(String countyOrState) {
        deliveryCountyStateInputField.sendKeys(countyOrState);
    }

    public void setDeliveryPostCode(String postCode) {
        deliveryPostCode.sendKeys(postCode);
    }

    public DeliveryForm fillAddressForm(UserDTO addressInfo) {
        setFullName(addressInfo.fullName);
        setCountry(getNewSelect(deliveryCountryDropDown), addressInfo.deliveryCountry);
        setAddressLine1(addressInfo.addressLine1);
        setAddressLine2(addressInfo.addressLine2);
        setDeliveryCityTown(addressInfo.deliveryTownOrCity);
        setDeliveryCountyState(addressInfo.deliveryCountyOrState);
        setDeliveryPostCode(addressInfo.postCode);
        return this;
    }
}
