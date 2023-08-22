package driverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import utils.BrowserActions;
import utils.Helper;

import static org.testng.Assert.fail;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String browserTypeProperty = System.getProperty("browser.type");

    public enum BrowserType {
        MOZILLA_FIREFOX("Mozilla Firefox"),
         GOOGLE_CHROME("Google Chrome"),
        EDGE("edge"),
        FROM_PROPERTIES(browserTypeProperty);

        private String value;

        BrowserType(String type) {
            this.value = type;
        }

        protected String getValue() {
            return value;
        }
    }

    public static void quitDriver() {
        if (null != driver.get()) {
            try {
                driver.get().quit(); // First quit WebDriver session gracefully
                driver.remove(); // Remove WebDriver reference from the ThreadLocal variable.
                driver.set(null);
            } catch (Exception e) {
                System.err.println("Unable to gracefully quit WebDriver."+ e); // We'll replace this with actual Loggers later - don't worry !
            }


        }
    }

    public static WebDriver getDriver() {

        return getDriver(BrowserType.FROM_PROPERTIES);
    }
    // should i use synchronized or not? it a topic to search for later
    public static synchronized WebDriver getDriver(BrowserType browserType) {
        ITestResult result = Reporter.getCurrentTestResult();
        ITestContext context = result.getTestContext();
        if(browserType == BrowserType.GOOGLE_CHROME
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {


            driver.set(new ChromeDriverManager().createDriver());
            context.setAttribute("driver", driver.get());
//            Helper.implicitWait(driver.get());
            if (System.getProperty("maximize").equalsIgnoreCase("true")) {
                BrowserActions.maximizeWindow(driver.get());
            } else {
                BrowserActions.setWindowResolution(driver.get());
            }

        }
        else if (browserType == BrowserType.MOZILLA_FIREFOX
                || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))){

            driver.set(new FirefoxDriverManager().createDriver());
            context.setAttribute("driver", driver.get());
            if (System.getProperty("maximize").equalsIgnoreCase("true")) {
                BrowserActions.maximizeWindow(driver.get());
            } else {
                BrowserActions.setWindowResolution(driver.get());
            }
        }
        else if (browserType == BrowserType.EDGE
                || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("edge"))){
            driver.set(new EdgeDriverManager().createDriver());
            context.setAttribute("driver", driver.get());
            if (System.getProperty("maximize").equalsIgnoreCase("true")) {
                BrowserActions.maximizeWindow(driver.get());
            } else {
                BrowserActions.setWindowResolution(driver.get());
            }
        }
        else {
                String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
                        + "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
                fail(warningMsg);
            throw new NullPointerException(warningMsg);
        }

        return driver.get();
    }
}
