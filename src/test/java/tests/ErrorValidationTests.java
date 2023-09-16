package tests;

import DataManager.ddt.JsonManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ErrorValidationTests {
    private WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver = DriverFactory.getDriver();
    }
//    @DataProvider
//    public Object[][] getData(){
//        HashMap<Object, Object> map = new HashMap<Object,Object>();
//        map.put("email","ahmedabdelnaby@gmail.com");
//        map.put("password","Ab1234567891");
//        HashMap<Object, Object> map2 = new HashMap<Object,Object>();
//        map2.put("email","ahmedabdelnaby@gmail.com");
//        map2.put("password","Ab1234567891");
//        HashMap<Object, Object> map3 = new HashMap<Object,Object>();
//        map3.put("email","ahmedabdelnaby@gmail.com");
//        map3.put("password","Ab1234567891");
//        return new Object[][]{{map},{map2},{map3}};
//    }
    @DataProvider
    public Object[][] getData(){
        JsonManager manager = new JsonManager("purchaseOrder.json");
        List<HashMap<Object, Object>> data = manager.getJsonDataAsMap();
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
    @Test(groups = {"errorValidation"},dataProvider = "getData", testName = "InvalidLoginTest")
    public void InvalidLoginTest(HashMap<Object,Object> inputs){

        String errorMsg =
                new LandingPage(driver)
                .goTo()
                .loginInValid((String) inputs.get("email"), (String) inputs.get("password"))
                        .getErrorMsg();
        assertTrue(errorMsg.equalsIgnoreCase("Incorrect email or password."));


    }

    @Test( testName = "addInvalidProductTest")
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
