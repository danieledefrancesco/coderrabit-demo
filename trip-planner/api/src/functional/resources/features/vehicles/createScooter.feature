Feature: Create a scooter
  Background:
    Given the client is authenticated as a OPERATOR

  Scenario: When a scooter is correctly created its ID should be returned
    Given a create scooter request
      | model    | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate  | fuelType | emissions | fuelConsumption |
      | aScooter | 2         | 10             | 35           | 100      | 300               | AA0000 | PETROL   | 300       | 20              |
    When making a POST request to the "/vehicles/scooters" endpoint
    Then the status code should be 201
    And the response should contain the new vehicle's id in the location header
    And a vehicle of type scooter should be present in the database with that id

  Scenario Outline: When providing wrong values a 400 error should be returned
    Given a create scooter request
      | model   | maxPeople   | dailyRentPrice   | averageSpeed   | autonomy   | stopTimeInSeconds   | plate   | fuelType   | emissions   | fuelConsumption   |
      | <model> | <maxPeople> | <dailyRentPrice> | <averageSpeed> | <autonomy> | <stopTimeInSeconds> | <plate> | <fuelType> | <emissions> | <fuelConsumption> |
    When making a POST request to the "/vehicles/scooters" endpoint
    Then the status code should be 400
    Examples:
      | model    | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate   | fuelType | emissions | fuelConsumption |
      |          | 2         | 10             | 35           | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 0         | 10             | 35           | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | -1        | 10             | 35           | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | -1             | 35           | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 0            | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | -1           | 100      | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 35           | 0        | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 35           | -1       | 300               | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 35           | 100      | -1                | AA1234  | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 35           | 100      | 300               |         | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 35           | 100      | 300               | AA000BB | PETROL   | 300       | 20              |
      | aScooter | 2         | 10             | 35           | 100      | 300               | AA1234  | PETROL   | -1        | 20              |
      | aScooter | 2         | 10             | 35           | 100      | 300               | AA1234  | PETROL   | 300       | -1              |

  Scenario: When creating a scooter, a 403 Forbidden response should be returned if the client is not authenticated
    Given the client is not authenticated
    When making a POST request to the "/vehicles/scooters" endpoint
    Then the status code should be 403
    And the error message should be "Access Denied"
    And the error status should be 403