Feature: Delete a driver
  Background:
    Given the client is authenticated as a MANAGER

  Scenario: When deleting a driver, a 204 No Content response should be returned if the driver exists
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
    When making a DELETE request to the "/drivers/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 204
    And the driver with id 00000000-0000-0000-0000-000000000001 should no longer be present in the database

  Scenario:  When deleting a driver, a 404 Not Found response should be returned if the driver does not exist
    When making a DELETE request to the "/drivers/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 404
    And the error message should be "Driver with id 00000000-0000-0000-0000-000000000001 does not exist."
    And the error status should be 404