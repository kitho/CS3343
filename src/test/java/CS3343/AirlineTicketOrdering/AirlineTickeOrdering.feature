Feature: Ticket Ordering

  Scenario Outline: One Way Direct flight ticket purchasing
    Given: Client comes to the airline ticket ordering view
    And: System asks for the trip information
    When: Client input the <Destination> and <Date>
    And: System asks to input the number of tickets
    And: Client input the <Number of Ticket> air ticket 
    And: System shows up the airlines and times
    And: Client selects the <Airline> and <Time>
    And: System provides the classes and prices
    And: Client selects the <Class>
    And: System enquires the personal information
    And: Client inputs <First Name> <Last Name> <Email Address> <Phone Number> <Passport Number> 
    And: System displays the customers information and price
    And: System asks the credit card information
    And: Client inputs the <Credit Card Info>
    Then: System display the success order with <Order Number> <Airline> <Class> <Price> <Destination> 
    <Date> <Time> <First Name> <Last Name> <Email Address> <Phone Number> <Passport Number>
    
    Examples:
    |Destination|Date|Number of Ticket|Airline|Time|Class|First Name|Last Name|Email Address|Phone Number|Passport Number|Credit Card Info|Order Number|

