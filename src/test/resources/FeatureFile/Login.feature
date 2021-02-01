Feature: Login
 

@SIT
  Scenario Outline: User is not able Login with incorrect credentials 
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login 
    Then I Should not login 
    And I Should see the Login error message
    
    Examples:
    |Email|Password|
    |abc@yahoo.com|xyz123|
   
@SIT
  Scenario Outline: Login with Valid credentials   
    Given I am on login page
    When I Enter credentials <Email> and <Password> into respective fields
    And Click on Login button
    Then I Should see Your Account link on the Page
    
    
  Examples:
    |Email|Password|
    |devesh.kumar@southernwater.co.uk|360@Logica|
    
@SIT
  Scenario: User is able to move on Registration Page    
    Given I am accessing the Login Page
    When I Click on Registration 
    Then I Should Navigate to Registration Page
	