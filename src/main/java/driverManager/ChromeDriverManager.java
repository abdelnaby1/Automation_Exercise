package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserActions;

public class ChromeDriverManager extends DriverFactory {
    protected void createDriver() {
        driver.set(new ChromeDriver());
        context.setAttribute("driver", driver.get());
        if (System.getProperty("maximize").equalsIgnoreCase("true")) {
            BrowserActions.maximizeWindow(driver.get());
        } else {
            BrowserActions.setWindowResolution(driver.get());
        }
    }
}
