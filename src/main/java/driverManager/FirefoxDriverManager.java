package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FirefoxDriverManager extends DriverManager{
    @Override
    protected WebDriver createDriver() {
        if (DriverManager.executionTypeProperty.equalsIgnoreCase("remote")) {

                try {
                    return new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getFirefoxOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

        }else if (DriverManager.executionTypeProperty.equalsIgnoreCase("saucelab")) {
            try {
                return new RemoteWebDriver(new URL(saucelabUrl),
                        getFirefoxOptionsForSauceLab());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
            return new FirefoxDriver(getFirefoxOptions());

        }
        // else return chrome local
        return new FirefoxDriver();
    }
    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        return options;
    }
    private FirefoxOptions getFirefoxOptionsForSauceLab() {
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", saucelabUsername);
        sauceOptions.put("accessKey", saucelabKey);
        sauceOptions.put("build", "selenium-build-E0H34");
        sauceOptions.put("name", "Automation practice");
        browserOptions.setCapability("sauce:options", sauceOptions);
        return browserOptions;
    }
}
