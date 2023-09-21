package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import runners.TestContext;
import runners.TestRunner;

public class HomePageStepDe {
    String productName;
    TestContext testContext;
    public HomePageStepDe(TestContext testContext){
        this.testContext = testContext;
    }
//    @Before
//    public void beforeScenario() {
//        driver = DriverFactory.setupDriver();
//        homePage =  new HomePage(driver);
//    }
    @Given("User is on GreenCart Landing page")
    public void user_is_on_green_cart_landing_page() {
        testContext.homePage.openHomePage();
    }
    @When("User search with short name {string} and extract the actual name for the product")
    public void user_search_with_short_name(String searchTerm) {
        testContext.productName =
                testContext.homePage.
                searchFor(searchTerm)
                .getProductName(searchTerm);
    }

}
