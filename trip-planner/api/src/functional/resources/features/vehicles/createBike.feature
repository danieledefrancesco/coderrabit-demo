Feature: Create a bike

  Scenario: When a bike is correctly created its ID should be returned
    Given a create bike request
      | model | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | eBike | 2         | 10             | 35           | 100      |
    When making a POST request to the "/vehicles/bikes" endpoint
    Then the status code should be 201
    And the response should contain the new vehicle's id in the location header
    And a vehicle of type bike should be present in the database with that id

  Scenario Outline: When providing wrong values a 400 error should be returned
    Given a create bike request
      | model   | maxPeople   | dailyRentPrice   | averageSpeed   | autonomy   |
      | <model> | <maxPeople> | <dailyRentPrice> | <averageSpeed> | <autonomy> |
    When making a POST request to the "/vehicles/bikes" endpoint
    Then the status code should be 400
    Examples:
      | model | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      |       | 1         | 10             | 35           | 100      |
      | eBike | 0         | 10             | 35           | 100      |
      | eBike | -1        | 10             | 35           | 100      |
      | eBike | 1         | -1             | 35           | 100      |
      | eBike | 1         | 10             | 0            | 100      |
      | eBike | 1         | 10             | -1           | 100      |
      | eBike | 1         | 10             | 35           | 0        |
      | eBike | 1         | 10             | 35           | -1       |

