package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager implements DriverManager {

        @Override
        public WebDriver createDriver() {
            if (DriverManager.executionTypeProperty.equalsIgnoreCase("remote")) {
                 /*
//             * Steps to execute remotely with selenium grid and dockers VERY simple steps:...
//             * 1- Install docker
//             * 2- You need to have a .yml file to configure the network between the containers like that we have in the src/main/resource file "docker-compose_native.yml"
//             * 3- open a terminal on the .yml file directory
//             * 4- Enter the following command that will setup the containers (1 hub & 4 nodes) and run them automatically:
//             * docker-compose -f docker-compose_native.yml up --scale chrome=4 --remove-orphans -d
//             * 5- Enter the following command to check the running containers: docker ps
//             * 6- open a browser and enter this url to see the grid :D http://localhost:4444/ui/index.html
//             * 7- execute using this condition
//             */
                try {
                    return new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getChromeOptions());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }else if (DriverManager.executionTypeProperty.equalsIgnoreCase("saucelab")) {
                try {
                    return new RemoteWebDriver(new URL(saucelabUrl),
                            getChromeOptionsForSauceLab());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if (DriverManager.executionTypeProperty.equalsIgnoreCase("local headless")) {
                return new ChromeDriver(getChromeOptions());
            }
            // else return chrome local
            return new ChromeDriver();

        }
        private ChromeOptions getChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            return options;
        }
    private ChromeOptions getChromeOptionsForSauceLab() {
        ChromeOptions browserOptions = new ChromeOptions();
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
