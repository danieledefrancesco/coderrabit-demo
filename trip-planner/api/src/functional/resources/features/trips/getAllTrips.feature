Feature: Get all Trips

  Background:
    Given the existing drivers
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

  Scenario: When retrieving a trip by id, a 200 Ok response should be returned along with all the trips
    Given the existing trip 20000000-0000-0000-0000-000000000001 with the following stages
      | destinationWeatherCondition | distanceInKilometers | driverId                             | drivingProfile | fromLatitude        | fromLongitude       | fromName | numberOfPeople | startDateTime       | toLatitude          | toLongitude         | toName | vehicleId                            |
      | PARTLY_SUNNY                | 276.59190000000001   | 00000000-0000-0000-0000-000000000002 | CAR_PROFILE    | 45.473702000000003  | 9.17068500000000064 | Milan    | 2              | 2023-01-01T20:37:22 | 47.3737539999999981 | 8.53708699999999965 | Zurich | 10000000-0000-0000-0000-000000000003 |
      | PARTLY_SUNNY                | 588.517100000000028  | 00000000-0000-0000-0000-000000000001 | CAR_PROFILE    | 41.8782429999999977 | 12.5280900000000006 | Rome     | 2              | 2023-01-01T09:00:00 | 45.473702000000003  | 9.17068500000000064 | Milan  | 10000000-0000-0000-0000-000000000002 |
    When making a GET request to the "/trips" endpoint
    Then the status code should be 200
    And the response should consist of one trip with id 20000000-0000-0000-0000-000000000001 and the following stages
      | route.from | route.to | route.distance_in_kilometers | vehicle.id                           | vehicle.model | vehicle.autonomy | vehicle.plate | vehicle.emissions | vehicle.type | vehicle.max_people | vehicle.average_speed | vehicle.daily_rent_price | vehicle.stop_time_in_seconds | vehicle.fuel_type | vehicle.fuel_consumption | driver.id                            | driver.citizenship | driver.first_name | driver.last_name | driver.date_of_birth | driver.driving_license.code | driver.driving_license.expiry_date | price              | emissions | start_date_time     | destination_weather_condition | number_of_people | required_stops | arrival_date_time   | vehicle_not_suitable_for_destination_weather_condition |
      | Milan      | Zurich   | 276.5919                     | 10000000-0000-0000-0000-000000000003 | Panda         | 300.0            | AA000BB       | 30.0              | Car          | 4                  | 90.0                  | 100.0                    | 250                          | LPG               | 19.0                     | 00000000-0000-0000-0000-000000000002 | American           | John              | Smith            | 2001-01-01           | DEF456                      | 2025-12-12                         | 11.645974736842106 | 8297.757  | 2023-01-01T20:37:22 | PARTLY_SUNNY                  | 2                | 0              | 2023-01-02T00:02:14 | false                                                  |
      | Rome       | Milan    | 588.5171                     | 10000000-0000-0000-0000-000000000002 | SH125         | 300.0            | AA0000        | 10.0              | Scooter      | 2                  | 60.0                  | 50.0                     | 300                          | PETROL            | 25.0                     | 00000000-0000-0000-0000-000000000001 | Italian            | Mario             | Rossi            | 2006-01-01           | ABC123                      | 2023-12-12                         | 45.19811328        | 5885.171  | 2023-01-01T09:00:00 | PARTLY_SUNNY                  | 2                | 1              | 2023-01-01T20:37:22 | false                                                  |
