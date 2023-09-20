package stepDefinitions;

import driverManager.DriverFactory;
import greenKart.pages.HomePage;
import greenKart.pages.OffersPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchStepDe {
    private WebDriver driver;
    private HomePage homePage;
    String productName;
    @Before
    public void beforeScenario() {
        driver = DriverFactory.getDriver();
        homePage =  new HomePage(driver);
    }
    @Given("User is on GreenCart Landing page")
    public void user_is_on_green_cart_landing_page() {
        homePage.openHomePage();
    }
    @When("User search with short name {string}")
    public void user_search_with_short_name(String searchTerm) {
        homePage.searchFor(searchTerm);
    }
    @And("Extract the actual name for the product")
    public void extract_the_actual_name_for_the_product() {
        productName = homePage.getProductName();

    }
    @Then("The product should exist on offers page when the user search for {string}")
    public void the_product_should_exist_on_offers(String searchTerm) {
        String productOnOffers =
                homePage.openOffersPage()
                .searchFor(searchTerm)
                .getProductName();

        Assert.assertEquals(productOnOffers,productName,"The product should be exist on offers page");

    }
}
