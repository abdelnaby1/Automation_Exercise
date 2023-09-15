package tests;

import DataManager.ExcelFileManager;
import DataManager.JsonFileManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfigurationsTests {
    WebDriver driver;
    ExcelFileManager excelFileManager;
    @BeforeMethod()
    public void setup(){
//        driver = DriverFactory.getDriver();
        excelFileManager= new ExcelFileManager("testData.xlsx");

    }
    @Test
    public void testSomething(){
      var data = excelFileManager.switchToSheet("Sheet1")
              .getData("purchase");

        System.out.println("my login data");
        System.out.println(data);
    }

    @AfterMethod
    public void teardown(){
//        DriverFactory.quitDriver();

    }
}
