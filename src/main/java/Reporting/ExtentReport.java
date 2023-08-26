package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentReport {
    private static ExtentReports report;
    private static ExtentTest test;
    public static void initReports() {
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports//ExtentReport.html");
        spark.config().setReportName(System.getProperty("report.name"));
        spark.config().setDocumentTitle(System.getProperty("document.title"));

        report = new ExtentReports();
        report.attachReporter(spark);

        report.setSystemInfo("Java Version",System.getProperty("java.version"));
        report.setSystemInfo("Java Home",System.getProperty("java.home"));
        report.setSystemInfo("OS Name",System.getProperty("os.name"));
        report.setSystemInfo("OS Version",System.getProperty("os.version"));
        report.setSystemInfo("OS Architecture",System.getProperty("os.arch"));
        report.setSystemInfo("User",System.getProperty("user.name"));
        report.setSystemInfo("Tester Name",System.getProperty("tester.name"));
        report.setSystemInfo("Tester Email",System.getProperty("tester.email"));
    }
    public static void flushReports() {
        report.flush();
    }
    public static void createTest(String testcaseName) {
        test = report.createTest(testcaseName);
    }
    public static void removeTest(String testcaseName) {
        report.removeTest(testcaseName);
    }

    public static void info(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    public static void info(Markup m) {
        test.info(m);
    }

    public static void pass(String message) {
        test.pass(message);
    }

    public static void pass(Markup m) {
        test.pass(m);
    }
    public static void pass(Media m) {
        test.pass(m);
    }

    public static void fail(String message) {
        test.fail(message);
    }

    public static void fail(Markup m) {
        test.fail(m);
    }

    public static void fail(Throwable t) {
        test.fail(t);
    }

    public static void fail(Media media) {
        test.fail(media);
    }

    public static void skip(String message) {
        test.skip(message);
    }

    public static void skip(Markup m) {
        test.skip(m);
    }

    public static void skip(Throwable t) {
        test.skip(t);
    }
    public static Media attachScreenshotToExtentReport(WebDriver driver) {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64), "Full Page Screenshot").build();
    }
}
