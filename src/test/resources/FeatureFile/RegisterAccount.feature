Feature: Registration

#SSR-502 >> Agree terms and conditions

@OLD
Scenario: Verify Terms and Condition on Customer Registration Page 
Given I am at SouthernWater web portal
When I open Customer Registration Page
Then I am able to see Terms & Conditions Check Box 
And Hyperlink for Terms & Conditions

@OLDNotworking
Scenario: Verify that user is able to navigate to the terms and conditions page after clicking the button for the same
Given I am at online Customer Registration Page
When I Click on term & condition link
Then I Should move to Terms & Conditions Page
And I can See the Close Button
And I Click on Close button
And I should move to Customer Registration Page



@OLD
Scenario: Verify that Agree Terms & Condition checkbox is mandatory 
Given Customer Registration Page is open
When I click on Start button without agree Terms & Conditions
Then I should see the error message for agree Terms & Conditions 
And Second at below the Terms & Condition line

@OLD
Scenario: Verify that the validation message for agree Terms & Condition should disappear after checking agree Terms & Condition 
Given Customer Registration Page is open and having Terms & Condition error on page
When I Click checkbox against Terms & Conditions
Then Agree Terms & Conditions Error message should remove from both locations

@OLD
Scenario: Verify that user is navigated to your privacy page after clicking the Your Privacy Link 
Given I am on Customer Registration Page 
When I Click Your Privacy Link
Then I Should move to Privacy Page


#SSR-444 >> Tell the user what information they will need at the start of the register account journey


@OLD
Scenario: Verify field details message on customer registration page
Given I am on online SouthernWater Portal page
When I Access Customer Registration Page
Then I am able to see It should only take a few minutes 
And Your customer number This is the 8-digit number shown on the top right of your bill
And Your last name as shown on the bill
And Your email address



# SSR-926 >> Tell the customer a verification email has been sent to them

@NA 
Scenario: Verify that a message is displayed to the user informing email has been sent to confirm registration
Given I am on online SouthernWater Customer Registration Page
When An email for verification send to me 
Then I am able to see the message to check the email on Customer Registration page.

# SSR-960 >> Confirm account has been successfully registered/activated

@NA
Scenario: Verify that user is able to activate the account after verifying the email address and able to login as well
Given I have the Email Verification Link
When I Click on verification email before 48 hours
Then I should move to new page
And I am able to see that my account successful registered/activated
And I am able to login

@NA 
Scenario: Verify that Verification Link Expire After 48 hours
Given I have the Account Verification Link in mail
When I Click on verification email after 48 hours
Then I should Error message for linked is expired 

 @OLD
 Scenario Outline: Verify that as SouthernWater User I can verify my detail on Registration Checkdetail Step 
    Given I am at online Register Web page
    When I Check Terms & Condition
    And Click on Start
    And Enter on Customer Number <CustNumber> Last Name <LastName> Email <EmailID>
    And Click on Continue link 
    Then I Can see Name  
    And Email 
    And Customer Number
    And Address

Examples:
    | CustNumber | LastName | EmailID|
    |30041953|Pinku|xovisi7659@hmnmw.com|
  
 
 @OLD
 Scenario Outline: Verify the user is able to navigate to the previous step using the back button on Online Registration
    Given I am at Your Details step in Online Registration
    When Enter Customer Number <CustNumber> Last Name <LastName> Email <EmailID>
    And Click Continue  
    And Click on back Button of Check Details
    Then I should move to Your Details


Examples:
    | CustNumber | LastName | EmailID|
    |30041953|Pinku|xovisi7659@hmnmw.com|
   
 @OLD
 Scenario Outline:  Verify that as SouthernWater User I can Change my Email on check detail step in online Registration
    Given I am Accessing Online Regisration Page
    And Check Terms and Condition
    And Click Start
    And Enter detail Customer Number <CustNumber> Last Name <LastName> Email <EmailID>
    And Click Continue Button link
    When I Click Back Button of Check Detials Step
    And I Update the New Email <NewEmail>
    And Click on again on Continue link 
    Then I Can see Updated Emails

Examples:
    | CustNumber | LastName | EmailID|NewEmail|
    |30041953|Pinku|xovisi7659@hmnmw.com|abc.xyz@yahoo.com|


@OLD
 Scenario Outline: Verify that first Selected Security Question does not display in Second Security Question
    Given I am Login Details Page after Entering Customer Number <CustNumber> Last Name <LastName> Email <EmailID> and <Password>
    And I Select First Security Question <Question1> and Answer <Answer1>
   	When I want to Select Second Security Question <Question1> same as in First Security Question
    Then I Can not See First Secuirty Question Option in Security Question Options

Examples:
    | CustNumber|LastName|EmailID|Password|Question1|Answer1|
    |30041953|Pinku|xovisi7659@hmnmw.com|Apple@123| What was the model of the first car you owned? |Maruti|

    
 @OLD
 Scenario Outline:  Verify that user is able to register the account successfully after filling out all of the mandatory fields
    Given I have open the Online Registration Link
    When I agree  Terms and Condition
    And I Click Start
    And Enter My Detail Customer Number <CustNumber> Last Name <LastName> Email <EmailID>
    And Click Continue Button Present on Screen
    And Click Confirm and Continue
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    And Click on Login Detail Continue Button
    Then I Can See Activation Mail send on my EmailID

Examples:
    | CustNumber | LastName | EmailID|Password|Question1|Answer1|Question2|Answer2|
    |30041953|Pinku|xovisi7659@hmnmw.com|Apple@123| What was the model of the first car you owned? |Maruti| What is the name of your first pet? |Dog|

