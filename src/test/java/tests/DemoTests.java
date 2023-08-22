package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LandingPage;
import pages.OrderDetailsPage;
import utils.BrowserActions;


public class DemoTests {
    WebDriver driver;
    String actualProductName = "ZARA COAT 3";
    String countryName = "egypt";

    @BeforeClass
    public void setup(){
        driver = DriverFactory.getDriver();

    }
    @Test
    public void addToCartTest(){

        Boolean isProductExistedOnCart =
                new LandingPage(driver)
                .goTo()
                .loginValid("ahmedabdelnaby@gmail.com","Ab123456789")
                        .addProductToCart(actualProductName)
                        .goToCart()
                        .isProductAdded(actualProductName);

        Assert.assertTrue(isProductExistedOnCart);


        new CartPage(driver)
                .goToCheckout()
                .enterCountry(countryName)
                        .clickPlaceOrder();

    }

    @Test(dependsOnMethods = {"addToCartTest"})
    public void checkOrderTest(){

        String productName =
                new OrderDetailsPage(driver)
                .goToMyOrders()
                .getLastProductOrdered();

        Assert.assertTrue(actualProductName.equalsIgnoreCase(productName));
    }
    @AfterClass
    public void teardown(){
        DriverFactory.quitDriver();

    }
}
