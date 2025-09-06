package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WebDriverUtils {

    private final WebDriver driver;
    private final FluentWait<WebDriver> wait;
    private final JavascriptExecutor js;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        this.js = (JavascriptExecutor) driver;
    }

    public void highlightAndClickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        highlightElement(element);
        element.click();
    }

    public void highlightClearAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
        // Validate text was entered
        String enteredText = element.getAttribute("value");
        if (!enteredText.equals(text)) {
            throw new RuntimeException("Failed to enter text '" + text + "' into element with locator: " + locator);
        }
    }

    public void highlightElement(WebElement element) {
        try {
            String originalStyle = element.getAttribute("style");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 3px solid red; background: yellow;");
            Thread.sleep(500); // Brief pause to show highlight
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, originalStyle != null ? originalStyle : "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}