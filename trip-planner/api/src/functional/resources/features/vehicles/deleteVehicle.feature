Feature: Delete a vehicle
  Background:
    Given the client is authenticated as a MANAGER

  Scenario: When deleting a vehicle, a 204 No Content response should be returned, if it is stored in the database
    Given the existing bikes
      | id                                   | model | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | 00000000-0000-0000-0000-000000000001 | eBike | 1         | 10             | 20           | 100      |
    When making a DELETE request to the "/vehicles/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 204
    And the vehicle with id 00000000-0000-0000-0000-000000000001 should no longer exist

  Scenario:  When deleting a vehicle, a 404 Not Found response should be returned if the driver is not stored in the database
    When making a DELETE request to the "/vehicles/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 404
    And the error message should be "Vehicle with id 00000000-0000-0000-0000-000000000001 does not exist."
    And the error status should be 404
    