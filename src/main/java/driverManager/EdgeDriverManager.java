package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeDriverManager extends DriverManager {
    @Override
    protected WebDriver createDriver() {
        result = Reporter.getCurrentTestResult();
        context = result.getTestContext();
        if(DriverManager.executionTypeProperty.equalsIgnoreCase("remote")){

            try {
                driver.set(new RemoteWebDriver(new URL("http://" + DriverManager.host + ":" + DriverManager.port + "/wd/hub"),
                        getEdgeOptions()));
                context.setAttribute("driver", driver.get());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
            driver.set(new EdgeDriver(getEdgeOptions()));
        } else {
            driver.set(new EdgeDriver());
            if (System.getProperty("maximize").equalsIgnoreCase("true")) {
                BrowserActions.maximizeWindow(driver.get());
            } else {
                BrowserActions.setWindowResolution(driver.get());
            }
        }
        context.setAttribute("driver", driver.get());
        return driver.get();
    }
    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        return options;
    }
}
