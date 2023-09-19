package CucmberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions"}
        ,monochrome = true
)
public class TestngRunner extends AbstractTestNGCucumberTests {
}
