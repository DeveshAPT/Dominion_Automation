Feature: Move-In

  @SIT
  Scenario: Verify End to End Move-In Mandatory Field
    Given I have open the move-in page
    And I click on Start button
    And I Click Continue in MoveIN
    And I See Mandatory Field Error Message Moving Details
    And I Enter Mandatory Fileds Moving Details
    And I Click Continue in MoveIN
    And I Click Continue in MoveIN
    And I See Mandatory Field Error Message Your Details
    And I Enter Mandatory Fileds Your Details
    And I Click Continue in MoveIN
    And I Click Continue in MoveIN
    And I See Mandatory Field Error Message Payment Details
    And I Enter Mandatory Fileds Payment Details
    When I Click Continue in MoveIN
    Then I Should move to Check Detail Page

  #Via Excel
  @excel
  Scenario Outline: Move-In into Metered Property as non DD Customer
    Given I have open the move-in page
    And I click on Start button
    And I Enter Address PostCode for TestCase <TestCase>
    And I Click on Find Address
    And I Select my Address <TestCase> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter Customer Detail for <TestCase>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Login Password as <TestCase>
    And First Security Question and Answer <TestCase>
    And Second Security Question and Answer <TestCase>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | TestCase |
      | MPNDD    |
      
  
    #Via Excel
  @excel11
  Scenario Outline: Move-In into Metered Property as non DD Customer
    Given I have open the move-in page
    And I click on Start button
    And I Enter Address PostCode for TestCase <TestCase>
    And I Click on Find Address
    And I Select my Address <TestCase> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter Customer Detail for <TestCase>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Login Password as <TestCase>
    And First Security Question and Answer <TestCase>
    And Second Security Question and Answer <TestCase>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | TestCase |
      | MoveIn    |

  @excel
  Scenario Outline: Move-In Asses Property as non DD Customer
    Given I have open the move-in page
    And I click on Start button
    And I Enter Address PostCode for TestCase <TestCase>
    And I Click on Find Address
    And I Select my Address <TestCase> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter Customer Detail for <TestCase>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Login Password as <TestCase>
    And First Security Question and Answer <TestCase>
    And Second Security Question and Answer <TestCase>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | TestCase |
      | APNDD    |

  @excel
  Scenario Outline: Move-in into Unmetered Property non DD Customer
    Given I have open the move-in page
    And I click on Start button
    And I Enter Address PostCode for TestCase <TestCase>
    And I Click on Find Address
    And I Select my Address <TestCase> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter Customer Detail for <TestCase>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Login Password as <TestCase>
    And First Security Question and Answer <TestCase>
    And Second Security Question and Answer <TestCase>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | TestCase |
      | UPNDD    |

  @SIT
  Scenario Outline: Move-In into Metered Property as non DD Customer different Corespondence address
    Given I have open the move-in page
    And I click on Start button
    And I Enter Address PostCode for TestCase <TestCase>
    And I Click on Find Address
    And I Select my Address <TestCase> from address list
    And I Enter my  moving date
    And I Select Correspondence address No
    And I enter Correspodant Address PostCode <TestCase>
    And I Select my Correspodant Address <TestCase>
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Enter Customer Detail for <TestCase>
    And I Click Continue in MoveIN
    And Select Direct Dabit as No
    And I Click Continue in MoveIN
    When I Click Confirm Details
    And Customer ID, Payment Reference depict on Screen
    And I Click Continue in MoveIN
    And Set Login Password as <TestCase>
    And First Security Question and Answer <TestCase>
    And Second Security Question and Answer <TestCase>
    And I agree  Terms and Condition
    And I Click Continue in MoveIN
    Then I Should  See Activation Mail send on my EmailID Message
    And Click on Finish
    And Confirmation Message

    Examples: 
      | TestCase |
      | MPNDDC   |

  #Non Excel
  @SIT
  Scenario Outline: Move-In into Metered Property as non DD Customer
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
      | PostCode | Address                            | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO33 1AT | 155 MARLBOROUGH ROAD,RYDE,PO33 1AT | Dr.    | FTest21 | MTest21 | LTest21 | 25/10/1985 | 9818156878 | test2A@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Move-In Asses Property as non DD Customer
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
      | PostCode | Address                            | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO33 1AT | 155 MARLBOROUGH ROAD,RYDE,PO33 1AT | Dr.    | FTest21 | MTest21 | LTest21 | 25/10/1985 | 9818156878 | test2A@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Move-in into Unmetered Property non DD Customer
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
      | PostCode | Address                            | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO33 1AT | 155 MARLBOROUGH ROAD,RYDE,PO33 1AT | Dr.    | FTest21 | MTest21 | LTest21 | 25/10/1985 | 9818156878 | test2A@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Move-In into Metered Property as non DD Customer different Corespondence address
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
      | PostCode | Address                            | PostCode2 | Address2                           | NTitle | FName   | MName   | LName   | DOB        | PNumber    | email            | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO33 1AT | 155 MARLBOROUGH ROAD,RYDE,PO33 1AT | PO33 2JT  | 19 RIBOLEAU STREET, RYDE, PO33 2JT | Dr.    | FTest21 | MTest21 | LTest21 | 25/10/1985 | 9818156878 | test2A@yahoo.com | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Move-in into metered Property as DD Customer Full payment
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
    And I select to 'Pay in Full'
    And I Select Randome Day of Month
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
      | PostCode | Address                                     | NTitle | FName  | MName  | LName  | DOB        | PNumber    | email           | SortCode | AccNumber | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN | Miss   | FTest1 | MTest1 | LTest1 | 25/10/1985 | 9818156878 | test1@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Move-in Asses Property as non  Customer Full payment
    Given I have open the move-in page
    And I click on Start button
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Enter my  moving date
    And I Select Correspondence address yes
    And I Enter Number of occupant
    And I Click Continue in MoveIN
    And I Select Home as Contact
    And I Enter NameTitle <NTitle> FirstName <FName> MName <MName> LName <LName> Date of Birth <DOB> PhoneNumber <PNumber> email <email>
    And I Click Continue in MoveIN
    And Select Direct Debit as Yes
    And I Enter BankDetail <FName> Sort Code <SortCode> Account Number <AccNumber>
    And I select to 'Pay in Full'
    And I Select Randome Day of Month
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
      | PostCode | Address                                     | NTitle | FName  | MName  | LName  | DOB        | PNumber    | email           | SortCode | AccNumber | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN | Miss   | FTest1 | MTest1 | LTest1 | 25/10/1985 | 9818156878 | test1@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario Outline: Move-in into Unmetered Property DD customer
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
      | PostCode | Address                                     | NTitle | FName  | MName  | LName  | DOB        | PNumber    | email           | SortCode | AccNumber | Password  | Question1                                      | Answer1 | Question2                           | Answer2 |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN | Miss   | FTest1 | MTest1 | LTest1 | 25/10/1985 | 9818156878 | test1@yahoo.com |   200000 |  55779911 | Apple@123 | What was the model of the first car you owned? | Maruti  | What is the name of your first pet? | Dog     |

  @SIT
  Scenario: Verify the Back and Continue Traversal from Check and Confirm
    Given I am at Check Detail Step of Move-in
    When I Click Back on Button
    Then I Should move to Payment Detail Page
    And I Click Back
    And I Should move to Your Detail Page
    And I Click Back
    And I Should move to Moving Detail Page
    And I Click Continue in MoveIN
    And I Click Continue in MoveIN
    And I Click Continue in MoveIN
    And I Should move to Check Detail Page

  @SIT
  Scenario Outline: Verify Address Edit from Check and Confirm Step
    Given I am at Check Detail Step of Move-in
    When I Click Back on Button
    Then I Should move to Payment Detail Page
    And I Click Back
    And I Should move to Your Detail Page
    And I Click Back
    And I Should move to Moving Detail Page
    And I enter Address post code <PostCode>
    And I Click on Find Address
    And I Select my Address <Address> from address list
    And I Click Continue in MoveIN
    And I Click Continue in MoveIN
    And I Click Continue in MoveIN
    And I Should move to Check Detail Page
    And I Should see the Update Address <Address>

    Examples: 
      | PostCode | Address                                     |
      | PO39 0AN | IVYHURST ,  THE BROADWAY, TOTLAND, PO39 0AN |

  @SIT
  Scenario: Verify the User interface is loaded Correctly
    Given I have open the move-in page
    When I Moved till Check Detail Page
    Then I Can See UI is loaded Correctly
