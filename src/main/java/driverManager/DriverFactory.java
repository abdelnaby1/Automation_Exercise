package driverManager;

import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DriverFactory {

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



    public static WebDriver getDriver() {
        return getDriver(BrowserType.FROM_PROPERTIES);
    }
    public static synchronized WebDriver getDriver(BrowserType browserType) {
        if(browserType == BrowserType.GOOGLE_CHROME
                    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
            return new ChromeDriverManager().getDriver();
        } else if (browserType == BrowserType.MOZILLA_FIREFOX
                || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))){
            return new FirefoxDriverManager().getDriver();
        } else if (browserType == BrowserType.EDGE
                || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("edge"))){
            return new EdgeDriverManager().getDriver();
        } else {
                String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
                        + "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
                fail(warningMsg);
		        throw new NullPointerException(warningMsg);
        }
    }
}
