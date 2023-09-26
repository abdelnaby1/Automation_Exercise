package FD;

import org.openqa.selenium.WebDriver;

public class Driver {
    public static class GUI{
       public static class WebDriver{
           public static org.openqa.selenium.WebDriver getInstance{
               return new WebDriver()
           }
           public static WebDriver getInstance(DriverFactory.DriverType driverType) {
               return new WebDriver(driverType);
           }

           public static WebDriver getInstance(DriverFactory.DriverType driverType, MutableCapabilities mutableCapabilities) {
               return new WebDriver(driverType, mutableCapabilities);
           }
       }
    }
}
