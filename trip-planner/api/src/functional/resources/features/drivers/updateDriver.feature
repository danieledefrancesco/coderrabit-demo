Feature: Update Driver

  Background:
    Given the client is authenticated as a OPERATOR

  Scenario Outline: When updating a driver, a 204 No Content response should be returned if it is stored in the database
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2000-01-01  | Italian     | ABC123      | 2023-12-12        |
    And the need to update a driver with the following values
      | firstName                | lastName                | dateOfBirth                | citizenship                | licenseCode                | licenseExpiryDate                |
      | <updateFirstNameRequest> | <updateLastNameRequest> | <updateDateOfBirthRequest> | <updateCitizenshipRequest> | <updateLicenseCodeRequest> | <updateLicenseExpiryDateRequest> |
    When making a PATCH request to the "/drivers/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 204
    And a driver with id 00000000-0000-0000-0000-000000000001 should be present in the database with the following properties
      | firstName   | lastName   | dateOfBirth   | citizenship   | licenseCode   | licenseExpiryDate   |
      | <firstName> | <lastName> | <dateOfBirth> | <citizenship> | <licenseCode> | <licenseExpiryDate> |
    Examples:
      | updateFirstNameRequest | firstName | updateLastNameRequest | lastName | updateDateOfBirthRequest | dateOfBirth | updateCitizenshipRequest | citizenship | updateLicenseCodeRequest | licenseCode | updateLicenseExpiryDateRequest | licenseExpiryDate |
      | John                   | John      | Smith                 | Smith    | 2000-01-02               | 2000-01-02  | American                 | American    | ABC234                   | ABC234      | 2024-12-12                     | 2024-12-12        |
      |                        | Mario     |                       | Rossi    |                          | 2000-01-01  |                          | Italian     |                          | ABC123      |                                | 2023-12-12        |

  Scenario:  When updating a driver a 403 Forbidden response should be returned if the driver is not stored in the client is not authenticated
    Given the client is not authenticated
    And the need to update a driver with the following values
      | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      |           |          |             |             |             |                   |
    When making a PATCH request to the "/drivers/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 403
    And the error message should be "Access Denied"
    And the error status should be 403

  Scenario:  When updating a driver a 404 Not Found response should be returned if the driver is not stored in the database
    Given the need to update a driver with the following values
      | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      |           |          |             |             |             |                   |
    When making a PATCH request to the "/drivers/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 404
    And the error message should be "Driver with id 00000000-0000-0000-0000-000000000001 does not exist."
    And the error status should be 404
    