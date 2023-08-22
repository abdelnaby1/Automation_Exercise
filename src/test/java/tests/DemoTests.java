package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LandingPage;
import utils.BrowserActions;


public class DemoTests {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
        System.out.println("actual driver: "+driver);

    }
    @Test
    public void demoTest(){
        String actualProductName = "ZARA COAT 3";

        Boolean isProductExistedOnCart =
                new LandingPage(driver)
                .goTo()
                .loginValid("ahmedabdelnaby@gmail.com","Ab123456789")
                        .addProductToCart(actualProductName)
                        .goToCart()
                        .isProductAdded(actualProductName);

        Assert.assertTrue(isProductExistedOnCart);

        String countryName = "egypt";

        new CartPage(driver)
                .goToCheckout()
                .enterCountry(countryName)
                        .clickPlaceOrder();

    }


    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();

    }
}
