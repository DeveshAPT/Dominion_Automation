
Feature: UnAuth Submit Meter Reading 

@SIT
Scenario: I Want to Submit my meter reading 
    Given I am on meter submit reading pages
    When I click on Having trouble link
    Then I Should move to how to find your meter page
    
@SIT
Scenario: I Want Access Submit meter reading 
    Given I am on how to find your meter page
    When I click on Submit Meter link
    Then I Should move to Submit meter reading page

@SIT
Scenario: I Want to Verify customer meter reading details 
    Given I am on submit meter reading page
    When I submit all the details of customer
    Then I Should able to verify all the details of customer 
    

@SIT
  Scenario Outline: As Unregistered User i Can Submit my Meter Reading 
    Given I am on Unregistered Submit meter Reading page
    And  I Click on Start Button
    And I Enter CustomerNumber <CustomerNumber> Payment Reference <PayRef> and LastName <LastName>
    And I Click on Continue
    And I Click again on  Continue
    When I Can Enter Updated Meter Reading 
    And I can click to continue Button of Your meter reading page
    Then I can view updated meter reading on Confirm meter reading 
  
 Examples:
    |CustomerNumber|PayRef|LastName|
    |10470201|0004012407196|DOWNHYLL|
