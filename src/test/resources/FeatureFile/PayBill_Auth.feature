Feature: Pay Bill Auth


@SIT
Scenario Outline: As a Registered user I Can Pay Partial Amount of My Bill
	 	Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
		And I Click on 'Make a payment' from Dashboard
		And I Click Select Pay Another Amount Option
		And I Enter Amount as <Amount>
		And I Click on Make Payment Link on Page
		When I Fill Payment Card Detail <Card> Name <Name> Expiry Month <expiryMonth> Expiry Year <expiryYear> and SecurityCode <SecureCode>
		And I Click on Pay Now Button on Make Payment
		Then I Should see Payment Confirmation Message
		And I Should See Thankyou Message
		And I Should See Transaction with Transaction
		And I Should See Payment Reference With Reference
		And I Can See Same Amount as <Amount> I Pay on Portal
		
		 Examples:
    |Email|Password|Amount|Card|Name|expiryMonth|expiryYear|SecureCode|
    |kumar.devesh82@yahoo.com|Apple@123|1|122000000000003|Fiona Vardy|08|27|453|
    
 @SIT
Scenario Outline: As a Registered user I Can Pay Full Amount of My Bill
		Given I am on Account DashBoard Using Login Credentials <Email> and <Password> 
		And I Click on 'Make a payment' from Dashboard
		And I Click Select Pay Full Amount Option
		And I Click on Make Payment Link on Portal
		When I Fill Payment Card Detail <Card> Name <Name> Expiry Month <expiryMonth> Expiry Year <expiryYear> and SecurityCode <SecureCode>
		And I Click on Pay Now Button on Make Payment
		Then I Should see Payment Confirmation Message
		And I Should See Thankyou Message
		And I Should See Transaction with Transaction
		And I Should See Payment Reference With Reference
		And I Should See Payment Amount as I Pay on Portal
		
		 Examples:
    |Email|Password|Card|Name|expiryMonth|expiryYear|SecureCode|
    |kumar.devesh82@yahoo.com|Apple@123|122000000000003|Fiona Vardy|08|27|453|