package tests;

import driverManager.DriverFactory;
import driverManager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LandingPage;
import utils.BrowserActions;

import static org.testng.Assert.assertTrue;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
        System.out.println("actual driver: "+driver);
    }
    @Test
    public void loginTest(){

        String errorMsg =
                LandingPage
                .using(driver)
                .goTo()
                .loginInValid("ahmedabdelnaby@gmail.com","Ab1234567891")
                        .getErrorMsg();
        assertTrue(errorMsg.equalsIgnoreCase("Incorrect email or password."));
        assertTrue(BrowserActions.getPageUrl(driver).contains("/auth/login"));


    }


    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();
    }
}
