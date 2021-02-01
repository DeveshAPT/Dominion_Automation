Feature: Move In

@OLD
  Scenario: Verify that user is able to move in to a address successfully
    Given I am on create an account page
    And I click on Start button
    When I fill all moving details
    And I fill all the payment details
    Then I Should be able to move in

@OLD
  Scenario: Verify that user is able to move to Moving details page
    Given I am on a create an account page
    When I click on Start button to Moving details page
    Then I should be able to move to Moving details page

@OLD
  Scenario: Verify that user will get error messages on Moving details page for not filling any values
    Given I am on Moving details page
    When I clicked on Continue button
    Then I should be able to see error messages on Moving details page for not filling any values

@OLD
  Scenario Outline: Verify that user will get error message on Moving details page for not entering valid value in postcode
    Given I am on the Moving details page
    When I enter invalid <Postcode> in Enter the postcode
    And I click on Find address button
    Then I should be able to see error messages on Moving details page for invalid postcode

    Examples: 
      | Postcode |
      |   110033 |

@OLD
  Scenario: Verify that user will get error messages on Moving details page for not selecting an address
    Given I am on the moving details page
    When I am not selecting the address
    And I clicked on the Continue button
    Then I should be able to see error messages on Moving details page

@OLD
  Scenario Outline: Verify the i can enter either Mobile or Home Contact number while moving in SW region
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    When I Click Continue in MoveIN
    Then I can see Mobile and Home number for Contact Details
    And I can select one at a time

    Examples: 
      | PostCode | Address                                     |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN |

@OLD
  Scenario Outline: Verify The Name titles Available in Move-IN Journey at Your Details step
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    When I Click Continue in MoveIN
    And Click on Title
    Then I can See Title Dropdow with Miss,Mr,Mrs,Ms,Dr.,Prof

    Examples: 
      | PostCode | Address                                     |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN |

@OLD
  Scenario Outline: Verify that i can Move-in SW region with my Bank Detail and Correspondence as Yes
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    And Select Direct Debit as Yes
    And I Enter BankDetail <FName> Sort Code <SortCode> Account Number <AccNumber>
    And I Select Pay in Monthly Statement
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | PostCode | Address                                          | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | SortCode | AccNumber | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN      | Miss   | FTest1  | MTest1  | LTest1  | 25/10/1985 | 9818156878 | test1@yahoo.com  |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
      | SO45 2LU | 1 FRANCIS COURT, WALTONS AVENUE,HOLBURY,SO45 2LU | Mrs    | FTest12 | MTest12 | LTest12 | 12/02/1980 | 9818156878 | test12@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
      | PO30 1TP | 121 HIGH STREET, NEWPORT, PO30 1TP               | Mr     | FTest13 | MTest13 | LTest13 | 20/08/1986 | 9818156878 | test13@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |


@SIT1
  Scenario Outline: Verify that Payment Frequency While Move-in SW region with my Bank Detail and Correspondence as Yes
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    When I Select Direct Debit as Yes
    Then I can See 'Pay in monthly installments'
    And 'Pay in Full'
    And I am able to select to 'Pay in monthly installments'
    And I am able to select to 'Pay in Full'

    Examples: 
      | PostCode | Address                                          | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | 
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN      | Miss   | FTest1  | MTest1  | LTest1  | 25/10/1985 | 9818156878 | test1@yahoo.com  |


@SIT1
  Scenario Outline: Verify that I can Select Payment Frequency different day of month with Direct Dabit and Pay in monthly installments
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    When I Select Direct Debit as Yes
    And I select to 'Pay in monthly installments'
    Then I Can Select any day of Month for Direct Dabit

    Examples: 
      | PostCode | Address                                          | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | 
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN      | Miss   | FTest1  | MTest1  | LTest1  | 25/10/1985 | 9818156878 | test1@yahoo.com  |     


