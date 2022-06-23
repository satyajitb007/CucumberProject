Feature: Login into the Application 

Scenario: Add few products and check out 
	Given Intialize the browser with "chrome" 
	And Navigate to "http://automationpractice.com/" site 

@SmokeTest	
Scenario Outline: 
	When User searches the <product_name> in global search 
	And User clicks on the product <product_name> 
	And User selects the require <size> & <quantity> for the <product_name>
	When User adds the product into the cart by clicking in Add to cart button 
	Then Verify that the <product_name> is successfully added in cart 
	
	Examples: 
		|product_name                 |size    |quantity|
		|Faded Short Sleeve T-shirts  |M       |2|
		|Blouse                       |S       |1|  

		  
Scenario:
		Then User navigates to checkout page
		Then Close the browser	
		
		
