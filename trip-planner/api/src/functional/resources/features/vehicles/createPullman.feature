Feature: Create a pullman
  Background:
    Given the client is authenticated as a OPERATOR

  Scenario: When a pullman is correctly created its ID should be returned
    Given a create pullman request
      | model    | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate   | fuelType | emissions | fuelConsumption |
      | aPullman | 2         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
    When making a POST request to the "/vehicles/pullmans" endpoint
    Then the status code should be 201
    And the response should contain the new vehicle's id in the location header
    And a vehicle of type pullman should be present in the database with that id

  Scenario Outline: Scenario Outline:When providing wrong values a 400 error should be returned
    Given a create pullman request
      | model   | maxPeople   | dailyRentPrice   | averageSpeed   | autonomy   | stopTimeInSeconds   | plate   | fuelType   | emissions   | fuelConsumption   |
      | <model> | <maxPeople> | <dailyRentPrice> | <averageSpeed> | <autonomy> | <stopTimeInSeconds> | <plate> | <fuelType> | <emissions> | <fuelConsumption> |
    When making a POST request to the "/vehicles/pullmans" endpoint
    Then the status code should be 400
    Examples:
      | model    | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate   | fuelType | emissions | fuelConsumption |
      |          | 2         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 0         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | -1        | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | -1             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 0            | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | -1           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 35           | 0        | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 35           | -1       | 300               | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 35           | 100      | -1                | AA000BB | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 35           | 100      | 300               |         | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 35           | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aPullman | 2         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | -1        | 20              |
      | aPullman | 2         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | -1              |