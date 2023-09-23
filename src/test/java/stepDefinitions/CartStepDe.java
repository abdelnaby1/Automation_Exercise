package stepDefinitions;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CartStepDe {
    TestContext testContext;
    public CartStepDe(TestContext testContext){
        this.testContext = testContext;
    }

    @Then("^User validate the (.+) in the checkout page$")
    public void user_proceeds_to_checkout(String shortName) {
        String productOnCart =
                testContext.cartPage
                        .getProductNameOnCart(1);

        Assert.assertEquals(productOnCart,testContext.productName,"The product should be exist on Cart page");

    }

    @Then("User able to place the order")
    public void user_able_to_place_the_order() {
        testContext.cartPage
                .placeOrder("Egypt");
    }


}
