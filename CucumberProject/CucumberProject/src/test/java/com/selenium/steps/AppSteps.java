package com.selenium.steps;

import com.selenium.webdriver.WebConnector;

import io.cucumber.java.en.Then;

public class AppSteps {
	
	WebConnector con;
	public AppSteps(WebConnector con) {
		this.con=con;
	}
	
	
	@Then("^Login should (.*)$")
	public void validattion(String expectedResult) {
		con.reportInfo("verifying login");
		con.loginValidation(expectedResult);
        con.reportPass("Validation pass");
		
	}
	
	
	@Then("^Login with invlaid credentials should be (.*)$")
	public void invalidCredLogin(String expectedResult) {
		con.reportInfo("verifying login with invalid credentials");
		con.problemInLogin(expectedResult);
		con.reportPass("Validation pass");
		
	}
	
	@Then("^The new Portfolio (.*) is created and selected in (.*)$")
	public void validateListValue(String portfolioName,String locatorKey) {
		con.reportInfo("check newly created portfolio is selected or not");
		con.validateDropdownSelectedValue(portfolioName, locatorKey);
		
		
	}
	
	@Then("^The (.*) should be deleted from (.*) dropdown$")
	public void validateDeletedPortfolio(String portfolioName,String locatorKey) {
		con.reportInfo("verify whether selected portfolio been deleted or not");
		con.checkValueInDropdown(locatorKey, portfolioName);
	}

}
