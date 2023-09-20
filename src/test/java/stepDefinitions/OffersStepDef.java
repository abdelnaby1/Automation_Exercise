package stepDefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;

public class OffersStepDef {
    TestContext testContext;
    public OffersStepDef(TestContext testContext){
        this.testContext = testContext;
    }

    @Then("The product should exist on offers page when the user search for {string}")
    public void the_product_should_exist_on_offers(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} second")
    public void the_product_should_exist_on_offers2(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} third")
    public void the_product_should_exist_on_offers3(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} fourth")
    public void the_product_should_exist_on_offers4(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} fifth")
    public void the_product_should_exist_on_offers5(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} sixth")
    public void the_product_should_exist_on_offers6(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} seventh")
    public void the_product_should_exist_on_offers7(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
    @Then("The product should exist on offers page when the user search for {string} eighth")
    public void the_product_should_exist_on_offers8(String searchTerm) {
        String productOnOffers =
                testContext.homePage.openOffersPage()
                        .searchFor(searchTerm)
                        .getProductName();

        Assert.assertEquals(productOnOffers,testContext.productName,"The product should be exist on offers page");

    }
}
