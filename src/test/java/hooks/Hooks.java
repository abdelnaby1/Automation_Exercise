package hooks;

import driverManager.DriverFactory;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import reporting.AllureManager;
import utils.PropertiesReader;

import java.io.File;
import java.io.IOException;

public class Hooks {
//    @Before("@netBanking")
//    public void netBankSetup(){
//
//        System.out.println("Setup entries for net bank");
//    }
//    @Before("@vodBanking")
//    public void vodtBankSetup(){
//        System.out.println("Setup entries for vod bank");
//
//    }
    @BeforeAll
    public static void beforeAll() {
        System.out.println("================ BEFORE ALL Scenarios ================");
        PropertiesReader.loadProperties();
        AllureManager.setAllureEnvironmentInformation();
        try {
            FileUtils.deleteDirectory(new File("allure-results"));
            FileUtils.deleteDirectory(new File("screenshots"));
        } catch (IOException e) {
            System.out.println("allure-result not deleted");
        }
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("================ AFTER ALL Scenarios ================");

    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Stop Driver in Hooks ");
        DriverFactory.quitDriver();

    }
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot Failed");
        }
    }
}
