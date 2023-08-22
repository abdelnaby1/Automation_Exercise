package tests;

import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;
import utils.BrowserActions;

import static org.testng.Assert.assertTrue;

public class ErrorValidationTests {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
    }
    @Test
    public void InvalidLoginTest(){

        String errorMsg =
                new LandingPage(driver)
                .goTo()
                .loginInValid("ahmedabdelnaby@gmail.com","Ab1234567891")
                        .getErrorMsg();
        assertTrue(errorMsg.equalsIgnoreCase("Incorrect email or password."));
//        assertTrue(BrowserActions.getPageUrl(driver).contains("/auth/login"));


    }

    @Test
    public void addInvalidProductTest(){

        String actualProductName = "ZARA COATTT 3";

        Boolean isProductExisted =
                new LandingPage(driver)
                        .goTo()
                        .loginValid("ahmedabdelnaby@gmail.com","Ab123456789")
                        .isProductDisplayed(actualProductName);

        Assert.assertFalse(isProductExisted);

    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
