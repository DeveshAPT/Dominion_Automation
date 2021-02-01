Feature: Account Dashboard Non Direct Debit Customer

  Background: A

  # Non Direct Debit Customer >> NDD
  @SIT
  Scenario Outline: Verify Account Dashboard Quick links for Non Direct Debit Metered Customer
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    Then I can see the QucikLinks <Links> on Dashboard
    And I can see Customer Number,Payment Reference on Dashboard
    And I Can see Customer Email ID, Correspondence Address under your profile section
    And I Can see Last Meter Reading, Meter Number, Date last Read
    And I Can see Property address, Your Services
    And I Can see Latest Bill, Bill Amount, Bill Date
    And I Can see Amount Due, Amount, date

    Examples: 
      | CustType | Links                                                                                                |
      | NDD      | Set up a Direct Debit, View bills/account statement ,View payment history ,Change paperless settings |

  @SIT
  Scenario Outline: Verify Dashboard Menu Items
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    Then I Can See Following Menu <MenuItem> Items

    Examples: 
      | CustType | MenuItem                                                                                                                                                                       |
      | NDD      | Make a payment , Set up / Amend a Direct Debit ,View bills / Account statement ,Payment history,Submit a meter reading,Paperless settings,Tell us you_re moving,Update profile |

  @SIT
  Scenario Outline: Verify Logout Functionality
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click on Logout from Dashboard
    Then I Should See 'Thank You' Message after Logout
    And I am not able to see 'Your Account' Option

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Submit Meter Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click on 'Submit a meter reading' from Dashboard
    Then I Should Move to Submit Meter Reading Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Make a payment Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click on 'Make a payment' from Dashboard
    Then I Should Move to Payment Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Close Account Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click on 'Tell us you are moving' from Dashboard
    Then I Should Move to Close Account Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify the paperless Setting for online Bill
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click Change Paperless Setting
    And I Should move to Paperless Setting Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Direct Debit Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click Set Up Direct Debit
    Then I Should move to Setup Direct Debit Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Your Profile Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click update Profile
    Then I Should move to View Profile Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Payment History Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click Set Up Payment history
    Then I Should move to Payment history Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify View bills/Account statement Navigation
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And Click on 'Your Account' Option
    And I Click View bills/Account statement
    Then I Should move to View Bill Account Statement Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Submit Meter Navigation by quick link on Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click on 'Submit a meter reading' button from Dashboard
    Then I Should Move to Submit Meter Reading Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Close Account Navigation by quick link on Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click on 'Tell us you are moving' button from Dashboard
    Then I Should Move to Close Account Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Make a payment Navigation by quick link on Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click on 'Make a payment' button from Dashboard
    Then I Should Move to Payment Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Your Profile Navigation by quick link on Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click 'View Profile' button from Dashboard
    Then I Should move to View Profile Page

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Contact Us Navigation from Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click Contact us
    Then Contact Us page should open in new tab

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Verify Find out more Navigation from Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click Discover ways to save
    Then Save Water page should open in new tab

    Examples: 
      | CustType |
      | NDD      |

  @SIT
  Scenario Outline: Registered User can access How to keep it clear
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click Learn more
    Then How to prevent blockage page should open in new tab

    Examples: 
      | CustType |
      | NDD      |

  # UnMetered Non Direct Debit Customer >> UMNDD
  @SIT
  Scenario Outline: Verify Account Dashboard Quick links for Un-Metered Account
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    Then I can see the QucikLinks <Links> on Dashboard
    And I Can see Struggling to Pay,Amend Direct Debit
    And I Can see Meter Reading, This account is not metered
    And I Can see Customer Email ID, Correspondence Address under your profile section
    And I Can see Property address, Your Services
    And I Can see Latest Bill, Bill Amount, Bill Date

    #And I Can see Amount Due, Amount, date
    Examples: 
      | CustType | Links                                                                                          |
      | UMNDD    | Make a payment , View bills/account statement ,View payment history ,Change paperless settings |

  @SIT
  Scenario Outline: Verify Struggling to pay Navigation from Dashboard
    Given I am at login Page
    And Login with <CustType> Credentials
    When Click on Login
    And I Click Struggling to Pay
    Then Struggling to Pay page should open in new tab

    Examples: 
      | CustType |
      | UMNDD    |
