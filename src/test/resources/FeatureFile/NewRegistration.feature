Feature: New-Account-Registration

  @SIT
  Scenario Outline: New Account Registration
    Given I have open the Online Registration Link
    When I agree  Terms and Condition
    And I Click Start
    And I Enter Details as Customer Number <CustNumber> Last Name <LastName> Email <EmailID>
    And Click Continue Button Present on Screen
    And Click Confirm and Continue
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    And Click on Login Detail Continue Button
    Then I Can See Activation Mail send on my EmailID

    Examples: 
      | CustNumber | LastName | EmailID              | Password   | Question1                                      | Answer1 | Question2                           | Answer2 |
      |   30041953 | Pinku    | xovisi7659@hmnmw.com | Apple@1234 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Mandatory Field Validation
    Given I have open the Online Registration Link
    And I Click Start
    And I should see the Mandatory Field Message on Register Now Step
    And I agree Terms and Condition
    And I Click Start
    And Enter My Detail Customer Number <WrongInput>
    And Click Continue Button Present on Screen
    And I should see the Mandatory Field Message on Your Details Step
    And I Enter Details as Customer Number <CustNumber> Last Name <LastName> Email <EmailID>
    And Click Continue Button Present on Screen
    And Click Confirm and Continue
    And Click on Login Detail Continue Button Without input
    And I should see the Mandatory Field Message on Login Details Step
    When I Enter Password <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    Then I Should not see any Mandatory Error Message

    Examples: 
      | WrongInput | CustNumber | LastName | EmailID              | Password   | Question1                                      | Answer1 | Question2                           | Answer2 |
      | test33     |   30041953 | Pinku    | xovisi7659@hmnmw.com | Apple@1234 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Edit Email ID
    Given I am Login Detail page Using <CustNumber> Last Name <LastName> Email <EmailID>
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    When I Click Back Button
    And I Move to Check and Confirm Step
    And I Click Back Button
    And I Update the New Email <newEmail>
    And Click Continue Button Present on Screen
    Then I Can see Updated Emails

    Examples: 
      | WrongInput | CustNumber | LastName | EmailID              | newEmail           | Password   | Question1                                      | Answer1 | Question2                           | Answer2 |
      | test33     |   30041953 | Pinku    | xovisi7659@hmnmw.com | newEmail@hmnmw.com | Apple@1234 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
      
      
 @SIT
  Scenario Outline: Back Forward Traversal
    Given I am Login Detail page Using <CustNumber> Last Name <LastName> Email <EmailID>
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    When I Click Back Button
    And I Move to Check and Confirm Step
    And I Click Back Button
    And I Should move to Your Details Step
    And Click Continue Button Present on Screen
    And Click Confirm and Continue
    Then I Should move back to Login Details Step

    Examples: 
      | WrongInput | CustNumber | LastName | EmailID              | newEmail           | Password   | Question1                                      | Answer1 | Question2                           | Answer2 |
      | test33     |   30041953 | Pinku    | xovisi7659@hmnmw.com | newEmail@hmnmw.com | Apple@1234 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
