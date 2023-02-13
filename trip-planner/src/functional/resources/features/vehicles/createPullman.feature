Feature: Create a pullman

  Scenario: When a pullman is correctly created its ID should be returned
    Given a create pullman request
      | model | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate   | fuelType | emissions | fuelConsumption |
      | eBike | 2         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
    When making a POST request to the "/vehicles/pullmans" endpoint
    Then the status code should be 201
    And the response should contain the new vehicle's id in the location header
    And a pullman record should be present in the database with that id