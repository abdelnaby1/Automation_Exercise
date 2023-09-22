package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import driverManager.DriverFactory;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class CucumberListener implements EventListener {
    public static int count_totalTCs;
    public static int count_passedTCs;
    public static int count_skippedTCs;
    public static int count_failedTCs;

    public CucumberListener() {
        super();
    }
    private ExtentSparkReporter spark;
    private ExtentReports extent;


    Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();
    ExtentTest scenario;
    ExtentTest step;

    String featureSource;
    @Override
    public void setEventPublisher(EventPublisher publisher) {

        publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::ScenarioFinished);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);

    }


    // Here we create the reporter. Execute before run feature file
    private void runStarted(TestRunStarted event) {

        spark = new ExtentSparkReporter("./ExtentReports/ExtentReportResults.html");
        extent = new ExtentReports();
//        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName(System.getProperty("report.name"));
        spark.config().setDocumentTitle(System.getProperty("document.title"));

        // Create extent report instance with spark reporter
        extent.attachReporter(spark);

        extent.setSystemInfo("Java Version",System.getProperty("java.version"));
        extent.setSystemInfo("Java Home",System.getProperty("java.home"));
        extent.setSystemInfo("OS Name",System.getProperty("os.name"));
        extent.setSystemInfo("OS Version",System.getProperty("os.version"));
        extent.setSystemInfo("OS Architecture",System.getProperty("os.arch"));
        extent.setSystemInfo("User",System.getProperty("user.name"));
        extent.setSystemInfo("Tester Name",System.getProperty("tester.name"));
        extent.setSystemInfo("Tester Email",System.getProperty("tester.email"));

    }

    // runFinished event is triggered when all feature file executions are completed
    private void runFinished(TestRunFinished event) {
//        scenario.info("Scenario Passed: + "+count_passedTCs);
//        scenario.info("Scenario Skipped: + "+count_skippedTCs);
//        scenario.info("Scenario Failed: + "+count_failedTCs);
        extent.flush();

    }

    // This event is triggered when feature file is read
    // here we create the feature node
    private void featureRead(TestSourceRead event) {
        featureSource = event.getUri().toString();
        String featureName = featureSource.split(".*/")[1];
        if (feature.get(featureSource) == null) {
            feature.putIfAbsent(featureSource, extent.createTest(featureName));
        }
    }

    // This event is triggered when Test Case is started
    // here we create the scenario node
    private void ScenarioStarted(TestCaseStarted event) {
        String featureName = event.getTestCase().getUri().toString();
        scenario = feature.get(featureName).createNode(event.getTestCase().getName());
        String featureN= featureSource.split(".*/")[1];
        scenario.assignCategory(featureN.split("\\.")[0]);
        count_totalTCs = count_totalTCs + 1;

    }

    private void ScenarioFinished(TestCaseFinished event) {
        String featureName = event.getTestCase().getUri().toString();
        //Here we list the Scenarios. Because in Cucumber, a test case is a Scenario
        if (event.getResult().getStatus().toString().equals("PASSED")) {
            count_passedTCs = count_passedTCs + 1;
        }
        if (event.getResult().getStatus().toString().equals("FAILED")) {
            count_failedTCs = count_failedTCs + 1;
        }
        if (event.getResult().getStatus().toString().equals("SKIPPED")) {
            count_skippedTCs = count_skippedTCs + 1;
        }
    }

    // Step started event
    private void stepStarted(TestStepStarted event) {
        String stepName = " ";
        String keyword = "Triggered the hook :";


        // We check whether the event is from a hook or step
        if (event.getTestStep() instanceof PickleStepTestStep steps) {
            // TestStepStarted event implements PickleStepTestStep interface
            // Which have additional methods to interact with the event object
            // So we have to cast TestCase object to get those methods
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();
            step = scenario.createNode(Given.class, keyword + " " + stepName);

        }
//        else {
//            // Same with HookTestStep
//            HookTestStep hoo = (HookTestStep) event.getTestStep();
//            stepName = hoo.getHookType().name();
//        step = scenario.createNode(Given.class, keyword + " " + stepName);
//        }



    }

    // This is triggered when test Step is finished
    private void stepFinished(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep steps){
            if (Objects.equals(event.getResult().getStatus().toString(), "PASSED")) {
                step.log(Status.PASS, "This step was passed");
            } else if (Objects.equals(event.getResult().getStatus().toString(), "SKIPPED")) {
                step.log(Status.SKIP, "This step was skipped ");
            } else {
                step.log(Status.FAIL, "This step was failed");
//                step.log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(
//                        ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64), "Screenshot Of the Page").build());
            }
        }

    }
}
