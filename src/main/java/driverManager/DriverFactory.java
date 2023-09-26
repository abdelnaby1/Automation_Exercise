package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import utils.BrowserActions;



import static org.testng.Assert.fail;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String browserTypeProperty = System.getProperty("browser.type");
    private static final String onBrowserStack = System.getProperty("execution.browserstack");

    public enum BrowserType {
        MOZILLA_FIREFOX("Mozilla Firefox"),
        GOOGLE_CHROME("Google Chrome"),
        EDGE("Edge"),
        FROM_PROPERTIES(browserTypeProperty);

        private final String value;

        BrowserType(String type) {
            this.value = type;
        }

        private String getValue() {
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
                System.err.println("Unable to gracefully quit WebDriver."+ e); // We'll replace this with actual Loggers later - don't worry!
            }finally {
                driver.set(null);
            }

        }
    }

    public static synchronized WebDriver getDriver() {
        if (driver.get() == null){
            return setupDriver(BrowserType.FROM_PROPERTIES);

        }
        return driver.get();
    }
    public synchronized WebDriver getDriver(String browser) {
        if (driver.get() == null){
            if (browser.equalsIgnoreCase(BrowserType.MOZILLA_FIREFOX.value))
            {
                return setupDriver(BrowserType.MOZILLA_FIREFOX);
            } else if (browser.equalsIgnoreCase(BrowserType.EDGE.value)) {
                return setupDriver(BrowserType.EDGE);
            }else {
                // chrome will be the default
                return setupDriver(BrowserType.GOOGLE_CHROME);
            }
        }
        return driver.get();
    }
    public static WebDriver setupDriver() {
         return setupDriver(BrowserType.FROM_PROPERTIES);
    }
    private static WebDriver setupDriver(BrowserType browserType) {
        ITestResult result = Reporter.getCurrentTestResult();
        ITestContext context = result.getTestContext();
        if(browserType == BrowserType.GOOGLE_CHROME
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {


            driver.set(ThreadGuard.protect(new ChromeDriverManager().createDriver()));
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

            driver.set(ThreadGuard.protect(new FirefoxDriverManager().createDriver()));
            context.setAttribute("driver", driver.get());
//            Helper.implicitWait(driver.get());
            if (System.getProperty("maximize").equalsIgnoreCase("true")) {
                BrowserActions.maximizeWindow(driver.get());
            } else {
                BrowserActions.setWindowResolution(driver.get());
            }
        }
        else if (browserType == BrowserType.EDGE
                || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("edge"))){
            driver.set(ThreadGuard.protect(new EdgeDriverManager().createDriver()));
            context.setAttribute("driver", driver.get());
//            Helper.implicitWait(driver.get());
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
