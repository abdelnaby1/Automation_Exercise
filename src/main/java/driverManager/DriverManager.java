package driverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

public abstract class DriverManager {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ITestResult result;
    protected static ITestContext context;
    private static String executionTypeProperty = System.getProperty("execution.type");
    private static String host = System.getProperty("remote.execution.host");
    private static String port = System.getProperty("remote.execution.port");


    public enum ExecutionType {
        LOCAL("Local"), REMOTE("Remote"), LOCAL_HEADLESS("Local Headless"), FROM_PROPERTIES(executionTypeProperty);

        private String value;

        ExecutionType(String type) {
            this.value = type;
        }

        protected String getValue() {
            return value;
        }
    }
    protected abstract WebDriver createDriver(ExecutionType executionType,String executionTypeProperty,String host, String port);
    public WebDriver getDriver() {
        if (null == driver.get()) {
            driver.set(this.createDriver(ExecutionType.FROM_PROPERTIES,executionTypeProperty,host,port));
        }
        return driver.get();
    }


}
