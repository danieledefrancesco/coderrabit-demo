Feature: Get all fuel types

  Scenario: When retrieving all the fuel types a 200 Ok response should be returned along with the fuel types data
    Given the LPG cost is 0.8
    And the PETROL cost is 1.8
    And the DIESEL cost is 1.7
    When making a GET request to the "/fuel-types" endpoint
    Then the status code should be 200
    And the response should contain the following fuel types
      | name   | cost |
      | LPG    | 0.8  |
      | PETROL | 1.8  |
      | DIESEL | 1.7  |