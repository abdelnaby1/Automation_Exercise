package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import driverManager.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LandingPage;

import java.io.File;


public class ExtentReportTest {

//    WebDriver driver;
//    ExtentReports report;
//    private static ExtentTest test;


//    @BeforeTest
//    public void config(){
        // ExtentReports, ExtentSparkReporter
//
//        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports//ExtentReport.html");
//        spark.config().setReportName(System.getProperty("report.name"));
//        spark.config().setDocumentTitle(System.getProperty("document.title"));
//        report = new ExtentReports();
//        report.attachReporter(spark);
//
//        report.setSystemInfo("Java Version",System.getProperty("java.version"));
//        report.setSystemInfo("Java Home",System.getProperty("java.home"));
//        report.setSystemInfo("OS Name",System.getProperty("os.name"));
//        report.setSystemInfo("OS Version",System.getProperty("os.version"));
//        report.setSystemInfo("OS Architecture",System.getProperty("os.arch"));
//        report.setSystemInfo("User",System.getProperty("user.name"));
//        report.setSystemInfo("Tester Name",System.getProperty("tester.name"));
//        report.setSystemInfo("Tester Email",System.getProperty("tester.email"));

//    }

//    @BeforeClass
//    public void setup(){
//        driver = DriverFactory.getDriver();
//
//    }

//    @Test
//    public void extentReportDemoTest(){
////        ExtentTest test = report.createTest("Demo");
//        new LandingPage(driver)
//                .goTo();
//        Assert.assertTrue(true);
//    }
//    @Test(dependsOnMethods = {"extentReportDemoTest"})
//    public void extentReportDemoTest2(){
//        Assert.assertTrue(true);
//    }
//    @AfterTest
//    public void endConfig(){
////        report.flush();
//
//    }
//    @AfterClass
//    public void teardown(){
//        DriverFactory.quitDriver();
//
//    }
}
