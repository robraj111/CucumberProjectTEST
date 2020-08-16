package com.selenium.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		        features = "src/test/resources/",
		        glue = {"com.selenium.steps"},
		        monochrome = true,
		       
		        plugin = {
		                "pretty",
		                "html:target/cucumber-reports/cucumber-pretty",
		                "json:target/cucumber-reports/CucumberTestReport.json",
		                "rerun:target/cucumber-reports/rerun.txt"
		        }
		      
		       
		        
		        )
public class MyTestngRunner extends AbstractTestNGCucumberTests {
}
