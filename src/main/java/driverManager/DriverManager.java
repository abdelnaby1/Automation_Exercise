package driverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

public interface DriverManager {


    static final String executionTypeProperty = System.getProperty("execution.type");
    static final String host = System.getProperty("remote.execution.host");
    static final String port = System.getProperty("remote.execution.port");
    static final String saucelabUrl = System.getProperty("saucelab.url");
    static final String saucelabUsername = System.getProperty("saucelab.username");
    static final String saucelabKey = System.getProperty("saucelab.accesskey");


    public enum ExecutionType {
        LOCAL("Local"), REMOTE("Remote"), LOCAL_HEADLESS("Local Headless"), FROM_PROPERTIES(executionTypeProperty);

        private final String value;

        ExecutionType(String type) {
            this.value = type;
        }

        private String getValue() {
            return value;
        }
    }
        WebDriver createDriver();

}
