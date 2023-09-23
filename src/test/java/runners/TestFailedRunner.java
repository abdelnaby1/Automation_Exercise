package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = { "@target/failedRerun.txt" },
        glue = {"stepDefinitions","hooks"}
        ,monochrome = true
        ,plugin = {"summary","pretty",
        "rerun:target/failedrerun.txt",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "json:target/cucumber-reports/cucumber.json",
        "html:cucumber-reports/report.html",
        "listeners.CucumberListener",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
)
public class TestFailedRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
