package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.BrowserActions;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager extends DriverManager {

        @Override
        protected WebDriver createDriver(ExecutionType executionType,String executionTypeProperty,String host,String port) {
            result = Reporter.getCurrentTestResult();
            context = result.getTestContext();
            if(executionType == ExecutionType.REMOTE || (executionType == ExecutionType.FROM_PROPERTIES
                    && executionTypeProperty.equalsIgnoreCase("remote"))){
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
                    driver.set(new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
                            getChromeOptions()));
                    context.setAttribute("driver", driver.get());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else if (executionType == ExecutionType.LOCAL_HEADLESS || (executionType == ExecutionType.FROM_PROPERTIES
                    && executionTypeProperty.equalsIgnoreCase("local headless"))) {
                driver.set(new ChromeDriver(getChromeOptions()));
            } else {
                driver.set(new ChromeDriver());
                if (System.getProperty("maximize").equalsIgnoreCase("true")) {
                    BrowserActions.maximizeWindow(driver.get());
                } else {
                    BrowserActions.setWindowResolution(driver.get());
                }
            }
            context.setAttribute("driver", driver.get());
            return driver.get();
        }
        private ChromeOptions getChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            return options;
        }
}
