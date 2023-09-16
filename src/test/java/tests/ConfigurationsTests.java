package tests;

import dataManager.ddt.ExcelManager;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConfigurationsTests {
    // example of ddt using Excel sheet
    WebDriver driver;
    ExcelManager excelFileManager;
    @BeforeMethod()
    public void setup(){
        driver = DriverFactory.getDriver();
    }
    @DataProvider
    public Object[][] getSomeData(){
        excelFileManager= new ExcelManager("testData.xlsx");
        var data = excelFileManager
                        .switchToSheet("Sheet1")
                        .getData("Login");
        var data2 = excelFileManager
                .getData("Purchase");


        return new Object[][] {
                {data.get(0),data.get(1),data.get(2),data.get(3)},
                {data2.get(0),data2.get(1),data2.get(2),data2.get(3)},
        };
    }
    @Test(dataProvider = "getSomeData")
    public void testSomething(String l1,String l2,String l3,Double l4){

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println("done");

    }

    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();

    }
}
