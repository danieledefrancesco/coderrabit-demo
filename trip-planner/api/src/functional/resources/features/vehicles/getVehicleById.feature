Feature: Get a vehicle

  Scenario Outline: When retrieving a vehicle by id, a 200 Ok response should be returned along with the driver data, if it is stored in the database
    Given the existing bikes
      | id                                   | model       | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | 00000000-0000-0000-0000-000000000001 | eBike       | 1         | 10             | 20           | 100      |
      | 00000000-0000-0000-0000-000000000002 | anotherBike | 2         | 20             | 30           | 150      |
    When making a GET request to the "/vehicles/<id>" endpoint
    Then the status code should be 200
    And the response should contain the following bike
      | id   | model   | max_people  | daily_rent_price | average_speed  | autonomy   | stop_time_in_seconds | type |
      | <id> | <model> | <maxPeople> | <dailyRentPrice> | <averageSpeed> | <autonomy> | 28800                | Bike |
    Examples:
      | id                                   | model       | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | 00000000-0000-0000-0000-000000000001 | eBike       | 1         | 10.0           | 20.0         | 100.0    |
      | 00000000-0000-0000-0000-000000000002 | anotherBike | 2         | 20.0           | 30.0         | 150.0    |

  Scenario:  When retrieving a vehicle by id, a 404 Not Found response should be returned if the driver is not stored in the database
    When making a GET request to the "/vehicles/00000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 404
    And the error message should be "Vehicle with id 00000000-0000-0000-0000-000000000001 does not exist."
    And the error status should be 404
    