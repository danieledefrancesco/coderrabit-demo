Feature: Get all drivers

  Scenario: When retrieving all the drivers, a 200 Ok response should be returned along with the drivers stored in the database
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |
    When making a GET request to the "/drivers" endpoint
    Then the status code should be 200
    And the response should contain the following drivers
      | id                                   | first_name | last_name | date_of_birth | citizenship | driving_license.code | driving_license.expiry_date |
      | 00000000-0000-0000-0000-000000000001 | Mario      | Rossi     | 2000-01-01    | Italian     | ABC123               | 2023-12-12                  |
      | 00000000-0000-0000-0000-000000000002 | John       | Smith     | 2001-01-01    | American    | DEF456               | 2025-12-12                  |