@SIT
  Scenario Outline: Verify that I can Select Payment Frequency different day of month with Direct Dabit and Pay in Full
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    When I Select Direct Debit as Yes
    And I select to 'Pay in Full'
    Then I Can Select any day of Month for Direct Dabit

    Examples: 
      | PostCode | Address                                          | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | 
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN      | Miss   | FTest1  | MTest1  | LTest1  | 25/10/1985 | 9818156878 | test1@yahoo.com  |     


@OLD
  Scenario Outline: Verify that i can Move-in SW region without my Bank Detail and Correspondence as Yes
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | PostCode | Address                                | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO33 1AT | 155 MARLBOROUGH ROAD,RYDE,PO33 1AT     | Dr.    | FTest21 | MTest21 | LTest21 | 25/10/1985 | 9818156878 | test2A@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
      | BN14 0ES | 19 MARSHALL AVENUE,WORTHING,BN14 0ES   | Prof   | FTest22 | MTest22 | LTest22 | 25/10/1976 | 9818156878 | test2B@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
      | ME12 3AU | 2 HALFWAY ROAD,MINSTER-ON-SEA,ME12 3AU | Mrs    | FTest23 | MTest23 | LTest23 | 25/10/1983 | 9818156878 | test2C@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

@OLD
  Scenario Outline: Move-in into metered Property as non DD Customer differen Corespondence address
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address No
    And I enter Correspodant Address post code <PostCode2>
    And I Select my Correspodant Address <Address2> from address list
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    And Select Direct Debit as Yes
    And I Enter BankDetail <FName> Sort Code <SortCode> Account Number <AccNumber>
    And I Select Pay in Monthly Statement
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | PostCode | Address                                            | PostCode2 | Address2                           | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | SortCode | AccNumber | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO33 4NT | 2 LUSHINGTON VILLAS, LUSHINGTON HILL,RYDE,PO33 4NT | PO33 2JT  | 19 RIBOLEAU STREET, RYDE, PO33 2JT | Miss   | FTest3A | MTest3A | LTest3A | 25/10/1972 | 9818156878 | test3A@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
      | PO40 9EE | 2 MAYDENE, UPPER PRINCES ROAD,FRESHWATER,PO40 9EE  | PO33 2JT  | 19 RIBOLEAU STREET, RYDE, PO33 2JT | Ms     | FTest3B | MTest3B | LTest3B | 25/10/1990 | 9818156878 | test3B@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

@OLD
  Scenario Outline: Verify that i can Move-in SW region without my Bank Detail and Correspondence as No
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address No
    And I enter Correspodant Address post code <PostCode2>
    And I Select my Correspodant Address <Address2> from address list
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Password as <Password>
    And First Security Question <Question1> and Answer <Answer1>
    And Second Security Question <Question2> and Answer <Answer2>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | PostCode | Address                                          | PostCode2 | Address2                           | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email              | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | BN1 6YG  | 39 REGENCY COURT, WITHDEAN RISE,BRIGHTON,BN1 6YG | PO33 2JT  | 19 RIBOLEAU STREET, RYDE, PO33 2JT | Mr     | FTest4A | MTest4A | LTest4A | 25/10/1989 | 9818156878 | test44AA@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
   # Examples: 
   #   | PostCode | Address                                           | PostCode2 | Address2                           | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email              | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
   #   | SO14 3TP | 2 TASMAN COURT, TASMAN CLOSE,SOUTHAMPTON,SO14 3TP | PO33 2JT  | 19 RIBOLEAU STREET, RYDE, PO33 2JT | Mr     | FTest4A | MTest4A | LTest4A | 25/10/1989 | 9818156878 | test44AA@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
   #   | SO23 8SH | 20 LONGHOUSE GREEN,WINCHESTER,SO23 8SH            | PO33 2JT  | 19 RIBOLEAU STREET, RYDE, PO33 2JT | Mrs    | FTest4B | MTest4B | LTest4B | 25/10/1975 | 9818156878 | test44BB@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |
