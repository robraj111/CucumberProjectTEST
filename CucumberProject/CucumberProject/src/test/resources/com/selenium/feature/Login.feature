#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag1
Feature: Loginto Rediff Money


  @tag1
  Scenario: Lgin to RediffMoney
    Given I go to RediffMoney on browser
    When I click on loginLink_xpath
    And I enter username in username_xpath
    And I enter password in password_xpath
    When I click on submitButton_xpath
    Then Login should success
    
     @tag2
  Scenario: Lgin to RediffMoney and logout
    Given I go to RediffMoney on browser
    When I click on loginLink_xpath
    And I enter username in username_xpath
    And I enter password in password_xpath
    When I click on submitButton_xpath
    And I click on SignOut_xpath
    Then Logout should success
    
     @tag3
  Scenario: Lgin to RediffMoney with invalid credentials
    Given I go to RediffMoney on browser
    When I click on loginLink_xpath
    And I enter username1 in username_xpath
    And I enter password1 in password_xpath
    When I click on submitButton_xpath
    Then Login with invlaid credentials should be fail

    

 