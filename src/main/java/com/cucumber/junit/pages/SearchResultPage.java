package com.cucumber.junit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultPage extends BasketPage{

    String filterSectionXPath = "//label[text()='%s']//following-sibling::select";

    @FindBy (xpath = "\"/basket/addisbn/isbn13/9780131872486\"")
    private WebElement addToBasketButton;

    @FindBy (xpath = "//button[contains(text(), 'Refine results')]")
    private WebElement refineResultsButton;

    @FindBy (xpath = "//div[@class='book-item']")
    List<WebElement> allSearchResults;

    @FindBy (xpath = "//a[@class='btn btn-sm btn-primary add-to-basket' and @href = '/basket/addisbn/isbn13/9781684429097']")
    private WebElement preOrderButton;

    @FindBy (xpath = "//div[@class = 'primary-wrap']")
    private WebElement header;

    String preOrderButtonTemplate = "/html/body/div[3]/div[6]/div[4]/div[4]/div/div/div/div/div[1]/div[3]/div/a";

    private static final Map<String, String> bookInfo;

    static {
        bookInfo = new HashMap<>();
        bookInfo.put("Thinking in Java", "1675694820210");
    }

    public void clickOnPreOrderButtonForSelectedBook(String bookName) {
        String bookIsbn = bookInfo.get(bookName);
        WebElement btn = header.findElement(By.xpath(String.format(preOrderButtonTemplate, bookIsbn)));
        btn.click();
    }

    public WebElement getAddToBasketButton(){
        return addToBasketButton;
    }

    public WebElement getRefineResultsButton(){
        return refineResultsButton;
    }

    public WebElement getPreOrderButton() {
        return preOrderButton;
    }

    public String formatLocatorForSearchDropDown(String filterName) {
        return String.format(filterSectionXPath, filterName);
    }

    public List<WebElement> getAllSearchResults() {
        return allSearchResults;
    }

    public void clickOnRefineResultsButton() {
        refineResultsButton.click();
    }

    public void clickOnPreOrderButton() {
        preOrderButton.click();
    }
}
