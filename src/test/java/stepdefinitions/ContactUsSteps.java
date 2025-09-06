package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.Locators;
import utils.WebDriverUtils;

public class ContactUsSteps {

    @Given("I am on the Contact Us page")
    public void i_am_on_the_contact_us_page() {
        Hooks.driver.get("https://www.webdriveruniversity.com/Contact-Us/contactus.html");
    }

    @When("I fill in the form with valid details")
    public void i_fill_in_the_form_with_valid_details() {
        Hooks.utils.highlightClearAndSendKeys(Locators.FIRST_NAME.getLocator(), "John");
        Hooks.utils.highlightClearAndSendKeys(Locators.LAST_NAME.getLocator(), "Doe");
        Hooks.utils.highlightClearAndSendKeys(Locators.EMAIL.getLocator(), "john.doe@example.com");
        Hooks.utils.highlightClearAndSendKeys(Locators.MESSAGE.getLocator(), "This is a test message.");
    }

    @And("I submit the form")
    public void i_submit_the_form() {
        Hooks.utils.highlightAndClickElement(Locators.SUBMIT_BUTTON.getLocator());
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        String successMessage = Hooks.driver.findElement(Locators.SUCCESS_MESSAGE.getLocator()).getText();
        Assert.assertTrue(successMessage.contains("Thank You for your Message!"));
    }
}