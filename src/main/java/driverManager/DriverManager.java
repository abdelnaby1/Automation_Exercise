package driverManager;

import org.openqa.selenium.WebDriver;

public interface DriverManager {


    String executionTypeProperty = System.getProperty("execution.type");
    String host = System.getProperty("remote.execution.host");
    String port = System.getProperty("remote.execution.port");
    String saucelabUrl = System.getProperty("saucelab.url");
    String saucelabUsername = System.getProperty("saucelab.username");
    String saucelabKey = System.getProperty("saucelab.accesskey");


    enum ExecutionType {
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
