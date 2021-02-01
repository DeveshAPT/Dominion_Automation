

Feature: Logout
 
 @SIT
  Scenario Outline: Registered User Can Log out from Protal   
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
    When I Click on Logout from Dashboard
    Then I Should See 'Thank You' Message after Logout
    And I am not able to see 'Your Account' Option
    
    
    Examples:
    |Email|Password|
    |devesh.kumar@southernwater.co.uk|360@Logica|   
 
  @SIT
  Scenario Outline: User can login again after logout
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
    And I Logout from SW Portal
    When Relogin with Using Login Credentials <Email> and <Password> 
   	Then I Should see Your Account link on the Page 
    
    
    Examples:
    |Email|Password|
    |devesh.kumar@southernwater.co.uk|360@Logica|   
 
