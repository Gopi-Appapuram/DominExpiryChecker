Feature: Check Domain URLs and Retrieve Expiry Date
	
  Scenario: Check the Domain URL Expiry Date
    Given I am on "https://www.websiteplanet.com/webtools/check-domain/" page
    When I get the URLs from the Excel
    Then I enter the URL in the search box
    When I get the expiry date of the domain exceeds todays date I set the cell color as "Red"
    When I get the expiry date of the domain expiry date is one month ahead I set the cell color as "Light_Orange"
    When I get the expiry date of the domain expiry date is six months ahead I set the cell color as "Yellow"
    When I get the expiry date of the domain expiry date is more than six months ahead I set the cell color as "Green"
    Then I close the browser
  
  