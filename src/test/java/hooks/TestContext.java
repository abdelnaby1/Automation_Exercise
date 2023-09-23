package hooks;

import driverManager.DriverFactory;
import greenKart.pages.CartPage;
import greenKart.pages.HomePage;
import org.openqa.selenium.WebDriver;

public  class TestContext {
    public WebDriver driver;
    public HomePage homePage;
    public CartPage cartPage;
    public String productName;

    public TestContext() {
        this.driver = DriverFactory.setupDriver();
        this.homePage = new HomePage(driver);
        this.cartPage = new CartPage(driver);

    }
    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}