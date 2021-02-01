 Feature: Paper Less Setting 
 
 Background: A
    Given I am at login Page
    And Login with Credentials
    When Click on Login
 
 @SIT
  Scenario: Verify the paperless Setting for online Bill
    And Click on 'Your Account' Option
    And I Click Change Paperless Setting
    And I Should move to Paperless Setting Page
    And I Click on Update
    Then I Can See Thankyou Message
