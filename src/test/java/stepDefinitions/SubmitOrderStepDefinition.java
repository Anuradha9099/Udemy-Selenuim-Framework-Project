package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.anuradhaacademy.pageObjects.landingPage;

import java.io.IOException;

/*
This step definition is used to purchase the order from the e commerce site.
*/

public class SubmitOrderStepDefinition extends BaseTest {
    public landingPage landingPage;

    @Given("^I am landed to the site$")
    public void iAmLandedToTheSite() throws IOException {
        launchWebSite();
        //TODO: Implement the logic to land to the site
    }
}