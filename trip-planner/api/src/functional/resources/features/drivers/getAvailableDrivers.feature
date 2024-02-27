Feature: Get available drivers

  Scenario: When retrieving available drivers, a 200 Ok response should be returned along with the available drivers stored in the database
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |
    And the following drivers reservations
      | driverId                             | reservedFrom        | reservedTo          |
      | 00000000-0000-0000-0000-000000000001 | 2024-01-01T09:00:00 | 2024-01-01T09:30:00 |
    When making a GET request to the "/drivers/available?from=2024-01-01T00:00:00&to=2024-01-02T00:00:00" endpoint
    Then the status code should be 200
    And the response should contain the following drivers
      | id                                   | first_name | last_name | date_of_birth | citizenship | driving_license.code | driving_license.expiry_date |
      | 00000000-0000-0000-0000-000000000002 | John       | Smith     | 2001-01-01    | American    | DEF456               | 2025-12-12                  |



  Scenario: When retrieving available drivers, a 200 Ok response should be returned along with the available drivers stored in the database
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |
    And the following drivers reservations
      | driverId                             | reservedFrom        | reservedTo          |
      | 00000000-0000-0000-0000-000000000001 | 2024-01-01T09:00:00 | 2024-01-01T09:30:00 |
    When making a GET request to the "/drivers/available?from=2024-01-02T00:00:00&to=2024-01-03T00:00:00" endpoint
    Then the status code should be 200
    And the response should contain the following drivers
      | id                                   | first_name | last_name | date_of_birth | citizenship | driving_license.code | driving_license.expiry_date |
      | 00000000-0000-0000-0000-000000000001 | Mario      | Rossi     | 2000-01-01    | Italian     | ABC123               | 2023-12-12                  |
      | 00000000-0000-0000-0000-000000000002 | John       | Smith     | 2001-01-01    | American    | DEF456               | 2025-12-12                  |

