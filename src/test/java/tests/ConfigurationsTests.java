package tests;

import DataManager.JsonFileManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfigurationsTests {
    WebDriver driver;
    JsonFileManager jsonFileManager;
    @BeforeMethod()
    public void setup(){
        driver = DriverFactory.getDriver();
        jsonFileManager = new JsonFileManager("testJson.json");

    }
    @Test
    public void testSomething(){
        System.out.println("hello there!");
        System.out.println(jsonFileManager.getAsListOfString("orders"));
        System.out.println(jsonFileManager.getAsString("name"));
        System.out.println(jsonFileManager.getAsInteger("dob"));
    }

    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();

    }
}
