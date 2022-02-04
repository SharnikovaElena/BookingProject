Feature: Hotel search on www.booking.com
  Scenario: Hotel search by name
    Given The name of the hotel "Rixos Sungate"
    When User does search
    Then Hotel "Rixos Sungate" on the first page
    And Hotel rating "8.0"



