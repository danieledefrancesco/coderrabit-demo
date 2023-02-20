Feature: Create a driver

  Scenario Outline: When a driver is correctly created then its ID should be returned
    Given a create driver request
      | firstName   | lastName   | dateOfBirth   | citizenship   | licenseCode   | licenseExpiryDate   |
      | <firstName> | <lastName> | <dateOfBirth> | <citizenship> | <licenseCode> | <licenseExpiryDate> |
    When making a POST request to the "/drivers" endpoint
    Then the status code should be 201
    And the response should contain the new driver's id in the location header
    And a driver should be present in the database with that id and the following properties
      | firstName   | lastName   | dateOfBirth   | citizenship   | licenseCode   | licenseExpiryDate   |
      | <firstName> | <lastName> | <dateOfBirth> | <citizenship> | <licenseCode> | <licenseExpiryDate> |
    Examples:
      | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | John      | Smith    | 2001-01-01  | American    | null        | null              |

  Scenario Outline: When creating a driver, if the values provided are invalid then a 404 Bad Request response is returned
    Given a create driver request
      | firstName   | lastName   | dateOfBirth   | citizenship   | licenseCode   | licenseExpiryDate   |
      | <firstName> | <lastName> | <dateOfBirth> | <citizenship> | <licenseCode> | <licenseExpiryDate> |
    When making a POST request to the "/drivers" endpoint
    Then the status code should be 400
    And the error status should be 400
    Examples:
      | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      |           | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | Mario     |          | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
      | Mario     | Rossi    |             | Italian     | ABC123      | 2023-12-12        |
      | Mario     | Rossi    | 2000-01-01  |             | ABC123      | 2023-12-12        |


  Scenario: When creating a driver, if a license with the same code already exists then a 409 conflict error is returned
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | John      | Smith    | 2001-01-01  | American    | ABC123      | 2025-12-12        |
    And a create driver request
      | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
    When making a POST request to the "/drivers" endpoint
    Then the status code should be 409
    And the error message should be "DrivingLicense with id ABC123 already exists."
    And the error status should be 409
