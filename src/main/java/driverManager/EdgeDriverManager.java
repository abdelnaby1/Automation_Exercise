package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class EdgeDriverManager implements DriverManager{
    @Override
    public WebDriver createDriver() {
        if (DriverManager.executionTypeProperty.equalsIgnoreCase("remote")) {

                try {
                    return new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getEdgeOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

        }else if (DriverManager.executionTypeProperty.equalsIgnoreCase("saucelab")) {
            try {
                return new RemoteWebDriver(new URL(saucelabUrl),
                        getEdgeOptionsForSauceLab());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(getEdgeOptions());

        }
        // else return chrome local
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        return options;
    }
    private EdgeOptions getEdgeOptionsForSauceLab() {
        EdgeOptions browserOptions = new EdgeOptions();
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
