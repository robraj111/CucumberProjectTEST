package com.selenium.steps;



import com.selenium.webdriver.WebConnector;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenericSteps {
	
	WebConnector con;
	
	public GenericSteps(WebConnector con) {
		this.con=con;
	}
	
	@Before
	public void before(Scenario s) {
		System.out.println("####### Scenario started execution ######"+s.getName());
		con.initReport(s.getName());
		
	}
	
	@After
	public void after() {
		System.out.println("####### execution completed  ######");
		con.quitRep();
		con.quitBrowser();
		
	}
	
	@Given("^I go to (.*) on (.*)$")
	public void launchUrl(String url,String browser) {
		con.reportInfo("Open browser : "+browser);
		con.openBrowser(browser);
		con.reportInfo("launch "+url);
		con.launchUrl(url);
	}
	
    @When("^I click on (.*)$")
    public void click(String locatorKey) {
    	con.reportInfo("click on  : "+locatorKey);
    	con.click(locatorKey);
    	con.reportInfo("clicked on  : "+locatorKey);
		
	}
	
	
    @And("^I enter (.*) in (.*)$")
    public void type(String data,String locatorKey ) {
    	con.reportInfo(" type "+data+" in "+locatorKey);
    	con.clearText(locatorKey);
    	con.type(locatorKey, data);
    	con.reportInfo(" typed "+data+" in "+locatorKey);
		
	}
	
    @Then("^Logout should (.*)$")
    public void logout(String expectedResult) {
    	con.logoutValidation(expectedResult);
    	con.reportPass("Logged out successfully");

    }
    
    @Given("^I login to RediffMoney with deafult (.*) and (.*) on browser$")
    public void doLogin(String username,String password) {
    	con.doLogin(username, password);
    	
    }
	
    @When("^I select (.*) from (.*) dropdown$")
    public void selectValueFromDropdown(String data,String locatorKey) {
    	con.reportInfo("Select "+data+"from "+ locatorKey+ "dropdown");
    	con.isValuselectDropdownValue(locatorKey, data);
    }
    
    @When("^I Accept Alert$")
    	public void acceptAlert() {
    	con.reportInfo("Accept Alert");
    	con.acceptAlert();
    	con.reportInfo("accepted alert successfully");
    		
    	}
   	
	

}
