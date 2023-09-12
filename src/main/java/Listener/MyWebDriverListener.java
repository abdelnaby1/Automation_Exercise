package Listener;

import Reporting.ExtentReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import utils.ElementLocators;

import java.util.Arrays;


public class MyWebDriverListener implements WebDriverListener {
    @Override
    public void afterClick(WebElement element) {
        ExtentReport.info("Click on " + new ElementLocators().getElementLoc(element));
    }
    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        ExtentReport.info("Typing "+ Arrays.toString(keysToSend) +" into "+ new ElementLocators().getElementLoc(element));
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        ExtentReport.info("Navigating to ["+url+"] ");
    }
    @Override
    public void afterQuit(WebDriver driver) {
        ExtentReport.info("Quiting Driver");
    }

}
