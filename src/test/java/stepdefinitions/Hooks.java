package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.WebDriverUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverUtils utils;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        utils = new WebDriverUtils(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        // Log scenario status to the report
        String status = scenario.isFailed() ? "Failed" : "Passed";
        scenario.log("Scenario Status: " + status);

        // Capture and attach screenshot
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                // Attach screenshot to Cucumber report with status in name
                scenario.attach(screenshot, "image/png", "Screenshot_" + scenario.getName().replaceAll(" ", "_") + "_" + status);

                // Save screenshot to file
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                String screenshotName = "target/screenshots/" + scenario.getName().replaceAll(" ", "_") + "_" + status + "_" + timestamp + ".png";
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshotFile, new File(screenshotName));
            } catch (IOException e) {
                e.printStackTrace();
                scenario.log("Failed to capture or save screenshot: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }
}