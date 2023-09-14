package Listener;

import Reporting.ExtentReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import utils.ElementLocators;

import java.util.Arrays;

import static org.testng.Assert.fail;


public class MyWebDriverListener implements WebDriverListener {
    @Override
    public void afterClick(WebElement element) {
        try {
            ExtentReport.info("Click on " + element.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        try {
            ExtentReport.info("Typing "+ Arrays.toString(keysToSend) +" into "+ element.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        try{
            ExtentReport.info("Navigating to ["+url+"] ");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Override
    public void afterQuit(WebDriver driver) {
        try {
            ExtentReport.info("Quiting Driver");
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}
