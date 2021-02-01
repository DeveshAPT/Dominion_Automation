Feature: Auth Submit Meter Reading 

@SIT
 Scenario Outline: Authorized User Should have Access Submit Meter Page   
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
    When I Click on 'Submit a meter reading' from Dashboard
    Then I can view Your meter reading page
    
 Examples:
    |Email|Password|
    |kumar.devesh82@yahoo.com|Apple@123|
    
@SIT
 Scenario Outline: Authorized Submit Meter Page Back Button
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
    When I Click on 'Submit a meter reading' from Dashboard
    And I click to Back button of Your meter reading page
    Then I should navigate to my account dashboard
    And I Should see Customer Number & Payment Reference
    
 Examples:
    |Email|Password|
    |kumar.devesh82@yahoo.com|Apple@123|    
    
@SIT
  Scenario Outline: Login with Valid credentials to verify updated Meter Reading and today date 
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
    When I Click on 'Submit a meter reading' from Dashboard
    And I enter meter reading 
    And I click on Continue button
    Then I should move to Confirm meter reading step 
    And I can view updated Meter Reading 
    And I can view today date 
    
 Examples:
    |Email|Password|
    |kumar.devesh82@yahoo.com|Apple@123|
    
@SIT1
  Scenario Outline: Login with Valid credentials to update meter reading  
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
    When I Click on 'Submit a meter reading' from Dashboard
    When I click Back button of Confirm meter reading page
    Then I can update the new meter reading
    And I can click to continue Button of Your meter reading page
    And I can view updated meter reading on Confirm meter reading 
  
 Examples:
    |Email|Password|
    |kumar.devesh82@yahoo.com|Apple@123|
    
 