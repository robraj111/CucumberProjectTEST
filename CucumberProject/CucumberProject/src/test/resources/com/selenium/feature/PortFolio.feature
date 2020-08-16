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
@tag
Feature: Add and Delete PortFolio
  I want to use this template for my feature file

  @tag4
  Scenario: Adding portFolio
    Given I login to RediffMoney with deafult username and password on browser
    When I click on createPortfolio_xpath
    And I enter portfolioName in enterPortfolioName_xpath
    And I click on createPortfolioButton_xpath
    Then The new Portfolio portfolioName is created and selected in prtfolioLocation_xpath
    
  @tag5
  Scenario: Delete portFolio
    Given I login to RediffMoney with deafult username and password on browser
    When I select portfolioName from prtfolioLocation_xpath dropdown
    And I click on deletePortfolio_xpath
    When I Accept Alert
    Then The portfolioName should be deleted from prtfolioLocation_xpath dropdown
   

  