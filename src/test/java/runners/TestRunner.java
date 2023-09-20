package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions"}
        ,monochrome = true
        ,tags = "@search"
        ,plugin = {"summary","pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "json:target/cucumber-reports/cucumber.json",
                "html:cucumber-reports/report.html",
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
