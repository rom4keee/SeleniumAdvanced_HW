package com.cucumber.junit.steps;

import com.cucumber.junit.DTO.UserDTO;
import com.cucumber.junit.driver.WDriver;
import com.cucumber.junit.JavaScriptExecutor.JSExecutor;
import com.cucumber.junit.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.Assert.assertEquals;

public class AddProductAsGuest {
    private final HomePage homePage = new HomePage();
    private final BasketPage basketPage = new BasketPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();
    private final SearchResultPage searchResultsPage = new SearchResultPage();
    private final BasketPopUp basketPopUp = new BasketPopUp();
    private final DeliveryForm deliveryInfoForm = new DeliveryForm();


    @Given("I am an anonymous customer with clear cookies")
    public void clearCookies() {
    WDriver.getDriver().manage().deleteAllCookies();
    }


    @And("I open the {string}")
    public void openInitialHomePage() {
        homePage.openInitialHomePage();
    }

    @And("I search for {string}")
    public void searchForSearchTerm(String searchTerm) {
        homePage.getMenuSearchField().sendKeys(searchTerm);
        homePage.getSearchButton().click();
    }

    @And("I am redirected to a {string}")
    public void verifyUserIsRedirectedToCorrectPage(String pageTitle) {
        assertEquals("Wrong page!", BasePage.getExpectedUrlTitle(pageTitle),
                JSExecutor.getPageURLJavaScript());
    }

    @And("Search results contain the following products")
    public void verifySearchResults(List<String> expectedSearchResults) {
        for (String bookName : expectedSearchResults) {
            List<WebElement> books = WDriver.getDriver().findElements(
                    By.xpath("//div[@class='book-item']//*[contains(text(),'" + bookName + "')]"));
            assertSoftly(softAssertions -> {
                softAssertions.assertThat(books).isNotEmpty();
            });
        }
    }

    @And("I apply the following search filters")
    public void applySearchFilters(Map<String, String> rows) {
        for (Map.Entry<String, String> map : rows.entrySet()) {
            Select dropDown = new Select(WDriver.getDriver().findElement(
                    By.xpath(searchResultsPage.formatLocatorForSearchDropDown(map.getKey()))));
            dropDown.selectByVisibleText(map.getValue());
        }
        searchResultsPage.clickOnRefineResultsButton();
    }

    @And("Search results contain only the following products")
    public void verifySearchResultsContainFollowingProducts(List<String> expectedFilteredSearchResults) {
        List<WebElement> actualFilteredSearchResults = searchResultsPage.getAllSearchResults();
        assertEquals(expectedFilteredSearchResults.size(), actualFilteredSearchResults.size());
    }

    @When("I click 'Pre-order' button for product with name {string}")
    public void clickPreOrderButton(String selectedBook) {
        searchResultsPage.clickOnPreOrderButtonForSelectedBook(selectedBook);

    }

    @And("I select Basket Checkout in basket pop-up")
    public void clickBasketCheckoutInBasketPopUp() {
        basketPopUp.clickOnBasketCheckoutButton();
    }

    @Then("I am redirected to the {string}")
    public void verifyUserRedirectedToCorrectPage(String pageTitle) {
        assertEquals("Wrong page!", BasePage.getExpectedUrlTitle(pageTitle),
                JSExecutor.getPageURLJavaScript());
    }

    @And("Basket order summary is as following:")
    public void verifyBasketOrderSummary(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        assertSoftly(
                softAssertions -> {
                    softAssertions.assertThat(basketPage.getDeliveryCost().getText())
                            .as("Delivery incorrect")
                            .isEqualTo(rows.get(0).get("Delivery cost"));
                    softAssertions.assertThat(basketPage.getBasketTotal().getText())
                            .as("Total incorrectly")
                            .isEqualTo(rows.get(0).get("Total"));
                });
    }

    @When("I click 'Checkout' button on 'Basket' page")
    public void clickCheckoutButtonOnBasketPage() {
        basketPage.getCheckoutButton().click();
    }

    @Then("I am redirected to the {string} page")
    public void verifyUserIsAtCorrectPage(String pageTitle) {
        assertEquals("Wrong page!", BasePage.getExpectedUrlTitle(pageTitle),
                JSExecutor.getPageURLJavaScript());
    }

    @When("I click 'Buy now' button")
    public void clickBuyNowButton() {
        checkoutPage.getBuyNowButton().click();
    }

    @Then("the following validation error messages are displayed on 'Delivery Address' form:")
    public void verifyErrorMessagesAreDisplayedOnDeliveryAddressForm(List<Map<String, String>> listOfInputFieldsAndErrorMessages) {
        for (Map<String, String> map : listOfInputFieldsAndErrorMessages) {
            assertSoftly(softAssertions -> {
                softAssertions.assertThat(checkoutPage.getErrorMessageText(map.get("Form field name"))).as(
                        "Validation error message incorrect").isEqualTo(
                        map.get("validation error message"));
            });
        }
    }

    @And("the following validation error messages are displayed on 'Payment' form:")
    public void verifyErrorMessagesAreDisplayedOnPaymentForm(List<String> listOfErrorMessages) {
        for (String errorMessage : listOfErrorMessages) {
            Assert.assertEquals("Validation error message incorrect", errorMessage,
                    checkoutPage.getTextOfErrorMessagesAtPaymentForm().replace("\n",", "));
        }
    }

    @And("Checkout order summary is as following:")
    public void checkoutOrderSummaryIsAsFollowing(DataTable table) {
        List<Map<String, String>> prices = table.asMaps(String.class, String.class);
        assertSoftly(
                softAssertions -> {
                    softAssertions.assertThat(checkoutPage.getOrderSummaryTotal().getText())
                            .as("Sub-total incorrect")
                            .isEqualTo(prices.get(0).get("Sub-total"));
                    softAssertions.assertThat(checkoutPage.getOrderSummaryDelivery().getText())
                            .as("Delivery incorrect")
                            .isEqualTo(prices.get(0).get("Delivery"));
                    softAssertions.assertThat(checkoutPage.getOrderSummaryTax().getText())
                            .as("Order Tax incorrect")
                            .isEqualTo(prices.get(0).get("VAT"));
                    softAssertions.assertThat(checkoutPage.getOrderSummaryTotal().getText())
                            .as("Order Total incorrect")
                            .isEqualTo(prices.get(0).get("Total"));
                });
    }

    @And("I checkout as a new customer with email {string}")
    public void checkoutAsANewCustomerWithEmail(String email) {
        checkoutPage.enterEmailAddress(email);
    }

    @When("I fill delivery address information manually:")
    public void fillDeliveryAddressInformation(DataTable table) {
        List<Map<String, String>> userList = table.asMaps(String.class, String.class);
        for (Map<String, String> map : userList) {
            UserDTO user = UserDTO.transform(map);
            deliveryInfoForm.fillAddressForm(user);
        }
    }

    @Then("there is no validation error messages displayed on 'Delivery Address' form")
    public void verifyErrorMessagesIsNorDisplayed() {
        Assert.assertFalse(checkoutPage.areErrorMessagesDisplayed());
    }

    @When("I enter my card details")
    public void enterCardDetails(Map<String, String> cardDetails) {
        for (Map.Entry<String, String> map : cardDetails.entrySet()) {
            switch (map.getKey()) {
                case "Card number":
                    checkoutPage.enterCreditCardNumber(map.getValue());
                    break;
                case "Expiry date":
                    checkoutPage.enterExpiryDate(map.getValue());
                    break;
                case "CVV":
                    checkoutPage.enterCVV(map.getValue());
                    break;
            }
        }
    }
}