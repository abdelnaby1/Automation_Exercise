package tests;


import driverManager.DriverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LandingPage;

import static org.testng.Assert.assertTrue;


public class DemoTests {
    WebDriver driver;
    String actualProductName = "ZARA COAT 3";
    String countryName = "egypt";
    String token;
    @BeforeMethod()
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

        assertTrue(isProductExistedOnCart);
        token = (String) ((JavascriptExecutor)driver).executeScript("return localStorage.getItem('token')");

        new CartPage(driver)
                .goToCheckout()
                .enterCountry(countryName)
                        .clickPlaceOrder();


    }

    @Test(dependsOnMethods = {"addToCartTest"})
    public void checkOrderTest(){

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
