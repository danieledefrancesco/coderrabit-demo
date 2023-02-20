Feature: Update fuel type cost

  Scenario Outline: When updating the <fuelType> cost, a 204 No Content response should be returned and the <fuelType> cost should be updated accordingly to the request.
    Given the need to update the cost to <fuelTypeCost>
    When making a PATCH request to the "/fuel-types/<fuelType>/cost" endpoint
    Then the status code should be 204
    And the <fuelType> cost should be <fuelTypeCost>
    Examples:
      | fuelType | fuelTypeCost |
      | PETROL   | 1.8          |
      | DIESEL   | 1.7          |
      | LPG      | 0.8          |

  Scenario Outline: When updating the fuel type cost, 400 Bad Request response should be returned when the provided cost is lower than or equal to 0
    Given the need to update the cost to <fuelTypeCost>
    When making a PATCH request to the "/fuel-types/PETROL/cost" endpoint
    Then the status code should be 400
    Examples:
      | fuelTypeCost |
      | 0            |
      | -1           |

  Scenario: When updating the fuel type cost, a 404 Not Found response should be returned when the provided fuel type does not exist
    Given the need to update the cost to 2
    When making a PATCH request to the "/fuel-types/HYDROGEN/cost" endpoint
    Then the status code should be 404
    And the error message should be "Fuel type with id HYDROGEN does not exist."
    And the error status should be 404