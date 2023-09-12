package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LandingPage;
import utils.BrowserActions;

import static org.testng.Assert.assertTrue;


public class DemoTwoTests {
    WebDriver driver;
    String actualProductName = "ZARA COAT 3";
    String countryName = "egypt";
    String token;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();

    }
    @Test
    public void addToCartTestTwo(){
        Boolean isProductExistedOnCart =
                new LandingPage(driver)
                        .goTo()
                        .loginValid("ahmedabdelnaby123@gmail.com","Ab123456789")
                        .addProductToCart(actualProductName)
                        .goToCart()
                        .isProductAdded(actualProductName);

        assertTrue(isProductExistedOnCart);
        token = (String) ((JavascriptExecutor)driver).executeScript("return localStorage.getItem('token')");

        new CartPage(driver)
                .goToCheckout()
                .enterCountry(countryName)
                .clickPlaceOrder();

    }

    @Test(dependsOnMethods = {"addToCartTestTwo"})
    public void checkOrderTestTwo(){


        new LandingPage(driver).goTo();

        ((JavascriptExecutor)driver).executeScript("localStorage.setItem('token',arguments[0])",token);

        Boolean isProductDisplayed =
                new LandingPage(driver)
                        .goTo()
                        .getNavbar()
                        .gotToMyOrders()
                        .isProductDisplayed(actualProductName);

        assertTrue(isProductDisplayed);

    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
