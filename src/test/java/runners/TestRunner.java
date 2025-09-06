package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "utils"},
        plugin = {"com.aventstack.chaintest.plugins.ChainTestCucumberListener:com.aventstack.chaintest.reporter.ChainTestReporter",
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        publish = true,
        monochrome = true



)
public class TestRunner extends AbstractTestNGCucumberTests {
}