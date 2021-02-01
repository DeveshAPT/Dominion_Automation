Feature: Account Dashboard

@OLD
  Scenario Outline: As A Registered User i can see Your Account Link after Login
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should see Your Account link on the Page

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: Account Dashboard should load after your account click
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    And Click on 'Your Account' Option
    Then I Should see 'Dashboard' Option
    And I Should See 'Log out' Option
    And I Should See 'Make a payment' Option
    And I Should See 'Payment history' Option
    And I Should See 'Tell us you are moving' Option
    And I Should See 'Set up/Amend a Direct Debit' Option
    And I Should See 'Paperless settings' Option
    And I Should See 'Your profile' Option
    And I Should See 'View bills/Account statement' Option
    And I Should See 'Submit a meter reading' Option

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: Registered User Can Log out from Protal
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password>
    When I Click on Logout from Dashboard
    Then I Should See 'Thank You' Message after Logout
    And I am not able to see 'Your Account' Option

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: Registered User can access Submit Meter Reading
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password>
    When I Click on 'Submit a meter reading' from Dashboard
    Then I Should Move to Submit Meter Reading Page

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: Registered User can access Make a Payment
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password>
    When I Click on 'Make a payment' from Dashboard
    Then I Should Move to Payment Page

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: Registered User can access Close Account
    Given I am on Account DashBoard Using Login Credentials <Email> and <Password>
    When I Click on 'Tell us you are moving' from Dashboard
    Then I Should Move to Close Account Page

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: As A Registered User i can see Your Account Link after Login
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should See Quick Link for 'Set up a Direct Debit'
    And I Should See Quick Link for 'View bills/account statement'
    And I Should See Quick Link for 'View payment history'
    And I Should See Quick Link for 'Change paperless settings'
    And I Should See Quick Link for 'Struggling to pay'
    And I Should See Quick Link for 'Submit a meter reading'
    And I Should See Quick Link for 'Tell us you are moving'
    And I Should See Quick Link for 'Contact us'
    And I Should See Quick Link for 'Find out more'
    And I Should See Quick Link for 'How to keep it clear'

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should see Quick link for Make Payment on dashboard if Payment is pending
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should See Quick Link for 'Make a payment'

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should See latest Bill and Bill Date
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should See Latest Bill
    And Latest Bill Amount
    And Latest Bill Date

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should See Payment Reference and Customer Number on Dashboard
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should Customer Number on dashboard
    And Payment Reference Number on dashboard

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should See latest Bill and Bill Date
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should See Amount Due Label on Dashboard
    And Latest Bill Amount Due on Dashboard
    And Bill Date on Date

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should See latest Bill and Bill Date
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then i Should see latest Bill Date, Due Dates are same

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Can set paperless Setting for online Bill
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    And I Click Change Paperless Setting
    And I Should move to Paperless Setting Page
    When I Click on Update
    Then I Can See Thankyou Message

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Can Set Up Direct Debit
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    When I Click Set Up Direct Debit
    Then I Should move to Setup Direct Debit Page

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Can See Profile
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    When I Click Set Up your Profile
    Then I Should move to View Profile Page

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Can Payment History
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    When I Click Set Up Payment history
    Then I Should move to Payment history Page

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should See Meter Reading Section With last Meter Reading, Meter Number and Date last read
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should see the Meter Reading Heading
    And Last Meter Reading label with Value
    And Meter Number label with Value
    And Date Last Read label with Value

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User Should not see Quick link for Make Payment on dashboard if Payment is not pending
    Given I am at login page
    When I Enter my <Email> and <Password> into respective fields
    And Click on Login
    Then I Should not see Quick Link for 'Make a payment'
    And I Should See Quick Link for 'Amend Direct Debit'

    Examples: 
      | Email                            | Password   |
      | devesh.kumar@southernwater.co.uk | 360@Logica |

@OLD
  Scenario Outline: Registered User can access Contact Us from Dashboard
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    When I Click Contact us
    Then Contact Us page should open in new tab

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User can access Find out more from Dashboard
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    When I Click Find out more
    Then Target_100 page should open in new tab

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |

@OLD
  Scenario Outline: Registered User can access How to keep it clear
    Given I have open login page
    And Login With  my <Email> and <Password>
    And Click on Login
    When I Click How to keep it clear
    Then Keep it clear page should open in new tab

    Examples: 
      | Email                    | Password  |
      | Kumar.devesh82@yahoo.com | Apple@123 |
