package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import hooks.TestContext;

public class HomePageStepDe {
    String productName;
    TestContext testContext;
    public HomePageStepDe(TestContext testContext){
        this.testContext = testContext;
    }
    @Given("User is on GreenCart Landing page")
    public void user_is_on_green_cart_landing_page() {
        testContext.homePage.openHomePage();
    }
    @When("^User search with short name (.+) and extract the actual name for the product with ok$")
    public void user_search_with_short_name_with_ok(String searchTerm) {
        testContext.productName =
                testContext.homePage.
                searchFor(searchTerm)
                .getProductName(searchTerm);
    }
    @When("User search with short name {string} and extract the actual name for the product")
    public void user_search_with_short_name(String searchTerm) {
        testContext.productName =
                testContext.homePage.
                        searchFor(searchTerm)
                        .getProductName(searchTerm);
    }

//    @When("^User search with short name (.+)$")
//    public void user_search_with_short_name_to_add_it_to_cart(String searchTerm) {
//                testContext.homePage.
//                        searchFor(searchTerm);
//    }
    @When("^User add (.+) items of product with shortname (.+) to the cart and proceeds to checkout$")
    public void user_add_product_to_cart(int qty,String shortName) {
        testContext.homePage.
                addProductToCart(1,qty,shortName)
                .goToCart();
    }

}
