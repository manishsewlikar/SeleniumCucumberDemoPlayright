package utils;

import org.openqa.selenium.By;

public enum Locators {
    FIRST_NAME(By.name("first_name")),
    LAST_NAME(By.name("last_name")),
    EMAIL(By.name("email")),
    MESSAGE(By.name("message")),
    SUBMIT_BUTTON(By.cssSelector("input[type='submit']")),
    SUCCESS_MESSAGE(By.id("contact_reply"));

    private final By locator;

    Locators(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }
}