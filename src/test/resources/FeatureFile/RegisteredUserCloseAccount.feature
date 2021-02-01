Feature: Auth Close Account
Description: The purpose of this feature is to test the Close Account Functionality of Registered User

#Background: I should be Login in SouthernWater Portal
#Given I have SouthernWater Login Page on Browser
#When I Enter my Account EmailID and Password
#And I Click on Login Button On Page
#Then I Should Login to SouthernWater Portal

@SIT
Scenario Outline: I Should move to DashBoard after clicking back button of Close Account
Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
When I Click on 'Tell us you are moving' from Dashboard
Then I Should see Your Account link on the Page

Examples:
    |Email|Password|
    |kumar.devesh82@yahoo.com|Apple@123|

@SIT
Scenario Outline: As Registered User I Should Close My Account 
Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
When I Click on 'Tell us you are moving' from Dashboard
And I Click Close your account from you are moving 
And I Enter the MoveOut Date
And I Click On Continue of Move Out Step
And I Click On Continue of Final Bill Step
And I Enter Post Code as <PostCode>
And I Click on Find Address
And I Select <Address> from Address List
And I Click Continue of Forwarding Address
And I Click Confirm and Close Account
Then My Account Should Close
And I Can See Closing Message

 Examples:
    |Email|Password|PostCode|Address|
     |kumar.devesh82@yahoo.com|Apple@123|CT16 3NR|18 WITLEY WALK, WHITFIELD, CT16 3NR|