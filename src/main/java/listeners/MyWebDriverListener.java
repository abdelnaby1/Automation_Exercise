package listeners;

import reporting.ExtentReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Arrays;

import static org.testng.Assert.fail;


public class MyWebDriverListener implements WebDriverListener {

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        try {
            ExtentReport.info("Click on [" + element.toString().split("->")[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        try {
            ExtentReport.info("Typing ["+ Arrays.toString(keysToSend) +" into "+ element.toString().split("->")[1]);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


        @Override
    public void afterGet(WebDriver driver, String url) {
        WebDriverListener.super.afterGet(driver, url);
        try{
            ExtentReport.info("Navigating to ["+url+"] ");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

//    @Override
//    public void afterQuit(WebDriver driver) {
//        try {
//            ExtentReport.info("Quiting Driver");
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//
//    }

}
