Feature: Ticket Ordering

Scenario: One Way Direct flight ticket purchasing

    Given Airline companies are provided:
    
    |Airline				|
	|Cathay Pacific Airways	|
    
    And Flights are provided for the customers:
    
    |Airline Company		|Flight	Number	|Travel Class	|Depature	|Destination|Depature DateTime		|Arrival DateTime		|Available	|One Way Price	|
	|Cathay Pacific Airways |CP001			|FIRST			|Hong Kong	|Taiwan		|2014-01-01 14:30:00	|2014-01-01 17:30:00	|30			|2500.00		|
    
    And Client comes to the airline ticket ordering view
    When Client inputs the depature and destination and date:
    
	|Depature	|Destination|Date			|
	|Hong Kong	|Taiwan		|2014-01-01		|
    
    And System shows up the flights and classes and prices and times
    When Client selects the flight and travel Class and number of ticket:
    
	|Flight		|Travel Class	|Number of Ticket	|
	|CP001		|FIRST			|1					|
    
    And System displays the total prices
    When Customer confirms the order
    And System enquires the personal information and credit card number to complete the order
    When Client inputs personal information:
    
	|First Name	|Last Name	|Email Address		|Phone Number	|Passport Number	|Credit Card Number |
	|John		|Chan		|johnchan@abc.com 	|123456			|H999888777			|1234-1234-1234-1234|

    And System display the order for the confirmation
    Then Client confirms the order
    And System displays the order Number:
    
	|Order Number|
	|OR1		 |

Scenario: Test

