

Feature: SouthernWater Application Access
 
@OLD
Scenario: Check the SouthernWater App Access
    Given I am on SouthernWater Pay Bill page 
    When I Click on Start
    Then I move to customer details page
    And Back link is also avialable 
   
@OLD
Scenario: Verify PayBill BackButton Functionality
		Given I am on SouthernWater PayBill detail Page
		When I Click on Back Button
		Then I moved to Term page

@OLD
Scenario: Verify QuestionMark ToolTip
		Given I am on SouthernWater Pay Bill detail Page
		When I Click First Time on Question Mark Icons
		Then I Can see the Suggestion Messsage
		When I Click Second Time on Question Mark Icons
		Then I Suggestion Messsage should hide
    
@OLD
Scenario: Check the mandatory field error message
		Given I am on SouthernWater Pay Bill page detail Page
		When I Click on Containue
		Then I Should see the mandatory field Error Message
		
@OLD
Scenario: Check the error message for Incorrect Data
		Given I am on SouthernWater Pay Bill with mandatory field error
		When I enter all details
		And I click on Continue
		Then I Should see the error Message for incorrect Data
		

@OLD
Scenario: Check the error message removed after entering mandatory field
		Given I am on SouthernWater Pay Bill with mandatory field error message
		When I enter all details in form
		Then I Should not see the error Message for mandatroy fields


		