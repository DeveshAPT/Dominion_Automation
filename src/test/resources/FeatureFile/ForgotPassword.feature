Feature: Forgot Password

@SIT
  Scenario: User is able to reset the Password  
    Given User is at login page
    When I Click on Forgot Password
    Then I Should navigate to Forgot Password Page 
    And I Should See Email address field for reset password
    And Back to login, Continue links 
  
@SIT
  Scenario Outline: User is notifying that Reset Password link sent on mail   
    Given I am at Reset Password Page
    When I Enter <Email> in Reset Password 
    And Click on Continue 
    Then I Can See the Verification Link Sent Message
		Examples:
    |Email|
    |abc@yahoo.com|