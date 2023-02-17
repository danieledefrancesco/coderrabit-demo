Feature: Get Driver by Id

  Scenario Outline: When retrieving a drive by id, a 200 Ok response should be returned along with the driver data, if it is stored in the database
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |
    When making a GET request to the "/drivers/<id>" endpoint
    Then the status code should be 200
    And the response should contain the following driver
      | id   | first_name  | last_name  | date_of_birth | citizenship   | driving_license.code | driving_license.expiry_date |
      | <id> | <firstName> | <lastName> | <dateOfBirth> | <citizenship> | <licenseCode>        | <licenseExpiryDate>         |
    Examples:
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |

  Scenario:  When retrieving a drive by id, a 404 Not Found response should be returned if the driver is not stored in the database
    When making a GET request to the "/drivers/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 404
    And the error message should be "Driver with id 00000000-0000-0000-0000-000000000001 does not exist."
    And the error status should be 404
    