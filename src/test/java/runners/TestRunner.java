package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions","hooks"}
        ,monochrome = false
        ,tags = "@search or @searchAgain or @checkout"
        ,plugin = {"summary","pretty",
                "rerun:target/failedRerun.txt",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "json:target/cucumber-reports/cucumber.json",
                "html:cucumber-reports/report.html",
                "listeners.CucumberListener",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
