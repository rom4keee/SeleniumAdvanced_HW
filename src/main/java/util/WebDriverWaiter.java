package util;

import com.cucumber.junit.driver.WDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.time.Duration;

public class WebDriverWaiter {

    public WebDriverWaiter waitForElement(WebElement element, long seconds){
        getWebDriverWait(seconds).until(elementIsDisplayed(element));
        return this;
    }

    public WebDriverWait getWebDriverWait(long seconds) {
        return new WebDriverWait(WDriver.getDriver(), Duration.ofSeconds(30));
    }

    public ExpectedCondition<Boolean> elementIsDisplayed(WebElement element) {
        return driver -> element.isDisplayed();
    }
}
