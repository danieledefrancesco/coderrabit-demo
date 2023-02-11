Feature: Get all vehicles

  Scenario: invoking the GET /vehicles endpoint should return the vehicles stored in the database
    Given the existing bikes
      | id                                   | model       | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | 00000000-0000-0000-0000-000000000001 | eBike       | 1         | 10             | 20           | 100      |
      | 00000000-0000-0000-0000-000000000002 | anotherBike | 2         | 20             | 30           | 150      |
    When making a GET request to the "/vehicles" endpoint
    Then the status code should be 200
    And the response should have 2 vehicles