Feature: Ticket Ordering

Scenario: One way direct flight ticket purchasing

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
    
    And System shows up the flights and classes and prices and times:
    
    |Airline Company		|Flight	Number	|Travel Class	|Depature	|Destination|Depature DateTime		|Arrival DateTime		|Available	|One Way Price	|
	|Cathay Pacific Airways |CP001			|FIRST			|Hong Kong	|Taiwan		|2014-01-01 14:30:00	|2014-01-01 17:30:00	|30			|2500.00		|
    
    When Client selects the flight and travel Class and number of ticket:
    
	|Flight		|Travel Class	|Number of Ticket	|
	|CP001		|FIRST			|1					|
        
    And System enquires the credit card information
    When Client inputs credit card information to pay the order:
    
	|Bank	|Credit Card Type	|Credit Card Number |
	|HSBC	|VISA				|1234-1234-1234-1234|

    And System display the order information to ask for the confirmation
    Then Client confirms the order:
    
    |Confirm	|
    |YES		|
    
    And System displays the order Number:
    
	|Order Number|
	|OR1		 |


