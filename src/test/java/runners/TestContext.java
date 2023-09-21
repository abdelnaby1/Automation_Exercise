package runners;

import driverManager.DriverFactory;
import greenKart.pages.HomePage;
import org.openqa.selenium.WebDriver;

public  class TestContext {
    public WebDriver driver;
    public HomePage homePage;
    public String productName;

    public TestContext() {
        this.driver = DriverFactory.setupDriver();
        homePage = new HomePage(driver);

    }
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}