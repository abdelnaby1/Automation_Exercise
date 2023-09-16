package tests;

import DataManager.ddt.JsonManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LandingPage;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ErrorValidationsTests {
    private WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
    }
    @DataProvider
    public Object[][] getData(){
        JsonManager manager = new JsonManager("purchaseOrder.json");
        List<HashMap<Object, Object>> data = manager.getJsonDataAsMap();
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
    @Test(dataProvider = "getData")
    public void InvalidLoginTest2(HashMap<Object,Object> inputs){

        String errorMsg =
                new LandingPage(driver)
                        .goTo()
                        .loginInValid((String) inputs.get("email"), (String) inputs.get("password"))
                        .getErrorMsg();
        assertTrue(errorMsg.equalsIgnoreCase("Incorrect email or password."));


    }

    @Test
    public void addInvalidProductTest2(){

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
