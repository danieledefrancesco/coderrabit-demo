Feature: Create Trip

  Background:
    Given the existing drivers
      | id                                   | firstName | lastName | dateOfBirth | citizenship | licenseCode | licenseExpiryDate |
      | 00000000-0000-0000-0000-000000000001 | Mario     | Rossi    | 2006-01-01  | Italian     | ABC123      | 2023-12-12        |
      | 00000000-0000-0000-0000-000000000002 | John      | Smith    | 2001-01-01  | American    | DEF456      | 2025-12-12        |
    And the existing bikes
      | id                                   | model | maxPeople | dailyRentPrice | averageSpeed | autonomy |
      | 10000000-0000-0000-0000-000000000001 | eBike | 1         | 10             | 20           | 100      |
    And the existing scooters
      | id                                   | model | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate  | fuelType | emissions | fuelConsumption |
      | 10000000-0000-0000-0000-000000000002 | SH125 | 2         | 50             | 60           | 300      | 300               | AA0000 | PETROL   | 10        | 25              |
    And the existing cars
      | id                                   | model | maxPeople | dailyRentPrice | averageSpeed | autonomy | stopTimeInSeconds | plate   | fuelType | emissions | fuelConsumption |
      | 10000000-0000-0000-0000-000000000003 | Panda | 4         | 100            | 90           | 300      | 250               | AA000BB | LPG      | 30        | 19              |
    And the PETROL cost is 1.92
    And the LPG cost is 0.8
    And today is 2023-01-01

  Scenario: When a trip is correctly created then its ID is returned.
    Given the need to plan a trip for 2 people starting the 2023-01-01 at 09:00 and consisting of the following stages
      | from  | to     | preferredPlanPolicy |
      | Rome  | Milan  | LEAST_POLLUTING     |
      | Milan | Zurich | FASTEST             |
    When making a POST request to the "/trips" endpoint
    Then the status code should be 201
    And the response should contain the new trip's id in the location header
    And a trip should be stored in the database with the following stages
      | destinationWeatherCondition | distanceInKilometers | driverId                             | drivingProfile | fromLatitude        | fromLongitude       | fromName | numberOfPeople | startDateTime       | toLatitude          | toLongitude         | toName | vehicleId                            |
      | PARTLY_SUNNY                | 276.59190000000001   | 00000000-0000-0000-0000-000000000002 | CAR_PROFILE    | 45.473702000000003  | 9.17068500000000064 | Milan    | 2              | 2023-01-01T20:37:22 | 47.3737539999999981 | 8.53708699999999965 | Zurich | 10000000-0000-0000-0000-000000000003 |
      | PARTLY_SUNNY                | 588.517100000000028  | 00000000-0000-0000-0000-000000000001 | CAR_PROFILE    | 41.8782429999999977 | 12.5280900000000006 | Rome     | 2              | 2023-01-01T09:00:00 | 45.473702000000003  | 9.17068500000000064 | Milan  | 10000000-0000-0000-0000-000000000002 |
