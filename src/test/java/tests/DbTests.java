package tests;

import DataManager.ddt.ExcelManager;
import db.JDBCConnector;
import driverManager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTests {
    private WebDriver driver;
    ResultSet resultSet;
    @BeforeMethod()
    public void setup(){
        driver = DriverFactory.getDriver();
        JDBCConnector jdbcConnector = new JDBCConnector();
        resultSet = jdbcConnector.connect();
    }
    @Test
    public void testDb(){
        while (true){
            try {
                if (!resultSet.next()) break;
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("location"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        // here you can use the data returned from db to continue with your selenium code
    }
    @AfterMethod
    public void teardown(){
        DriverFactory.quitDriver();

    }

}
