package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LandingPage;


public class DemoTests {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver =  DriverFactory.getDriver();
    }
    @Test
    public void loginTest(){
        String actualProductName = "ZARA COAT 3";

        Boolean isProductExistedOnCart =
                LandingPage.using(driver)
                .goTo()
                .login("ahmedabdelnaby@gmail.com","Ab123456789")
                        .addProductToCart(actualProductName)
                        .goToCart()
                        .isProductAdded(actualProductName);

        Assert.assertTrue(isProductExistedOnCart);

        String countryName = "egypt";

        CartPage.using(driver)
                .goToCheckout()
                .enterCountry(countryName)
                        .clickPlaceOrder();

    }


    @AfterClass
    public void teardown(){
        if (driver != null){
            driver.quit();
        }
    }
}
