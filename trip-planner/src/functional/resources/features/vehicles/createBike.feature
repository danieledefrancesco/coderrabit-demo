Feature: Create a bike

  Scenario: When a bike is correctly created its ID should be returned
    Given a create bike request
      | model | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | eBike | 2         | 10             | 35           | 100      |
    When making a POST request to the "/vehicles/bikes" endpoint
    Then the status code should be 201
    And the response should contain the new vehicle's id in the location header
    And a bike record should be present in the database with that id