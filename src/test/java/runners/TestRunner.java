package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions"}
        ,monochrome = true
        ,tags = "@netBanking or @vodBanking"
)
public class TestngRunner extends AbstractTestNGCucumberTests {
}
