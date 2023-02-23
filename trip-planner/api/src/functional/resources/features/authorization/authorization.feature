Feature: The client must provide a valid token

  Scenario: When providing an authorization header without a Bearer token a 401 Unauthorized response should be returned
    Given the authorization header is "abc123"
    When making a GET request to the "/fuel-types" endpoint
    Then the status code should be 401
    And the error message should be "The authorization header does not contain a valid bearer token."
    And the error status should be 401

  Scenario: When providing an invalid JWT token a 401 Unauthorized response should be returned
    Given the authorization header is "Bearer abc123"
    When making a GET request to the "/fuel-types" endpoint
    Then the error status should be 401
    And the error message should be "Invalid JWT."
    And the status code should be 401

  Scenario: When providing an unrecognized role a 401 Unauthorized response should be returned
    Given the client is authenticated as a UNRECOGNIZED_ROLE
    When making a GET request to the "/fuel-types" endpoint
    Then the error status should be 401
    And the error message should be "Unrecognized role."
    And the status code should be 401