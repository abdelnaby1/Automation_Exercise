package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MainSteps {
    @When("the user open login page")
    public void the_user_open_login_page() {
        System.out.println("user on login page");
    }
    @Given("User login into the application")
    public void user_login_into_the_application() {
        System.out.println("user login into the application");
    }
    @Then("Homepage is displayed")
    public void homepage_is_displayed() {
        System.out.println("the user on the homepage");
    }
    @Then("Cards are displayed")
    public void cards_are_displayed() {
        System.out.println("and the card is displayed");
    }

}
