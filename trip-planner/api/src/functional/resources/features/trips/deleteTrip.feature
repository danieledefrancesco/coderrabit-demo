Feature: Delete a trip

  Background:
    Given the client is authenticated as a MANAGER
    And the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2006-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |
    And the existing scooters
      | id                                   | model | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate  | fuelType | emissions | fuelConsumption |
      | 10000000-0000-0000-0000-000000000002 | SH125 | 2         | 50             | 60           | 300      | 300               | AA0000 | PETROL   | 10        | 25              |
    And the existing cars
      | id                                   | model | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate   | fuelType | emissions | fuelConsumption |
      | 10000000-0000-0000-0000-000000000003 | Panda | 4         | 100            | 90           | 300      | 250               | AA000BB | LPG      | 30        | 19              |
    And the PETROL cost is 1.92
    And the LPG cost is 0.8

  Scenario: When deleting a trip by id, a 204 No Content response should be returned, if it is stored in the database
    Given the existing trip 20000000-0000-0000-0000-000000000001 with the following stages
      | destinationWeatherCondition | distanceInKilometers | driverId                             | drivingProfile | fromLatitude        | fromLongitude       | fromName | numberOfPeople | startDateTime       | toLatitude          | toLongitude         | toName | vehicleId                            |
      | PARTLY_SUNNY                | 276.59190000000001   | 00000000-0000-0000-0000-000000000002 | CAR_PROFILE    | 45.473702000000003  | 9.17068500000000064 | Milan    | 2              | 2023-01-01T20:37:22 | 47.3737539999999981 | 8.53708699999999965 | Zurich | 10000000-0000-0000-0000-000000000003 |
      | PARTLY_SUNNY                | 588.517100000000028  | 00000000-0000-0000-0000-000000000001 | CAR_PROFILE    | 41.8782429999999977 | 12.5280900000000006 | Rome     | 2              | 2023-01-01T09:00:00 | 45.473702000000003  | 9.17068500000000064 | Milan  | 10000000-0000-0000-0000-000000000002 |
    When making a DELETE request to the "/trips/20000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 204
    And the trip with id 20000000-0000-0000-0000-000000000001 should no longer be present in the database

  Scenario: when deleting a trip by id, a 403 Forbidden response should be returned, if the client is not authenticated
    Given the client is not authenticated
    When making a DELETE request to the "/trips/20000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 403
    And the error message should be "Access Denied"
    And the error status should be 403

  Scenario: when deleting a trip by id, a 403 Forbidden response should be returned, if the client is authenticated as an OPERATOR
    Given the client is authenticated as a OPERATOR
    When making a DELETE request to the "/trips/20000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 403
    And the error message should be "Access Denied"
    And the error status should be 403

  Scenario: When deleting a trip by id, a 404 Not Found response should be returned, if the trip is not stored in the database
    When making a DELETE request to the "/trips/20000000-0000-0000-0000-000000000001" endpoint
    Then the status code should be 404
    And the error message should be "Trip with id 20000000-0000-0000-0000-000000000001 does not exist."
    And the error status should be 404